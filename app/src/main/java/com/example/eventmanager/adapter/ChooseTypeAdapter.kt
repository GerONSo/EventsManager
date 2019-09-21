package adapter

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanager.R

class ChooseTypeAdapter(
    val list: List<Info>,
    val onItemClickListener : (position: Int) -> Unit
) : RecyclerView.Adapter<ChooseTypeAdapter.TypeCard>(){

    val CARD_NUMBER = 2
    //val ids = listOf(R.drawable.fish, R.drawable.fish)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeCard {
        return TypeCard(LayoutInflater.from(parent.context).inflate(R.layout.type_layout, parent, false))
    }

    override fun getItemCount() = CARD_NUMBER

    override fun onBindViewHolder(holder: TypeCard, position: Int) {
        holder.typeName.text = list[position].name
        //holder.typeImage.setImageResource(ids[position])
    }


    inner class TypeCard(val view: View) : RecyclerView.ViewHolder(view){

        init {
            view.setOnClickListener{ onItemClickListener(adapterPosition) }
        }

        val typeName = view.findViewById<TextView>(R.id.typeName)
        val typeImage = view.findViewById<ImageView>(R.id.typeImage)

    }
}

data class Info(val name: String, val image: String)