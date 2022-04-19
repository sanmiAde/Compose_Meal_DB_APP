package com.sanmiade.composemealdbapp.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TopAppBar(
    modifier: Modifier,
    title: String,
    showNavigationIcon : Boolean = true,
    navigationIcon: ImageVector? = null,
    onNavigationIconClick: () -> Unit = {}
) {
    if (showNavigationIcon) {
        androidx.compose.material.TopAppBar(
            modifier = modifier,
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = { onNavigationIconClick() }) {
                    Icon(
                        imageVector = navigationIcon!!,
                        contentDescription = "Navigation icon"
                    )
                }
            })
    } else {
        androidx.compose.material.TopAppBar(
            modifier = modifier,
            title = { Text(text = title) },
        )
    }
}