package com.sanmiade.composemealdbapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun MealInstructions(modifier: Modifier, instructions: String) {
    var toggled by remember {
        mutableStateOf(false)
    }
    Column(modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable {
                    toggled = toggled.not()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Description")
            // TODO: Find arrow drop down but in the opposite direction.
            Icon(
                imageVector = if (toggled) Icons.Default.ArrowDropDown else Icons.Default.ArrowForward,
                contentDescription = "Toggle meal description",
            )
        }
        if (toggled) {
            Text(text = instructions)
        }
    }
} 