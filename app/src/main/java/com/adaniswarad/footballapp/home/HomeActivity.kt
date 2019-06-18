package com.adaniswarad.footballapp.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.adaniswarad.footballapp.R.layout.activity_main
import com.adaniswarad.footballapp.favorite.FavoriteMatchFragment
import com.adaniswarad.footballapp.match.LastMatchFragment
import com.adaniswarad.footballapp.match.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        setSupportActionBar(toolbar)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
        tablayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }
}

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) LastMatchFragment()
        else if (position == 1) NextMatchFragment()
        else FavoriteMatchFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}
