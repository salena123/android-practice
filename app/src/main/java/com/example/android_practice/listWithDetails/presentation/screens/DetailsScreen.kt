package com.example.android_practice.listWithDetails.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.android_practice.listWithDetails.data.repository.DogsRepository
import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
import com.example.android_practice.listWithDetails.presentation.state.DogDetailState
import com.example.android_practice.listWithDetails.presentation.viewsModel.DetailsViewModel
import com.example.android_practice.ui.Spacing
import com.example.android_practice.ui.components.EmptyDataBox
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
    val dogId: String
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {

        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<DetailsViewModel>{ parametersOf(navigation, dogId)}
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
        val dog = state.dog ?: run {
            EmptyDataBox("Такая собака не найдена")
            return@Scaffold
        }



        Column(
            Modifier
                .padding(it)
                .padding(Spacing.medium)) {
            Row{
                AsyncImage(
                    model = dog.image  ?: "нет изображения", contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )

                Spacer(
                    modifier = Modifier.width(Spacing.medium)
                )

                Column (Modifier.weight(1f)) {
                    Text(
                        text = dog.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 10.dp),
                    )

                    Text(
                        text = "Выведены для: ${dog.bredFor  ?: "неизвестно"} ",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 10.dp),
                    )

                    Text(
                        text = "Страна происхождения: ${dog.origin  ?: "неизвестно"}",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 10.dp),
                    )

                    Text(
                        text = "Группа пород: ${dog.breedGroup  ?: "неизвестно"}",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 10.dp),
                    )

                    Text(
                        text = "Продолжительность жизни: ${dog.lifeSpan  ?: "неизвестно"}",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 10.dp),
                    )

                    Text(
                        text = "Характер: ${dog.temperament  ?: "неизвестно"}",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 10.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            Text(
                text = "Описание: ${dog.description ?: "не найдено"}",
                style = MaterialTheme.typography.titleSmall,
            )

            LikeButton(
                likeCount = state.likes,
                isLiked = state.isLiked,
                onLikeClicked = onLikePressed
            )
        }
    }


}

@Preview
@Composable
private fun DogScreenContentPreview() {
    DogScreenContent(
        object : DogDetailState {
            override val dog = DogsRepository().getById("3")
            override val likes = 2
            override val isLiked = true
        },
        onBackPressed = {}
    ) { }
}

