package es.dam2.ejercicio04_selectorcantidad

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var txtNumCantidad: TextView
    // Yo aquí me confundí y metí ImageView en lugar de ImageButton, pero para este caso funcionan igual, pues son clickables y mandan click inputs
    private lateinit var imgMenos: ImageView
    private lateinit var imgMas: ImageView
    private lateinit var txtTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtNumCantidad = findViewById<TextView>(R.id.txtNumCantidad);
        imgMenos = findViewById<ImageView>(R.id.imgMenos)
        imgMas = findViewById<ImageView>(R.id.imgMas)
        txtTotal = findViewById<TextView>(R.id.txtTotal)

        imgMenos.setOnClickListener {
            // Se parsea la cantidad para decidir qué hacer de entrada
            var cantidad = Integer.parseInt(txtNumCantidad.text.toString());
            if (cantidad > 0) {
                imgMenos.imageAlpha = 255;
                imgMenos.isClickable = true;
                imgMas.imageAlpha = 255;
                imgMas.isClickable = true;
                txtNumCantidad.text = "" + --cantidad;
            }
            // Y se parsea la cantidad tras la acción, para decidir qué hacer a continuación con el símbolo - tras la posible modificación de la cantidad
            cantidad = Integer.parseInt(txtNumCantidad.text.toString());
            if (cantidad <= 0) {
                // Si la cantidad es igual a 0, modificamos el alfa de la imagen para hacerla más transparente e indicar visualmente que el botón está
                // deshabilitado. Y hacemos que la ImageView (o el ImageButton) no sea clickable
                imgMenos.imageAlpha = 35;
                imgMenos.isClickable = false;
            }
            txtTotal.text = "Total:  " + (cantidad * 3) + " €";
        }

        imgMas.setOnClickListener {
            var cantidad = Integer.parseInt(txtNumCantidad.text.toString());
            if (cantidad < 10) {
                imgMas.imageAlpha = 255;
                imgMas.isClickable = true;
                imgMenos.imageAlpha = 255;
                imgMenos.isClickable = true;
                txtNumCantidad.text = "" + ++cantidad;
            }
            cantidad = Integer.parseInt(txtNumCantidad.text.toString());
            if (cantidad >= 10) {
                imgMas.imageAlpha = 35;
                imgMas.isClickable = false;
            }
            txtTotal.text = "Total:  " + (cantidad * 3) + " €";
        }
    }
}