package com.github.mslocombe.pionandroidtechtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.mslocombe.pionandroidtechtest.navigation.AppNavHost
import com.github.mslocombe.pionandroidtechtest.ui.theme.SBTechincalTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SBTechincalTestTheme {
                AppNavHost()
            }
        }
    }
}