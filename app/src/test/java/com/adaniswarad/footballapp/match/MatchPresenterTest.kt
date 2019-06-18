package com.adaniswarad.footballapp.match

import com.adaniswarad.footballapp.TestContextProvider
import com.adaniswarad.footballapp.api.ApiRepository
import com.adaniswarad.footballapp.model.Match
import com.adaniswarad.footballapp.model.MatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock private lateinit var view: MatchView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLastMatchList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)
        val option = "eventspastleague.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson( "", MatchResponse::class.java)).thenReturn(response)

            presenter.getMatchList(option)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(matches)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetNextMatchList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)
        val option = "eventsnextleague.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson( "", MatchResponse::class.java)).thenReturn(response)

            presenter.getMatchList(option)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(matches)
            Mockito.verify(view).hideLoading()
        }
    }
}