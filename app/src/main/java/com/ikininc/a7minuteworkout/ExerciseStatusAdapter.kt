package com.ikininc.a7minuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ExerciseStatusAdapter(val items:ArrayList<ExerciseModel>, val context: Context)
    : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>(){

//Initiate view for each item according to item xml file
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvItem = view.tvItem!!  //item xml's Id

    }
//create ViewHolder with LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_exercise_status, parent, false)
        return ExerciseStatusAdapter.ViewHolder(inflatedView)
//    return ViewHolder(
//        LayoutInflater.from(context).inflate(
//            R.layout.item_exercise_status,
//            parent,
//            false
//        )
//    )

    }
//Item and ViewHolder binding and preparing to show them on screen
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseModel=items[position]

        holder.tvItem.text = model.getId().toString()
        if(model.getIsSelected()){
            holder.tvItem.background=ContextCompat.getDrawable(context, R.drawable.item_circular_thin_color_accent_border)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }else if(model.getIsCompleted()){
            holder.tvItem.background=ContextCompat.getDrawable(context, R.drawable.item_circular_color_accent_background)
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            holder.tvItem.background=ContextCompat.getDrawable(context, R.drawable.item_circular_color_gray_background)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }

    }

    override fun getItemCount(): Int {
        return items.size

    }
}