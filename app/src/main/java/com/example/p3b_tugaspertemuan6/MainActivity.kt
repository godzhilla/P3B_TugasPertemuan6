package com.example.p3b_tugaspertemuan6

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.p3b_tugaspertemuan6.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val systemTanggal = Calendar.getInstance()
            systemTanggal.timeInMillis = System.currentTimeMillis()

            var waktuNya = "${timePickerView.hour} : ${timePickerView.minute}"
            val bulanNya = resources.getStringArray(R.array.bulan)
            val listAbsen = resources.getStringArray(R.array.presensi)

            var tanggalNya = "${systemTanggal.get(Calendar.DAY_OF_MONTH)} ${bulanNya[systemTanggal.get(Calendar.MONTH)]} ${systemTanggal.get(Calendar.YEAR)}"
            datePicker.setOnDateChangeListener { view, year, month, dayOfMonth ->
                tanggalNya = "$dayOfMonth ${bulanNya[month]} $year"
            }

            timePickerView.setOnTimeChangedListener { timePicker, hour, minute ->
                waktuNya = "$hour : $minute"
            }

            spinnerAbsen.adapter = ArrayAdapter( this@MainActivity, android.R.layout.simple_spinner_item, listAbsen)

            spinnerAbsen.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        if (listAbsen[p2] == "Sakit"){
                            txtKeterangan.visibility = View.VISIBLE
                        } else {
                            txtKeterangan.visibility = View.GONE
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

            btnSubmit.setOnClickListener() {
                Toast.makeText( this@MainActivity , "Presensi berhasil $tanggalNya jam $waktuNya", Toast.LENGTH_SHORT).show()
            }
        }
    }
}