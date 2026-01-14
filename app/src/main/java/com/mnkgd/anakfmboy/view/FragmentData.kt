package com.mnkgd.anakfmboy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnkgd.anakfmboy.databinding.FragmentDataBinding
import com.mnkgd.anakfmboy.viewmodel.ListPengukuranViewModel // Pakai ViewModel untuk List

class FragmentData : Fragment() {
    private lateinit var binding: FragmentDataBinding
    private lateinit var viewModel: ListPengukuranViewModel // Ganti ini

    // Pastikan Adapter sudah dibuat dan menerima ArrayList
    private val ukurListAdapter = UkurListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListPengukuranViewModel::class.java]
        viewModel.refresh()

        // Menghubungkan binding ke viewModel (opsional jika ingin binding otomatis di XML)
        // binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recData.layoutManager = LinearLayoutManager(context)
        binding.recData.adapter = ukurListAdapter

        // Logic SwipeRefresh (Opsional)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefresh.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        // Observe LiveData yang isinya List<Pengukuran>
        viewModel.pengukuranLD.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                ukurListAdapter.updateUkurList(it)
                binding.recData.visibility = View.VISIBLE
                // Jika ada text error/empty di XML, sembunyikan
                // binding.txtError.visibility = View.GONE
            }
        })
    }
}