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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    // Un Handler permite programar tareas para que se ejecuten en un hilo.
    // Looper.getMainLooper() indica que se utilizará el hilo principal de
    // Android, que es donde se actualizan los controles de la pantalla.

    // CIUDADO: Hay que importar el Handler de android.os.Handler, no el de java.util.logging.Handler
    private val handler = Handler(Looper.getMainLooper())

    private val runnable = object : Runnable {
        override fun run() {

            if (mediaPlayer!!.isPlaying) {
                binding.seekBarDuracion.progress = mediaPlayer!!.currentPosition / 1000
                handler.postDelayed(this, 1000)
            }

        }
    }

    private var enCurso: Int = 0

    private lateinit var datos: MutableList<Cancion>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Reflejar el movimiento del usuario
        binding.seekBarDuracion.setOnClickListener {
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    binding.txtTiempoActual.text =
                        "$progress"
//                    binding.txtTiempoTotal.text = "${binding.seekBarDuracion.max}"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                // Lo que sucede cuando el usuario deja de tocar el seekBar
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    mediaPlayer?.seekTo(seekBar!!.progress*1000)
                }
            }

        }

        datos = mutableListOf<Cancion>(
            Cancion("Titulo1", "Autor1", R.raw.tension),
            Cancion("Titulo1", "Autor1", R.raw.suspense),
            Cancion("Titulo1", "Autor1", R.raw.thinking_time)
        )

        cargarCancion()

    }

    private fun cargarCancion() {
        handler.removeCallbacks(runnable)
        mediaPlayer?.release()
        // CUIDADO: El método create() es estático, así que tiene que llamarse con
        //  la clase, no con la instancia
        MediaPlayer.create(this, datos[enCurso].pista)
        binding.txtTitulo.text = datos[enCurso].titulo
        binding.txtArtista.text = datos[enCurso].autor
        binding.txtArtista.text = datos[enCurso].autor
        binding.txtTiempoTotal.text = (mediaPlayer!!.duration/1000).toString()

        binding.seekBarDuracion.max = mediaPlayer!!.duration/1000
        binding.seekBarDuracion.progress = 0
    }
}