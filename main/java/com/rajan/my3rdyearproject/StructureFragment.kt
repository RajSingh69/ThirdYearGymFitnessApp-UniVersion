package com.rajan.my3rdyearproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import android.widget.TextView
import androidx.fragment.app.Fragment

import java.util.Locale

/**
 * Class for setting up the breakfast fragment in the 3rd year project.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class StructureFragment : Fragment() {

    /**
     * All EditTexts and Buttons are called in correctly.
     * I have set a timer of 5 seconds (waiting so the user can reply).
     */

    private lateinit var howToDoButton: Button
    private lateinit var relaxationStuffButton: Button
    private lateinit var sendButton: Button
    private lateinit var userInputEditText: EditText
    private lateinit var chatLayout: ViewGroup
    private val TIMEOUT_MS = 5000 // 5 seconds

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_structure, container, false)
    }

    /**
     * These functions are setting listeners for different buttons.
     * They are for the different pages and also for the button to start
     *      the chat with the chat bot.
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        howToDoButton = view.findViewById(R.id.howToDoButton)
        relaxationStuffButton = view.findViewById(R.id.relaxationStuffButton)
        sendButton = view.findViewById(R.id.sendButton)
        userInputEditText = view.findViewById(R.id.userInputEditText)
        chatLayout = view.findViewById(R.id.chatLayout)

        howToDoButton.setOnClickListener {
            howToDoClick()
        }

        relaxationStuffButton.setOnClickListener {
            relaxationStuffClick()
        }

        sendButton.setOnClickListener {
            startChat()
        }
    }

    /**
     * This function starts the call with the chat bot. It outputs strings to the logcat when
     *      first working on startup.
     *
     * The bot works by responding to key words.
     * Otherwise it will ask for a different response until the user quits.
     * The chats work where the bot speaks, waits for the user's response, and the goes again.
     */

    private fun startChat() {
        Log.d("ChatBot", "Welcome to ChatBot!")
        Log.d("ChatBot", "Ask me anything, or say 'exit' to end the conversation.")

        val userInput = userInputEditText.text.toString().lowercase(Locale.ROOT)

        val botReply = when (userInput) {
            "hello", "hi" -> "Hi there!"
            "how are you" -> "I'm great, how are you?"
            "how do i use" -> "Tell me how you're feeling! Tell me if you're scared, tired, anxious," +
                    " depressed, burned out, worried etc :)"
            "scared" -> "Don't be scared. Write down your issues, and start with the easiest task!"
            "tired" -> "Rest up! Give yourself a spa day  and read your favourite book or watch your" +
                    " favourite TV show!"
            "anxious" -> "Take a break and rest. You are doing good. Everything is going " +
                    "to be fine!"
            "depressed" -> "Smile more."
            "burned out" -> "Take a break!"
            "bye" -> "Goodbye!"
            else -> "I'm sorry, I don't understand."
        }

        addMessage("You: $userInput")
        addMessage("Dr Phone: $botReply")
    }

    /**
     * These methods are here to take the user to a different pages.
     */

    private fun howToDoClick() {
        val newIntent = Intent(requireContext(), AppBarFoodSetup::class.java)
        startActivity(newIntent)
    }

    private fun relaxationStuffClick() {
        val newIntent = Intent(requireContext(), AppBarFoodSetup::class.java)
        startActivity(newIntent)
    }

    private fun addMessage(message: String) {
        val textView = TextView(requireContext())
        textView.text = message
        chatLayout.addView(textView)
    }
}

