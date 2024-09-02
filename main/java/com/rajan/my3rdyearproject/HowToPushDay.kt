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
 * This class is about explaining workout information for the "Push Day" workouts.
 * These workouts primarily focus on the chest, shoulders and triceps.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class HowToPushDay: AppCompatActivity() {

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
        setContentView(R.layout.how_to_push_day)

        val backToPushMain = findViewById<Button>(R.id.toPushDayExercise)
        backToPushMain.setOnClickListener { v -> backToPushDay(v) }

        inclinePlateBenchVideo()
        inclinePlateBenchInfoLayout()

        regularPlateBenchVideo()
        regularPlateBenchInfoLayout()

        dumbbellShoulderPressVideo()
        dumbbellShoulderPressInfoLayout()

        cableTricepPushdownVideo()
        cableTricepPushdownInfoLayout()

        overheadRopeExtensionVideo()
        overheadRopeExtensionInfoLayout()

        lateralRaisesVideo()
        lateralRaisesInfoLayout()

    }

    private fun backToPushDay(view: View) {
        val newIntent = Intent(this, GymPushDayMain::class.java)
        startActivity(newIntent)
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Incline Bench Press.
     */

    private fun inclinePlateBenchVideo() {
        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Incline Bench Press.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.InclinePlateBenchVideo)
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

    private fun inclinePlateBenchInfoLayout() {
        val textView: TextView = findViewById(R.id.InclinePlateBenchEquipment)
        val text = getString(R.string.incline_Plate_Bench_Info)

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
     *  text required about Regular Bench Press.
     */

    private fun regularPlateBenchVideo() {

        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Bench Press Video.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.RegularPlateBenchVideo)
            videoView.setVideoURI(uri)

            // Set OnClickListener to toggle play/pause on the VideoView
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

    private fun regularPlateBenchInfoLayout() {
        val textView: TextView = findViewById(R.id.RegularPlateBenchEquipment)
        val text = getString(R.string.regular_Plate_Bench_Info)

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

        // Set the text for TextView
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Dumbbell Shoulder Press.
     */

    private fun dumbbellShoulderPressVideo() {

        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Shoulder Press.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.DumbbellShoulderPressVideo)
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

    private fun dumbbellShoulderPressInfoLayout() {
        val textView: TextView = findViewById(R.id.DumbbellShoulderPressEquipment)
        val text = getString(R.string.dumbbell_Shoulder_Press_Info)

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
     *  text required about Cable Tricep Pushdown.
     */

    private fun cableTricepPushdownVideo() {
         val videoRef = storage.getReferenceFromUrl(
            "https://firebasestorage.googleapis.com/v0/b/rajan-3rd-year-project.appspot" +
                    ".com/o/" + "color%20thirst%20trap.mp4?alt=media&token=30f2a5bf-f41c-4530-" +
                    "b0f4-9a29505e1fec")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.CableTricepPushdownVideo)
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

    private fun cableTricepPushdownInfoLayout() {
        val textView: TextView = findViewById(R.id.CableTricepPushdownEquipment)
        val text = getString(R.string.cable_Tricep_Pushdown_Info)

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
     *  text required about Overhead Rope Extension.
     */

    private fun overheadRopeExtensionVideo() {
        val videoRef = storage.getReferenceFromUrl(
            "https://firebasestorage.googleapis.com/v0/b/rajan-3rd-year-project.appspot" +
                    ".com/o/" + "color%20thirst%20trap.mp4?alt=media&token=30f2a5bf-f41c-4530-" +
                    "b0f4-9a29505e1fec")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.OverheadRopeExtensionVideo)
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

    private fun overheadRopeExtensionInfoLayout() {
        val textView: TextView = findViewById(R.id.OverheadRopeExtensionEquipment)
        val text = getString(R.string.overhead_rope_extension_Info)

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


        textView.gravity = Gravity.LEFT
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Lateral Raise.
     */

    private fun lateralRaisesVideo() {
        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Shoulder Press.mp4")
        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.LateralRaisesVideo)
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

    private fun lateralRaisesInfoLayout() {
        val textView: TextView = findViewById(R.id.LateralRaisesEquipment)
        val text = getString(R.string.lateral_Raises_Info)

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

        // Set the text for TextView
        textView.text = spannableString
    }

}
