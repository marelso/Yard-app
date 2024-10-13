package io.marelso.shineyard.navigation

sealed class Routes(val title: String, val route: String) {
    data object Detail: Routes(title = "Plant detail page", route = "detail/")
    data object Schedule: Routes(title = "Plant watering schedule page", route = "schedule/")
    data object Login: Routes(title = "Plant watering login page", route = "login/")

    companion object {
        fun navigate(to: Routes): String {
            return when (to) {
                Detail -> to.route
                Schedule -> to.route
                Login -> to.route
            }
        }
    }
}