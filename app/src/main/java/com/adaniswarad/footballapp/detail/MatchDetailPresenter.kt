package com.adaniswarad.footballapp.detail

import com.adaniswarad.footballapp.api.ApiRepository
import com.adaniswarad.footballapp.api.TheSportDBApi
import com.adaniswarad.footballapp.model.MatchResponse
import com.adaniswarad.footballapp.model.TeamResponse
import com.adaniswarad.footballapp.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getMatchDetail(id: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatchDetail(id)).await(), MatchResponse::class.java)
            val homeBadge = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getBadge(data.matches[0].homeTeamId)).await(), TeamResponse::class.java)
            val awayBadge = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getBadge(data.matches[0].awayTeamId)).await(), TeamResponse::class.java)
            val badges = arrayListOf(homeBadge.teams[0].teamBadge, awayBadge.teams[0].teamBadge)
            view.showMatchDetail(data.matches[0], badges)
            view.hideLoading()
        }
    }
}