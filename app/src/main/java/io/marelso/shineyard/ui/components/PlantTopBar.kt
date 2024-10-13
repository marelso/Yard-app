package io.marelso.shineyard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.marelso.shineyard.ui.theme.Brand

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PlantTopBar(modifier: Modifier = Modifier) {
    Column {
        TopAppBar(
            modifier = modifier.background(color = Brand).padding(horizontal = 8.dp),
            title = { },
            actions = {
                IconButton(
                    onClick = { },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.background)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit plant infos",
                        tint = Brand
                    )
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = { },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.background)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to list",
                        tint = Brand
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Brand)
        )
    }
}