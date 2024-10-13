package io.marelso.shineyard.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.marelso.shineyard.ui.detail.DetailScreenHoisting
import io.marelso.shineyard.ui.detail.DetailViewModel
import io.marelso.shineyard.ui.login.LoginScreenHoisting
import io.marelso.shineyard.ui.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigationGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            val viewModel = koinViewModel<LoginViewModel>()
            LoginScreenHoisting(
                viewModel = viewModel,
                onLoginSuccess = {
                    navHostController.navigate(Routes.navigate(to = Routes.Detail))
                }
            )
        }

        composable(route = Routes.Detail.route) {
            val viewModel = koinViewModel<DetailViewModel>()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DetailScreenHoisting(
                    viewModel = viewModel,
                    redirectToSchedule = {
                        navHostController.navigate(
                            route = Routes.navigate(to = Routes.Schedule)
                        )
                    }
                )
            }
        }

        composable(route = Routes.Schedule.route) {

        }
    }
}