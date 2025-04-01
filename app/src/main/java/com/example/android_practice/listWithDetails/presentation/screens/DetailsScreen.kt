package com.example.android_practice.listWithDetails.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.android_practice.listWithDetails.presentation.state.DogDetailState
import com.example.android_practice.listWithDetails.presentation.viewsModel.DetailsViewModel
import com.example.android_practice.ui.Spacing
import com.example.android_practice.ui.components.EmptyDataBox
import com.example.android_practice.ui.components.FullscreenLoading
import com.example.android_practice.ui.components.LikeButton
import com.example.android_practice.ui.components.SimpleAppBar
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Parcelize
class DetailsScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
    val dogName: String
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<DetailsViewModel>{ parametersOf(navigation, dogName)}
        val state = viewModel.viewState

        DogScreenContent(
            state,
            onBackPressed = { viewModel.back() },
            onLikePressed = { viewModel.onLikeCounterChanged()}
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun DogScreenContent(

    state: DogDetailState,
    onBackPressed: () -> Unit,
    onLikePressed: () -> Unit,
) {
    Scaffold (
        topBar = { SimpleAppBar(state.dog?.name.orEmpty(), onBackPressed) },
    ) {
        if (state.isLoading) {
            FullscreenLoading()
            return@Scaffold
        }

        val dog = state.dog ?: run {
            EmptyDataBox("Такая собака не найдена")
            return@Scaffold
        }
        state.error?.let {
            EmptyDataBox(msg = it)
            return@Scaffold
        }

        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(Spacing.medium)) {
            Row{
                AsyncImage(
                    model = dog.image?.url  ?: "", contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )

                Spacer(
                    modifier = Modifier.width(Spacing.medium)
                )

                Column (Modifier
                    .weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = dog.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier,
                    )

                    if (dog.bredFor != "") {
                        Text(
                            text = "Выведены для: ${dog.bredFor} ",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier,
                        )
                    }

                    if (dog.origin != "") {
                        Text(
                            text = "Страна происхождения: ${dog.origin}",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier,
                        )
                    }

                    if (dog.breedGroup != "") {
                        Text(
                            text = "Группа пород: ${dog.breedGroup}",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier,
                        )
                    }

                    if (dog.lifeSpan != "") {
                    Text(
                        text = "Продолжительность жизни: ${dog.lifeSpan}",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier,
                    )
                }

                    if (dog.temperament != "") {
                        Text(
                            text = "Характер: ${dog.temperament}",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            if (dog.description != "") {
                Text(
                    text = "Описание: ${dog.description}",
                    style = MaterialTheme.typography.titleSmall,
                )
            }

            LikeButton(
                likeCount = state.likes,
                isLiked = state.isLiked,
                onLikeClicked = onLikePressed
            )
        }
    }


}

//@Preview
//@Composable
//private fun DogScreenContentPreview() {
//    DogScreenContent(
//        object : DogDetailState {
//            override val dog = DogsData.dogsFullEntity.find { it.id == "7"}
//            override val likes = 2
//            override val isLiked = true
//        },
//        onBackPressed = {}
//    ) { }
//}

