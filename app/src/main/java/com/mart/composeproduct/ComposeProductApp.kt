package com.mart.composeproduct

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@Composable
fun ComposeProductApp() {
    val navController = rememberNavController()

    var isFabVisible by remember { mutableStateOf(true) }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        val currentKeyRoute = controller.currentBackStackEntry?.arguments?.get(KEY_ROUTE)
        isFabVisible = currentKeyRoute == Screen.Home.route
    }

    ComposeProductApp(navController, isFabVisible)
}

@Composable
fun ComposeProductApp(
    navController: NavHostController,
    isFabVisible: Boolean
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) },
        floatingActionButton = {
            if (isFabVisible) {
                MyFloatingActionButton(navController)
            }
        }
    ) {
        NavHostManager(navController)
    }
}

@Composable
fun MyFloatingActionButton(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate(Screen.AddProduct.route) },
        content = { Icon(Icons.Filled.Add, "FAB") },
        modifier = Modifier.padding(16.dp)
    )
}