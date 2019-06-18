package com.adaniswarad.footballapp.match

import com.adaniswarad.footballapp.api.ApiRepository
import com.adaniswarad.footballapp.api.TheSportDBApi
import com.adaniswarad.footballapp.model.MatchResponse
import com.adaniswarad.footballapp.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMatchList(option: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatches(option)).await(), MatchResponse::class.java)
            view.showMatchList(data.matches)
            view.hideLoading()
        }
    }
}