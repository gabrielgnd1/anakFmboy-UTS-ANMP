package com.mnkgd.anakfmboy.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnkgd.anakfmboy.databinding.DataUkurItemBinding
import com.mnkgd.anakfmboy.model.Pengukuran

class UkurListAdapter(
    val ukurList: ArrayList<Pengukuran>
) : RecyclerView.Adapter<UkurListAdapter.UkurViewHolder>() {

    // ViewHolder menangkap binding hasil generate dari XML
    class UkurViewHolder(val binding: DataUkurItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UkurViewHolder {
        val binding = DataUkurItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UkurViewHolder(binding)
    }

    override fun getItemCount() = ukurList.size

    override fun onBindViewHolder(holder: UkurViewHolder, position: Int) {
        // Sesuai materi Week 11: Pasang objek pengukuran ke variabel binding di XML
        holder.binding.pengukuran = ukurList[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateUkurList(newUkurList: List<Pengukuran>) {
        ukurList.clear()
        ukurList.addAll(newUkurList)
        notifyDataSetChanged()
    }
}
