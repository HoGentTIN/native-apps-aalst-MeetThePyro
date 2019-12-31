package com.example.projectandroid.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroid.R
import com.example.projectandroid.model.Game
import com.example.projectandroid.model.GameDetailed

class GameDetailedAdapter: RecyclerView.Adapter<GameDetailedAdapter.ViewHolder>() {
    var data = listOf<GameDetailed>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.resources
        holder.gameName.text = item.name
        //holder.gameDev.text = "Developer: " + item.developer
        //holder.gamePublisher.text = "Publisher: " + item.publisher
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.game_fragment, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val gameName: TextView = itemView.findViewById(R.id.game_detailed_name)
        //val gameDev: TextView = itemView.findViewById(R.id.game_dev)
        //val gamePublisher: TextView = itemView.findViewById(R.id.game_publisher)
    }
}