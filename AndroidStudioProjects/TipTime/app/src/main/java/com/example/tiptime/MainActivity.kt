package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // 클래스에서 결합 객체의 최상위 변수를 선언
    // activity_main.xml은 ActivityMainBinding이 되고
    // binding.textView로 @id/text_view에 액세스할 수 있음
    // lateinit 키워드는 새로운 키워드로, 코드가 변수를 사용하기 전에 먼저 초기화할 것임을 확인
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activity_main.xml 레이아웃에서 Views에 액세스하는 데 사용할 binding 객체를 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // 루트는 모든 뷰에 연결되어 있음

        binding.calculateButton.setOnClickListener { calculateTip() }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull() // input cost

        if (cost == null) {
            Toast.makeText(applicationContext, "invalid input", Toast.LENGTH_SHORT).show()
            displayResult(0.0)
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost

        // round up?
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        displayResult(tip)
    }

    private fun displayResult(tip: Double) {
        // 시스템이 자동으로 통화 형식을 지정
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}