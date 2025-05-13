package com.example.android_practice.profile.presentation.screens


import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.android_practice.R
import com.example.android_practice.listWithDetails.presentation.screens.DogItem
import com.example.android_practice.profile.presentation.viewModel.ProfileViewModel
import com.example.android_practice.ui.Typography
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class ProfileScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val viewModel = koinViewModel<ProfileViewModel>()
        val navigation = LocalStackNavigation.current
        val state = viewModel.viewState

        val context = LocalContext.current

        Scaffold (
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.profile))
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            Modifier
                                .padding(end = dimensionResource(R.dimen.spacing_small))
                                .clickable { navigation.forward(EditProfileScreen()) }
                        )
                    }
                )
            },
        ) { padding ->
            Column(
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = state.photoUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(dimensionResource(R.dimen.avatar_size)),
                    error = painterResource(R.drawable.profile)
                )
                Text(text = state.name, style = Typography.headlineLarge)

                Button(onClick = { enqueueDownloadRequest("https://msp.midural.ru/download/97998/", context)} ) {
                    Text("Скачать файл")
                }
            }

        }
    }
}

private fun enqueueDownloadRequest(
    url: String,
    context: Context
){
    val request: DownloadManager.Request = DownloadManager.Request(Uri.parse(url))
    with(request) {
        setTitle("Test.doc")
        setMimeType("doc")
        setDescription("Загрузка документа")
        setNotificationVisibility((DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED))
        setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS, "test.doc"
        )
    }
    val manager: DownloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    manager.enqueue(request)
}