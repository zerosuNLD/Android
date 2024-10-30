package com.example.bai3

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextMSSV: EditText
    private lateinit var editTextName: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonToggleCalendar: Button
    private lateinit var calendarView: CalendarView
    private lateinit var spinnerWard: Spinner
    private lateinit var spinnerDistrict: Spinner
    private lateinit var spinnerCity: Spinner
    private lateinit var checkBoxSports: CheckBox
    private lateinit var checkBoxMovies: CheckBox
    private lateinit var checkBoxMusic: CheckBox
    private lateinit var checkBoxAgree: CheckBox
    private lateinit var buttonSubmit: Button

    private var selectedDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ánh xạ các thành phần giao diện
        editTextMSSV = findViewById(R.id.editTextMSSV)
        editTextName = findViewById(R.id.editTextName)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPhone = findViewById(R.id.editTextPhone)
        buttonToggleCalendar = findViewById(R.id.buttonToggleCalendar)
        calendarView = findViewById(R.id.calendarView)
        spinnerWard = findViewById(R.id.spinnerWard)
        spinnerDistrict = findViewById(R.id.spinnerDistrict)
        spinnerCity = findViewById(R.id.spinnerCity)
        checkBoxSports = findViewById(R.id.checkBoxSports)
        checkBoxMovies = findViewById(R.id.checkBoxMovies)
        checkBoxMusic = findViewById(R.id.checkBoxMusic)
        checkBoxAgree = findViewById(R.id.checkBoxAgree)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        val adapterWard = ArrayAdapter.createFromResource(
            this,
            R.array.ward, // Mảng các lựa chọn
            android.R.layout.simple_spinner_item
        )

        val adapterDis = ArrayAdapter.createFromResource(
            this,
            R.array.district, // Mảng các lựa chọn
            android.R.layout.simple_spinner_item
        )

        val adapterCity = ArrayAdapter.createFromResource(
            this,
            R.array.city, // Mảng các lựa chọn
            android.R.layout.simple_spinner_item
        )

        adapterWard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterDis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerWard.adapter = adapterWard
        spinnerDistrict.adapter = adapterDis
        spinnerCity.adapter = adapterCity

        // Hiển thị hoặc ẩn CalendarView
        buttonToggleCalendar.setOnClickListener {
            calendarView.visibility = if (calendarView.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        // Lấy ngày được chọn từ CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        }

        buttonSubmit.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Thông tin hợp lệ!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        if (editTextMSSV.text.isEmpty()) {
            editTextMSSV.error = "Vui lòng nhập MSSV"
            return false
        }
        if (editTextName.text.isEmpty()) {
            editTextName.error = "Vui lòng nhập họ tên"
            return false
        }
        if (radioGroupGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editTextEmail.text.isEmpty()) {
            editTextEmail.error = "Vui lòng nhập email"
            return false
        }
        if (editTextPhone.text.isEmpty()) {
            editTextPhone.error = "Vui lòng nhập số điện thoại"
            return false
        }
        if (selectedDate.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn ngày sinh", Toast.LENGTH_SHORT).show()
            return false
        }
        if (spinnerWard.selectedItem == null || spinnerDistrict.selectedItem == null || spinnerCity.selectedItem == null) {
            Toast.makeText(this, "Vui lòng chọn nơi ở hiện tại", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!checkBoxAgree.isChecked) {
            Toast.makeText(this, "Vui lòng đồng ý với các điều khoản", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}