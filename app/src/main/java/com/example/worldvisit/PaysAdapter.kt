package com.example.worldvisit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldvisit.ws.Pays

class PaysAdapter( private var listePays: MutableList<Pays>, private val listeActivity: ListeActivity): RecyclerView.Adapter<PaysAdapter.PaysViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaysViewHolder
    {
        val viewPays = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pays, parent, false)
        return PaysViewHolder(viewPays)
    }
    // Renseigne le contenu de chaque vue item :
    override fun onBindViewHolder(holder: PaysViewHolder, position: Int)
    {
        holder.textViewLibellePays.text = listePays[position].name
    }
    override fun getItemCount(): Int = listePays.size
    // ViewHolder :
    inner class PaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewLibellePays: TextView = itemView.findViewById(R.id.nom)
    }
}