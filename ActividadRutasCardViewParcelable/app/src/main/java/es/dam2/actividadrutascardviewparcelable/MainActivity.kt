package es.dam2.actividadrutascardviewparcelable

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import es.dam2.actividadrutascardviewparcelable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var customAdapter: CustomAdapter
    private lateinit var dataset: MutableList<Dato>
    // Declaramos el launcher para recibir el resultado de DetalleRuta.
    // Esta variable es de tipo ActivityResultLauncher. Esto no lo vamos a ver ni lo vamos a usar,
    // pues nosotros trabajaremos con bases de datos para leer y escribir datos, y seguiremos una
    // arquitectura diferente
    private val launcherDetalle = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val posicionModificada = data?.getIntExtra("PosicionModificada", -1) ?: -1
            val rutaModificada = data?.getParcelableExtra<Dato>("RutaModificada")

            if (posicionModificada != -1 && rutaModificada != null) {
                dataset[posicionModificada] = rutaModificada
                customAdapter.notifyItemChanged(posicionModificada)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dataset = mutableListOf(
            Dato(
                R.drawable.lago,
                "A la orilla del lago",
                "4 km",
                "fácil",
                "30 minutos",
                "Pasear a la orilla del lago Pipicaca de transquis XD",
                false
            ),
            Dato(
                R.drawable.bosque,
                "Bosque mágico",
                "7 km",
                "media",
                "60 minutos",
                "Piérdete en el bosque,a saber si te encuentran luego jeje",
                false
            ),
            Dato(
                R.drawable.costa,
                "Solecico en la playa",
                "3 km",
                "fácil",
                "20 minutos",
                "Lo mismo que el lago pero en la playa, yo que sé",
                false
            ),
            Dato(
                R.drawable.cascada,
                "Entrenamiento con el Pilar de la roca",
                "20 km",
                "difícil",
                "10 horas",
                "Entrenamiento con el Pilar de la roca para hacer lo de aguantar el chorro de la cascada en la espalda y luego pegarte tremendo paseo con una roca enorme atada a la cintura",
                false
            ),
            Dato(
                R.drawable.montana,
                "A la Sombra de la Sierra",
                "16 km",
                "difícil",
                "6 horas",
                "Si te encuentro, gritaré a viva voz, que prefiero verte que, ganar la guerra. Levántate mi corazón, te escondiste a la sombra de la sierra",
                false
            ),
            Dato(
                R.drawable.desierto,
                "Steel Ball Run",
                "6000 km",
                "DIOS",
                "116 días",
                "Quiero descubir el secreto de esas bolas de acero",
                false
            ),
        )

        // Cuando tenemos que pasar más de 1 función como parámetro, todas menos la última deben ir
        // obligatoriamente dentro de los  paréntesis, separadas por coma de los otros parámetros y funciones.
        // Y la última puede ir o bien fuera de  los paréntesis, entre llaves, y sin coma que la separe del
        // paréntesis o bien meterla entre llaves dentro de los paréntesis, y separarla
        // con coma del parámetro/función anterior. Esta última forma es la más limpia
        customAdapter = CustomAdapter(dataset, { dato, posicion ->
            val intent = Intent(this, DetalleRuta::class.java)
            intent.putExtra("RutaSeleccionada", dato)
            intent.putExtra("PosicionSeleccionada", posicion)
            launcherDetalle.launch(intent)
            startActivity(intent)
            // Esta función está metida únicamente como prueba de concepto, realmente no aporta nada
        }, {dato ->
            System.out.printf("Ruta %s añadida como favorita\n", dato.titulo)
        })


        binding.txtRutasDisponibles.text = "${customAdapter.itemCount} rutas disponibles"

        binding.customRecyclerView.layoutManager = LinearLayoutManager(this)
        // Si se quisiera usar un GridLayoutManager, comentar la línea de arriba y descomentar la
        // de abajo
//        binding.customRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.customRecyclerView.adapter = customAdapter
    }

    override fun onResume() {
        super.onResume()
        if (::customAdapter.isInitialized) {
            val ruta = intent.getParcelableExtra<Dato>("RutaModificada", Dato::class.java)
            val posicionSeleccionada = intent.getIntExtra("PosicionModificada", -1)
            if (ruta != null && posicionSeleccionada != -1) {
                customAdapter.notifyItemChanged(posicionSeleccionada, ruta)
            }

//            customAdapter.notifyDataSetChanged()
        }
    }
}