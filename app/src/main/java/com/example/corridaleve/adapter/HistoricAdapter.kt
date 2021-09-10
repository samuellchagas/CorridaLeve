package com.example.corridaleve.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.corridaleve.R
import com.example.corridaleve.model.Historic

class HistoricAdapter(private val list: List<Historic>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoricViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.info_run, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HistoricViewHolder){
            holder.distancia.text = "Distância: "+list[position].distancia
            holder.tempo.text ="Tempo: "+ list[position].tempo
            holder.pace.text ="Pace: "+ list[position].pace
        }
    }

    class HistoricViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val distancia: TextView = itemView.findViewById(R.id.text_distancia)
        val tempo: TextView = itemView.findViewById(R.id.text_tempo)
        val pace: TextView = itemView.findViewById(R.id.text_pace)
    }


}