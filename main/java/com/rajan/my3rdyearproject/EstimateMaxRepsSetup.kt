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
 * This class stores all workout data and takes in user input exercise data.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */


class EstimateMaxRepsSetup : AppCompatActivity() {

    /**
     * Getting the necessary requirements from firebase authentication about correct user.
     * Getting correct requirements about Firebase Firestore.
     * Getting values about different views which contain values.
     */

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String

    private lateinit var benchWeight: EditText
    private lateinit var squatWeight: EditText
    private lateinit var deadliftWeight: EditText
    private lateinit var pushUpReps: EditText
    private lateinit var bwSquat: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.estimate_max_reps_layout)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = mAuth.currentUser?.uid ?: ""

        benchWeight = findViewById(R.id.obtain_bench_weight)
        squatWeight = findViewById(R.id.obtain_squat_weight)
        deadliftWeight = findViewById(R.id.obtain_deadlift_weight)
        pushUpReps = findViewById(R.id.obtain_push_up_reps)
        bwSquat = findViewById(R.id.obtain_bw_squat_reps)


        val launchButton = findViewById<Button>(R.id.next_button_maxReps_page)
        launchButton.setOnClickListener { heightWeightNextButtonClick() }
    }

    /**
     * This method is to get the values entered in certain fields. We first make sure the fields
     *      are not empty, and then I store the data.
     *  I convert the data type from a string to a double to store and call it correctly.
     *  I am storing the values for bench press, deadlift, squats, push ups and bodyweight squats.
     *  I am storing base values (weight, sets and reps) for different workouts for the app.
     *  All the data is stored in Firebase if all has been inputted correctly. Otherwise there are
     *      error catches to stop the code from breaking.
     */



    private fun saveUserMaxReps() {
        // Check if the input fields are not empty
        val benchWeightText = benchWeight.text.toString()
        val squatWeightText = squatWeight.text.toString()
        val deadliftWeightText = deadliftWeight.text.toString()
        val pushUpRepsText = pushUpReps.text.toString()
        val bwSquatText = bwSquat.text.toString()

        if (benchWeightText.isNotEmpty() && squatWeightText.isNotEmpty() &&
            deadliftWeightText.isNotEmpty() && pushUpRepsText.isNotEmpty() &&
            bwSquatText.isNotEmpty()
        ) {
            try {
                // Converting EditText values to Double - google play error here
                val benchWeightValue = benchWeightText.toDouble()
                val squatWeightValue = squatWeightText.toDouble()
                val deadliftWeightValue = deadliftWeightText.toDouble()
                val pushUpRepsValue = pushUpRepsText.toDouble()
                val bwSquatValue = bwSquatText.toDouble()

                // Creating new hashmap with converted values
                val user = hashMapOf(
                    "BenchPress Weight" to benchWeightValue,
                    "Squat Weight" to squatWeightValue,
                    "Deadlift Weight" to deadliftWeightValue,
                    "PushUp Reps" to pushUpRepsValue,
                    "BodyweightSquat Reps" to bwSquatValue,

                    "BenchPress Reps" to 10.0,
                    "BenchPress Sets" to 3.0,

                    "Squat Reps" to 10.0,
                    "Squat Sets" to 3.0,

                    "Deadlift Reps" to 10.0,
                    "Deadlift Reps" to 3.0,

                    "PushUp Sets" to 3.0,
                    "BodyweightSquat Sets" to 3.0,

                    //PullDayExercises

                    "BicepCurls Weight" to 12.0,
                    "BicepCurls Sets" to 3.0,
                    "BicepCurls Reps" to 8.0,

                    "HammerCurls Weight" to 12.0,
                    "HammerCurls Sets" to 3.0,
                    "HammerCurls Reps" to 8.0,

                    "DumbbellRows Weight" to 16.0,
                    "DumbbellRows Sets" to 3.0,
                    "DumbbellRows Reps" to 6.0,

                    "TBarRows Weight" to 30.0,
                    "TBarRows Sets" to 2.0,
                    "TBarRows Reps" to 8.0,

                    "CableMachineRows Weight" to 35.0,
                    "CableMachineRows Sets" to 3.0,
                    "CableMachineRows Reps" to 10.0,

                    "LatPulldown Weight" to 35.0,
                    "LatPulldown Sets" to 3.0,
                    "LatPulldown Reps" to 10.0,

                    "PlateLoadedRows Weight" to 20.0,
                    "PlateLoadedRows Sets" to 3.0,
                    "PlateLoadedRows Reps" to 6.0,

                    //PushDayExercises

                    "InclineBenchPress Weight" to 40.0,
                    "InclineBenchPress Sets" to 3.0,
                    "InclineBenchPress Reps" to 10.0,

                    "DumbbellShoulderPress Weight" to 10.0,
                    "DumbbellShoulderPress Sets" to 3.0,
                    "DumbbellShoulderPress Reps" to 10.0,

                    "TricepCablePushdown Weight" to 20.0,
                    "TricepCablePushdown Sets" to 3.0,
                    "TricepCablePushdown Reps" to 8.0,

                    "OverheadRopeExtension Weight" to 20.0,
                    "OverheadRopeExtension Sets" to 3.0,
                    "OverheadRopeExtension Reps" to 8.0,

                    "LateralRaises Weight" to 5.0,
                    "LateralRaises Sets" to 3.0,
                    "LateralRaises Reps" to 10.0,

                    //Alternate exercises attempt

                    "KneePushUps Weight" to 0.0,
                    "KneePushUps Sets" to 3.0,
                    "KneePushUps Reps" to 10.0,

                    "InclinePushUps Weight" to 0.0,
                    "InclinePushUps Sets" to 3.0,
                    "InclinePushUps Reps" to 10.0,

                    "DeclinePushUps Weight" to 0.0,
                    "DeclinePushUps Sets" to 3.0,
                    "DeclinePushUps Reps" to 10.0,


                    "CloseGripPushUps Weight" to 0.0,
                    "CloseGripPushUps Sets" to 3.0,
                    "CloseGripPushUps Reps" to 10.0,

                    "WideGripPushUps Weight" to 0.0,
                    "WideGripPushUps Sets" to 3.0,
                    "WideGripPushUps Reps" to 10.0,

                    "DiamondPushUps Weight" to 0.0,
                    "DiamondPushUps Sets" to 3.0,
                    "DiamondPushUps Reps" to 10.0,

                    //Leg Day Exercises

                    "HackSquat Weight" to 50.0,
                    "HackSquat Sets" to 3.0,
                    "HackSquat Reps" to 10.0,

                    "LegPress Weight" to 100.0,
                    "LegPress Sets" to 3.0,
                    "LegPress Reps" to 8.0,

                    "RomanianDeadlift Weight" to 20.0,
                    "RomanianDeadlift Sets" to 3.0,
                    "RomanianDeadlift Reps" to 8.0,

                    "GobletSquat Weight" to 12.0,
                    "GobletSquat Sets" to 4.0,
                    "GobletSquat Reps" to 8.0,

                    "LegExtension Weight" to 30.0,
                    "LegExtension Sets" to 3.0,
                    "LegExtension Reps" to 8.0,

                    "LegCurl Weight" to 30.0,
                    "LegCurl Sets" to 8.0,
                    "LegCurl Reps" to 3.0

                )

                val docReference = db.collection(userId).document("EstimateMaxRepsDocument")
                docReference
                    .set(user)
                    .addOnSuccessListener {
                        Log.d(ContentValues.TAG, "New gym user info successfully")
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding/updating new gym user info", e)
                    }
            } catch (e: NumberFormatException) {
                Log.e(ContentValues.TAG, "Error converting input to Double", e)
            }
        } else {
            // Handle the case where any of the input fields is empty
            Log.e(ContentValues.TAG, "One or more input fields are empty")
        }
    }


    private fun heightWeightNextButtonClick() {
        saveUserMaxReps()
        launchUnderstandLifeSetup()
    }

    private fun launchUnderstandLifeSetup() {
        val newIntent = Intent(this, UnderstandLifeSetup::class.java)
        startActivity(newIntent)
    }
}