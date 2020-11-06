package com.example.hischool.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.SearchSchoolRecyclerViewData

class SelectSchoolAdapter(val selectSchoolArrayList: ArrayList<SearchSchoolRecyclerViewData>) : RecyclerView.Adapter<SelectSchoolAdapter.ViewHolder>(){
    var selected = -2;
    val selectedColor = Color.rgb(225, 226, 227)

    fun updateList(list : ArrayList<SearchSchoolRecyclerViewData>) {
        selectSchoolArrayList.clear()
        selectSchoolArrayList.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_school_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(selectSchoolArrayList[position])
        Log.d("TAG", "SELECTED : $selected, position : $position")
        if(position == selected) holder.itemView.setBackgroundColor(selectedColor)
        else holder.itemView.setBackgroundColor(Color.WHITE)
    }

    override fun getItemCount(): Int {
        Log.d("searchList", "data: ${selectSchoolArrayList.size}")
        return selectSchoolArrayList.size
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.select_school_school_name)
        val address: TextView = itemView.findViewById(R.id.select_school_school_address)

        fun bind(item : SearchSchoolRecyclerViewData)
        {
            Log.d("TAG", item.name)
            name.text = item.name
            address.text = item.address

            itemView.setOnClickListener {
                selected = adapterPosition;
                notifyDataSetChanged()
            }

        }
    }
}