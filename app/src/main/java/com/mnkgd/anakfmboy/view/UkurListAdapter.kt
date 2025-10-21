package com.mnkgd.anakfmboy.view

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnkgd.anakfmboy.databinding.DataUkurItemBinding
import com.mnkgd.anakfmboy.model.Ukur

class UkurListAdapter(val ukurList: ArrayList<Ukur>): RecyclerView.Adapter<UkurListAdapter.DataUkurViewHolder>() {
    class DataUkurViewHolder (var binding: DataUkurItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataUkurViewHolder {
        val binding = DataUkurItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataUkurViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DataUkurViewHolder,
        position: Int
    ) {
        if (position == 0) {
            //yang paling atas
            holder.binding.txtAge.text = "Age"
            holder.binding.txtHeight.text = "Height (cm)"
            holder.binding.txtWeight.text = "Weight (kg)"


            holder.binding.txtAge.setTypeface(null, Typeface.BOLD) //typeface buat ngeBold
            holder.binding.txtHeight.setTypeface(null, Typeface.BOLD)
            holder.binding.txtWeight.setTypeface(null, Typeface.BOLD)

        } else {
            val dataPosition = position - 1

            holder.binding.txtAge.text = ukurList[dataPosition].usia.toString()
            holder.binding.txtHeight.text = ukurList[dataPosition].tinggi.toString()
            holder.binding.txtWeight.text = ukurList[dataPosition].berat.toString()

            holder.binding.txtAge.setTypeface(null, Typeface.NORMAL)
            holder.binding.txtHeight.setTypeface(null, Typeface.NORMAL)
            holder.binding.txtWeight.setTypeface(null, Typeface.NORMAL)
        }
    }

    override fun getItemCount(): Int = ukurList.size + 1

    fun updateUkurList(newUkurList: ArrayList<Ukur>) {
        ukurList.clear()
        ukurList.addAll(newUkurList)
        notifyDataSetChanged()
    }
}