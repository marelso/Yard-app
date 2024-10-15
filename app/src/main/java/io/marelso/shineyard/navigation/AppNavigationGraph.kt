package io.marelso.shineyard.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.marelso.shineyard.ui.detail.DetailScreenHoisting
import io.marelso.shineyard.ui.detail.DetailViewModel
import io.marelso.shineyard.ui.detail.DeviceDetailRepository
import io.marelso.shineyard.ui.list.ListScreenHoisting
import io.marelso.shineyard.ui.list.ListViewModel
import io.marelso.shineyard.ui.login.LoginScreenHoisting
import io.marelso.shineyard.ui.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavigationGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            LoginScreenHoisting(viewModel = koinViewModel<LoginViewModel>(parameters = {
                parametersOf({ navHostController.navigate(Routes.navigate(to = Routes.List)) })
            }))
        }

        composable(route = Routes.List.route) {
            ListScreenHoisting(
                viewModel = koinViewModel<ListViewModel>(),
                redirectToDetail = {
                    navHostController.navigate(
                        Routes.navigate(
                            to = Routes.Detail,
                            KEY_DEVICE_ID to it
                        )
                    )
                }
            )
        }

        composable(
            route = Routes.Detail.route,
            arguments = listOf(navArgument(KEY_DEVICE_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val repository: DeviceDetailRepository = koinInject(
                parameters = { parametersOf(backStackEntry.arguments?.getString(KEY_DEVICE_ID).orEmpty()) }
            )
            val viewModel = koinViewModel<DetailViewModel>(
                parameters = {
                    parametersOf(backStackEntry.arguments?.getString(KEY_DEVICE_ID).orEmpty(), repository)
                }
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DetailScreenHoisting(
                    viewModel = viewModel,
                    redirectToSchedule = {
                        navHostController.navigate(
                            route = Routes.navigate(to = Routes.Schedule)
                        )
                    },
                    navigateBack = {
                        navHostController.popBackStack()
                    }
                )
            }
        }

        composable(route = Routes.Schedule.route) {

        }
    }
}