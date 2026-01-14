package com.mnkgd.anakfmboy.view

import android.app.Fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
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

// Wajib implement interface Listener seperti TodoApp
class FragmentProfilAnak : Fragment(), ProfilAnakListener {
    private lateinit var viewModel: ProfilViewModel
    private lateinit var dataBinding: FragmentProfilAnakBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inisialisasi Data Binding
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profil_anak, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        viewModel.refresh()

        // Hubungkan variabel di XML ke Fragment ini
        dataBinding.listener = this
        dataBinding.lifecycleOwner = viewLifecycleOwner

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.profilLD.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                // Masukkan data profil ke variabel 'profil' di XML
                dataBinding.profil = it
            } else {
                // Jika DB kosong, buat object baru agar tidak error null
                dataBinding.profil = Profil(1, "", "", "")
            }
        })
    }

    // --- Implementasi ProfilAnakListener ---

    override fun onSimpanClick(v: View) {
        // Ambil data terbaru dari input field manual (karena pakai satu arah @{})
        val nama = dataBinding.inputNama.text.toString()
        val tgl = dataBinding.inputTanggal.text.toString()
        val gender = dataBinding.profil?.jenisKelamin ?: ""

        val profilUpdate = Profil(1, nama, tgl, gender)

        viewModel.simpanProfil(profilUpdate)
        Toast.makeText(v.context, "Profil Berhasil Diperbarui", Toast.LENGTH_SHORT).show()
    }

    override fun onGenderClick(v: View) {
        // Ambil nilai dari tag RadioButton (Laki-laki / Perempuan)
        val genderTerpilih = v.tag.toString()
        dataBinding.profil?.jenisKelamin = genderTerpilih
    }
}