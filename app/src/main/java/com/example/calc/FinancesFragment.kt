package com.example.calc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.calc.R

fun pow(x: Int, y: Int): Int {
    var ans = 1
    var st = y
    while (st > 0) {
        ans *= x
        st -= 1
    }
    return ans
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


        }
    }
}