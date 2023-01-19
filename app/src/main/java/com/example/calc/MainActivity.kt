package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.calc.DemandFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))
    }

    override fun onSupportNavigateUp(): Boolean {
        val controller = findNavController(R.id.fragmentContainerView)
        return controller.navigateUp() || super.onSupportNavigateUp()
    }
}