package com.example.projectandroid.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroid.R
import com.example.projectandroid.model.Game

class GameListAdapter (context: Context, gamez: List<Game>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var context: Context
    var games: List<Game>
    var TAG = "EmpAdapter"
    init {
        this.context = context
        this.games = gamez


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return GameHolder(inflater.inflate(R.layout.single_game, parent, false))

    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {



        val game = games[position]
        val gh = holder as GameHolder

        gh.lbl_name.setText(game.name)

        /*gh.lbl_designation.setText(game.emp_designation)
        gh.lbl_name.setText(game.emp_name)
        gh.lbl_salary.setText(game.emp_salary)
        gh.img_emp.setImageResource(game.emp_photo!!)*/
    }
    override fun getItemCount(): Int {
        return games.size
    }

    internal class GameHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lbl_name: TextView
        /*var img_emp: ImageView
        var lbl_designation: TextView
        var lbl_salary: TextView
*/

        init {
            lbl_name = itemView.findViewById(R.id.lbl_name) as TextView
            /*img_emp = itemView.findViewById(R.id.img_emp) as ImageView
            lbl_designation = itemView.findViewById(R.id.lbl_designation) as TextView
            lbl_salary = itemView.findViewById(R.id.lbl_salary) as TextView*/
        }
    }
}