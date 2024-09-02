package com.rajan.my3rdyearproject

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Class for setting up the breakfast fragment in the 3rd year project.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class SpecifyHeightWeightSetup : AppCompatActivity() {

    /**
     * I get the necessary conditions for Firebase Authentication.
     * I get the necessary conditions for Firebase Firestore too.
     * All EditTexts and Buttons are called in correctly.
     */

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String

    private lateinit var currentHeight: EditText
    private lateinit var currentWeight: EditText
    private lateinit var targetWeight: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.specify_height_weight_layout)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = mAuth.currentUser?.uid ?: ""

        currentHeight = findViewById(R.id.obtain_current_height)
        currentWeight = findViewById(R.id.obtain_current_weight)
        targetWeight = findViewById(R.id.obtain_target_weight)

        val launchButton = findViewById<Button>(R.id.next_button_HeightWeight_page)
        launchButton.setOnClickListener { heightWeightNextButtonClick() }
    }

    /**
     *  This method is where I save the user goal info.
     *  First i am changing the data types. I am converting from a string to a float.
     *  I am then creating a hashmap of all values.
     *  Those values are then saved to Firebase.
     *  The collection name is userId / currentUser. The document ID is HeightWeightDocument.
     */

    private fun saveUserGoalInfo() {
        val currentHeightValue = currentHeight.text.toString().toFloatOrNull() ?: 0.0f
        val currentWeightValue = currentWeight.text.toString().toFloatOrNull() ?: 0.0f
        val targetWeightValue = targetWeight.text.toString().toFloatOrNull() ?: 0.0f

        val user = hashMapOf(
            "Current Height" to currentHeightValue,
            "Current Weight" to currentWeightValue,
            "Target Weight" to targetWeightValue
        )

        val docReference = db.collection(userId).document("HeightWeightDocument")
        docReference
            .set(user)
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "New gym user info successfully")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding/updating new gym user info", e)
            }
    }


    private fun heightWeightNextButtonClick() {
        saveUserGoalInfo()
        launchMaxRepsSetup()
    }

    private fun launchMaxRepsSetup() {
        val newIntent = Intent(this, EstimateMaxRepsSetup::class.java)
        startActivity(newIntent)
    }
}