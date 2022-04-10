package com.sanmiade.composemealdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesViewModel
import com.sanmiade.composemealdbapp.ui.theme.ComposeMealDBAPpTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewmodel by viewModels<MealCategoriesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMealDBAPpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(Modifier.clickable { viewmodel.click() },"Android", )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier, name: String) {
    Text(text = "Hello $name!", modifier)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeMealDBAPpTheme {

    }
}