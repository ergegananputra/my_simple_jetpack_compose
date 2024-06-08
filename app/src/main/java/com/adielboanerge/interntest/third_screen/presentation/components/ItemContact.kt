package com.adielboanerge.interntest.third_screen.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.adielboanerge.interntest.R

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Composable
private fun ItemPersonPreview() {
    ItemPerson(
        fullName = "Adiel Boanerge",
        email = "tes@mail.com",
        avatar = "https://avatars.githubusercontent.com/u/126530940?v=4"
    )
}


@Composable
fun ItemPerson(
    fullName: String = "",
    email: String = "",
    avatar: String = "",
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .heightIn(
                min = 80.dp
            )
    ) {

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        vertical = 18.dp,
                        horizontal = 20.dp
                    )
            ) {
                ProfilePicture(
                    profilePictureUrl = avatar
                )

                Column {
                    Text(
                        text = fullName,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(
                                start = 24.dp
                            ),
                    )
                    Text(
                        text = email,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(
                                start = 24.dp
                            ),
                    )
                }
            }

            HorizontalDivider(
                color = Color.Gray.copy(
                    alpha = 0.3f
                ),
                thickness = 1.dp,
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp
                    )
            )
        }
    }


}

@Composable
private fun ProfilePicture(
    profilePictureUrl : String = ""
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(100))
    ) {
        AsyncImage(
            model = profilePictureUrl,
            placeholder = painterResource(id = R.drawable.baseline_person_24),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop
        )
    }
}