package com.adaniswarad.footballapp.match

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adaniswarad.footballapp.R
import com.adaniswarad.footballapp.R.layout.fragment_main
import com.adaniswarad.footballapp.api.ApiRepository
import com.adaniswarad.footballapp.detail.MatchDetailActivity
import com.adaniswarad.footballapp.model.Match
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.view.*

class NextMatchFragment : Fragment(), MatchView {

    private var matches: MutableList<Match> = mutableListOf()
    private var OPTION: String = "eventsnextleague.php"
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(fragment_main, container, false)
        swipeRefresh = rootView.findViewById(R.id.swiperefresh)

        adapter = MainAdapter(matches) {
            context?.startActivity(Intent(context, MatchDetailActivity::class.java).putExtra("id", it.matchId))
        }
        rootView.recyclerview.adapter = adapter
        rootView.recyclerview.layoutManager = LinearLayoutManager(context)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        presenter.getMatchList(OPTION)

        swipeRefresh.setColorSchemeResources(
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_green_light
        )
        swipeRefresh.setOnRefreshListener {
            presenter.getMatchList(OPTION)
        }

        return rootView
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }
}