package com.rajan.my3rdyearproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Food adapter for diet fragment on the 3rd year project app.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class FoodAdapter(private val foodList: List<FoodItem>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val collectionIdTextView: TextView = itemView.findViewById(R.id.collectionIdTextView)
        val documentIdTextView: TextView = itemView.findViewById(R.id.documentIdTextView)
        val field1TextView: TextView = itemView.findViewById(R.id.field1TextView)
        val field2TextView: TextView = itemView.findViewById(R.id.field2TextView)
        val field3TextView: TextView = itemView.findViewById(R.id.field3TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodItem = foodList[position]
        holder.collectionIdTextView.text = "Collection ID: ${foodItem.collectionId}"
        holder.documentIdTextView.text = "Meal Name: ${foodItem.documentId}"
        holder.field1TextView.text = "Base Components: ${foodItem.field1}"
        holder.field2TextView.text = "Secondary Components: ${foodItem.field2}"
        holder.field3TextView.text = "Additional Components: ${foodItem.field3}"
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}
