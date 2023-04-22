package com.example.calc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.calc.R

fun get(A: Double, B: Double, C: Double): Double {
    return -B / 2 / A
}

class PerfectCompetitionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfect_competition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.button_go)
        val input_Qd: EditText = view.findViewById(R.id.Input_Qd_Olig)

        val input_TC1: EditText = view.findViewById(R.id.input_TC1)
        val input_TC2: EditText = view.findViewById(R.id.input_TC2)
        val input_TC3: EditText = view.findViewById(R.id.input_TC3)
        val input_TC4: EditText = view.findViewById(R.id.input_TC4)
        val input_TC5: EditText = view.findViewById(R.id.input_TC5)
        val input_TC6: EditText = view.findViewById(R.id.input_TC6)

        val out_q1: TextView = view.findViewById(R.id.out_q1)
        val out_q2: TextView = view.findViewById(R.id.out_q2)
        val out_q3: TextView = view.findViewById(R.id.out_q3)
        val out_q4: TextView = view.findViewById(R.id.out_q4)
        val out_q5: TextView = view.findViewById(R.id.out_q5)
        val out_q6: TextView = view.findViewById(R.id.out_q6)

        val out_TC1: TextView = view.findViewById(R.id.out_TC1)
        val out_TC2: TextView = view.findViewById(R.id.out_TC2)
        val out_TC3: TextView = view.findViewById(R.id.out_TC3)
        val out_TC4: TextView = view.findViewById(R.id.out_TC4)
        val out_TC5: TextView = view.findViewById(R.id.out_TC5)
        val out_TC6: TextView = view.findViewById(R.id.out_TC6)

        val out_Q: TextView = view.findViewById(R.id.out_Q)
        val out_P: TextView = view.findViewById(R.id.out_P)

        button.setOnClickListener {

            val TC1_value = getStrWithoutSpace(input_TC1.text.toString())
            val TC2_value = getStrWithoutSpace(input_TC2.text.toString())
            val TC3_value = getStrWithoutSpace(input_TC3.text.toString())
            val TC4_value = getStrWithoutSpace(input_TC4.text.toString())
            val TC5_value = getStrWithoutSpace(input_TC5.text.toString())
            val TC6_value = getStrWithoutSpace(input_TC6.text.toString())

            val Qd_value = getStrWithoutSpace(input_Qd.text.toString())

            val indexesTC1 = getMap(TC1_value)
            val indexesTC2 = getMap(TC2_value)
            val indexesTC3 = getMap(TC3_value)
            val indexesTC4 = getMap(TC4_value)
            val indexesTC5 = getMap(TC5_value)
            val indexesTC6 = getMap(TC6_value)
            val indexesQD = getMap(Qd_value)

            val ad = indexesQD[1]!!
            val bd = indexesQD[0]!!

            val a1 = indexesTC1[0]!!
            val b1 = indexesTC1[1]!!
            val c1 = indexesTC1[2]!!

            val a2 = indexesTC2[0]!!
            val b2 = indexesTC2[1]!!
            val c2 = indexesTC2[2]!!

            val a3 = indexesTC3[0]!!
            val b3 = indexesTC3[1]!!
            val c3 = indexesTC3[2]!!

            val a4 = indexesTC4[0]!!
            val b4 = indexesTC4[1]!!
            val c4 = indexesTC4[2]!!

            val a5 = indexesTC5[0]!!
            val b5 = indexesTC5[1]!!
            val c5 = indexesTC5[2]!!

            val a6 = indexesTC6[0]!!
            val b6 = indexesTC6[1]!!
            val c6 = indexesTC6[2]!!

            var q1 = 0.0
            var q2 = 0.0
            var q3 = 0.0
            var q4 = 0.0
            var q5 = 0.0
            var q6 = 0.0

            if (TC3_value.isEmpty() || TC4_value.isEmpty() || TC5_value.isEmpty() || TC6_value.isEmpty()) {
                q2 = (((-bd / a1 - b1) / (2 * (c1 - 1 / ad)) - b2) / (2 * (c2 - 1 / ad))) /
                        (1 - 1 / ((a1 * 2 * ((c1 - 1 / ad)) * 2 * (c2 - 1 / ad))))
                q1 = ((q2 - bd) / a1 - b1) / (2 * (c1 - 1 / ad))
            } else if (TC1_value.isEmpty() && TC2_value.isEmpty() && TC5_value.isEmpty() && TC6_value.isEmpty()) {
                q4 = (((-bd / a3 - b3) / (2 * (c3 - 1 / ad)) - b4) / (2 * (c4 - 1 / ad))) /
                        (1 - 1 / ((a3 * 2 * ((c3 - 1 / ad)) * 2 * (c4 - 1 / ad))))
                q3 = ((q4 - bd) / a3 - b3) / (2 * (c3 - 1 / ad))
            } else if (TC1_value.isEmpty() && TC2_value.isEmpty() && TC3_value.isEmpty() && TC4_value.isEmpty()) {
                q6 = (((-bd / a5 - b5) / (2 * (c5 - 1 / ad)) - b6) / (2 * (c6 - 1 / ad))) /
                        (1 - 1 / ((a5 * 2 * ((c5 - 1 / ad)) * 2 * (c6 - 1 / ad))))
                q5 = ((q6 - bd) / a5 - b5) / (2 * (c5 - 1 / ad))
            }
            val q = q1 + q2 + q3 + q4 + q5 + q6
            val p = (q - bd) / ad
            val tc1 = a1 + b1 * q1 + c1 * q1 * q1
            val tc2 = a2 + b2 * q2 + c2 * q2 * q2
            val tc3 = a3 + b3 * q3 + c3 * q3 * q3
            val tc4 = a4 + b4 * q4 + c4 * q4 * q4
            val tc5 = a5 + b5 * q5 + c5 * q5 * q5
            val tc6 = a6 + b6 * q6 + c6 * q6 * q6
            out_TC1.setText(cutString(tc1.toString()))
            out_TC2.setText(cutString(tc2.toString()))
            out_TC3.setText(cutString(tc3.toString()))
            out_TC4.setText(cutString(tc4.toString()))
            out_TC5.setText(cutString(tc5.toString()))
            out_TC6.setText(cutString(tc6.toString()))

        }
    }
}