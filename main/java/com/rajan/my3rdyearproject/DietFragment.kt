package com.rajan.my3rdyearproject

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Diet fragment on the 3rd year project app.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class DietFragment : Fragment() {

    private lateinit var foodRecyclerView: Button
    private lateinit var currentWeightTextView: TextView
    private lateinit var targetWeightTextView: TextView
    private lateinit var currentCaloriesTextView: TextView
    private lateinit var currentCarbsTextView: TextView
    private lateinit var currentProteinTextView: TextView
    private lateinit var currentFatsTextView: TextView
    private lateinit var currentSugarsTextView: TextView
    private lateinit var currentFiberTextView: TextView

    private lateinit var submitFoodEaten: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_diet, container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = mAuth.currentUser?.uid ?: ""

        foodRecyclerView = view.findViewById(R.id.toFoodPages)
        currentWeightTextView = view.findViewById(R.id.currentWeight)
        targetWeightTextView = view.findViewById(R.id.targetWeight)
        currentCaloriesTextView = view.findViewById(R.id.currentCalories)
        currentCarbsTextView = view.findViewById(R.id.currentCarbs)
        currentProteinTextView = view.findViewById(R.id.currentProtein)
        currentFatsTextView = view.findViewById(R.id.currentFats)
        currentSugarsTextView = view.findViewById(R.id.currentSugars)
        currentFiberTextView = view.findViewById(R.id.currentFiber)

        submitFoodEaten = view.findViewById(R.id.submitFoodEaten)

        foodRecyclerView.setOnClickListener { submitFoodRecyclerClick() }

        submitFoodEaten.setOnClickListener { fetchAndUpdateChickenValues() }

        fetchWeightDocumentInfo()
        fetchNutritionDocumentInfo()

    }

    /**
     * In this method, I fetch all the existing values from firebase and store it here.
     * I am fetching the height and weight values.
     */

    private fun fetchWeightDocumentInfo() {

        db.collection(userId)
            .document("HeightWeightDocument")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val currentWeight = document.getDouble("Current Weight") ?: 0.0
                    val targetWeight = document.getDouble("Target Weight") ?: 0.0
                    currentWeightTextView.text = "Current Weight: $currentWeight"
                    targetWeightTextView.text = "Target Weight: $targetWeight"
                } else {
                    Log.w(ContentValues.TAG, "Error adding/updating info")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error adding/updating info")
            }
    }

    /**
     * In this method, I fetch all the existing values from firebase and store it here.
     * I get teh calories, fats, sugars, proteins, carbs and fiber.
     * I then display these in the textview so the user can see it.
     */


    private fun fetchNutritionDocumentInfo() {

        // Fetching gym goal information
        db.collection(userId)
            .document("GymGoalDocument")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val currentCalories = document.getDouble("Current Calories") ?: 0.0
                    val currentCarbs = document.getDouble("Current Carbs") ?: 0.0
                    val currentProtein = document.getDouble("Current Protein") ?: 0.0
                    val currentFats = document.getDouble("Current Fats") ?: 0.0
                    val currentSugars = document.getDouble("Current Sugars") ?: 0.0
                    val currentFiber = document.getDouble("Current Fiber") ?: 0.0

                    currentCaloriesTextView.text = "Current Calories: $currentCalories"
                    currentCarbsTextView.text = "Current Carbs: $currentCarbs"
                    currentProteinTextView.text = "Current Protein: $currentProtein"
                    currentFatsTextView.text = "Current Fats: $currentFats"
                    currentSugarsTextView.text = "Current Sugars: $currentSugars"
                    currentFiberTextView.text = "Current Fiber: $currentFiber"
                } else {
                    Log.w(ContentValues.TAG, "Error adding/updating info")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error adding/updating info")
            }

    }

    /**
     * In this method, I am updating the nutrition values assuming a
     *  user has eaten chicken.
     *  First, I parse current values from TextViews. I then calculate new total values.
     *  I then update TextViews with new total values.
     */


    private fun fetchAndUpdateChickenValues() {
        db.collection("foodHealthyStuff")
            .document("chicken")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val caloriesToAdd = document.getDouble("Calories") ?: 0.0
                    val carbsToAdd = document.getDouble("Carbs") ?: 0.0
                    val proteinToAdd = document.getDouble("Protein") ?: 0.0
                    val fatsToAdd = document.getDouble("Fats") ?: 0.0
                    val sugarsToAdd = document.getDouble("Sugars") ?: 0.0
                    val fiberToAdd = document.getDouble("Fiber") ?: 0.0

                    val currentCalories = currentCaloriesTextView.text.toString().toDoubleOrNull() ?: 0.0
                    val currentCarbs = currentCarbsTextView.text.toString().toDoubleOrNull() ?: 0.0
                    val currentProtein = currentProteinTextView.text.toString().toDoubleOrNull() ?: 0.0
                    val currentFats = currentFatsTextView.text.toString().toDoubleOrNull() ?: 0.0
                    val currentSugars = currentSugarsTextView.text.toString().toDoubleOrNull() ?: 0.0
                    val currentFiber = currentFiberTextView.text.toString().toDoubleOrNull() ?: 0.0

                    val newCalories = currentCalories + caloriesToAdd
                    val newCarbs = currentCarbs + carbsToAdd
                    val newProtein = currentProtein + proteinToAdd
                    val newFats = currentFats + fatsToAdd
                    val newSugars = currentSugars + sugarsToAdd
                    val newFiber = currentFiber + fiberToAdd

                    currentCaloriesTextView.text = "Current Calories: $newCalories"
                    currentCarbsTextView.text = "Current Carbs: $newCarbs"
                    currentProteinTextView.text = "Current Protein: $newProtein"
                    currentFatsTextView.text = "Current Fats: $newFats"
                    currentSugarsTextView.text = "Current Sugars: $newSugars"
                    currentFiberTextView.text = "Current Fiber: $newFiber"
                } else {
                    Log.w(ContentValues.TAG, "Error adding/updating info")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error adding/updating info")
            }

    }

    private fun submitFoodRecyclerClick() {
        val newIntent = Intent(requireContext(), AppBarFoodSetup::class.java)
        startActivity(newIntent)
    }
}
