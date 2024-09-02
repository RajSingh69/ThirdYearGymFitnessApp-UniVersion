package com.rajan.my3rdyearproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * Class for when the user selects their gender.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */


class SpecifyGenderSetup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.specify_gender_layout)

        val launchButton = findViewById<Button>(R.id.next_button_gender_page)
        launchButton.setOnClickListener { v -> launchGoalSetup(v) }
    }

    /**
     * This takes the user to the next step of the process (setting the goals).
     * This is the launchGoalSetup class.
     */

    private fun launchGoalSetup(view: View) {
        Log.d("SpecifyGenderSetup", "Button Clicked")
        val newIntent = Intent(this, SpecifyGoalSetup::class.java)
        startActivity(newIntent)
    }
}
