package com.rajan.my3rdyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

/**
 * Main activity starting class for 3rd year project.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val launchButton = findViewById<Button>(R.id.newUserHomePage)
        launchButton.setOnClickListener { v -> launchCreateNewUser(v) }

        val loginLaunchButton = findViewById<Button>(R.id.toLoginPage)
        loginLaunchButton.setOnClickListener { v -> launchLoginPage(v) }
    }

    /**
     * These methods take the user to sign in or to create a new account.
     * This is done so they can proceed into the app.
     */

    private fun launchCreateNewUser(view: View) {
        val newIntent = Intent(this, NewUserPage::class.java)
        startActivity(newIntent)

    }

    private fun launchLoginPage(view: View) {
        val newIntent = Intent(this, LoginPage::class.java)
        startActivity(newIntent)

    }

}