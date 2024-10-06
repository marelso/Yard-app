package io.marelso.shineyard.data

import io.marelso.shineyard.R

enum class PlantAction(
    val title: Int,
    val icon: Int,
    val empty: Int,
    val cta: Int,
    var onCreate: (() -> Unit)? = null,
    var onEdit: (() -> Unit)? = null
) {
    SCHEDULE(
        title = R.string.title_action_schedule,
        icon = R.drawable.ic_schedule,
        empty = R.string.title_action_schedule_empty,
        cta = R.string.title_action_schedule_watering
    ),
    NOTIFICATION(
        title = R.string.title_action_notification,
        icon = R.drawable.ic_notification,
        empty = R.string.title_action_notification_empty,
        cta = R.string.title_action_schedule_notification
    )
}