package com.mnkgd.anakfmboy.view

import android.app.DatePickerDialog
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mnkgd.anakfmboy.R
import com.mnkgd.anakfmboy.databinding.FragmentProfilAnakBinding
import com.mnkgd.anakfmboy.model.Profil
import com.mnkgd.anakfmboy.viewmodel.ProfilViewModel
import java.util.*

class FragmentProfilAnak : Fragment(), ProfilAnakListener {
    private lateinit var viewModel: ProfilViewModel
    private lateinit var dataBinding: FragmentProfilAnakBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profil_anak, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        viewModel.refresh()
        dataBinding.listener = this
        dataBinding.lifecycleOwner = viewLifecycleOwner

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.profilLD.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                dataBinding.profil = it
            } else {
                dataBinding.profil = Profil(1, "", "", "")
            }
        })
    }

    override fun onSave(v: View) {
        // Ambil data dari EditText dan simpan ke Room
        val nama = dataBinding.inputNama.text.toString()
        val tgl = dataBinding.inputTanggal.text.toString()
        val gender = dataBinding.profil?.jenisKelamin ?: ""

        val profilUpdate = Profil(1, nama, tgl, gender)

        viewModel.simpanProfil(profilUpdate)
        Toast.makeText(v.context, "Profil Berhasil Diperbarui", Toast.LENGTH_SHORT).show()
    }

    override fun onPickDate(v: View) {
        // Logika DatePicker sesuai permintaan di Interface
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), { _, y, m, d ->
            val selectedDate = "$d/${m + 1}/$y"
            dataBinding.inputTanggal.setText(selectedDate)
            dataBinding.profil?.tanggalLahir = selectedDate
        }, year, month, day)
        dpd.show()
    }

    override fun onGenderSelect(v: View) {
        val genderTerpilih = v.tag.toString()
        dataBinding.profil?.jenisKelamin = genderTerpilih
    }
}