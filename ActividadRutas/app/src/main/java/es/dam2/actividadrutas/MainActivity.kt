package es.dam2.actividadrutas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dataset = mutableListOf<Dato>(
            Dato(R.drawable.lago, "A la orilla del lago", "4 km", "fácil", "30 minutos", "Pasear a la orilla del lago Pipicaca de transquis XD"),
            Dato(R.drawable.bosque, "Bosque mágico", "7 km", "media", "60 minutos", "Piérdete en el bosque,a saber si te encuentran luego jeje"),
            Dato(R.drawable.costa, "Solecico en la playa", "3 km", "fácil", "20 minutos", "Lo mismo que el lago pero en la playa, yo que sé"),
            Dato(R.drawable.cascada, "Entrenamiento con el Pilar de la roca", "20 km", "difícil", "10 horas", "Entrenamiento con el Pilar de la roca para hacer lo de aguantar el chorro de la cascada en la espalda y luego pegarte tremendo paseo con una roca enorme atada a la cintura"),
            Dato(R.drawable.montana, "A la Sombra de la Sierra", "16 km", "difícil", "6 horas", "Si te encuentro, gritaré a viva voz, que prefiero verte que, ganar la guerra. Levántate mi corazón, te escondiste a la sombra de la sierra"),
            Dato(R.drawable.desierto, "Steel Ball Run", "6000 km", "DIOS", "116 días", "Quiero descubir el secreto de esas bolas de acero"),
        )

        val customAdapter = CustomAdapter(dataset)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
    }
}