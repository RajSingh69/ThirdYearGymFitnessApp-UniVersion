package com.rajan.my3rdyearproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Class for backend of the overview fragment.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class OverviewFragment : Fragment() {

    private lateinit var firestore: FirebaseFirestore

    private lateinit var emailTextView: TextView
    private lateinit var passwordTextView: TextView

    private lateinit var bodyAreaFocusTextView: TextView
    private lateinit var gymGoalsTextView: TextView
    private lateinit var heightWeightTextView: TextView
    private lateinit var estimateMaxRepsTextView: TextView
    private lateinit var understandLifeTextView: TextView

    private lateinit var editUserPersonalInfo: Button

    /**
     * I get the necessary conditions for Firebase Authentication.
     * I get the necessary conditions for Firebase Firestore too.
     * All EditTexts and Buttons are called in correctly.
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        emailTextView = view.findViewById(R.id.emailTextView)
        passwordTextView = view.findViewById(R.id.passwordTextView)
        bodyAreaFocusTextView = view.findViewById(R.id.bodyAreaFocusTextView)
        gymGoalsTextView = view.findViewById(R.id.gymGoalsTextView)
        estimateMaxRepsTextView = view.findViewById(R.id.estimateMaxRepsTextView)

        heightWeightTextView = view.findViewById(R.id.heightWeightTextView)
        understandLifeTextView = view.findViewById(R.id.understandLifeTextView)

        editUserPersonalInfo = view.findViewById(R.id.editUserPersonalInfo)
        editUserPersonalInfo.setOnClickListener { v -> launchUserPersonalInfo(v) }

        firestore = FirebaseFirestore.getInstance()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataFromFirebase()
    }

    /**
     * In this method, I am loading a few different documents from firebase. The documents are:
     * - UserInfoDocument
     * - BodyAreaFocusDocument
     * - GymGoalDocument
     * - HeightWeightDocument
     * - EstimateMaxRepsDocument
     * - UnderstandLifeDocument
     *
     * I am calling the data for each document and storing it inside a specified text box.
     * The IDs are set at the top.
     */

    private fun loadDataFromFirebase() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let { user ->
            val userId = user.uid

            val documentRef = firestore.collection(userId)
                .document("UserInfoDocument")

            documentRef.get().addOnSuccessListener { documentSnapshot ->
                val email = documentSnapshot.getString("Email") ?: ""
                val password = documentSnapshot.getString("Password") ?: ""

                emailTextView.text = "Email: $email"
                passwordTextView.text = "Password: $password"
            }

            firestore.collection(userId)
                .document("BodyAreaFocusDocument").get()
                .addOnSuccessListener { documentSnapshot ->
                    val abdominalFocus = documentSnapshot.getBoolean("Abdominal Focus") ?: false
                    val armsFocus = documentSnapshot.getBoolean("Arms Focus") ?: false
                    val backFocus = documentSnapshot.getBoolean("Back Focus") ?: false
                    val buttocksFocus = documentSnapshot.getBoolean("Buttocks Focus") ?: false
                    val chestFocus = documentSnapshot.getBoolean("Chest Focus") ?: false
                    val legsFocus = documentSnapshot.getBoolean("Legs Focus") ?: false
                    val shoulderFocus = documentSnapshot.getBoolean("Shoulder Focus") ?: false

                    val bodyAreaFocusText = "Body Area Focus:\n" +
                            "Abdominal: $abdominalFocus\n" +
                            "Arms: $armsFocus\n" +
                            "Back: $backFocus\n" +
                            "Buttocks: $buttocksFocus\n" +
                            "Chest: $chestFocus\n" +
                            "Legs: $legsFocus\n" +
                            "Shoulder: $shoulderFocus"
                    bodyAreaFocusTextView.text = bodyAreaFocusText
                }

            firestore.collection(userId)
                .document("GymGoalDocument").get()
                .addOnSuccessListener { documentSnapshot ->
                    val enduranceFocus = documentSnapshot.getBoolean("Endurance Focus") ?: false
                    val muscleGainFocus = documentSnapshot.getBoolean("Muscle Gain Focus") ?: false
                    val strengthFocus = documentSnapshot.getBoolean("Strength Focus") ?: false
                    val toneUpFocus = documentSnapshot.getBoolean("Tone Up Focus") ?: false

                    val gymGoalsText = "Gym Goals:\n" +
                            "Endurance: $enduranceFocus\n" +
                            "Muscle Gain: $muscleGainFocus\n" +
                            "Strength: $strengthFocus\n" +
                            "Tone Up: $toneUpFocus"
                    gymGoalsTextView.text = gymGoalsText
                }

            firestore.collection(userId)
                .document("HeightWeightDocument").get()
                .addOnSuccessListener { documentSnapshot ->
                    val currentHeight = documentSnapshot.getDouble("Current Height") ?: 0.0
                    val currentWeight = documentSnapshot.getDouble("Current Weight") ?: 0.0
                    val targetWeight = documentSnapshot.getDouble("Target Weight") ?: 0.0

                    val currentHeightStr = currentHeight.toString()
                    val currentWeightStr = currentWeight.toString()
                    val targetWeightStr = targetWeight.toString()

                    val heightWeightText = "Height and Weight:\n" +
                            "Current Height: $currentHeightStr\n" +
                            "Current Weight: $currentWeightStr\n" +
                            "Target Weight: $targetWeightStr"
                    heightWeightTextView.text = heightWeightText
                }


                firestore.collection(userId)
                    .document("EstimateMaxRepsDocument").get()
                    .addOnSuccessListener { documentSnapshot ->
                        val benchWeight = documentSnapshot.getDouble("Bench Weight") ?: 0.0
                        val squatWeight = documentSnapshot.getDouble("Squat Weight") ?: 0.0
                        val deadliftWeight = documentSnapshot.getDouble("Deadlift Weight") ?: 0.0
                        val pushUpReps = documentSnapshot.getDouble("Push Up Reps") ?: 0.0
                        val bwSquat = documentSnapshot.getDouble("BodyWeight Squat Reps") ?: 0.0

                        val benchWeightStr = benchWeight.toString()
                        val squatWeightStr = squatWeight.toString()
                        val deadliftWeightStr = deadliftWeight.toString()
                        val pushUpRepsStr = pushUpReps.toString()
                        val bwSquatStr = bwSquat.toString()

                        val estMaxRepsText = "Current Weight Lifted:\n" +
                                "Bench 5 Rep Max: $benchWeightStr\n" +
                                "Squat 5 Rep Max: $squatWeightStr\n" +
                                "Deadlift Weight: $deadliftWeightStr\n" +
                                "Max 1 Set Push-Ups: $pushUpRepsStr\n" +
                                "Max 1 Set Bodyweight-Squat: $bwSquatStr"

                        estimateMaxRepsTextView.text = estMaxRepsText
                    }

            firestore.collection(userId)
                .document("UnderstandLifeDocument").get()
                .addOnSuccessListener { documentSnapshot ->
                    val noEquipment = documentSnapshot.getBoolean("No Equipment") ?: false
                    val someEquipment = documentSnapshot.getBoolean("Some Equipment") ?: false
                    val allEquipment = documentSnapshot.getBoolean("All Equipment") ?: false
                    val littleFreeTime = documentSnapshot.getBoolean("Little Free Time") ?: false
                    val moderateFreeTime = documentSnapshot.getBoolean("Moderate Free Time") ?: false
                    val lotOfFreeTime = documentSnapshot.getBoolean("A Lot Of Free Time") ?: false

                    val understandLifeText = "Understand Life:\n" +
                            "No Equipment: $noEquipment\n" +
                            "Some Equipment: $someEquipment\n" +
                            "All Equipment: $allEquipment\n" +
                            "Little Free Time: $littleFreeTime\n" +
                            "Moderate Free Time: $moderateFreeTime\n" +
                            "A Lot Of Free Time: $lotOfFreeTime"
                    understandLifeTextView.text = understandLifeText
                }
        }
    }

    private fun launchUserPersonalInfo(view: View) {
        val newIntent = Intent(requireContext(), SpecifyGenderSetup::class.java)
        startActivity(newIntent)
    }
}
