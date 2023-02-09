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

fun getMap(givenString: String): Map<Int, Double> {
    val res = mutableMapOf<Int, Double>()
    var current_string = ""
    val len = givenString.length
    for (j in 0..2) {
        res[j] = 0.0
    }
    var i = 0
    while (i < len) {
        if (givenString[i].equals('P') || givenString[i].equals('Q')) {
            if (i + 2 < len && givenString[i + 1].equals('^') && givenString[i + 2].equals('2')) {
                if (current_string == "+" || current_string == "-" || current_string.isEmpty()) {
                    current_string += "1"
                }
                if (current_string[0].equals('+')) {
                    current_string = current_string.drop(1)
                }
                val cur_value = current_string.toDouble();
                i += 2
                res[2] = cur_value
                current_string = ""
            } else {
                if (current_string == "+" || current_string == "-" || current_string.isEmpty()) {
                    current_string += "1"
                }
                if (current_string[0].equals('+')) {
                    current_string = current_string.drop(1)
                }
                val cur_value = current_string.toDouble();
                current_string = ""
                res[1] = cur_value
            }
        } else if (i == len - 1 || givenString[i + 1].equals('+') || givenString[i + 1].equals('-')) {
            current_string += givenString[i]
            if (current_string.equals('+') || current_string.equals('-') || current_string.isEmpty()) {
                current_string += "1"
            }
            val cur_value = current_string.toDouble();
            res[0] = cur_value
            current_string = ""
        } else {
            current_string += givenString[i]
        }
        i += 1
    }
    return res
}

fun getStrWithoutSpace(givenString: String): String {
    var res = ""
    for (el in givenString) {
        if (!el.equals(' ')) {
            if (el.equals('p')) {
                res += 'P'
            } else if (el.equals('q')) {
                res += 'Q'
            } else {
                res += el
            }
        }
    }
    return res
}

fun solveOfEquation(indexes: List<Double>): Double {
    val a = indexes[2]
    val b = indexes[1]
    val c = indexes[0]
    if (a == 0.0) {
        if (b == 0.0) {
            return 1e12
        }
        return -c / b
    }
    val d = b * b - 4 * a * c
    if (d < 0) {
        return 1e12
    }
    return (-b + sqrt(d)) / (2 * a)
}

fun cutString(str: String): String {
    var res = ""
    var i = 0
    while (!str[i].equals('.')) {
        res += str[i]
        i += 1
    }
    res += '.'
    var cnt = 0
    i += 1
    while (cnt < 3 && i < str.length) {
        cnt += 1
        res += str[i]
        i += 1
    }
    return res
}

fun getSqureForQd(indexes: List<Double>, P: Double, Q: Double): Double {
    var a = indexes[2]
    var b = indexes[1]
    var c = indexes[0]
    if (a == 0.0) {
        return (c - P) * Q / 2.0
    }
    return c * P + 3.0 * P * P + c * P * P * P / 3.0 - P * Q
}

fun getSquareForQs(indexes: List<Double>, P: Double, Q: Double): Double {
    var a = indexes[2]
    var b = indexes[1]
    var c = indexes[0]
    if (a == 0.0) {
        return (P - c) * Q / 2.0 + c * Q
    }
    return P * Q - (c * P + 3.0 * P * P + c * P * P * P / 3.0)
}

class DemandFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_demand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: Button = view.findViewById(R.id.button_go_calc)

        val input_Qs: EditText = view.findViewById(R.id.input_Qs)
        val input_Qd: EditText = view.findViewById(R.id.input_Qd)

        val outputP: TextView = view.findViewById(R.id.outputP)
        val outputQ: TextView = view.findViewById(R.id.outputQ)
        val outputE: TextView = view.findViewById(R.id.outputE)
        val outputBPotr: TextView = view.findViewById(R.id.outputBPotr)
        val outputBProizv: TextView = view.findViewById(R.id.outputBProizv)
        val outputTX: TextView = view.findViewById(R.id.outputTX)
        val outputPGov: TextView = view.findViewById(R.id.outputPGov)
        val outputQGov: TextView = view.findViewById(R.id.outputQGov)
        val outputG: TextView = view.findViewById(R.id.outputG)
        val outputEGov: TextView = view.findViewById(R.id.outputEGov)
        val outputBPotrGov: TextView = view.findViewById(R.id.outputBPotrGov)
        val outputBProizvGov: TextView = view.findViewById(R.id.outputBProizvGov)
        val outputSocialWGOV: TextView = view.findViewById(R.id.outputSocialWGOV)
        val outputSocialW: TextView = view.findViewById(R.id.outputSocialW)

        button.setOnClickListener {
            if (input_Qs.text.isEmpty() || input_Qd.text.isEmpty()) {
                Toast.makeText(
                    view.context, "Одно из полей пустое",
                    Toast.LENGTH_LONG
                ).show()
            } else {
//                Toast.makeText(
//                    view.context, "Успешно",
//                    Toast.LENGTH_LONG
//                ).show()
                val Qs_value = getStrWithoutSpace(input_Qs.text.toString())
                val Qd_value = getStrWithoutSpace(input_Qd.text.toString())
                val indexesQs = getMap(Qs_value)
                val indexesQd = getMap(Qd_value)
                val res = mutableListOf(0.0, 0.0, 0.0)
                for (i in 0..2) {
                    res[i] += indexesQd[i].toString().toDouble()
                    res[i] -= indexesQs[i].toString().toDouble()
                }
                val res_Qd =
                    indexesQd[0].toString() + ", " + indexesQd[1].toString() + ", " + indexesQd[2].toString()
                val res_Qs =
                    indexesQs[0].toString() + ", " + indexesQs[1].toString() + ", " + indexesQs[2].toString()
                val res_indexes =
                    res[0].toString() + ", " + res[1].toString() + ", " + res[2].toString()
                Toast.makeText(
                    view.context, res_Qs + "\n" + res_Qd + "\n" + res_indexes,
                    Toast.LENGTH_LONG
                ).show()
                val p = solveOfEquation(res)
                val rsQs = mutableListOf(0.0, 0.0, 0.0)
                rsQs[0] = indexesQs[0]!!
                rsQs[1] = indexesQs[1]!!
                rsQs[2] = indexesQs[2]!!
                val rsQd = mutableListOf(0.0, 0.0, 0.0)
                rsQd[0] = indexesQd[0]!!
                rsQd[1] = indexesQd[1]!!
                rsQd[2] = indexesQd[2]!!
                if (p == 1e12) {
                    Toast.makeText(
                        view.context, "ОШИБКА!!!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val q = indexesQd[2].toString().toDouble() * p * p + indexesQd[1].toString()
                        .toDouble() * p + indexesQd[0].toString().toDouble()
                    outputP.setText(cutString(p.toString()))
                    outputQ.setText(cutString(q.toString()))
                    if (q == 0.0) {
                        outputE.setText("-")
                    } else {
                        val del = p / q
                        val e = del * (2 * indexesQd[2].toString().toDouble() * p + indexesQd[1].toString()
                                .toDouble())
                        outputE.setText(cutString(e.toString()))
                    }
                    outputBProizv.setText(cutString(getSquareForQs(rsQs, p, q).toString()))
                    outputBPotr.setText(cutString(getSqureForQd(rsQd, p, q).toString()))
                    val got = getSquareForQs(rsQs, p, q) + getSqureForQd(rsQd, p, q)
                    outputSocialW.setText(cutString(got.toString()))
                }
                val a1 = rsQs[2]
                val b1 = rsQs[1]
                val c1 = rsQs[0]
                val a2 = rsQd[2]
                val b2 = rsQd[1]
                val c2 = rsQd[0]
            //tx = (-c1 - P * b1 + c2 + P * b2) / b1
                //b1 * tx + b1 * P + c1 = b2 * P + c2
                //((b2 * P + c2) * (-c1 - P * b1 + c2 + P * b2)) / b1
                //P = (-c1 * b2 + 2 * b2 * c2 - b1 * c2) / (2 * (-(b2 * b2 - b1 * b2)))
                val Pgov = (-c1 * b2 + 2 * b2 * c2 - b1 * c2) / (2 * (-(b2 * b2 - b1 * b2)))
                outputPGov.setText(cutString(Pgov.toString()))
                val tx = -(-c1 - Pgov * b1 + c2 + Pgov * b2) / b1;
                outputTX.setText(cutString(tx.toString()))
                val Qgov = c2 + b2 * Pgov
                outputQGov.setText(cutString(Qgov.toString()))
                val G = tx * Qgov
                outputG.setText(cutString(G.toString()))
                val Egov = b2 * (Pgov / Qgov)
                outputEGov.setText(cutString(Egov.toString()))
                val BPotr = (c2 - Pgov) * Qgov / 2.0
                val Bproizv = (Qgov * (Pgov - tx)) / 2.0
                outputBPotrGov.setText(cutString(BPotr.toString()))
                outputBProizvGov.setText(cutString(Bproizv.toString()))
                val got = BPotr + Bproizv + G
                outputSocialWGOV.setText(cutString(got.toString()))
            }
        }
    }
}