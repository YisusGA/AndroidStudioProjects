package es.dam2.prueba_jesus_gomez_alonso

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.dam2.prueba_jesus_gomez_alonso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.txtCharlas.text = HtmlCompat.fromHtml(
            """
            <p><strong>Charla de no sé qué</strong></p>
            <p>Bla bla bla</p>
            <p>Bla bla bla</p>
            """.trimIndent(),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.txtExperimentos.text = HtmlCompat.fromHtml(
            """
            <p><strong>Experimento de no sé cuál</strong></p>
            <p>Bla bla bla</p>
            <p>Bla bla bla</p>
            """.trimIndent(),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.txtActividades.text = HtmlCompat.fromHtml(
            """
            <strong>Más de</strong> 20 actividades
            """.trimIndent(),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.btnInscribirme.setOnClickListener {
            val intent = Intent(this@MainActivity, FormInscripcion::class.java)
            startActivity(intent)

        }
    }

    override fun onRestart() {
        super.onRestart()
        binding.txtExplora.text = "¡Gracias por participar!"
    }
}