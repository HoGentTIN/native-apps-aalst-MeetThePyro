package com.example.projectandroid.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroid.R
import com.example.projectandroid.model.Data
import com.example.projectandroid.model.Game
import com.example.projectandroid.model.GameDetailed
import com.squareup.picasso.Picasso
import java.net.URI

class GameDetailedAdapter: RecyclerView.Adapter<GameDetailedAdapter.ViewHolder>() {
    var data = listOf<Data>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.resources
        holder.gameName.text = item.name
        holder.gameDesc.text = item.short_description
        Picasso.get().load(item.header_image).into(holder.gameImg)
        holder.gameSite.text = item.website
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_game_detailed, parent, false)
        return ViewHolder(view)
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val gameName: TextView = itemView.findViewById(R.id.game_detailed_name)
        val gameDesc: TextView = itemView.findViewById(R.id.game_detailed_desc)
        val gameSite: TextView = itemView.findViewById(R.id.game_detailed_website)
        val gameImg: ImageView = itemView.findViewById(R.id.game_detailed_img)
    }




}