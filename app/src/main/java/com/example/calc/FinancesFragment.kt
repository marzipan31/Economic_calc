package com.example.calc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.calc.R
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.ceil

fun pow(x: Double, y: Int): Double {
    var ans = 1.0
    var st = y
    while (st > 0) {
        ans *= x
        st -= 1
    }
    return ans
}

fun cutString1(str: String): String {
    var res = ""
    var i = 0
    while (!str[i].equals('.')) {
        res += str[i]
        i += 1
    }
    res += '.'
    var cnt = 0
    i += 1
    while (cnt < 5 && i < str.length) {
        cnt += 1
        res += str[i]
        i += 1
    }
    return res
}

class FinancesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_finances, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.button_ready)

        button.setOnClickListener {
            val output_input_X: EditText = view.findViewById(R.id.input_output_X)
            val output_input_S: EditText = view.findViewById(R.id.input_output_S)
            val output_input_P: EditText = view.findViewById(R.id.input_output_P)
            val output_input_N: EditText = view.findViewById(R.id.input_output_N)

            val X_value = output_input_X.text.toString()
            val S_value = output_input_S.text.toString()
            val P_value = output_input_P.text.toString()
            val N_value = output_input_N.text.toString()

            if (X_value.isEmpty()) {
                val S = S_value.toDouble()
                val P = P_value.toDouble()
                val N = N_value.toInt()
                val X = S * (P + P / (pow(1 + P, N) - 1))
                output_input_X.setText(cutString(X.toString()))
            } else if (S_value.isEmpty()) {
                val X = X_value.toDouble()
                val P = P_value.toDouble()
                val N = N_value.toInt()
                val S = X / (P + P / (pow(1 + P, N) - 1))
                output_input_S.setText(cutString(S.toString()))
            } else if (P_value.isEmpty()) {
                val X = X_value.toDouble()
                val S = S_value.toDouble()
                val N = N_value.toInt()
                var ans = 0.0
                var mn = 1e18
                for (p in 0..100000) {
                    val Ps = "0." + p.toString()
                    val P = Ps.toDouble()
                    val res = S * (P + P / (pow(1 + P, N) - 1))
                    val cur = abs(res - X)
                    if (cur < mn) {
                        ans = P
                        mn = cur
                    }
                }
                output_input_P.setText(cutString1(ans.toString()))
            } else if (N_value.isEmpty()) {
                val X = X_value.toDouble()
                val P = P_value.toDouble()
                val S = S_value.toDouble()
                val N = ceil((ln(X / (X - S * P)) / ln(1 + P))).toInt()
                output_input_N.setText(N.toString())
            }
        }
    }
}