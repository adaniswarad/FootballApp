package com.adaniswarad.footballapp.match

import com.adaniswarad.footballapp.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}