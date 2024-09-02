package com.rajan.my3rdyearproject

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter for the app pages.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class FirstTabsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    /**
     * This shows the different pages to show when at different positions.
     * If no position, immediately show position 0 / overview fragment.
     */

    override fun createFragment(index: Int): Fragment {
        return when (index) {
            0 -> OverviewFragment()
            1 -> GymFragment()
            2 -> DietFragment()
            3 -> RoutineFragment()
            4 -> StructureFragment()
            else -> OverviewFragment()
        }
    }

    override fun getItemCount(): Int {
        return 5
    }
}

