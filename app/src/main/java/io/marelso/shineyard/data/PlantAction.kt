package io.marelso.shineyard.data

import io.marelso.shineyard.R

enum class PlantAction(
    val title: Int, var onClick: (() -> Unit)? = null
) {
    SCHEDULE(R.string.title_action_schedule_watering)
}
