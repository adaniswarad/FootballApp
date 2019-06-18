package com.adaniswarad.footballapp.detail

import com.adaniswarad.footballapp.TestContextProvider
import com.adaniswarad.footballapp.api.ApiRepository
import com.adaniswarad.footballapp.api.TheSportDBApi
import com.adaniswarad.footballapp.model.Match
import com.adaniswarad.footballapp.model.MatchResponse
import com.adaniswarad.footballapp.model.Team
import com.adaniswarad.footballapp.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    @Mock private lateinit var view: MatchDetailView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchDetail() {

    }
}