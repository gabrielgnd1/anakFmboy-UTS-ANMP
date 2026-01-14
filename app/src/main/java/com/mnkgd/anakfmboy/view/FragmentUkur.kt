package com.mnkgd.anakfmboy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mnkgd.anakfmboy.databinding.FragmentUkurBinding
import com.mnkgd.anakfmboy.model.Pengukuran
import com.mnkgd.anakfmboy.viewmodel.UkurViewModel

class FragmentUkur : Fragment(), UkurListener {
    private lateinit var binding: FragmentUkurBinding
    private lateinit var viewModel: UkurViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUkurBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel standar (ganti delegasi by viewModels agar konsisten)
        viewModel = ViewModelProvider(this)[UkurViewModel::class.java]

        binding.listener = this
        binding.lifecycleOwner = viewLifecycleOwner
    }

    // Implementasi interface UkurListener
    override fun onClick(v: View) {
        val usiaStr = binding.inputUsia.text.toString()
        val tinggiStr = binding.inputTB.text.toString()
        val beratStr = binding.inputBB.text.toString()

        if (usiaStr.isNotEmpty() && tinggiStr.isNotEmpty() && beratStr.isNotEmpty()) {
            // Buat object Pengukuran baru (ID 0 karena auto-increment)
            val baru = Pengukuran(
                usia = usiaStr.toInt(),
                tinggiBadan = tinggiStr.toInt(),
                beratBadan = beratStr.toInt()
            )

            // Panggil fungsi simpan di ViewModel yang ke Room
            viewModel.tambahPengukuran(baru)

            Toast.makeText(context, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()

            // Opsional: Kosongkan input setelah simpan
            binding.inputUsia.text?.clear()
            binding.inputTB.text?.clear()
            binding.inputBB.text?.clear()
        } else {
            Toast.makeText(context, "Harap isi semua data", Toast.LENGTH_SHORT).show()
        }
    }
}