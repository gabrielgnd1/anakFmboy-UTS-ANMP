package com.mnkgd.anakfmboy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnkgd.anakfmboy.databinding.FragmentDataBinding
import com.mnkgd.anakfmboy.viewmodel.UkurViewModel

class FragmentData : Fragment() {
    private lateinit var binding: FragmentDataBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: UkurViewModel
    private val ukurListAdapter = UkurListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[UkurViewModel::class.java]
        viewModel.refresh()

        binding.recData.layoutManager = LinearLayoutManager(context)
        binding.recData.adapter = ukurListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.ukurLD.observe(viewLifecycleOwner, Observer {
            ukurListAdapter.updateUkurList(it)
        })
    }
}