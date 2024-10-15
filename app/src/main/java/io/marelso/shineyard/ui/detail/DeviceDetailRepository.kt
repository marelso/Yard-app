package io.marelso.shineyard.ui.detail

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.marelso.shineyard.data.WaterSchedule

class DeviceDetailRepository(private val database: DatabaseReference) {
    fun lastPumpActivateDateTime(onDataChange: (String) -> Unit) {
        database.child("sensors/lastPumpActivateDateTime").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onDataChange(snapshot.getValue(String::class.java).orEmpty())
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun currentMoisturePercent(onDataChange: (Int) -> Unit) {
        database.child("sensors/currentMoisturePercent").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onDataChange(snapshot.getValue(Int::class.java) ?: 0)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun currentWaterVolume(onDataChange: (Double) -> Unit) {
        database.child("sensors/currentWaterVolume").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onDataChange(snapshot.getValue(Double::class.java) ?: 0.00)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun maximumWaterVolume(onDataChange: (Double) -> Unit) {
        database.child("sensors/maximumWaterVolume").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onDataChange(snapshot.getValue(Double::class.java) ?: 0.00)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun pumpActiveStatus(onDataChange: (Boolean) -> Unit) {
        database.child("sensors/pumpActiveStatus").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onDataChange(snapshot.getValue(Boolean::class.java) ?: false)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun changePumpStatus(update: Boolean) {
        database.updateChildren(mapOf<String, Any>("pumpActiveStatus" to update))
    }

    fun registerSchedule(schedule: WaterSchedule) {
        database.push().setValue(schedule)
    }
}