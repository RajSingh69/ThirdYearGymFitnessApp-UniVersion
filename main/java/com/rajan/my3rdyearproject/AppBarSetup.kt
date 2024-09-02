package com.rajan.my3rdyearproject

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * App bar setup for diet fragment on the 3rd year project app.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class AppBarSetup : AppCompatActivity() {

    /**
     * This block sets the xml layout to "app_bar_layout".
     * It also sets about the tab layout and the required pager.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_main_layout)

        val myToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.first_toolbar)
        setSupportActionBar(myToolbar)

        val tabLayout = findViewById<TabLayout>(R.id.first_tab_layout)
        val viewPager = findViewById<ViewPager2>(R.id.first_pager)
        Log.d("ViewPager2", "ViewPager2 found hiiiiiiiii")

        val tabTitles = resources.getStringArray(R.array.tabTitles)
        val pagerAdapter = FirstTabsPagerAdapter(this)

        viewPager.adapter = pagerAdapter

        /**
         * This code block sets teh required page to the correct title.
         * It sets [0] as Overview, [1] as Gym Tracker, [2] as Diet Planner,
         *  [3] as Routine Scheduler and [4] as Life Structure / Mental Health Section
         */

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = tabTitles[0]
                1 -> tab.text = tabTitles[1]
                2 -> tab.text = tabTitles[2]
                3 -> tab.text = tabTitles[3]
                4 -> tab.text = tabTitles[4]
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.first_toolbar_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }
}