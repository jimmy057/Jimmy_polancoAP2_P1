package com.example.jimmy_polancoap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jimmy_polancoap2_p1.presentacion.edit.HuacalEditScreen
import com.example.jimmy_polancoap2_p1.presentacion.list.HuacalListScreen
import com.example.jimmy_polancoap2_p1.ui.theme.Jimmy_polancoAP2_P1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jimmy_polancoAP2_P1Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        NavHost(
            navController = navController,
            startDestination = "list",
            modifier = Modifier.padding(padding).fillMaxSize()
        ) {
            composable("list") {
                HuacalListScreen(
                    onEdit = { id -> navController.navigate("edit/$id") },
                    onAdd = { navController.navigate("edit/-1") }
                )
            }

            composable(
                route = "edit/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: -1
                HuacalEditScreen(
                    huacalId = if (id == -1) null else id,
                    onSave = { navController.popBackStack() },
                    onCancel = { navController.popBackStack() }
                )
            }
        }
    }
}