package com.example.projectandroid.data.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroid.R
import com.example.projectandroid.TextItemViewHolder
import com.example.projectandroid.model.Game

class GameAdapter: RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    var data = listOf<Game>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.resources
        holder.gameName.text = item.name
        holder.gameDev.text = "Developer: " + item.developer
        holder.gamePublisher.text = "Publisher: " + item.publisher
        holder.appid.text = item.appid.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_games, parent, false)
        return ViewHolder(view)
    }

    var onItemClick: ((pos: Int, view: View) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val gameName: TextView = itemView.findViewById(R.id.game_name)
        val gameDev: TextView = itemView.findViewById(R.id.game_dev)
        val gamePublisher: TextView = itemView.findViewById(R.id.game_publisher)
        val appid: TextView = itemView.findViewById(R.id.game_appid)
        override fun onClick(v: View){
            onItemClick?.invoke(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }


}