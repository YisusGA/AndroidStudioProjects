package es.dam2.actividadrutas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val dataSet: MutableList<Dato>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView
        val titulo: TextView
        val distancia: TextView
        val dificultad: TextView
        val duracion: TextView
        val favorita: ImageView

        init {
            // Define click listener for the ViewHolder's View
            titulo = view.findViewById(R.id.txtTitulo)
            imagen = view.findViewById(R.id.imgRuta)
            distancia = view.findViewById(R.id.txtDistancia)
            dificultad = view.findViewById(R.id.txtDificultad)
            duracion = view.findViewById(R.id.txtDuracion)
            favorita = view.findViewById(R.id.imgFavorita)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.titulo.text = dataSet[position].titulo
        viewHolder.imagen.setImageResource(dataSet[position].image)
        viewHolder.distancia.text = dataSet[position].distancia
        viewHolder.dificultad.text = dataSet[position].dificultad
        viewHolder.duracion.text = dataSet[position].duracion
        viewHolder.favorita.setImageResource(if (dataSet[position].esFavorita) R.drawable.ic_favorito_on else R.drawable.ic_favorito_off)

        viewHolder.favorita.setOnClickListener {
            dataSet[position].esFavorita = !dataSet[position].esFavorita
            if (dataSet[position].esFavorita)
                viewHolder.favorita.setImageResource(R.drawable.ic_favorito_on)
            else
                viewHolder.favorita.setImageResource(R.drawable.ic_favorito_off)
        }

        viewHolder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}