package com.adaniswarad.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    @SerializedName("idEvent") var matchId: String? = null,
    @SerializedName("dateEvent") var matchDate: String? = null,
    @SerializedName("idHomeTeam") var homeTeamId: String? = null,
    @SerializedName("idAwayTeam") var awayTeamId: String? = null,
    @SerializedName("strHomeTeam") var homeTeamName: String? = null,
    @SerializedName("strAwayTeam") var awayTeamName: String? = null,
    @SerializedName("intHomeScore") var homeScore: String? = null,
    @SerializedName("intAwayScore") var awayScore: String? = null,
    @SerializedName("strHomeGoalDetails") var homeGoalDetails: String? = null,
    @SerializedName("strAwayGoalDetails") var awayGoalDetails: String? = null,
    @SerializedName("intHomeShots") var homeShots: String? = null,
    @SerializedName("intAwayShots") var awayShots: String? = null,
    @SerializedName("strHomeLineupGoalkeeper") var homeLineupGoalKeeper: String? = null,
    @SerializedName("strAwayLineupGoalkeeper") var awayLineupGoalKeeper: String? = null,
    @SerializedName("strHomeLineupDefense") var homeLineupDefense: String? = null,
    @SerializedName("strAwayLineupDefense") var awayLineupDefense: String? = null,
    @SerializedName("strHomeLineupMidfield") var homeLineupMidfield: String? = null,
    @SerializedName("strAwayLineupMidfield") var awayLineupMidfield: String? = null,
    @SerializedName("strHomeLineupForward") var homeLineupForward: String? = null,
    @SerializedName("strAwayLineupForward") var awayLineupForward: String? = null,
    @SerializedName("strHomeLineupSubstitutes") var homeLineupSubtitutes: String? = null,
    @SerializedName("strAwayLineupSubstitutes") var awayLineupSubtitutes: String? = null
) : Parcelable