package com.rajan.my3rdyearproject

/**
 * @author Rajan Singh Bhamra
 * @version 2.0
 * This class is created to show the data for the gym pull day
 * Pull day = Focus on Back and Biceps
 */

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

/**
 * This data class is used to store the individual data for sets, reps and weight.
 * Each stored bit of data has it's own type.
 * A timestamp is used to show the changes over time
 */

//data class ExerciseEntry(val timestamp: Long, val sets: Int, val reps: Int, val weight: Double)

/**
 * This is the main class.
 * This is where the data is stored, retrieved, edited and displayed.
 * It is also where data is projected onto the graph.
 */

class GymLegDayMain : AppCompatActivity() {

    /**
     * This is where we intialise the database, userId and line chart.
     * We also intialise a mutable list to store the data (weight, sets and reps)
     */

    private val exerciseData: MutableMap<String, MutableList<ExerciseEntry>> = mutableMapOf()

    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String
    private lateinit var lineChart: LineChart

    /**
     * This is where we define all widgets.
     * For every exercise, we are taking in a name, weight, sets and reps.
     * There is a yes button to increase the weight, sets and reps.
     * There is a no button to decrease the weight, sets and reps.
     */

    private lateinit var exerciseOneName: TextView
    private lateinit var exerciseOneWeight: TextView
    private lateinit var exerciseOneSets: TextView
    private lateinit var exerciseOneReps: TextView
    private lateinit var exerciseOneYes: Button
    private lateinit var exerciseOneNo: Button

    private lateinit var exerciseTwoName: TextView
    private lateinit var exerciseTwoWeight: TextView
    private lateinit var exerciseTwoSets: TextView
    private lateinit var exerciseTwoReps: TextView
    private lateinit var exerciseTwoYes: Button
    private lateinit var exerciseTwoNo: Button

    private lateinit var exerciseThreeName: TextView
    private lateinit var exerciseThreeWeight: TextView
    private lateinit var exerciseThreeSets: TextView
    private lateinit var exerciseThreeReps: TextView
    private lateinit var exerciseThreeYes: Button
    private lateinit var exerciseThreeNo: Button

    private lateinit var exerciseFourName: TextView
    private lateinit var exerciseFourWeight: TextView
    private lateinit var exerciseFourSets: TextView
    private lateinit var exerciseFourReps: TextView
    private lateinit var exerciseFourYes: Button
    private lateinit var exerciseFourNo: Button

    private lateinit var exerciseFiveName: TextView
    private lateinit var exerciseFiveWeight: TextView
    private lateinit var exerciseFiveSets: TextView
    private lateinit var exerciseFiveReps: TextView
    private lateinit var exerciseFiveYes: Button
    private lateinit var exerciseFiveNo: Button

    private lateinit var exerciseSixName: TextView
    private lateinit var exerciseSixWeight: TextView
    private lateinit var exerciseSixSets: TextView
    private lateinit var exerciseSixReps: TextView
    private lateinit var exerciseSixYes: Button
    private lateinit var exerciseSixNo: Button

    /**
     * Theis is where we instantiate everything on creation.
     * There are 2 other buttons which take us back to the main page, or to a page
     *  which explains all the back day workouts.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gym_leg_day_main)

        db = FirebaseFirestore.getInstance()
        userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        lineChart = findViewById(R.id.lineChart)


        val legsPushButton = findViewById<Button>(R.id.BackToGymFragLegs)
        legsPushButton.setOnClickListener { v -> backToGymFragment(v) }

        val understandLegDay = findViewById<Button>(R.id.toUnderstandingLegDay)
        understandLegDay.setOnClickListener { v -> goToUnderstandLegDay(v) }

        /**
         * This is where we assign an id from the xml file to the code file.
         * This is repeated for all 6 exercises.
         */

        exerciseOneName = findViewById(R.id.exerciseOneName)
        exerciseOneWeight = findViewById(R.id.exerciseOneWeight)
        exerciseOneSets = findViewById(R.id.exerciseOneSets)
        exerciseOneReps = findViewById(R.id.exerciseOneReps)
        exerciseOneYes = findViewById(R.id.exerciseOneYes)
        exerciseOneNo = findViewById(R.id.exerciseOneNo)
        exerciseOneYes.setOnClickListener {
            updateExerciseData("HackSquat", true)
        }
        exerciseOneNo.setOnClickListener {
            updateExerciseData("HackSquat", false)
        }


