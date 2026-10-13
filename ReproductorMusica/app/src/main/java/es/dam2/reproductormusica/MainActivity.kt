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
                binding.seekBarReproduccion.progress = mediaPlayer!!.currentPosition / 1000
                // Esto hace que se ejecute cada segundo, y no continuamente. Así, se ahorran
                // recursos. Si se pusiera cada 5000 ms, la barra de progreso se actualizaría cada
                // 5 segundos
                handler.postDelayed(this, 1000)
            }

        }
    }

    private var indiceReproduccionActual: Int = 0

    // Una lista mutable donde vamos a almacenar una lista con las canciones disponibles
    private lateinit var playlistCanciones: MutableList<Cancion>


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
        binding.seekBarReproduccion.setOnSeekBarChangeListener(
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

        // El tercer parámetro, que le dice la Canción, se almacena como un Int (entero)
        playlistCanciones = mutableListOf<Cancion>(
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
            if (indiceReproduccionActual < playlistCanciones.size - 1) {
                indiceReproduccionActual++
                cargarCancion()
            }
        }

        binding.btnAnterior.setOnClickListener {
            if (indiceReproduccionActual > 0) {
                indiceReproduccionActual--
                cargarCancion()
            }
        }

    }

    private fun cargarCancion() {
        // Cancela hilos en ejecución
        handler.removeCallbacks(runnable)
        // Elimina canciones que estuvieran asociadas al reproductor
        mediaPlayer?.release()
        // CUIDADO1: El método create() es estático, así que tiene que llamarse con
        // la clase, no con la instancia
        // CUIDADO2: Al crear la instancia de MediaPlayer, debemos almacenar esa instancia en la
        // variable mediaPlayer, para que esta no siga siendo nula y no dé error al lanzar la app
        mediaPlayer =  MediaPlayer.create(this, playlistCanciones[indiceReproduccionActual].pista)
        binding.txtTitulo.text = playlistCanciones[indiceReproduccionActual].titulo
        binding.txtArtista.text = playlistCanciones[indiceReproduccionActual].autor
        binding.txtTiempoTotal.text = formatearTiempo(mediaPlayer!!.duration/1000)

        binding.seekBarReproduccion.max = mediaPlayer!!.duration / 1000
        binding.seekBarReproduccion.progress = 0
        binding.btnReproducir.setImageResource(R.drawable.ic_reproducir)

        // Actualizamos los botones cada vez que cargamos una canción
        actualizarEstadoBotones()
    }

    private fun actualizarEstadoBotones() {
        // Botón Anterior
        if (indiceReproduccionActual == 0) {
            binding.btnAnterior.isClickable = false
            binding.btnAnterior.alpha = 0.5F
        } else {
            binding.btnAnterior.isClickable = true
            binding.btnAnterior.alpha = 1F
        }

        // Botón Siguiente
        if (indiceReproduccionActual == playlistCanciones.size - 1) {
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