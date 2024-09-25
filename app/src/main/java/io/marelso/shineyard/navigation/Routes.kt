package io.marelso.shineyard.navigation

sealed class Routes(val title: String, val route: String) {
    data object Detail: Routes(title = "Plant detail page", route = "detail/")
    data object Schedule: Routes(title = "Plant watering schedule page", route = "schedule/")

    companion object {
        fun navigate(to: Routes): String {
            return when (to) {
                Detail -> to.route
                Schedule -> to.route
            }
        }
    }
}