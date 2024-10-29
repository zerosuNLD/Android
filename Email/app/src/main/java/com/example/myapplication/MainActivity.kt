package com.example.calculatorapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstNumber = 0.0
    private var secondNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.textViewResult)

        // Gán sự kiện cho các nút số
        setNumberClickListener(R.id.button0, "0")
        setNumberClickListener(R.id.button1, "1")
        setNumberClickListener(R.id.button2, "2")
        setNumberClickListener(R.id.button3, "3")
        setNumberClickListener(R.id.button4, "4")
        setNumberClickListener(R.id.button5, "5")
        setNumberClickListener(R.id.button6, "6")
        setNumberClickListener(R.id.button7, "7")
        setNumberClickListener(R.id.button8, "8")
        setNumberClickListener(R.id.button9, "9")

        // Gán sự kiện cho các phép tính
        setOperatorClickListener(R.id.buttonPlus, "+")
        setOperatorClickListener(R.id.buttonMinus, "-")
        setOperatorClickListener(R.id.buttonMultiply, "*")
        setOperatorClickListener(R.id.buttonDivide, "/")

        // Nút dấu chấm thập phân
        findViewById<TextView>(R.id.buttonDot).setOnClickListener {
            if (!currentInput.contains(".")) {
                currentInput += "."
                textViewResult.text = currentInput
            }
        }

        // Nút bằng để tính toán kết quả
        findViewById<TextView>(R.id.buttonEqual).setOnClickListener {
            if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
                secondNumber = currentInput.toDouble()
                val result = calculateResult(firstNumber, secondNumber, operator)
                textViewResult.text = result.toString()
                currentInput = ""
                operator = ""
            }
        }
    }

    private fun setNumberClickListener(buttonId: Int, number: String) {
        findViewById<TextView>(buttonId).setOnClickListener {
            currentInput += number
            textViewResult.text = currentInput
        }
    }

    private fun setOperatorClickListener(buttonId: Int, operatorSymbol: String) {
        findViewById<TextView>(buttonId).setOnClickListener {
            if (currentInput.isNotEmpty()) {
                firstNumber = currentInput.toDouble()
                operator = operatorSymbol
                currentInput = ""
            }
        }
    }

    private fun calculateResult(num1: Double, num2: Double, operator: String): Double {
        return when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else 0.0
            else -> 0.0
        }
    }
}
