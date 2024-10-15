package io.marelso.shineyard.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.marelso.shineyard.data.Device
import io.marelso.shineyard.ui.list.components.PlantCard

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListScreenHoisting(viewModel: ListViewModel) {
    val devices by viewModel.devices.collectAsStateWithLifecycle()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            initialValue = SheetValue.PartiallyExpanded,
            skipPartiallyExpanded = false,
            skipHiddenState = true
        )
    )
    ListScreen(sheetState = scaffoldState, devices = devices)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    sheetState: BottomSheetScaffoldState,
    devices: List<Device>,
) {
    val a = LocalConfiguration.current.screenHeightDp / 2
    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetPeekHeight = a.dp,
        sheetContent = {
            LazyColumn(
                modifier
                    .height(a.dp)
                    .weight(.5F),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Text(text = "Suas plantas", style = MaterialTheme.typography.titleMedium)
                }

                devices.forEach {
                    item {
                        PlantCard(device = it)
                    }
                }
            }
        },
        content = {

        }
    )
}