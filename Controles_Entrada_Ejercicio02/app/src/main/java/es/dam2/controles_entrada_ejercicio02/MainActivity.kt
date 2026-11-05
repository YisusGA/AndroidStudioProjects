package es.dam2.controles_entrada_ejercicio02

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.dam2.controles_entrada_ejercicio02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var esFavorita = false
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

        var colorStateListHint = binding.txtInputNombre.hintTextColors

        binding.txtInputNombre.setOnClickListener {
            binding.txtInputNombre.setHintTextColor(colorStateListHint)
            binding.txtInputNombre.hint = "Nombre"
        }
        binding.btnActualizar.setOnClickListener {
            if (!binding.txtInputNombre.text.isNullOrBlank()) {
                binding.txtNombreResult.text = binding.txtInputNombre.text.toString()
                binding.txtInputNombre.setText("")
                binding.txtFraseResult.text = if (!binding.txtInputFrase.text.isNullOrBlank()) binding.txtInputFrase.text else "-"
                binding.txtInputFrase.setText("")
            } else {
                binding.txtInputNombre.setHintTextColor("#FF0000".toColorInt())
                binding.txtInputNombre.hint = "No valido"
            }
        }

        // Esto fuerza que se haga un cambio entre el themes.xml claro y el themes.xml oscuro
        // (values-night/themes.xml), los cuales tengo configurados para que apliquen la paleta de
        // colores que quiero
        binding.toggleButtonTema.setOnClickListener {
            cambiarTema()
        }

        binding.switchIcono.setOnClickListener {
            if(binding.switchIcono.isChecked) {
                binding.imgFavoritoResult.visibility = View.VISIBLE
            } else {
                binding.imgFavoritoResult.visibility = View.INVISIBLE
            }
        }


        binding.imgFavorito.setOnClickListener {
            if (esFavorita) {
                binding.imgFavorito.setImageResource(android.R.drawable.btn_star_big_off)
                esFavorita = false
                binding.txtFavoritaResult.text = ""
            } else {
                binding.imgFavorito.setImageResource(android.R.drawable.btn_star_big_on)
                esFavorita = true
                binding.txtFavoritaResult.text = "Favorita"
            }
        }

        binding.btnRestablecer.setOnClickListener {
            // Cambia al modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.txtInputNombre.setText("")
            binding.txtInputFrase.setText("")
            binding.txtNombreResult.text = ""
            binding.txtFraseResult.text = ""
            binding.toggleButtonTema.isChecked = false
            binding.switchIcono.isChecked = false
            binding.imgFavoritoResult.visibility = View.INVISIBLE
            binding.imgFavorito.setImageResource(android.R.drawable.btn_star_big_off)
            esFavorita = false
            binding.txtFavoritaResult.text = ""
            binding.txtInputNombre.setHintTextColor(colorStateListHint)
            binding.txtInputNombre.hint = "Nombre"
        }

    }

    private fun cambiarTema() {
        val currentMode = AppCompatDelegate.getDefaultNightMode()

        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            // Cambiar a modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            // Cambiar a modo oscuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    // Al cambiar el tema que se aplica en toda la Activity, se destruye la Activity. Por lo tanto,
    // debemos guardar los valores que queramos de la Activity mediante el método
    //  onSaveInstanceState(outState: Bundle), y restaurarlos mediante
    //  onRestoreInstanceState(savedInstanceState: Bundle). Si se intentan guardar valores en
    //  variables sin hacer uso de estos 2 métodos, no funciona
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean("SWITCH_ESTADO", binding.switchIcono.isChecked)
        outState.putString("TEXTO_NOMBRE", binding.txtNombreResult.text.toString())
        outState.putString("TEXTO_FRASE", binding.txtFraseResult.text.toString())
        outState.putInt("VISIBILIDAD_IMG", binding.imgFavoritoResult.visibility)
        outState.putString("TEXTO_FAVORITO", binding.txtFavoritaResult.text.toString())
        outState.putBoolean("IMG_FAVORITA", esFavorita)
        // Nota: Guardar drawables directamente en el Bundle no es eficiente,
        // es mejor guardar un booleano (ej. si es favorito o no) y cambiar la imagen según ese booleano.
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        binding.switchIcono.isChecked = savedInstanceState.getBoolean("SWITCH_ESTADO")
        binding.txtNombreResult.text = savedInstanceState.getString("TEXTO_NOMBRE")
        binding.txtFraseResult.text = savedInstanceState.getString("TEXTO_FRASE")
        binding.imgFavoritoResult.visibility = savedInstanceState.getInt("VISIBILIDAD_IMG")
        binding.txtFavoritaResult.text = savedInstanceState.getString("TEXTO_FAVORITO")
        esFavorita = savedInstanceState.getBoolean("IMG_FAVORITA")
        if (esFavorita) {
            binding.imgFavorito.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            binding.imgFavorito.setImageResource(android.R.drawable.btn_star_big_off)
        }
    }
}