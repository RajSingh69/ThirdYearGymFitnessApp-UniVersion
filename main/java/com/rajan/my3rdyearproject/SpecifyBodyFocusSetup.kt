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

class SpecifyBodyFocusSetup : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String

    private lateinit var switchBack: Switch
    private lateinit var switchShoulders: Switch
    private lateinit var switchChest: Switch
    private lateinit var switchArms: Switch
    private lateinit var switchButtocks: Switch
    private lateinit var switchAbdominals: Switch
    private lateinit var switchLegs: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.specify_body_focus_areas)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = mAuth.currentUser?.uid ?: ""

        switchBack = findViewById(R.id.switchBack)
        switchShoulders = findViewById(R.id.switchShoulders)
        switchChest = findViewById(R.id.switchChest)
        switchArms = findViewById(R.id.switchArms)
        switchButtocks = findViewById(R.id.switchButtocks)
        switchAbdominals = findViewById(R.id.switchAbdominals)
        switchLegs = findViewById(R.id.switchLegs)

        val launchButton = findViewById<Button>(R.id.next_button_bodyFocus_page)
        launchButton.setOnClickListener { bodyFocusNextButtonClick() }
    }

    private fun saveUserBodyFocusInfo() {
        val user = hashMapOf(
            "Back Focus" to switchBack.isChecked,
            "Shoulder Focus" to switchShoulders.isChecked,
            "Chest Focus" to switchChest.isChecked,
            "Arms Focus" to switchArms.isChecked,
            "Buttocks Focus" to switchButtocks.isChecked,
            "Abdominal Focus" to switchAbdominals.isChecked,
            "Legs Focus" to switchLegs.isChecked
        )

        val docReference = db.collection(userId).document("BodyAreaFocusDocument")
        docReference
            .set(user)
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "New gym user info successfully")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding/updating new gym user info", e)
            }
    }

    private fun bodyFocusNextButtonClick() {
        saveUserBodyFocusInfo()
        launchHeightWeightSetup()
    }

    private fun launchHeightWeightSetup() {
        val newIntent = Intent(this, SpecifyHeightWeightSetup::class.java)
        startActivity(newIntent)
    }
}
