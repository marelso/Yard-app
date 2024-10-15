package io.marelso.shineyard.navigation

const val KEY_DEVICE_ID = "DEVICE_ID"

sealed class Routes(val title: String, val route: String) {
    data object List: Routes(title = "Plants list page", route = "list/")
    data object Detail: Routes(title = "Plant detail page", route = "detail?device={$KEY_DEVICE_ID}")
    data object Schedule: Routes(title = "Plant watering schedule page", route = "schedule/")
    data object Login: Routes(title = "Plant watering login page", route = "login/")

    companion object {
        fun navigate(to: Routes, vararg parameters: Pair<String, String>?): String {
            return parameters.fold(to.route) { route, parameter ->
                parameter?.let {
                    route.replace("{${it.first}}", it.second)
                } ?: route
            }
        }
    }
}