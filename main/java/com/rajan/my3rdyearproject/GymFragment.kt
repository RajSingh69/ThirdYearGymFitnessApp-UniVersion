package com.rajan.my3rdyearproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

/**
 * This class is the home page for the gym section, contains the different workout alternatives.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class GymFragment: Fragment() {

    private lateinit var pullButton : Button
    private lateinit var pushButton : Button
    private lateinit var legsButton : Button
    private lateinit var conditioningButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_gym, container, false)!!;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullButton = getView()?.findViewById(R.id.pull_button) as Button
        pullButton.setOnClickListener { submitPullDayButtonClick() }

        pushButton = getView()?.findViewById(R.id.push_button) as Button
        pushButton.setOnClickListener { submitPushDayButtonClick() }

        legsButton = getView()?.findViewById(R.id.legs_button) as Button
        legsButton.setOnClickListener { submitLegsDayButtonClick() }

        conditioningButton = getView()?.findViewById(R.id.conditioning_button) as Button
        conditioningButton.setOnClickListener { submitConditioningDayButtonClick() }


    }

    /**
     * These are methods for different buttons tat can take you to:
     * - Pull Day Workouts (Back and Biceps)
     * - Push Day Workouts (Chest and Triceps)
     * - Leg Day Workouts
     * - Stretching and body structure
     */

    private fun submitPullDayButtonClick() {
        val newIntent = Intent(requireContext(), GymPullDayMain::class.java)
        startActivity(newIntent)
    }

    private fun submitPushDayButtonClick() {
        val newIntent = Intent(requireContext(), GymPushDayMain::class.java)
        startActivity(newIntent)
    }

    private fun submitLegsDayButtonClick() {
        val newIntent = Intent(requireContext(), GymLegDayMain::class.java)
        startActivity(newIntent)
    }

    private fun submitConditioningDayButtonClick() {
        val newIntent = Intent(requireContext(), HowToPullDay::class.java)
        startActivity(newIntent)
    }

}