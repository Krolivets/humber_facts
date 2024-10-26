package com.example.numberfacts.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.numberfacts.ui.screen.DetailScreen
import com.example.numberfacts.ui.screen.MainScreen
import com.example.numberfacts.viewmodel.MainViewModel

@Composable
fun AppNavHost(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(viewModel, navigateToDetail = { fact ->
                navController.navigate("detail/${fact.id}")
            })
        }
        composable("detail/{id}") { backStackEntry ->
            val factId = backStackEntry.arguments?.getString("id")?.toIntOrNull()

            LaunchedEffect(factId) {
                factId?.let { viewModel.loadFactById(it) }
            }

            val fact by viewModel.factById.observeAsState()

            if (fact != null) {
                DetailScreen(numberFact = fact!!, onBack = { navController.popBackStack() })
            } else {
                Text("Loading...")
            }
        }
    }
}
