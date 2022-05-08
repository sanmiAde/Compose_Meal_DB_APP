import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sanmiade.composemealdbapp.domain.model.MealDetailModel
import com.sanmiade.composemealdbapp.ui.components.IngredientsList
import com.sanmiade.composemealdbapp.ui.features.meal.MealUiState
import com.sanmiade.composemealdbapp.ui.features.meal.MealViewModel

@Composable
fun MealScreen() {
    val mealViewModel: MealViewModel = hiltViewModel()
    val mealUiState by mealViewModel.mealsUiState.collectAsState()

    MealContent(modifier = Modifier.fillMaxWidth(), mealUiState = mealUiState)
}


@Composable
fun MealContent(modifier: Modifier, mealUiState: MealUiState) {
    if (mealUiState.showProgress) {
        Box(modifier = modifier) {
            CircularProgressIndicator()
        }
    } else {
        mealUiState.mealDetail?.let { mealDetailModel: MealDetailModel ->
            Column(modifier) {
                AsyncImage(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    model = mealDetailModel.thumbNail,
                    contentDescription = mealDetailModel.name,
                    contentScale = ContentScale.FillBounds,
                )
                IngredientsList(modifier = modifier, ingredients = mealDetailModel.ingredients)
            }
        }
    }
}