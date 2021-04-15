package com.mart.composeproduct

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import com.mart.composeproduct.ui.home.Home
import com.mart.composeproduct.ui.newproduct.NewProduct
import com.mart.composeproduct.ui.productdetail.ProductDetail

@Composable
fun NavHostManager(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { Home(navController) }

        composable(Screen.AddProduct.route) { NewProduct(navController) }

        composable(
            Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            ProductDetail(navController, backStackEntry.arguments?.getInt("productId"))
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen(HOME)
    object AddProduct : Screen(NEW_PRODUCT)
    object ProductDetail : Screen("$PRODUCT_DETAIL/{productId}")
}

fun NavHostController.navigateToProductDetail(productId: Int) {
    navigate("$PRODUCT_DETAIL/$productId")
}

const val HOME = "home"
const val PRODUCT_DETAIL = "product_detail"
const val NEW_PRODUCT = "new_product"