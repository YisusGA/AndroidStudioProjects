package es.dam2.prueba_jesus_gomez_alonso

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.dam2.prueba_jesus_gomez_alonso.databinding.ActivityConfirmacionBinding
import es.dam2.prueba_jesus_gomez_alonso.databinding.ActivityFormInscripcionBinding

class Confirmacion : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityConfirmacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_confirmacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spannedText = HtmlCompat.fromHtml(
            """
            <p><strong>Inscripción realizada</strong></p>
            <p>Gracias ${intent.getStringExtra("nombre")}</p>
            <p>Tu plaza para el taller ${intent.getStringExtra("taller")} está solicitada</p>
            """.trimIndent(),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.txtResult.text = spannedText

        binding.btnFinalizar.setOnClickListener {
            finish()
        }
    }
}