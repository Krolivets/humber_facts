package com.example.numberfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.numberfacts.ui.navigation.AppNavHost
import com.example.numberfacts.ui.theme.NumberFactsTheme
import com.example.numberfacts.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val userViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumberFactsTheme {
                AppNavHost(userViewModel)
            }
        }
    }
}