        exerciseTwoName = findViewById(R.id.exerciseTwoName)
        exerciseTwoWeight = findViewById(R.id.exerciseTwoWeight)
        exerciseTwoSets = findViewById(R.id.exerciseTwoSets)
        exerciseTwoReps = findViewById(R.id.exerciseTwoReps)
        exerciseTwoYes = findViewById(R.id.exerciseTwoYes)
        exerciseTwoNo = findViewById(R.id.exerciseTwoNo)
        exerciseTwoYes.setOnClickListener {
            updateExerciseData("LegPress", true)
        }
        exerciseTwoNo.setOnClickListener {
            updateExerciseData("LegPress", false)
        }


        exerciseThreeName = findViewById(R.id.exerciseThreeName)
        exerciseThreeWeight = findViewById(R.id.exerciseThreeWeight)
        exerciseThreeSets = findViewById(R.id.exerciseThreeSets)
        exerciseThreeReps = findViewById(R.id.exerciseThreeReps)
        exerciseThreeYes = findViewById(R.id.exerciseThreeYes)
        exerciseThreeNo = findViewById(R.id.exerciseThreeNo)
        exerciseThreeYes.setOnClickListener {
            updateExerciseData("RomanianDeadlift", true)
        }
        exerciseThreeNo.setOnClickListener {
            updateExerciseData("RomanianDeadlift", false)
        }


        exerciseFourName = findViewById(R.id.exerciseFourName)
        exerciseFourWeight = findViewById(R.id.exerciseFourWeight)
        exerciseFourSets = findViewById(R.id.exerciseFourSets)
        exerciseFourReps = findViewById(R.id.exerciseFourReps)
        exerciseFourYes = findViewById(R.id.exerciseFourYes)
        exerciseFourNo = findViewById(R.id.exerciseFourNo)
        exerciseFourYes.setOnClickListener {
            updateExerciseData("GobletSquat", true)
        }
        exerciseFourNo.setOnClickListener {
            updateExerciseData("GobletSquat", false)
        }


        exerciseFiveName = findViewById(R.id.exerciseFiveName)
        exerciseFiveWeight = findViewById(R.id.exerciseFiveWeight)
        exerciseFiveSets = findViewById(R.id.exerciseFiveSets)
        exerciseFiveReps = findViewById(R.id.exerciseFiveReps)
        exerciseFiveYes = findViewById(R.id.exerciseFiveYes)
        exerciseFiveNo = findViewById(R.id.exerciseFiveNo)
        exerciseFiveYes.setOnClickListener {
            updateExerciseData("LegExtension", true)
        }
        exerciseFiveNo.setOnClickListener {
            updateExerciseData("LegExtension", false)
        }


        exerciseSixName = findViewById(R.id.exerciseSixName)
        exerciseSixWeight = findViewById(R.id.exerciseSixWeight)
        exerciseSixSets = findViewById(R.id.exerciseSixSets)
        exerciseSixReps = findViewById(R.id.exerciseSixReps)
        exerciseSixYes = findViewById(R.id.exerciseSixYes)
        exerciseSixNo = findViewById(R.id.exerciseSixNo)
        exerciseSixYes.setOnClickListener {
            updateExerciseData("LegCurl", true)
        }
        exerciseSixNo.setOnClickListener {
            updateExerciseData("LegCurl", false)
        }

