package com.rajan.my3rdyearproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Class for setting up the dinner fragment in the 3rd year project.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class Z_Dinner_Fragment: Fragment(){

    private lateinit var backToDiet : Button
    private lateinit var recyclerViewDinner: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private var foodList = mutableListOf<FoodItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.z_dinner_fragment, container, false)!!;
    }

    /**
     * I get the necessary conditions for the correct recyclerview.
     * All EditTexts and Buttons are called in correctly.
     * I am setting methods to fetch only the dinner options.
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backToDiet = getView()?.findViewById(R.id.BackToDietPage) as Button
        backToDiet.setOnClickListener { submitToDietPageClick() }

        recyclerViewDinner = view.findViewById(R.id.recyclerViewDinner)
        recyclerViewDinner.layoutManager = LinearLayoutManager(context)
        foodAdapter = FoodAdapter(foodList)
        recyclerViewDinner.adapter = foodAdapter

        val halalButton: Button = view.findViewById(R.id.halalButtonDinner)
        val kosherButton: Button = view.findViewById(R.id.kosherButtonDinner)
        val veganButton: Button = view.findViewById(R.id.veganButtonDinner)
        val vegetarianButton: Button = view.findViewById(R.id.vegetarianButtonDinner)

        halalButton.setOnClickListener {
            fetchData("halalDinner")
        }

        kosherButton.setOnClickListener {
            fetchData("kosherDinner")
        }

        veganButton.setOnClickListener {
            fetchData("veganDinner")
        }

        vegetarianButton.setOnClickListener{
            fetchData("vegetarianDinner")
        }

        fetchData("halalDinner")

    }

    private fun fetchData(collectionId: String) {
        foodList.clear()
        val db = FirebaseFirestore.getInstance()
        db.collection(collectionId)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val foodItem = FoodItem(
                        collectionId,
                        document.id,
                        document.getString("field1") ?: "",
                        document.getString("field2") ?: "",
                        document.getString("field3") ?: ""
                    )
                    foodList.add(foodItem)
                }
                foodAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
            }
    }

    private fun submitToDietPageClick() {
        requireActivity().onBackPressed()
        val newIntent = Intent(requireContext(), AppBarSetup::class.java)
        startActivity(newIntent)
    }
}