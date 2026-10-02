package es.dam2.ejercicio05_tresenraya

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.dam2.ejercicio05_tresenraya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // View binding es una característica que nos facilita programar cuando interactuamos con vistas.
    // Nos permite no tener que referenciar a cada una de las vistas por separado (findViewById…)
    // Ver pdf de ViewBinding para mayor detalle
    private lateinit var binding: ActivityMainBinding
    private lateinit var btnReiniciar: Button

    // turno 1: X; turno 2: O
    private var turno: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Esto es para que no tengamos que declarar e inicializar una variable para cada una de las
        // ImageView. Para ello, previamente hemos tenido que abrir el build.gradle.kts que hay dentro
        // de la carpeta app y haber pegado buildFeatures { viewBinding = true }
        // Después, vamos a la barra superior del IDE y seleccionamos Sync Project with Gradle Files
        // (el icono a la izquiera de la lupa, arriba a la derecha). Y tras eso, podemos meter este código
        // Para que funcione, es importante comentar el setContentView(R.layout.activity_main) que
        // nos da hecho el IDE con la clase, de lo contrario, se sobreescribiría esto que hacemos aquí por
        // el setContentView(R.layout.activity_main) y no funcionarían los clicks en las ImageView
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        var celdas = mutableListOf<ImageView>(
            binding.img00,
            binding.img01,
            binding.img02,
            binding.img10,
            binding.img11,
            binding.img12,
            binding.img20,
            binding.img21,
            binding.img22
        )

        celdas.forEach { img ->
            img.setOnClickListener {
                if (turno == 1) {
                    img.setImageResource(R.drawable.ic_equis)
                    ++turno
                } else {
                    img.setImageResource(R.drawable.ic_circulo)
                    --turno
                }
                img.isClickable = false
            }
        }

        btnReiniciar = findViewById<Button>(R.id.btnReiniciar)

        btnReiniciar.setOnClickListener {
            celdas.forEach { img -> img.setImageResource(R.drawable.ic_celda) }
            turno = 1
        }

    }
}