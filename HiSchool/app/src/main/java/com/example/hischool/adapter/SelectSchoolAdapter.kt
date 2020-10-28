package com.example.hischool.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.SearchRecyclerViewData

class SelectSchoolAdapter(val selectSchoolArrayList: ArrayList<SearchRecyclerViewData>) : RecyclerView.Adapter<SelectSchoolAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_school_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(selectSchoolArrayList[position])
    }

    override fun getItemCount(): Int {
        Log.d("searchList", "data: ${selectSchoolArrayList.size}")
        return selectSchoolArrayList.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.select_school_school_name)
        val address: TextView = itemView.findViewById(R.id.select_school_school_address)

        fun bind(item : SearchRecyclerViewData)
        {
            Log.d("TAG", item.name)
            name.text = item.name
            address.text = item.address
        }
    }
}