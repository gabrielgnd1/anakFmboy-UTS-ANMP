package com.mnkgd.anakfmboy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mnkgd.anakfmboy.R
import com.mnkgd.anakfmboy.databinding.FragmentProfilAnakBinding

class FragmentProfilAnak : Fragment() {
    private lateinit var binding: FragmentProfilAnakBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilAnakBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("profil_anak", 0)
        binding.inputNama.setText(sharedPref.getString("nama", ""))
        binding.inputTanggal.setText(sharedPref.getString("tanggal", ""))

        val gender = sharedPref.getString("gender", "")
        when (gender) {
            "Laki-laki" -> binding.radioGroupGender.check(R.id.radioMale)
            "Perempuan" -> binding.radioGroupGender.check(R.id.radioFem)
        }

        binding.btnSimpanProfil.setOnClickListener {
            val nama = binding.inputNama.text.toString()
            val tanggal = binding.inputTanggal.text.toString()
            val gender = when (binding.radioGroupGender.checkedRadioButtonId) {
                R.id.radioMale -> "Laki-laki"
                R.id.radioFem -> "Perempuan"
                else -> ""
            }

            val editor = sharedPref.edit()
            editor.putString("nama", nama)
            editor.putString("tanggal", tanggal)
            editor.putString("gender", gender)
            editor.apply()

            Toast.makeText(context,  "Data added successfully", Toast.LENGTH_SHORT).show()


        }


    }
}