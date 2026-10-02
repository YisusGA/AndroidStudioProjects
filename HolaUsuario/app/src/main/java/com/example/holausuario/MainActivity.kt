package com.example.holausuario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    // lateinit porque le vamos a dar luego el valor. var porque su valor es variable. nombre es el
    // nombre que le doy a la variable. Y lo que hay a la derecha de los 2 puntos es el tipado de la
    // variable. Ver cheatsheet de Kotlin para más info
    private lateinit var nombre: TextInputEditText
    private lateinit var boton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Esto que hay dentro del ViewCompact es lo que respeta el notch de la pantalla para la
        // cámara frontal
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nombre = findViewById<TextInputEditText>(R.id.textInputNombre)
        boton = findViewById<Button>(R.id.btnAceptar)

        boton.setOnClickListener {
            // Creamos la variable para almacenar el Intent. No hace falta declarar el tipo de
            // variable porque Kotlin lo infiere
            // Origen this@MainActivity, destino SaludoActivity::class.java
            val intent = Intent(this@MainActivity, SaludoActivity::class.java)
            // Aquí añadimos más info al intent. El primer parámetro es el nombre del parámetro y el
            // segundo parámetro es el valor, que lo coge de lo que se escriba en el
            // TextInputEditText que hemos creado en la activity_main.xml
            intent.putExtra("nombre", nombre.text.toString())
            // Aquí lanzamos la activity con el intent.
            startActivity(intent)
        }
    }
}