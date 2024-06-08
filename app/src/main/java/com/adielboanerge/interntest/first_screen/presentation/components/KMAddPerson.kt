package com.adielboanerge.interntest.first_screen.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.adielboanerge.interntest.R

@Composable
fun KMPersonAdd() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(116.dp)
            .clip(RoundedCornerShape(100))
            .background(
                color = Color.White.copy(
                    alpha = 0.5f
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.person_add_24px),
            contentDescription = "Person Add",
            modifier = Modifier
                .padding(32.dp)
                .fillMaxSize()
        )
    }

}