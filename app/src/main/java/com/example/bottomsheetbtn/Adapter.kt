package com.example.bottomsheetbtn

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomsheetbtn.databinding.ItemHolderBinding
import com.google.android.material.transition.Hold

class Adapter ():RecyclerView.Adapter<Adapter.Holder>() {
    var items :Int = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder.create(parent)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.tv.text = position.toString()
    }

    override fun getItemCount(): Int =items

    class Holder private constructor(val binding:ItemHolderBinding):RecyclerView.ViewHolder(binding.root){

        companion object{
            fun  create(parent: ViewGroup):Holder{
                val inflater =LayoutInflater.from(parent.context)
                val binding = ItemHolderBinding.inflate(inflater,parent,false)
                return  Holder(binding)
            }
        }
    }


}