        loadAndProcessData()
    }


    /**
     * This is where I update and edit the data. The data is stored in:
     *  collection: userID, document:EstimateMaxRepsDocument.
     * I have stored it in by havging the exercise name and then the weight, sets and reps.
     * If completed, it raises teh weight, sets and reps. If not completed, then the opposite.
     */

    private fun updateExerciseData(exerciseName: String, completed: Boolean) {
        db.collection(userId)
            .document("EstimateMaxRepsDocument").get()
            .addOnSuccessListener { documentSnapshot ->
                var weight = documentSnapshot.getDouble("$exerciseName Weight") ?: 0.0
                var sets = documentSnapshot.getLong("$exerciseName Sets")?.toInt() ?: 2
                var reps = documentSnapshot.getLong("$exerciseName Reps")?.toInt() ?: 8

                if (completed) {
                    reps = if (reps < 14) reps + 2 else 8
                    sets = if (sets < 4) sets + 1 else 2
                    weight += 5
                } else {
                    if (reps > 8) {
                        reps -= 4
                        weight -= 5
                    }
                }


                /**
                 * This code block adds to the graph by storing separate values. it stores separate
                 * values by recording the time at each interval (yes or no button click).
                 *
                 * "entry" creates a new instance of the name, weight, set and reps.
                 * It is then added to teh data set created above.
                 * If these values do not exist, it initialises an empty list and associates it
                 * with the exerciseName key in the map.
                 */

                // Add new entry to exerciseData
                val currentTime = System.currentTimeMillis()
                val entry = ExerciseEntry(currentTime, sets, reps, weight)
                if (!exerciseData.containsKey(exerciseName)) {
                    exerciseData[exerciseName] = mutableListOf()
                }
                exerciseData[exerciseName]?.add(entry)

                // Update UI and chart
                updateUI(exerciseName, weight, sets, reps)
                updateLineChart(weight, sets, reps, exerciseName)

                /**
                 * This code gets the weight, sets and reps from the firestore document.
                 * It uses a hashmap.
                 *
                 * It then updates and replaces the old values with the new values by
                 * rewriting them.
                 */

                val userUpdates = hashMapOf<String, Any>(
                    "$exerciseName Weight" to weight,
                    "$exerciseName Sets" to sets,
                    "$exerciseName Reps" to reps
                )

                db.collection(userId)
                    .document("EstimateMaxRepsDocument")
                    .update(userUpdates)
                    .addOnSuccessListener {
                        Log.d("MainActivity", "$exerciseName weight and reps updated successfully")
                        updateUI(exerciseName, weight, sets, reps)
                        //updateLineChart(weight, sets, reps)
                        updateLineChart(weight, sets, reps, exerciseName)
                    }
                    .addOnFailureListener { e ->
                        Log.w("MainActivity", "Error updating $exerciseName weight and reps", e)
                    }
            }
            .addOnFailureListener { e ->
                Log.w("MainActivity", "Error loading data from Firebase", e)
            }
    }


    /**
     * This method updates the UI.
     * It stores what each exercise name is and where the each bit of into should be stored.
     */

    private fun updateUI(exerciseName: String, weight: Double, sets: Int, reps: Int) {
        when (exerciseName) {
            "HackSquat" -> {
                exerciseOneWeight.text = "Weight: $weight"
                exerciseOneSets.text = "Sets: $sets"
                exerciseOneReps.text = "Reps: $reps"
            }

            "LegPress" -> {
                exerciseTwoWeight.text = "Weight: $weight"
                exerciseTwoSets.text = "Sets: $sets"
                exerciseTwoReps.text = "Reps: $reps"
            }

            "RomanianDeadlift" -> {
                exerciseThreeWeight.text = "Weight: $weight"
                exerciseThreeSets.text = "Sets: $sets"
                exerciseThreeReps.text = "Reps: $reps"
            }

            "GobletSquat" -> {
                exerciseFourWeight.text = "Weight: $weight"
                exerciseFourSets.text = "Sets: $sets"
                exerciseFourReps.text = "Reps: $reps"
            }

            "LegExtension" -> {
                exerciseFiveWeight.text = "Weight: $weight"
                exerciseFiveSets.text = "Sets: $sets"
                exerciseFiveReps.text = "Reps: $reps"
            }

            "LegCurl" -> {
                exerciseSixWeight.text = "Weight: $weight"
                exerciseSixSets.text = "Sets: $sets"
                exerciseSixReps.text = "Reps: $reps"
            }
        }
    }

    /**
     * This method is called to update the exercise data.
     * It happens when the completed workout flag is set to true.
     * It specialises between each workout so not all are updated at the same time.
     */

    private fun loadAndProcessData() {
        updateExerciseData("HackSquat", true)
        updateExerciseData("LegPress", true)
        updateExerciseData("RomanianDeadlift", true)
        updateExerciseData("GobletSquat", true)
        updateExerciseData("LegExtension", true)
        updateExerciseData("LegCurl", true)
    }

    /**
     * This method is used to update the line chart.
     * It acts as an area chart.
     */

    private fun updateLineChart(weight: Double, sets: Int, reps: Int, exerciseName: String) {
        val areaChart = findViewById<LineChart>(R.id.lineChart)

        /**
         * This code gets all the historical data from each exercise name.
         * From each exercise name, it gets the historical data from the weight,
         *  sets and reps differently.
         * It then converts it all to a float to be displayed.
         */

        // Get historical data for the exercise
        val entriesWeight = exerciseData[exerciseName]?.mapIndexed { index, entry ->
            Entry(index.toFloat(), entry.weight.toFloat())
        } ?: emptyList()

        val entriesSets = exerciseData[exerciseName]?.mapIndexed { index, entry ->
            Entry(index.toFloat(), entry.sets.toFloat())
        } ?: emptyList()

        val entriesReps = exerciseData[exerciseName]?.mapIndexed { index, entry ->
            Entry(index.toFloat(), entry.reps.toFloat())
        } ?: emptyList()

        // Create line datasets for weight, sets, and reps
        val dataSetWeight = LineDataSet(entriesWeight, "Weight")
        val dataSetSets = LineDataSet(entriesSets, "Sets")
        val dataSetReps = LineDataSet(entriesReps, "Reps")

        /**
         * This defines the different colours for each exercise.
         * This is for looks and so they can all be distinguished.
         * It also edits the linear option and the space legnth between each update.
         * This is so the weight is solid and teh reps + sets are broken up to show importance.
         */

        // Define the color for each exercise
        val lineColor = when (exerciseName) {
            "HackSquat" -> Color.BLUE
            "LegPress" -> Color.RED
            "RomanianDeadlift" -> Color.GREEN
            "GobletSquat" -> Color.MAGENTA
            "LegExtension" -> Color.rgb(255, 105, 180) // Pink
            "LegCurl" -> Color.rgb(165, 42, 42) // Brown
            else -> Color.BLACK
        }

        // Customizing line appearance for each data series
        dataSetWeight.color = lineColor
        dataSetWeight.lineWidth = 2f
        dataSetWeight.mode = LineDataSet.Mode.LINEAR

        dataSetSets.color = lineColor
        dataSetSets.lineWidth = 2f
        dataSetSets.mode = LineDataSet.Mode.LINEAR
        dataSetSets.enableDashedLine(10f, 10f, 0f)

        dataSetReps.color = lineColor
        dataSetReps.lineWidth = 2f
        dataSetReps.mode = LineDataSet.Mode.LINEAR
        dataSetReps.enableDashedLine(10f, 10f, 0f)

        /**
         * This code adds in the data set to the graph.
         * It also sets the data onto the chart using methods from the API.
         * Here is also where we set the description and touch interface to allow it.
         */

        // Assemble datasets
        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(dataSetWeight)
        dataSets.add(dataSetSets)
        dataSets.add(dataSetReps)

        // Set data to chart
        val lineData = LineData(dataSets)
        areaChart.data = lineData

        // Set description and enable touch/zoom features
        val description = Description()
        description.text = "Pull Day Statistics"
        areaChart.description = description
        areaChart.setTouchEnabled(true)
        areaChart.isDragEnabled = true
        areaChart.setScaleEnabled(true)
        areaChart.setPinchZoom(true)

        // Refresh chart
        areaChart.invalidate()
    }


    /**
     * These methods are for the top 2 buttons.
     * One takes the user to the logged in home page.
     * The other takes them to a page that explains how to do each workout with a video.
     */

    private fun backToGymFragment(view: View) {
        val newIntent = Intent(this, AppBarSetup::class.java)
        startActivity(newIntent)
    }

    private fun goToUnderstandLegDay(view: View) {
        val newIntent = Intent(this, HowToLegDay::class.java)
        startActivity(newIntent)
    }

}
