package com.example.hischool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.SelectSchoolRecyclerViewData

class SelectSchoolAdapter(val selectSchoolArrayList: ArrayList<SelectSchoolRecyclerViewData>) : RecyclerView.Adapter<SelectSchoolAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_school_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(selectSchoolArrayList[position])
    }

    override fun getItemCount(): Int {
        return selectSchoolArrayList.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val schoolName: TextView = itemView.findViewById(R.id.select_school_school_name)
        val schoolAddrees: TextView = itemView  .findViewById(R.id.select_school_school_address)

        fun bind(item : SelectSchoolRecyclerViewData)
        {
            schoolName.text = item.schoolName
            schoolAddrees.text = item.schoolAddress
        }
    }
}