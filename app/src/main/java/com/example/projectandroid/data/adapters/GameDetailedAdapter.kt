package com.example.projectandroid.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroid.R
import com.example.projectandroid.model.Data
import com.squareup.picasso.Picasso
import java.security.AccessController.getContext

class GameDetailedAdapter : RecyclerView.Adapter<GameDetailedAdapter.ViewHolder>() {
    private lateinit var context: Context
    var data = listOf<Data>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.resources
        holder.gameName.text = item.name
        if (!isEmpty(item.short_description)) {
            holder.gameDesc.text = item.short_description

        } else {
            holder.gameDesc.text = context.getString(R.string.short_desc_missing)
        }

        Picasso.get().load(item.header_image).into(holder.gameImg)


        holder.gameSite.text = item.website

        if (!isEmpty(item.website)) {
            holder.gameSite.text = item.website
        } else {
            holder.gameSite.text = context.getString(R.string.website_missing)
        }

        /*if(item.developers?.size != null && item.developers.isNotEmpty()) {
            holder.gameDev.text = item.developers.joinToString { "," }
        } else {
            holder.gameDev.text = "No developers available"
        }*/

        if(!isListEmpty(item.developers)){
            holder.gameDev.text = context.getString(R.string.dev, listToString(item.developers))
        } else {
            holder.gameDev.text = context.getString(R.string.dev_missing)
        }

        if(!isListEmpty(item.publishers)){
            holder.gamePublisher.text = context.getString(R.string.publisher, listToString(item.publishers))
        } else {
            holder.gamePublisher.text = context.getString(R.string.publisher_missing)
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_game_detailed, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameName: TextView = itemView.findViewById(R.id.game_detailed_name)
        val gameDesc: TextView = itemView.findViewById(R.id.game_detailed_desc)
        val gameDev: TextView = itemView.findViewById(R.id.game_detailed_dev)
        val gamePublisher: TextView = itemView.findViewById(R.id.game_detailed_publisher)
        val gameSite: TextView = itemView.findViewById(R.id.game_detailed_website)
        val gameImg: ImageView = itemView.findViewById(R.id.game_detailed_img)
    }

    fun isEmpty(input: String?): Boolean {
        return (input == null || input == "")
    }

    fun isListEmpty(input: List<String>?): Boolean{
        return (input?.size == null || input.isEmpty())
    }

    fun listToString(input: List<String>?): String{
        return input.toString()!!.removePrefix("[").removeSuffix("]")
    }


}