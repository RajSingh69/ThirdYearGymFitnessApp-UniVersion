package com.rajan.my3rdyearproject

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage

/**
 * This class is about explaining workout information for the "Leg Day" workouts.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class HowToLegDay: AppCompatActivity() {

    /**
     * In this class, I am calling in any references to Firebase.
     * This is because I have the required videos stored in FirebaseStorage.
     * For each leg workout, there is a method for calling the video
     *  and a method for calling the text.
     */

    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.how_to_leg_day)

        val backToLegsMain = findViewById<Button>(R.id.toLegDayExercise)
        backToLegsMain.setOnClickListener { v -> backToLegDay(v) }

        hackSquatVideo()
        hackSquatInfoLayout()

        legPressVideo()
        legPressInfoLayout()

        romanianDeadliftVideo()
        romanianDeadliftInfoLayout()

        gobletSquatVideo()
        gobletSquatInfoLayout()

        legExtensionVideo()
        legExtensionInfoLayout()

        legCurlVideo()
        legCurlInfoLayout()

    }

    private fun backToLegDay(view: View) {
        val newIntent = Intent(this, GymLegDayMain::class.java)
        startActivity(newIntent)
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Hack Squats.
     */

    private fun hackSquatVideo() {

        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Hack Squat.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.HackSquatVideo)
            videoView.setVideoURI(uri)

            var isPlaying = false
            videoView.setOnClickListener {
                if (isPlaying) {
                    videoView.pause()
                } else {
                    videoView.start()
                }
                isPlaying = !isPlaying
            }

            videoView.setOnCompletionListener {
                videoView.seekTo(0)
                videoView.start()
            }

        }.addOnFailureListener { exception ->
            // Handle any errors that may occur while getting the download URL
            Log.e("VideoDownload", "Video download failed", exception)
        }
    }

    private fun hackSquatInfoLayout() {
        val textView: TextView = findViewById(R.id.HackSquatEquipment)
        val text = getString(R.string.hack_Squat_Info)

        val spannableString = SpannableString(text)

        val nameStart = text.indexOf("Name:")
        val nameEnd = nameStart + "Name:".length
        val equipmentStart = text.indexOf("Equipment:")
        val equipmentEnd = equipmentStart + "Equipment:".length

        val focusAreaStart = text.indexOf("Focus area:")
        val focusAreaEnd = focusAreaStart + "Focus area:".length

        val preparationStart = text.indexOf("Preparation:")
        val preparationEnd = preparationStart + "Preparation:".length
        val keyTipsStart = text.indexOf("Key Tips:")
        val keyTipsEnd = keyTipsStart + "Key Tips:".length

        // Apply bold and italic style to specific words
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), nameStart, nameEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), focusAreaStart, focusAreaEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set gravity to center only for specific words
        textView.gravity = Gravity.LEFT
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Leg Press.
     */

    private fun legPressVideo() {

        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Leg Press.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.LegPressVideo)
            videoView.setVideoURI(uri)

            var isPlaying = false
            videoView.setOnClickListener {
                if (isPlaying) {
                    videoView.pause()
                } else {
                    videoView.start()
                }
                isPlaying = !isPlaying
            }

            videoView.setOnCompletionListener {
                videoView.seekTo(0)
                videoView.start()
            }

        }.addOnFailureListener { exception ->
            // Handle any errors that may occur while getting the download URL
            Log.e("VideoDownload", "Video download failed", exception)
        }
    }

    private fun legPressInfoLayout() {
        val textView: TextView = findViewById(R.id.LegPressEquipment)
        val text = getString(R.string.leg_Press_Info)

        // Create a SpannableString
        val spannableString = SpannableString(text)

        // Find the start and end indices of bold and italic parts
        val nameStart = text.indexOf("Name:")
        val nameEnd = nameStart + "Name:".length
        val equipmentStart = text.indexOf("Equipment:")
        val equipmentEnd = equipmentStart + "Equipment:".length

        val focusAreaStart = text.indexOf("Focus area:")
        val focusAreaEnd = focusAreaStart + "Focus area:".length

        val preparationStart = text.indexOf("Preparation:")
        val preparationEnd = preparationStart + "Preparation:".length
        val keyTipsStart = text.indexOf("Key Tips:")
        val keyTipsEnd = keyTipsStart + "Key Tips:".length

        // Apply bold and italic style to specific words
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), nameStart, nameEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), focusAreaStart, focusAreaEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set gravity to center only for specific words
        textView.gravity = Gravity.LEFT

        // Set the text for TextView
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Romanian DeadLifts.
     */

    private fun romanianDeadliftVideo() {

        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj RDLs.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.RomanianDeadliftVideo)
            videoView.setVideoURI(uri)

            var isPlaying = false
            videoView.setOnClickListener {
                if (isPlaying) {
                    videoView.pause()
                } else {
                    videoView.start()
                }
                isPlaying = !isPlaying
            }

            videoView.setOnCompletionListener {
                videoView.seekTo(0)
                videoView.start()
            }

        }.addOnFailureListener { exception ->
            Log.e("VideoDownload", "Video download failed", exception)
        }
    }

    private fun romanianDeadliftInfoLayout() {
        val textView: TextView = findViewById(R.id.RomanianDeadliftEquipment)
        val text = getString(R.string.romanian_Deadlift_Info)

        // Create a SpannableString
        val spannableString = SpannableString(text)

        // Find the start and end indices of bold and italic parts
        val nameStart = text.indexOf("Name:")
        val nameEnd = nameStart + "Name:".length
        val equipmentStart = text.indexOf("Equipment:")
        val equipmentEnd = equipmentStart + "Equipment:".length

        val focusAreaStart = text.indexOf("Focus area:")
        val focusAreaEnd = focusAreaStart + "Focus area:".length

        val preparationStart = text.indexOf("Preparation:")
        val preparationEnd = preparationStart + "Preparation:".length
        val keyTipsStart = text.indexOf("Key Tips:")
        val keyTipsEnd = keyTipsStart + "Key Tips:".length

        // Apply bold and italic style to specific words
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), nameStart, nameEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), focusAreaStart, focusAreaEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.gravity = Gravity.LEFT
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Goblet Squats.
     */

    private fun gobletSquatVideo() {
        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Goblet Squat.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.GobletSquatVideo)
            videoView.setVideoURI(uri)

            var isPlaying = false
            videoView.setOnClickListener {
                if (isPlaying) {
                    videoView.pause()
                } else {
                    videoView.start()
                }
                isPlaying = !isPlaying
            }

            videoView.setOnCompletionListener {
                videoView.seekTo(0)
                videoView.start()
            }

        }.addOnFailureListener { exception ->
            Log.e("VideoDownload", "Video download failed", exception)
        }
    }

    private fun gobletSquatInfoLayout() {
        val textView: TextView = findViewById(R.id.GobletSquatEquipment)
        val text = getString(R.string.goblet_Squat_Info)

        // Create a SpannableString
        val spannableString = SpannableString(text)

        // Find the start and end indices of bold and italic parts
        val nameStart = text.indexOf("Name:")
        val nameEnd = nameStart + "Name:".length
        val equipmentStart = text.indexOf("Equipment:")
        val equipmentEnd = equipmentStart + "Equipment:".length

        val focusAreaStart = text.indexOf("Focus area:")
        val focusAreaEnd = focusAreaStart + "Focus area:".length

        val preparationStart = text.indexOf("Preparation:")
        val preparationEnd = preparationStart + "Preparation:".length
        val keyTipsStart = text.indexOf("Key Tips:")
        val keyTipsEnd = keyTipsStart + "Key Tips:".length

        // Apply bold and italic style to specific words
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), nameStart, nameEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), focusAreaStart, focusAreaEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set gravity to center only for specific words
        textView.gravity = Gravity.LEFT

        // Set the text for TextView
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Leg Extensions.
     */

    private fun legExtensionVideo() {

        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Leg Extension.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.LegExtensionVideo)
            videoView.setVideoURI(uri)

            var isPlaying = false
            videoView.setOnClickListener {
                if (isPlaying) {
                    videoView.pause()
                } else {
                    videoView.start()
                }
                isPlaying = !isPlaying
            }

            videoView.setOnCompletionListener {
                videoView.seekTo(0)
                videoView.start()
            }

        }.addOnFailureListener { exception ->
            // Handle any errors that may occur while getting the download URL
            Log.e("VideoDownload", "Video download failed", exception)
        }
    }

    private fun legExtensionInfoLayout() {
        val textView: TextView = findViewById(R.id.LegExtensionEquipment)
        val text = getString(R.string.leg_Extension_Info)

        // Create a SpannableString
        val spannableString = SpannableString(text)

        // Find the start and end indices of bold and italic parts
        val nameStart = text.indexOf("Name:")
        val nameEnd = nameStart + "Name:".length
        val equipmentStart = text.indexOf("Equipment:")
        val equipmentEnd = equipmentStart + "Equipment:".length

        val focusAreaStart = text.indexOf("Focus area:")
        val focusAreaEnd = focusAreaStart + "Focus area:".length

        val preparationStart = text.indexOf("Preparation:")
        val preparationEnd = preparationStart + "Preparation:".length
        val keyTipsStart = text.indexOf("Key Tips:")
        val keyTipsEnd = keyTipsStart + "Key Tips:".length

        // Apply bold and italic style to specific words
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), nameStart, nameEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), focusAreaStart, focusAreaEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set gravity to center only for specific words
        textView.gravity = Gravity.LEFT

        // Set the text for TextView
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Leg Curls.
     */

    private fun legCurlVideo() {

        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Leg Extension.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.LegCurlVideo)
            videoView.setVideoURI(uri)

            var isPlaying = false
            videoView.setOnClickListener {
                if (isPlaying) {
                    videoView.pause()
                } else {
                    videoView.start()
                }
                isPlaying = !isPlaying
            }

            videoView.setOnCompletionListener {
                videoView.seekTo(0)
                videoView.start()
            }

        }.addOnFailureListener { exception ->
            Log.e("VideoDownload", "Video download failed", exception)
        }
    }

    private fun legCurlInfoLayout() {
        val textView: TextView = findViewById(R.id.LegCurlEquipment)
        val text = getString(R.string.leg_Curl_Info)

        // Create a SpannableString
        val spannableString = SpannableString(text)

        // Find the start and end indices of bold and italic parts
        val nameStart = text.indexOf("Name:")
        val nameEnd = nameStart + "Name:".length
        val equipmentStart = text.indexOf("Equipment:")
        val equipmentEnd = equipmentStart + "Equipment:".length

        val focusAreaStart = text.indexOf("Focus area:")
        val focusAreaEnd = focusAreaStart + "Focus area:".length

        val preparationStart = text.indexOf("Preparation:")
        val preparationEnd = preparationStart + "Preparation:".length
        val keyTipsStart = text.indexOf("Key Tips:")
        val keyTipsEnd = keyTipsStart + "Key Tips:".length

        // Apply bold and italic style to specific words
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), nameStart, nameEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), focusAreaStart, focusAreaEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), equipmentStart, equipmentEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.gravity = Gravity.LEFT
        textView.text = spannableString
    }

}



