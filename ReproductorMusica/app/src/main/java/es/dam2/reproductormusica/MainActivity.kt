package es.dam2.reproductormusica

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.dam2.reproductormusica.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    // Un Handler permite programar tareas para que se ejecuten en un hilo.
    // Looper.getMainLooper() indica que se utilizará el hilo principal de
    // Android, que es donde se actualizan los controles de la pantalla.

    // CIUDADO: Hay que importar el Handler de android.os.Handler, no el de java.util.logging.Handler
    private val handler = Handler(Looper.getMainLooper())

    // Aquí estamos creando una clase anónima que implementa la interfaz funcional Runnable,
    // obligándonos por lo tanto a implementar su método abstracto
    private val runnable = object : Runnable {
        override fun run() {

            // Si el reproductor se está ejecutando, la barra de reproducción se pone a la posición
            // actual del reproductor. Hay que dividir entre 1000 porque la clase MediaPlayer
            // trabaja con tiempos en milisegundos, y por lo tanto el método currentPOsition de
            // MediaPlayer (una abreviatura de getCurrentPosition()) nos devuelve el tiempo en esa
            // unidad
            if (mediaPlayer?.isPlaying == true) {
                binding.seekBarDuracion.progress = mediaPlayer!!.currentPosition / 1000
                handler.postDelayed(this, 1000)
            }

        }
    }

    private var enCurso: Int = 0

    // Una lista mutable donde vamos a almacenar una lista con las canciones disponibles
    private lateinit var datos: MutableList<Cancion>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//      setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Reflejar el movimiento del usuario
        binding.seekBarDuracion.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?, progress: Int, fromUser: Boolean
                ) {
                    binding.txtTiempoActual.text = formatearTiempo(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                // Lo que sucede cuando el usuario deja de tocar el seekBar
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    mediaPlayer?.seekTo(seekBar!!.progress * 1000)
                }
            }

        )

        datos = mutableListOf<Cancion>(
            Cancion("Tensión", "Tensor", R.raw.tension),
            Cancion("Suspense", "Suspenso", R.raw.suspense),
            Cancion("Miedo", "Miedoso", R.raw.frighten),
            Cancion("Pensativo", "Pensador", R.raw.thinking_time)
        )

        cargarCancion()

        binding.btnReproducir.setOnClickListener {

            if (mediaPlayer?.isPlaying == true) {
                handler.removeCallbacks(runnable)
                binding.btnReproducir.setImageResource(R.drawable.ic_reproducir)
                mediaPlayer?.pause()
            } else {
                handler.removeCallbacks(runnable)
                handler.post(runnable)
                binding.btnReproducir.setImageResource(R.drawable.ic_pausar)
                mediaPlayer?.start()
            }
        }
        binding.btnSiguiente.setOnClickListener {
            if (enCurso < datos.size - 1) {
                enCurso++
                cargarCancion()
            }
        }

        binding.btnAnterior.setOnClickListener {
            if (enCurso > 0) {
                enCurso--
                cargarCancion()
            }
        }

    }

    private fun cargarCancion() {
        handler.removeCallbacks(runnable)
        mediaPlayer?.release()
        // CUIDADO1: El método create() es estático, así que tiene que llamarse con
        // la clase, no con la instancia
        // CUIDADO2: Al crear la instancia de MediaPlayer, debemos almacenar esa instancia en la
        // variable mediaPlayer, para que esta no siga siendo nula y no dé error al lanzar la app
        mediaPlayer =  MediaPlayer.create(this, datos[enCurso].pista)
        binding.txtTitulo.text = datos[enCurso].titulo
        binding.txtArtista.text = datos[enCurso].autor
        binding.txtTiempoTotal.text = formatearTiempo(mediaPlayer!!.duration/1000)

        binding.seekBarDuracion.max = mediaPlayer!!.duration / 1000
        binding.seekBarDuracion.progress = 0
        binding.btnReproducir.setImageResource(R.drawable.ic_reproducir)

        // Actualizamos los botones cada vez que cargamos una canción
        actualizarEstadoBotones()
    }

    private fun actualizarEstadoBotones() {
        // Botón Anterior
        if (enCurso == 0) {
            binding.btnAnterior.isClickable = false
            binding.btnAnterior.alpha = 0.5F
        } else {
            binding.btnAnterior.isClickable = true
            binding.btnAnterior.alpha = 1F
        }

        // Botón Siguiente
        if (enCurso == datos.size - 1) {
            binding.btnSiguiente.isClickable = false
            binding.btnSiguiente.alpha = 0.5F
        } else {
            binding.btnSiguiente.isClickable = true
            binding.btnSiguiente.alpha = 1F
        }
    }

    fun formatearTiempo(tiempo: Int): String {
        val minutos = tiempo / 60
        val segundos = tiempo % 60

        return String.format(Locale.getDefault(), "%02d:%02d", minutos, segundos)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}