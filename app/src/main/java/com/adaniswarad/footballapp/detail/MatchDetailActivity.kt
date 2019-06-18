package com.adaniswarad.footballapp.detail

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.adaniswarad.footballapp.R
import com.adaniswarad.footballapp.R.drawable.ic_add_to_favorites
import com.adaniswarad.footballapp.R.drawable.ic_added_to_favorites
import com.adaniswarad.footballapp.R.id.add_to_favorite
import com.adaniswarad.footballapp.R.layout.activity_match_detail
import com.adaniswarad.footballapp.api.ApiRepository
import com.adaniswarad.footballapp.db.Favorite
import com.adaniswarad.footballapp.db.database
import com.adaniswarad.footballapp.model.Match
import com.adaniswarad.footballapp.util.toSimpleString
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import java.text.SimpleDateFormat

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var match: Match
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var id: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_match_detail)

        swipeRefresh = find(R.id.swiperefresh)
        id = intent.getStringExtra("id")

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)
        presenter.getMatchDetail(id)

        swipeRefresh.setOnRefreshListener { presenter.getMatchDetail(id) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to match.matchId,
                            Favorite.MATCH_DATE to match.matchDate,
                    Favorite.HOME_TEAM_NAME to match.homeTeamName,
                    Favorite.AWAY_TEAM_NAME to match.awayTeamName,
                    Favorite.HOME_SCORE to match.homeScore,
                    Favorite.AWAY_SCORE to match.awayScore)
            }
            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})",
                    "id" to id)
            }
            swipeRefresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(MATCH_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showMatchDetail(data: Match, badges: List<String>) {
        match = Match(
            matchId = data.matchId,
            matchDate = data.matchDate,
            homeTeamName = data.homeTeamName,
            awayTeamName = data.awayTeamName,
            homeScore = data.homeScore,
            awayScore = data.awayScore
        )
        Picasso.get().load(badges[0]).into(home_badge)
        Picasso.get().load(badges[1]).into(away_badge)
        match_date_detail.text = formatDate(data.matchDate.toString())
        home_team_name_detail.text = data.homeTeamName
        away_team_name_detail.text = data.awayTeamName
        home_score_detail.text = data.homeScore
        away_score_detail.text = data.awayScore
        home_goal_details.text = data.homeGoalDetails
        away_goal_details.text = data.awayGoalDetails
        home_shots.text = data.homeShots
        away_shots.text = data.awayShots
        home_goalkeeper.text = data.homeLineupGoalKeeper
        away_goalkeeper.text = data.awayLineupGoalKeeper
        home_defense.text = data.homeLineupDefense
        away_defense.text = data.awayLineupDefense
        home_midfield.text = data.homeLineupMidfield
        away_midfield.text = data.awayLineupMidfield
        home_forward.text = data.homeLineupForward
        away_forward.text = data.awayLineupForward
        home_subtitutes.text = data.homeLineupSubtitutes
        away_subtitutes.text = data.awayLineupSubtitutes
        swipeRefresh.isRefreshing = false
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String? {
        val res = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return toSimpleString(res)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
