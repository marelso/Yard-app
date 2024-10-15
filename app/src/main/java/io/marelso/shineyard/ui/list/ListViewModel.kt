package io.marelso.shineyard.ui.list

import androidx.lifecycle.ViewModel
import io.marelso.shineyard.data.Account
import io.marelso.shineyard.data.Device
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListViewModel(
    private val currentAccount: Account,
    private val repository: DeviceListRepository
): ViewModel() {
    private val _devices = MutableStateFlow<List<Device>>(mutableListOf())
    val devices: StateFlow<List<Device>> = _devices

    init {
        currentAccount.accountDevices.forEach { accountDevice ->
            accountDevice.id?.let {
                repository.getDevice(it) {
                    it?.let { device ->
                        putDeviceIntoList(device.apply {
                            id = accountDevice.id
                        })
                    }
                }
            }
        }
    }

    private fun putDeviceIntoList(device: Device) {
        _devices.tryEmit(
            _devices.value.toMutableList().apply {
                val existingDeviceIndex = indexOfFirst { it.id == device.id } // Assuming each Device has a unique id
                if (existingDeviceIndex != -1) {
                    this[existingDeviceIndex] = device
                } else {
                    add(device)
                }
            }
        )
    }
}