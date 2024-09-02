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
 * Class for setting up the understanding the user's life page in the 3rd year project.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class UnderstandLifeSetup : AppCompatActivity() {

    /**
     * I get the necessary conditions for Firebase Authentication.
     * I get the necessary conditions for Firebase Firestore too.
     * All EditTexts and Buttons are called in correctly.
     */

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String

    private lateinit var switchNoEquipment: Switch
    private lateinit var switchSomeEquipment: Switch
    private lateinit var switchAllEquipment: Switch

    private lateinit var switchLittleFreeTime: Switch
    private lateinit var switchModerateFreeTime: Switch
    private lateinit var switchLotOfFreeTime: Switch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.understand_your_life_layout)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = mAuth.currentUser?.uid ?: ""

        switchNoEquipment = findViewById(R.id.switchNoEquipment)
        switchSomeEquipment = findViewById(R.id.switchSomeEquipment)
        switchAllEquipment = findViewById(R.id.switchAllEquipment)

        switchLittleFreeTime = findViewById(R.id.switchLittleFreeTime)
        switchModerateFreeTime = findViewById(R.id.switchModerateFreeTime)
        switchLotOfFreeTime = findViewById(R.id.switchLotOfFreeTime)

        val launchButton = findViewById<Button>(R.id.toOverview_understand_life_page)
        launchButton.setOnClickListener { understandLifeButtonClick() }

    }

    /**
     *  This method is where I save the user life info.
     *  First i am changing the data types. I am converting from a switch to
     *      a boolean (see if it is checked or not).
     *  I am then creating a hashmap of all values.
     *  Those values are then saved to Firebase.
     *  The collection name is userId / currentUser. The document ID is UnderstandLifeDocument.
     */

    private fun saveUserLifeInfo() {
        val user = hashMapOf(
            "No Equipment" to switchNoEquipment.isChecked,
            "Some Equipment" to switchSomeEquipment.isChecked,
            "All Equipment" to switchAllEquipment.isChecked,

            "Little Free Time" to switchLittleFreeTime.isChecked,
            "Moderate Free Time" to switchModerateFreeTime.isChecked,
            "A Lot Of Free Time" to switchLotOfFreeTime.isChecked
        )

        val docReference = db.collection(userId).document("UnderstandLifeDocument")
        docReference
            .set(user)
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "New gym user info successfully")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding/updating new gym user info", e)
            }
    }

    private fun understandLifeButtonClick() {
        launchAppBarSetup()
        saveUserLifeInfo()
    }

    private fun launchAppBarSetup() {
        val newIntent = Intent(this, AppBarSetup::class.java)
        startActivity(newIntent)
    }


}