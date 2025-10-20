package com.mnkgd.anakfmboy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.mnkgd.anakfmboy.R
import com.mnkgd.anakfmboy.databinding.FragmentUkurBinding
import com.mnkgd.anakfmboy.viewmodel.UkurViewModel

class FragmentUkur : Fragment() {
    private lateinit var binding: FragmentUkurBinding
    private val ukurViewModel : UkurViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUkurBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTambahData.setOnClickListener {
            val usia = binding.inputUsia.text.toString()
            val tinggi = binding.inputTB.text.toString()
            val berat = binding.inputBB.text.toString()
            ukurViewModel.saveToFile(usia, tinggi, berat)
            Toast.makeText(context,  "Data added successfully", Toast.LENGTH_SHORT).show()
        }
    }
}