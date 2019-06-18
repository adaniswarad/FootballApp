package com.adaniswarad.footballapp.detail

import com.adaniswarad.footballapp.model.Match

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: Match, badges: List<String>)
}