package es.dam2.prueba_jesus_gomez_alonso

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import es.dam2.prueba_jesus_gomez_alonso.databinding.ActivityFormInscripcionBinding
import es.dam2.prueba_jesus_gomez_alonso.databinding.ActivityMainBinding

class FormInscripcion : AppCompatActivity() {
    private lateinit var binding: ActivityFormInscripcionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFormInscripcionBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_form_inscripcion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnConfirmar.setOnClickListener {
            val intent = Intent(this@FormInscripcion, Confirmacion::class.java)
            intent.putExtra("nombre", binding.textInputEditTextNombre.text.toString())
            intent.putExtra("taller", binding.textInputEditTextTaller.text.toString())
            startActivity(intent)
            finish()
        }
    }
}