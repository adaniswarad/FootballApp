package com.adaniswarad.footballapp.favorite

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adaniswarad.footballapp.R
import com.adaniswarad.footballapp.R.layout.fragment_main
import com.adaniswarad.footballapp.db.Favorite
import com.adaniswarad.footballapp.db.database
import com.adaniswarad.footballapp.detail.MatchDetailActivity
import com.adaniswarad.footballapp.match.MainAdapter
import com.adaniswarad.footballapp.model.Match
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

/**
 * Created by Dennis on 03-Dec-18.
 */
class FavoriteMatchFragment : Fragment() {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var listMatch: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchAdapter(favorites) {
            context?.startActivity(Intent(context, MatchDetailActivity::class.java).putExtra("id", it.matchId))
        }

        listMatch.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(fragment_main, container, false)

        swipeRefresh = rootView.find(R.id.swiperefresh)
        swipeRefresh.setColorSchemeResources(
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_green_light
        )

        listMatch = rootView.find(R.id.recyclerview)
        listMatch.layoutManager = LinearLayoutManager(context)

        return rootView
    }
}