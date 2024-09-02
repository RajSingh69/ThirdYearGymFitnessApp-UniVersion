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

class AppBarFoodSetup : AppCompatActivity() {

    /**
     * This block sets the xml layout to "app_bar_food_layout".
     * It also sets about the tab layout and the required pager.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_food_layout)

        val myToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.food_toolbar)
        setSupportActionBar(myToolbar)

        val tabLayout = findViewById<TabLayout>(R.id.food_tab_layout)
        val viewPager = findViewById<ViewPager2>(R.id.food_pager)
        Log.d("Food ViewPager2", "Food ViewPager2 found")

        val tabTitles = resources.getStringArray(R.array.foodTitles)
        val pagerAdapter = FoodTabsPagerAdapter(this)

        viewPager.adapter = pagerAdapter

        /**
         * This code block sets teh required page to the correct title.
         * It sets [0] as Breakfast, [1] as Lunch and [2] as Dinner.
         */

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = tabTitles[0]
                1 -> tab.text = tabTitles[1]
                2 -> tab.text = tabTitles[2]
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.food_toolbar_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
