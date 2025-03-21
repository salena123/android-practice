package com.example.android_practice.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.android_practice.ui.Spacing

@Composable
fun LikeButton(
    likeCount: Int,
    modifier: Modifier = Modifier,
    isLiked: Boolean,
) {
    var likeCount by rememberSaveable { mutableStateOf(likeCount) }
    var isLiked by rememberSaveable { mutableStateOf(isLiked) }

    Row(modifier = Modifier) {
        Icon(
            imageVector = if (isLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            contentDescription = "Like",
            modifier = Modifier
                .clickable {
                    likeCount = if (isLiked) likeCount - 1 else likeCount + 1
                    isLiked = !isLiked
                }
        )

        Spacer(modifier = Modifier.width(Spacing.medium))

        Text(
            text = "$likeCount Хочу такую!!!",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}