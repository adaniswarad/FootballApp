package com.adaniswarad.footballapp.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(@SerializedName("events") val matches: List<Match>)