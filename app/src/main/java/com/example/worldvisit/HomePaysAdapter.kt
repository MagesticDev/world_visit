package com.example.worldvisit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldvisit.ws.Pays
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pays.view.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomePaysAdapter(var listePays: MutableList<Pays>): RecyclerView.Adapter<HomePaysAdapter.PaysViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaysViewHolder
    {
        val viewPays = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_pays, parent, false)
        return PaysViewHolder(viewPays)
    }
    // Renseigne le contenu de chaque vue item :
    override fun onBindViewHolder(holder: PaysViewHolder, position: Int)
    {
        holder.bind(listePays[position]);
    }
    override fun getItemCount(): Int = listePays.size
    // ViewHolder :
    inner class PaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(pays: Pays) = with(itemView){
            //itemView.flag
            Picasso.get().load(pays.url_flag).into(itemView.flag)
            itemView.name.text = pays.name
            itemView.capital.text = "Capitale : ${pays.capitale}"
            itemView.region.text = "Region : ${pays.region}"
        }
    }
}