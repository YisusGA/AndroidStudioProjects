package es.dam2.ejercicio06_panelcontroldomestico

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.alpha
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var cardLuz: TextView
    private var OnStateLuz = true

    private lateinit var cardCalefaccion: TextView
    private var OnStateCalefaccion = false

    private lateinit var cardVentilador: TextView
    private var OnStateVentilador = true

    private lateinit var cardAlarma: TextView
    private var OnStateAlarma = false

    private lateinit var cardPuerta: TextView
    private var OnStatePuerta = true

    private lateinit var cardRiego: TextView
    private var OnStateRiego = false

    private lateinit var cardApagarTodo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cardLuz = findViewById<TextView>(R.id.cardLuz)
        cardCalefaccion = findViewById<TextView>(R.id.cardCalefaccion)
        cardVentilador = findViewById<TextView>(R.id.cardVentilador)
        cardAlarma = findViewById<TextView>(R.id.cardAlarma)
        cardPuerta = findViewById<TextView>(R.id.cardPuerta)
        cardRiego = findViewById<TextView>(R.id.cardRiego)
        cardApagarTodo = findViewById<TextView>(R.id.cardApagarTodo)

        cardLuz.setOnClickListener {
            if (OnStateLuz) {
                cardLuz.setBackgroundResource(R.drawable.fondo_gris)
                OnStateLuz = false
                cardLuz.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_luz, 0, R.drawable.off_switch_resized_184x100);
            } else {
                cardLuz.setBackgroundResource(R.drawable.fondo_verde)
                OnStateLuz = true
                cardLuz.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_luz, 0, R.drawable.on_switch_resized_176x100);
            }
        }

        cardCalefaccion.setOnClickListener {
            if (OnStateCalefaccion) {
                cardCalefaccion.setBackgroundResource(R.drawable.fondo_gris)
                OnStateCalefaccion = false
                cardCalefaccion.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_calefaccion, 0, R.drawable.off_switch_resized_184x100);
            } else {
                cardCalefaccion.setBackgroundResource(R.drawable.fondo_verde)
                OnStateCalefaccion = true
                cardCalefaccion.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_calefaccion, 0, R.drawable.on_switch_resized_176x100);
            }
        }

        cardVentilador.setOnClickListener {
            if (OnStateVentilador) {
                cardVentilador.setBackgroundResource(R.drawable.fondo_gris)
                OnStateVentilador = false
                cardVentilador.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_ventilador, 0, R.drawable.off_switch_resized_184x100);
            } else {
                cardVentilador.setBackgroundResource(R.drawable.fondo_verde)
                OnStateVentilador = true
                cardVentilador.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_ventilador, 0, R.drawable.on_switch_resized_176x100);
            }
        }

        cardAlarma.setOnClickListener {
            if (OnStateAlarma) {
                cardAlarma.setBackgroundResource(R.drawable.fondo_gris)
                OnStateAlarma = false
                cardAlarma.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_alarma, 0, R.drawable.off_switch_resized_184x100);
            } else {
                cardAlarma.setBackgroundResource(R.drawable.fondo_verde)
                OnStateAlarma = true
                cardAlarma.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_alarma, 0, R.drawable.on_switch_resized_176x100);
            }
        }

        cardPuerta.setOnClickListener {
            if (OnStatePuerta) {
                cardPuerta.setBackgroundResource(R.drawable.fondo_gris)
                OnStatePuerta = false
                cardPuerta.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_puerta, 0, R.drawable.off_switch_resized_184x100);
            } else {
                cardPuerta.setBackgroundResource(R.drawable.fondo_verde)
                OnStatePuerta = true
                cardPuerta.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_puerta, 0, R.drawable.on_switch_resized_176x100);
            }
        }

        cardRiego.setOnClickListener {
            if (OnStateRiego) {
                cardRiego.setBackgroundResource(R.drawable.fondo_gris)
                OnStateRiego = false
                cardRiego.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_riego, 0, R.drawable.off_switch_resized_184x100);
            } else {
                cardRiego.setBackgroundResource(R.drawable.fondo_verde)
                OnStateRiego = true
                cardRiego.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_riego, 0, R.drawable.on_switch_resized_176x100);
            }
        }

        cardApagarTodo.setOnClickListener {
            cardLuz.setBackgroundResource(R.drawable.fondo_gris)
            OnStateLuz = false
            cardLuz.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_luz, 0, R.drawable.off_switch_resized_184x100);
            cardCalefaccion.setBackgroundResource(R.drawable.fondo_gris)
            OnStateCalefaccion = false
            cardCalefaccion.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_calefaccion, 0, R.drawable.off_switch_resized_184x100);
            cardVentilador.setBackgroundResource(R.drawable.fondo_gris)
            OnStateVentilador = false
            cardVentilador.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_ventilador, 0, R.drawable.off_switch_resized_184x100);
            cardAlarma.setBackgroundResource(R.drawable.fondo_gris)
            OnStateAlarma = false
            cardAlarma.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_alarma, 0, R.drawable.off_switch_resized_184x100);
            cardPuerta.setBackgroundResource(R.drawable.fondo_gris)
            OnStatePuerta = false
            cardPuerta.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_puerta, 0, R.drawable.off_switch_resized_184x100);
            cardRiego.setBackgroundResource(R.drawable.fondo_gris)
            OnStateRiego = false
            cardRiego.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_riego, 0, R.drawable.off_switch_resized_184x100);
        }
    }
}