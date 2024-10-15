package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var textResult: TextView
    var state = 1
    var op = ""
    var op1 = 0
    var op2 = 0
    var oldCal = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.textReuslt)
        listOf(
            R.id.CE_btn, R.id.C_btn, R.id.BS_btn,
            R.id.seven_btn, R.id.eight_btn, R.id.night_btn,
            R.id.four_btn, R.id.five_btn, R.id.six_btn,
            R.id.one_btn, R.id.two_btn, R.id.three_btn, R.id.zero_btn,
            R.id.add_btn, R.id.minus_btn, R.id.mul_btn, R.id.divide_btn,
            R.id.equals_btn
        ).forEach { id -> findViewById<Button>(id).setOnClickListener(this) }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.CE_btn -> clearAll()
            R.id.C_btn -> clearEntry()
            R.id.BS_btn -> backspace()
            R.id.equals_btn -> onEqual()
            R.id.add_btn -> onCal("+")
            R.id.minus_btn -> onCal("-")
            R.id.mul_btn -> onCal("*")
            R.id.divide_btn -> onCal("/")
            else -> {
                val digit = when (p0?.id) {
                    R.id.seven_btn -> 7
                    R.id.eight_btn -> 8
                    R.id.night_btn -> 9
                    R.id.four_btn -> 4
                    R.id.five_btn -> 5
                    R.id.six_btn -> 6
                    R.id.one_btn -> 1
                    R.id.two_btn -> 2
                    R.id.three_btn -> 3
                    R.id.zero_btn -> 0
                    else -> return
                }
                addDigit(digit)
            }
        }
    }

    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            displayResult(op1)
        } else {
            op2 = op2 * 10 + c
            displayResult(op2)
        }
    }

    fun onCal(c: String) {
        calculate()
        oldCal = c
        state = 2
        op2 = 0
    }

    fun onEqual() {
        calculate()
        reset()
    }

    fun calculate() {
        op1 = when (oldCal) {
            "+" -> op1 + op2
            "-" -> op1 - op2
            "*" -> op1 * op2
            "/" -> if (op2 != 0) op1 / op2 else 0
            else -> op1
        }
        displayResult(op1)
    }

    fun displayResult(c: Int) {
        textResult.text = "$c"
    }

    fun clearAll() {
        reset()
        displayResult(0)
    }

    fun clearEntry() {
        if (state == 1) op1 = 0 else op2 = 0
        displayResult(0)
    }

    fun backspace() {
        if (state == 1) {
            op1 /= 10
            displayResult(op1)
        } else {
            op2 /= 10
            displayResult(op2)
        }
    }

    private fun reset() {
        op1 = 0
        op2 = 0
        state = 1
        op = ""
        oldCal = ""
    }
}
