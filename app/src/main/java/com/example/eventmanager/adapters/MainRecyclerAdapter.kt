package com.example.eventmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanager.R
import com.example.eventmanager.requests.MainViewModel
import kotlinx.android.synthetic.main.card_layout.view.*

class MainRecyclerAdapter(
    val onItemCLickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<MainRecyclerAdapter.CardHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        return CardHolder(LayoutInflater.from(parent.context).
            inflate(R.layout.card_layout, parent, false))
    }

    override fun getItemCount() = MainViewModel.getData().value?.
        getOrNull(MainViewModel.currentType ?: -1)?.size ?: 0


    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        if (MainViewModel.currentType == null)
            return
        holder.name.text = MainViewModel.getData().value?.
            get(MainViewModel.currentType!!)?.get(position)?.name
        holder.number.text = MainViewModel.getData().value?.
            get(MainViewModel.currentType!!)?.get(position)?.participants.toString() + " participants"
        holder.date.text = MainViewModel.getData().value?.
            get(MainViewModel.currentType!!)?.get(position)?.date
    }

    inner class CardHolder(val view: View) : RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener{ onItemCLickListener(adapterPosition) }
        }
        val name = view.name
        var number = view.participants
        val date = view.date

    }
}