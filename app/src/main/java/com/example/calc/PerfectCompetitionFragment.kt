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
import kotlin.math.abs

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

        val out_q1: TextView = view.findViewById(R.id.out_q1)
        val out_q2: TextView = view.findViewById(R.id.out_q2)

        val out_TC1: TextView = view.findViewById(R.id.out_TC1)
        val out_TC2: TextView = view.findViewById(R.id.out_TC2)

        val out_Q: TextView = view.findViewById(R.id.out_Q)
        val out_P: TextView = view.findViewById(R.id.out_P)

        button.setOnClickListener {

            val TC1_value = getStrWithoutSpace(input_TC1.text.toString())
            val TC2_value = getStrWithoutSpace(input_TC2.text.toString())

            val Qd_value = getStrWithoutSpace(input_Qd.text.toString())

            val indexesTC1 = getMap(TC1_value)
            val indexesTC2 = getMap(TC2_value)
            val indexesQD = getMap(Qd_value)

            val ad = indexesQD[1]!!
            val bd = indexesQD[0]!!

            val a1 = indexesTC1[0]!!
            val b1 = indexesTC1[1]!!
            val c1 = indexesTC1[2]!!

            val a2 = indexesTC2[0]!!
            val b2 = indexesTC2[1]!!
            val c2 = indexesTC2[2]!!

            var q1 = 0.0
            var q2 = 0.0
            q1 =
                (((0 - bd / ad - b2) / (2 * (c2 - 1 / ad)) / ad - bd / ad - b1) / (2 * (c1 - 1 / ad))) / (1 - 1 / ad / (2 * (c2 - 1 / ad)) / ad / (2 * (c1 - 1 / ad)))
            q2 = (q1 / ad - bd / ad - b2) / (2 * (c2 - 1 / ad))
            q1 = abs(q1)
            q2 = abs(q2)

            val q = q1 + q2
            val p = (q - bd) / ad
            val tc1 = a1 + b1 * q1 + c1 * q1 * q1
            val tc2 = a2 + b2 * q2 + c2 * q2 * q2
            out_q1.setText(cutString(q1.toString()))
            out_q2.setText(cutString(q2.toString()))
            out_TC1.setText(cutString(tc1.toString()))
            out_TC2.setText(cutString(tc2.toString()))
            out_Q.setText(cutString(q.toString()))
            out_P.setText(cutString(p.toString()))
        }
    }
}