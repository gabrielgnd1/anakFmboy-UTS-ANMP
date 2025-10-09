package com.mnkgd.anakfmboy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mnkgd.anakfmboy.R
import com.mnkgd.anakfmboy.databinding.FragmentUkurBinding

class FragmentUkur : Fragment() {
    private lateinit var binding: FragmentUkurBinding



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
            val action = FragmentUkurDirections.actionFragmenData()
            it.findNavController().navigate(action)
        }

    }


}