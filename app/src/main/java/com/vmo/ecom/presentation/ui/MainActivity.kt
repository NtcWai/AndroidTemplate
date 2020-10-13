package com.vmo.ecom.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.vmo.ecom.R

class MainActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) =
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
    }

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavHost()
    }

    private fun initNavHost() {
        val navGraph = navController.graph
        navGraph.startDestination = R.id.shopListFragment
        navController.graph = navGraph
    }
}
