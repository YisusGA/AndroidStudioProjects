package es.dam2.controles_entrada_ejercicio02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.dam2.controles_entrada_ejercicio02.databinding.ActivityMainBinding

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

        binding.btnActualizar.setOnClickListener {
            if (!binding.txtInputNombre.text.isNullOrBlank()) {
                binding.txtNombreResult.text = binding.txtInputNombre.text
                binding.txtFraseResult.text = if (!binding.txtInputFrase.text.isNullOrBlank()) binding.txtInputFrase.text else "-"
            }
        }

        binding.btnRestablecer.setOnClickListener {

        }

    }
}