package com.rajan.my3rdyearproject

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Class for allowing the user to choose their fitness goals.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class SpecifyGoalSetup : AppCompatActivity() {

    /**
     * I get the necessary conditions for Firebase Authentication.
     * I get the necessary conditions for Firebase Firestore too.
     * All EditTexts and Buttons are called in correctly.
     */

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String


    private lateinit var switchMuscleGain: Switch
    private lateinit var switchEndurance: Switch
    private lateinit var switchStrength: Switch
    private lateinit var switchToneUp: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.specify_goal_layout)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = mAuth.currentUser?.uid ?: ""

        switchMuscleGain = findViewById(R.id.switchMuscleGain)
        switchEndurance = findViewById(R.id.switchEndurance)
        switchStrength = findViewById(R.id.switchStrength)
        switchToneUp = findViewById(R.id.switchToneUp)

        val launchButton = findViewById<Button>(R.id.next_button_goal_page)
        launchButton.setOnClickListener { goalPageNextButtonClick() }
    }

    /**
     *  This method is where I save the user goal info.
     *  I am then creating a hashmap of all values.
     *  Those values are then saved to Firebase.
     *  The collection name is userId / currentUser. The document ID is GymGoalDocument.
     */

    private fun saveUserGoalInfo() {
        val user = hashMapOf(
            "Muscle Gain Focus" to switchMuscleGain.isChecked,
            "Endurance Focus" to switchEndurance.isChecked,
            "Strength Focus" to switchStrength.isChecked,
            "Tone Up Focus" to switchToneUp.isChecked,

            "totalCalories" to 0.0,
            "totalCarbs" to 0.0,
            "totalProteins" to 0.0,
            "totalFats" to 0.0,
            "totalSugars" to 0.0,
            "totalFiber" to 0.0
        )

        val docReference = db.collection(userId).document("GymGoalDocument")
        docReference
            .set(user)
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "New gym user info successfully")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding/updating new gym user info", e)
            }
    }

    private fun goalPageNextButtonClick() {
        saveUserGoalInfo()
        launchBodyFocusSetup()
    }

    private fun launchBodyFocusSetup() {
        val newIntent = Intent(this, SpecifyBodyFocusSetup::class.java)
        startActivity(newIntent)
    }
}
