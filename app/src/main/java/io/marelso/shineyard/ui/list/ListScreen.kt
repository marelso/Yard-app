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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import io.marelso.shineyard.ui.list.components.PlantCard
import io.marelso.shineyard.ui.theme.Brand

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListScreenHoisting(viewModel: ListViewModel) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            initialValue = SheetValue.PartiallyExpanded,
            skipPartiallyExpanded = false,
            skipHiddenState = true
        )
    )
    ListScreen(sheetState = scaffoldState, value=viewModel.user.orEmpty())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    sheetState: BottomSheetScaffoldState,
    value: String) {
    val a = LocalConfiguration.current.screenHeightDp/2
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
                item {
                    Text(text = value, style = MaterialTheme.typography.titleMedium)
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
                item {
                    PlantCard()
                }
            }
        },
        content = {

        }
    )
}