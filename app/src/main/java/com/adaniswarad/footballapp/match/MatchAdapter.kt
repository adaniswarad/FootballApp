package com.adaniswarad.footballapp.match

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adaniswarad.footballapp.R.layout.item_match_list
import com.adaniswarad.footballapp.model.Match
import com.adaniswarad.footballapp.util.toSimpleString
import kotlinx.android.synthetic.main.item_match_list.view.*
import java.text.SimpleDateFormat

class MainAdapter(private val matches: List<Match>, private val listener: (Match) -> Unit) : RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(parent.context).inflate(item_match_list, parent, false))
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }

    override fun getItemCount(): Int = matches.size
}

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(match: Match, listener: (Match) -> Unit) {
        itemView.match_date.text = formatDate(match.matchDate.toString())
        itemView.home_team_name.text = reformatTeamName(match.homeTeamName.toString())
        itemView.away_team_name.text = reformatTeamName(match.awayTeamName.toString())
        itemView.home_score.text = match.homeScore
        itemView.away_score.text = match.awayScore
        itemView.setOnClickListener {
            listener(match)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String? {
        val res = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return toSimpleString(res)
    }

    private fun reformatTeamName(name: String): String {
        if (name.length > 10) {
            return name.substring(0, 10) + "..."
        } else {
            return name
        }
    }
}