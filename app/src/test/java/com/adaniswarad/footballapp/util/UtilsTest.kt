package com.adaniswarad.footballapp.util

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class UtilsTest {

    @Test
    fun testToSimpleString() {
        val date = SimpleDateFormat("yyyy-MM-dd").parse("2018-02-28")
        assertEquals("Wed, 28 Feb 2018", toSimpleString(date))
    }
}