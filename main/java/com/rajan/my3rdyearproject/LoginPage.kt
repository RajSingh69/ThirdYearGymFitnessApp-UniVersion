package com.rajan.my3rdyearproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

/**
 * Class for creating backend for a login page.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class LoginPage : AppCompatActivity() {

    /**
     * I get the necessary conditions for Firebase Authentication.
     * I get the necessary conditions for Firebase Firestore too.
     * All EditTexts and Buttons are called in correctly.
     */

    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser

    private lateinit var userEmail : EditText
    private lateinit var userPassword : EditText
    private lateinit var loginBtn : Button
    private lateinit var createAccountBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page_layout)

        userEmail = findViewById(R.id.enter_user_email_address)
        userPassword = findViewById(R.id.enter_user_password)

        loginBtn = findViewById(R.id.logInBtn_logOnPage)
        loginBtn.setOnClickListener { v -> loginClick(v) }

        createAccountBtn = findViewById(R.id.create_user_account_lgpageVersion)
        createAccountBtn.setOnClickListener { v -> signUp(v) }
    }

    /**
     * This method is for logging in the user.
     * It checks if the user entered username and password exist in Firebase and are correct.
     * If so, it allows them to login by taking them to the app fragments (starting on the
     *  overview fragment).
     *  It signs them in as the ID associated with the login as the current user.
     */


    private fun loginClick (view: View){

        if (userEmail.length() == 0 || userPassword.length() == 0) {
            displayMessage( (loginBtn), getString(R.string.errorThing) )
            return
        }

        mAuth.signInWithEmailAndPassword(
            userEmail.text.toString(),
            userPassword.text.toString()).addOnCompleteListener(this) {
                task ->
            if (task.isSuccessful) {
                closeKeyBoard()
                update()
                logMeIn(view)
            } else {
                closeKeyBoard()
                displayMessage( (loginBtn), getString(R.string.errorThing) )
                signUp(view)
            }

        }
    }

    /**
     * This method sets the id of the person trying to login as an authenticated
     *  current user of the program.
     *  it also displays a message what the current user id of the login is.
     */

    @SuppressLint("SetTextI18n")
    private fun update(){
        currentUser = mAuth.currentUser

        val currentEmail = currentUser?.email
        val greetingSpace = findViewById<TextView>(R.id.greetingViewLoginPage)

        if (currentEmail == null) {
            greetingSpace.text = "Please sign in above."
        } else {
            greetingSpace.text = "You are currently signed in with $currentEmail"
        }

        Log.d("THE CURRENT USER IS", "THE CURRENT USER IS $currentUser")
    }


    private fun displayMessage(view: View , msgText: String){
        val sb = Snackbar.make(view, msgText, Snackbar.LENGTH_SHORT)
        sb.show()
    }


    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     *  This function takes the user to the sign up page.
     *  Used when the button is clicked, starts a new activity.
     */

    private fun signUp(view: View) {
        val newIntent = Intent(this, NewUserPage::class.java)
        startActivity(newIntent)
    }

    /**
     *  This function logs in the user.
     *  When called, it takes the user to the activity "AppBarSetup".
     *      Aka - Goes to fragmentation page.
     */

    private fun logMeIn(view: View) {
        val newIntent = Intent(this, AppBarSetup::class.java)
        startActivity(newIntent)
    }

}