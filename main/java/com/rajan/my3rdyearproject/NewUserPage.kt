package com.rajan.my3rdyearproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * This page is for creating a new user page.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class NewUserPage : AppCompatActivity() {

    /**
     * I get the necessary conditions for Firebase Authentication.
     * I get the necessary conditions for Firebase Firestore too.
     * All EditTexts and Buttons are called in correctly.
     */

    private var mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private lateinit var enterEmail: EditText
    private lateinit var enterPassword: EditText
    private lateinit var createUserBtn: Button
    private lateinit var toLoginPageBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_user_page_layout)
        FirebaseApp.initializeApp(this)

        enterEmail = findViewById(R.id.editText_EnterEmail_CreateUserAccount)
        enterPassword = findViewById(R.id.editText_EnterPassword_CreateUserAccount)

        createUserBtn = findViewById(R.id.CreateUserButton_NewUserPage)
        createUserBtn.setOnClickListener { v -> createUserOnClick(v) }

        toLoginPageBtn = findViewById(R.id.LoginButton_NewUserPage)
        toLoginPageBtn.setOnClickListener { v -> launchLoginPage(v) }
    }

    /**
     * This method is used for creating a user.
     * It gets the entered email and password and stores it in the database.
     * If either boxes are empty, the user is prompted to enter a username or password.
     * If successful, it stores the username and password and sets that ID as the current user
     */

    private fun createUserOnClick(view: View) {
        val email = enterEmail.text.toString().trim()
        val password = enterPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            displayMessage(createUserBtn, "Please enter both email and password.")
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    displayMessage(createUserBtn, "New user account created successfully.")
                    val currentUser = mAuth.currentUser
                    currentUser?.let { user ->
                        saveUserInfoReal(user.uid, email, password)
                    }
                } else {
                    displayMessage(createUserBtn, "Failed to create user account. Please try again.")
                    Log.e("NewUserPage", "Failed to create user account", task.exception)
                }
            }
    }

    /**
     * This method is where the user email and password are stored in a different
     *  aspect of Firebase.
     * The collection is the user id and the document is UserInfoDocument.
     */

    private fun saveUserInfoReal(userId: String, email: String, password: String) {
        val userInfo = hashMapOf(
            "Email" to email,
            "Password" to password
        )

        db.collection(userId)
            .document("UserInfoDocument")
            .set(userInfo)
            .addOnSuccessListener {
                Log.d("NewUserPage", "User info saved successfully")
            }
            .addOnFailureListener { e ->
                Log.e("NewUserPage", "Error saving user info", e)
                displayMessage(createUserBtn, "Failed to save user information. Please try again later.")
            }
    }

    private fun displayMessage(view: View, msgText: String) {
        val sb = Snackbar.make(view, msgText, Snackbar.LENGTH_SHORT)
        sb.show()
    }

    private fun launchLoginPage(view: View) {
        val newIntent = Intent(this, LoginPage::class.java)
        startActivity(newIntent)
    }
}
