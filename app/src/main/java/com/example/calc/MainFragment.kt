package com.example.calc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.calc.R


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sup_dem = view.findViewById<Button>(R.id.button_sup_dem)
        val mon = view.findViewById<Button>(R.id.button_mon)
        val perf_comp = view.findViewById<Button>(R.id.button_perf_comp)
        val fins = view.findViewById<Button>(R.id.button_fin)
        val olig = view.findViewById<Button>(R.id.button_oligopoly)
        val controller = findNavController()
        sup_dem.setOnClickListener { controller.navigate(R.id.demandFragment) }
        mon.setOnClickListener { controller.navigate(R.id.monopolyFragment) }
        perf_comp.setOnClickListener { controller.navigate(R.id.perfectCompetitionFragment) }
        fins.setOnClickListener { controller.navigate(R.id.financesFragment) }
        olig.setOnClickListener { controller.navigate(R.id.oligopolyFragment) }
    }
}