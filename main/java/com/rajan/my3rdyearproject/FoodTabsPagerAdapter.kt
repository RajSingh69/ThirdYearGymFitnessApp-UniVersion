package com.rajan.my3rdyearproject

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
/**
 * Adapter for the food tabs pages.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class FoodTabsPagerAdapter(fragment: AppBarFoodSetup) : FragmentStateAdapter(fragment) {

    /**
     * This shows the different pages to show when at different positions.
     * If no position, immediately show position 0 / breakfast fragment.
     */


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Z_Breakfast_Fragment()
            1 -> Z_Lunch_Fragment()
            2 -> Z_Dinner_Fragment()
            else -> Z_Breakfast_Fragment()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}
