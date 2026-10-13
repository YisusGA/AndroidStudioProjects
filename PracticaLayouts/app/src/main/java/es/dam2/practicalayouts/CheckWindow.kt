package es.dam2.practicalayouts

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CheckWindow : AppCompatActivity() {

    private lateinit var txtImagenSeleccionada: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_check_window)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtImagenSeleccionada = findViewById<TextView>(R.id.txtImgSeleccionada)

        val imagenSeleccionada = intent.getIntExtra("numImagen", 1)

        // En Kotlin no existe el operador ternario condicion ? true : false
        val nombreImagen = if (imagenSeleccionada == 1) "Perro y gato siamés" else "Perro y gato negro"

        // Con esto, evitamos harcodear el texto
        txtImagenSeleccionada.text = getString(R.string.selectedImageMessage, imagenSeleccionada, nombreImagen)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this@CheckWindow, "Os abandono!!!", Toast.LENGTH_SHORT).show()
    }
}