package com.mnkgd.anakfmboy.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mnkgd.anakfmboy.R
import com.mnkgd.anakfmboy.databinding.FragmentProfilAnakBinding
import java.text.SimpleDateFormat
import java.util.*

class FragmentProfilAnak : Fragment() {

    private lateinit var binding: FragmentProfilAnakBinding
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilAnakBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ambil data dari SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("profil_anak", 0)
        binding.inputNama.setText(sharedPref.getString("nama", ""))
        binding.inputTanggal.setText(sharedPref.getString("tanggal", ""))

        val gender = sharedPref.getString("gender", "")
        when (gender) {
            "Laki-laki" -> binding.radioGroupGender.check(R.id.radioMale)
            "Perempuan" -> binding.radioGroupGender.check(R.id.radioFem)
        }

        // klik untuk pilih tanggal (DatePicker)
        binding.inputTanggal.apply {
            isFocusable = false
            isClickable = true
            setOnClickListener { showDatePicker() }
        }

        // tombol simpan data
        binding.btnSimpanProfil.setOnClickListener {
            val nama = binding.inputNama.text.toString()
            val tanggal = binding.inputTanggal.text.toString()
            val genderValue = when (binding.radioGroupGender.checkedRadioButtonId) {
                R.id.radioMale -> "Laki-laki"
                R.id.radioFem -> "Perempuan"
                else -> ""
            }

            // validasi sederhana
            if (nama.isBlank() || tanggal.isBlank() || genderValue.isBlank()) {
                Toast.makeText(context, "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener //buat kembali ke @ssetonclicklistener yg atas
            }

            // simpan ke SharedPreferences
            val editor = sharedPref.edit()
            editor.putString("nama", nama)
            editor.putString("tanggal", tanggal)
            editor.putString("gender", genderValue)
            editor.apply()

            Toast.makeText(context, "Data disimpan", Toast.LENGTH_SHORT).show()
        }
    }

    // fungsi untuk menampilkan DatePickerDialog
    private fun showDatePicker() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            requireContext(),
            { _, y, m, d ->
                calendar.set(y, m, d)
                val selectedDate = calendar.time
                val formatted = dateFormat.format(selectedDate)

                // tampilkan ke input dan simpan sebagai format dd/MM/yyyy
                binding.inputTanggal.setText(formatted)
            },
            year, month, day
        ).show()
    }
}
