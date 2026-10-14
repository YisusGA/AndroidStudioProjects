package es.dam2.ejercicio07_fichacontacto

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var txtEditNombre: TextInputEditText
    private lateinit var txtEditTelefono: TextInputEditText
    private lateinit var txtEditCorreo: TextInputEditText
    private lateinit var btnGuardar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        txtEditNombre = findViewById<TextInputEditText>(R.id.txtEditNombre)
        txtEditTelefono = findViewById<TextInputEditText>(R.id.txtEditTelefono)
        txtEditCorreo = findViewById<TextInputEditText>(R.id.txtEditCorreo)
        btnGuardar = findViewById<Button>(R.id.btnGuardar)

        txtEditNombre.setOnClickListener {
            txtEditNombre.setTextColor(Color.BLACK)
            txtEditNombre.setText("")
            txtEditTelefono.setTextColor(Color.BLACK)
            txtEditTelefono.setText("")
            txtEditCorreo.setTextColor(Color.BLACK)
            txtEditCorreo.setText("")
            btnGuardar.isClickable = true
            btnGuardar.alpha = 1f
        }

        txtEditTelefono.setOnClickListener {
            txtEditNombre.setTextColor(Color.BLACK)
            txtEditNombre.setText("")
            txtEditTelefono.setTextColor(Color.BLACK)
            txtEditTelefono.setText("")
            txtEditCorreo.setTextColor(Color.BLACK)
            txtEditCorreo.setText("")
            btnGuardar.isClickable = true
            btnGuardar.alpha = 1f
        }

        txtEditCorreo.setOnClickListener {
            txtEditNombre.setTextColor(Color.BLACK)
            txtEditNombre.setText("")
            txtEditTelefono.setTextColor(Color.BLACK)
            txtEditTelefono.setText("")
            txtEditCorreo.setTextColor(Color.BLACK)
            txtEditCorreo.setText("")
            btnGuardar.isClickable = true
            btnGuardar.alpha = 1f
        }

        btnGuardar.setOnClickListener {
            val nombre = txtEditNombre.text
            val telefono = txtEditTelefono.text
            val correo = txtEditCorreo.text
            if (!nombre.isNullOrBlank() && !telefono.isNullOrBlank() && !correo.isNullOrBlank()) {
                //Toast.makeText(Instancia de la clase, texto, duración del mensaje)
                Toast.makeText(this@MainActivity, "Guardado en el baúl de los recuerdos", Toast.LENGTH_SHORT).show()
                txtEditNombre.setText("")
                txtEditTelefono.setText("")
                txtEditCorreo.setText("")
            }

            if (nombre.isNullOrBlank()) {
                // No se puede hacer txtEditNombre.text = "Cadena" porque txtEditNombre.text no
                // devuelve un String, sino un Editable
                txtEditNombre.setTextColor(Color.RED)
                txtEditNombre.setText("Debes introducir un nombre")
                btnGuardar.isClickable = false
                btnGuardar.alpha = 0.2f
            }

            if (telefono.isNullOrBlank()) {
                txtEditTelefono.setTextColor(Color.RED)
                txtEditTelefono.setText("Debes introducir un teléfono")
                btnGuardar.isClickable = false
                btnGuardar.alpha = 0.2f
            }

            if (correo.isNullOrBlank()) {
                txtEditCorreo.setTextColor(Color.RED)
                txtEditCorreo.setText("Debes introducir un correo")
                btnGuardar.isClickable = false
                btnGuardar.alpha = 0.2f
            }
        }
    }
}