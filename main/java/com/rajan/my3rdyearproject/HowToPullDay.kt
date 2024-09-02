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
 * This class is about explaining workout information for the "Pull Day" workouts.
 * These workouts primarily focus on the back and biceps.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class HowToPullDay: AppCompatActivity() {

    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    /**
     * In this class, I am calling in any references to Firebase.
     * This is because I have the required videos stored in FirebaseStorage.
     * For each leg workout, there is a method for calling the video
     *  and a method for calling the text.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.how_to_pull_day)

        val backToPullMain = findViewById<Button>(R.id.toPullDayExercise)
        backToPullMain.setOnClickListener { v -> backToPullDay(v) }

        bicepCurlsVideo()
        bicepCurlsInfoLayout()

        hammerCurlsVideo()
        hammerCurlsInfoLayout()

        dumbbellRowsVideo()
        dumbbellRowsInfoLayout()

        tBarRowsVideo()
        tBarRowsInfoLayout()

        cableMachineRowsVideo()
        cableMachineRowsInfoLayout()

        latPulldownVideo()
        latPulldownInfoLayout()

        plateLoadedRowsVideo()
        plateLoadedRowsInfoLayout()
    }

    private fun backToPullDay(view: View) {
        val newIntent = Intent(this, GymPullDayMain::class.java)
        startActivity(newIntent)
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Bicep Curls.
     */

    private fun bicepCurlsVideo() {
        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Bicep Curls.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.BicepCurlsVideo)
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

    private fun bicepCurlsInfoLayout() {
        val textView: TextView = findViewById(R.id.BicepCurlsEquipment)
        val text = getString(R.string.bicep_Curls_Info)

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
     *  text required about Hammer Curls.
     */

    private fun hammerCurlsVideo() {

        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Hammer Curls.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.HammerCurlsVideo)
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

    private fun hammerCurlsInfoLayout() {
        val textView: TextView = findViewById(R.id.HammerCurlsEquipment)
        val text = getString(R.string.hammer_Curls_Info)

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
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.gravity = Gravity.LEFT
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Dumbbell Rows.
     */

    private fun dumbbellRowsVideo() {
        val videoRef = storage.getReferenceFromUrl(
            "https://firebasestorage.googleapis.com/v0/b/rajan-3rd-year-project.appspot" +
                    ".com/o/" + "color%20thirst%20trap.mp4?alt=media&token=30f2a5bf-f41c-4530-" +
                    "b0f4-9a29505e1fec")


        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.DumbbellRowsVideo)
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

    private fun dumbbellRowsInfoLayout() {
        val textView: TextView = findViewById(R.id.DumbbellRowsEquipment)
        val text = getString(R.string.dumbbell_Rows_Info)

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
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.gravity = Gravity.LEFT
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about T Bar Rows.
     */

    private fun tBarRowsVideo() {
        val videoRef = storage.getReferenceFromUrl(
            "https://firebasestorage.googleapis.com/v0/b/rajan-3rd-year-project.appspot" +
                    ".com/o/" + "color%20thirst%20trap.mp4?alt=media&token=30f2a5bf-f41c-4530-" +
                    "b0f4-9a29505e1fec")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.TbarRowsVideo)
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

    private fun tBarRowsInfoLayout() {
        val textView: TextView = findViewById(R.id.TbarRowsEquipment)
        val text = getString(R.string.tBar_Rows_Info)

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
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.gravity = Gravity.LEFT
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Cable Machine Rows.
     */

    private fun cableMachineRowsVideo() {

        val videoRef = storage.getReferenceFromUrl(
            "gs://rajan-3rd-year-project.appspot.com/Raj Rows.mp4")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.CableMachineRowsVideo)
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

    private fun cableMachineRowsInfoLayout() {
        val textView: TextView = findViewById(R.id.CableMachineRowsEquipment)
        val text = getString(R.string.cable_Machine_Rows_Info)

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
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set gravity to center only for specific words
        textView.gravity = Gravity.LEFT

        // Set the text for TextView
        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Lateral Pulldowns.
     */

    private fun latPulldownVideo() {
        val videoRef = storage.getReferenceFromUrl(
            "https://firebasestorage.googleapis.com/v0/b/rajan-3rd-year-project.appspot" +
                    ".com/o/" + "color%20thirst%20trap.mp4?alt=media&token=30f2a5bf-f41c-4530-" +
                    "b0f4-9a29505e1fec")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.LatPulldownVideo)
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

    private fun latPulldownInfoLayout() {
        val textView: TextView = findViewById(R.id.LatPulldownEquipment)
        val text = getString(R.string.lat_Pulldown_Info)

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
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.gravity = Gravity.LEFT

        textView.text = spannableString
    }

    /**
     * These two methods are for calling the video explanation and informative
     *  text required about Plate Loaded Rows.
     */

    private fun plateLoadedRowsVideo() {
        val videoRef = storage.getReferenceFromUrl(
            "https://firebasestorage.googleapis.com/v0/b/rajan-3rd-year-project.appspot" +
                    ".com/o/" + "color%20thirst%20trap.mp4?alt=media&token=30f2a5bf-f41c-4530-" +
                    "b0f4-9a29505e1fec")

        videoRef.downloadUrl.addOnSuccessListener { uri ->
            val videoView = findViewById<VideoView>(R.id.PlateLoadedRowsVideo)
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

    private fun plateLoadedRowsInfoLayout() {
        val textView: TextView = findViewById(R.id.PlateLoadedRowsEquipment)
        val text = getString(R.string.plate_Loaded_Rows_Info)


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
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), preparationStart, preparationEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD_ITALIC), keyTipsStart, keyTipsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.gravity = Gravity.LEFT
        textView.text = spannableString
    }

}
