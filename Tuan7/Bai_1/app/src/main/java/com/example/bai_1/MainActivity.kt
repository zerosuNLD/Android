package com.example.bai_1

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextNumber: EditText
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var listViewNumbers: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các thành phần giao diện
        editTextNumber = findViewById(R.id.editTextNumber)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioSquare = findViewById(R.id.radioSquare)
        buttonShow = findViewById(R.id.buttonShow)
        listViewNumbers = findViewById(R.id.listViewNumbers)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            textViewError.visibility = View.GONE
            val input = editTextNumber.text.toString()

            if (input.isEmpty()) {
                textViewError.text = "Vui lòng nhập một số nguyên dương."
                textViewError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n < 0) {
                textViewError.text = "Dữ liệu không hợp lệ."
                textViewError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val numbers = when {
                radioEven.isChecked -> (0..n step 2).toList()
                radioOdd.isChecked -> (1..n step 2).toList()
                radioSquare.isChecked -> generateSequence(0) { it + 1 }
                    .map { it * it }
                    .takeWhile { it <= n }
                    .toList()
                else -> {
                    textViewError.text = "Vui lòng chọn loại số."
                    textViewError.visibility = View.VISIBLE
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listViewNumbers.adapter = adapter
        }
    }
}