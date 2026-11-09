package es.dam2.controles_entrada_ejercicio01

import android.graphics.Color
import android.os.Bundle
import android.text.Spanned
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.dam2.controles_entrada_ejercicio01.databinding.ActivityMainBinding
import androidx.core.graphics.toColorInt

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

        // Crear un array con los elementos con los que vamos a alimentar el adapter que asociaremos al Spinner
        val datosSpinner = arrayOf("", "Programación Android", "Diseño de Interfaces", "Redes")
        // Crear el adaptador que asociaremos al Spinner
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, datosSpinner)
        // Crear vista apilada de elementos en el adaptador
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Asociar el adaptador al Spinner
        binding.spinnerTaller.adapter = adaptador

        // Gestionar evento de cambio de selección en el Spinner y almacenar la selección en una variable
        var opcionSeleccionadaSpinner = ""
        binding.spinnerTaller.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Así es como se hace un casting en Kotlin
                opcionSeleccionadaSpinner = parent!!.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                opcionSeleccionadaSpinner = ""
            }
        }

        // Gestionar evento de selección en el RadioGroup y almacenar la selección en una variable
        var opcionSeleccionadaRadioGroup = ""
        binding.radioGroupTurno.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.radioButtonManana.id ->
                    opcionSeleccionadaRadioGroup = binding.radioButtonManana.text.toString()
                binding.radioButtonTarde.id ->
                    opcionSeleccionadaRadioGroup = binding.radioButtonTarde.text.toString()
            }
        }

        // En esta app, no uso la checkbox para más que comprobar si está marcada o no, porque
        // no me interesa hacer más. En la chuleta, está puesto en más detalle cómo se gestionaría
        // un evento de cambio de selección en una CheckBox

        binding.btnInscribirme.setOnClickListener {
            if (comprobarFormRelleno(binding, opcionSeleccionadaSpinner, opcionSeleccionadaRadioGroup)) {
                binding.txtResultado.text = ""
                binding.txtResultado.setBackgroundColor("#FF6750A4".toColorInt())
                binding.txtResultado.append(construirNodosHtml(binding))
                limpiarFormulario(binding)
            }
        }

        binding.btnLimpiar.setOnClickListener {
            limpiarFormulario(binding)
            binding.txtResultado.text = ""
            binding.txtResultado.setBackgroundColor("#FEF7FF".toColorInt())
        }
    }

    private fun comprobarFormRelleno(binding: ActivityMainBinding, opcionSeleccionadaSpinner: String, opcionSeleccionadaRadioGroup: String): Boolean {
        var formRelleno = false
        if (
            !binding.textInputEditTextNombre.text.isNullOrBlank() &&
            !binding.textInputEditTextCorreo.text.isNullOrBlank() &&
            !opcionSeleccionadaSpinner.isBlank() &&
            !opcionSeleccionadaRadioGroup.isBlank() &&
            binding.checkBoxNormas.isChecked
        ) {
            formRelleno = true
        }
        return formRelleno
    }

    private fun construirNodosHtml(binding: ActivityMainBinding): Spanned {
        val textoForm = HtmlCompat.fromHtml("""
            <p style="color:blue"><strong>Inscripción realizada</strong></p>
            <p style="color:black">${binding.textInputEditTextNombre.text} - ${binding.textInputEditTextCorreo.text}</p>
            <p style="color:black">${binding.spinnerTaller.selectedItem} - ${if (binding.radioGroupTurno.checkedRadioButtonId == binding.radioButtonManana.id) "Mañana" else "Tarde"}</p>
        """.trimIndent(),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        return textoForm
    }

    private fun limpiarFormulario(binding: ActivityMainBinding) {
        binding.textInputEditTextNombre.setText("")
        binding.textInputEditTextCorreo.setText("")
        binding.spinnerTaller.setSelection(0)
        binding.radioGroupTurno.clearCheck()
        binding.checkBoxNormas.isChecked = false
    }
}