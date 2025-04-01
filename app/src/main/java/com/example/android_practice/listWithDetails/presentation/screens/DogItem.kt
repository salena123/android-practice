package com.example.android_practice.listWithDetails.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity
import com.example.android_practice.ui.Spacing

@Composable
fun DogItem(
    item: DogShortEntity,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(Spacing.medium)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.image ?: "без имени",
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(Spacing.medium))

        Column{
            Text(
                text = item.name ,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${item.temperament} ",
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}