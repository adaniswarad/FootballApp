package com.adaniswarad.footballapp.api

import com.adaniswarad.footballapp.BuildConfig

object TheSportDBApi {

    fun getMatches(option: String?): String {
        return "${BuildConfig.BASE_URL}/api/v1/json/${BuildConfig.TSDB_API_KEY}/${option}?id=4328"
    }

    fun getMatchDetail(id: String?): String {
        return "${BuildConfig.BASE_URL}/api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=${id}"
    }

    fun getBadge(id: String?): String {
        return "${BuildConfig.BASE_URL}/api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=${id}"
    }
}