package com.adaniswarad.footballapp.favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adaniswarad.footballapp.R.layout.item_match_list
import com.adaniswarad.footballapp.db.Favorite
import kotlinx.android.synthetic.main.item_match_list.view.*

class FavoriteMatchAdapter(private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(LayoutInflater.from(parent.context).inflate(item_match_list, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class FavoriteMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        itemView.match_date.text = favorite.matchDate
        itemView.home_team_name.text = reformatTeamName(favorite.homeTeamName.toString())
        itemView.away_team_name.text = reformatTeamName(favorite.awayTeamName.toString())
        itemView.home_score.text = favorite.homeScore
        itemView.away_score.text = favorite.awayScore
        itemView.setOnClickListener { listener(favorite) }
    }

    private fun reformatTeamName(name: String): String {
        if (name.length > 10) {
            return name.substring(0, 10) + "..."
        } else {
            return name
        }
    }
}