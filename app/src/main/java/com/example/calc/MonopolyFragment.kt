package com.example.calc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.calc.R
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sqrt

class MonopolyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_monopoly, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val input_QD: EditText = view.findViewById(R.id.input_QD)
        val input_TC: EditText = view.findViewById(R.id.input_TC)

        val output_MC: TextView = view.findViewById(R.id.outputMC)
        val output_MR: TextView = view.findViewById(R.id.outputMR)
        val output_AVC: TextView = view.findViewById(R.id.outputAVC)
        val output_VC: TextView = view.findViewById(R.id.outputVC)
        val output_Q: TextView = view.findViewById(R.id.outputQ)
        val output_E: TextView = view.findViewById(R.id.outputE)
        val output_P: TextView = view.findViewById(R.id.outputP)
        val output_MC_VAL: TextView = view.findViewById(R.id.outputMC_VAL)
        val output_VC_VAL: TextView = view.findViewById(R.id.outputVC_VAL)
        val output_AVC_VAL: TextView = view.findViewById(R.id.outputAVC_VAL)
        val output_Profit: TextView = view.findViewById(R.id.output_Profit)
        val output_B_Potr: TextView = view.findViewById(R.id.output_B_Potr)
        val output_TC: TextView = view.findViewById(R.id.output_TC)
        val output_TC_eq: TextView = view.findViewById(R.id.output_TC_eq)

        val button: Button = view.findViewById(R.id.calculate)

        button.setOnClickListener {
            val QD_value = getStrWithoutSpace(input_QD.text.toString())
            val TC_value = getStrWithoutSpace(input_TC.text.toString())
            val indexesQD = getMap(QD_value)
            val indexesTC = getMap(TC_value)
            var a_QD = indexesQD[1]!!
            var b_QD = indexesQD[0]!!
            var a_TC = indexesTC[2]!!
            var b_TC = indexesTC[1]!!
            var c_TC = indexesTC[0]!!
            var Q = (b_QD / a_QD + b_TC) / (2 * (1 / a_QD - a_TC))
            var P = (Q - b_QD) / a_QD
            output_TC_eq.setText("=")
            val resTC = a_TC * Q * Q + b_TC * Q + c_TC
            output_TC.setText(cutString(resTC.toString()))
            val del = P / Q
            val E = del * (2 * indexesQD[2].toString().toDouble() * P + indexesQD[1].toString()
                .toDouble())
            output_P.setText(cutString(P.toString()))
            output_Q.setText(cutString(Q.toString()))
            output_E.setText(cutString(E.toString()))
            val mcA = 2 * a_TC
            val mcB = b_TC
            var resMC = cutString(mcA.toString())
            resMC += "*Q"
            if (mcB >= 0) {
                resMC += "+"
            }
            resMC += cutString(mcB.toString())
            output_MC.setText(resMC)
            val resMC_VAL = mcA * Q + mcB
            output_MC_VAL.setText("=" + cutString(resMC_VAL.toString()))
            val resMR = (2 / a_QD) * Q - b_QD / a_QD
            output_MR.setText(cutString(resMR.toString()))
            var resAVC = cutString(a_TC.toString())
            resAVC += "*Q"
            if (b_TC >= 0) {
                resAVC += "+"
            }
            resAVC += cutString(b_TC.toString())
            output_AVC.setText(resAVC)
            val resAVC_VAL = a_TC * Q + b_TC
            output_AVC_VAL.setText("=" + cutString(resAVC_VAL.toString()))
            var resVC = cutString(a_TC.toString())
            resVC += "*Q^2"
            if (b_TC >= 0) {
                resVC += "+"
            }
            resVC += cutString(b_TC.toString())
            resVC += "*Q"
            output_VC.setText(resVC)
            val resVC_VAL = a_TC * Q * Q + b_TC * Q
            output_VC_VAL.setText("=" + cutString(resVC_VAL.toString()))
            val profit = P * Q - resTC
            output_Profit.setText(cutString(profit.toString()))
            val win = (b_QD - P) * Q / 2
            output_B_Potr.setText(cutString(win.toString()))
        }
    }
}