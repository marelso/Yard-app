package io.marelso.shineyard.ui.list

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.marelso.shineyard.data.Device
import io.marelso.shineyard.data.toEntity

class DeviceListRepository(private val database: DatabaseReference) {
    fun getDevice(deviceId: String, onDataChange: (Device?) -> Unit) {
        database.child(deviceId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onDataChange(snapshot.getValue(Device::class.java))
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}