package com.adielboanerge.interntest.first_screen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adielboanerge.interntest.R
import com.adielboanerge.interntest.core.presentation.KMButton
import com.adielboanerge.interntest.first_screen.presentation.components.KMPersonAdd
import com.adielboanerge.interntest.first_screen.presentation.components.KMTextField
import com.adielboanerge.interntest.nav.SecondScreenRoute
import com.adielboanerge.interntest.second_screen.presentation.SecondScreenState
import com.adielboanerge.interntest.ui.theme.JetpackCompose062024Theme

@Preview(
    name = "Light Mode",
    showSystemUi = true,
    showBackground = true,
)
@Composable
private fun FirstScreenDeveloperPreview() {
    JetpackCompose062024Theme{
        FirstScreen(
            viewModel = FirstScreenViewModel(),
            navController = NavController(LocalContext.current)
        )
    }
}

@Composable
fun FirstScreen(
    viewModel: FirstScreenViewModel,
    navController: NavController
) {
    val backgroundImage = painterResource(id = R.drawable.first_screen_background)

    val firstScreenState by viewModel.state.collectAsState()
    val labelName = "Nama"
    val labelPalindrome = "Palindrome"
    val labelCheck = "CHECK"
    val labelNext = "NEXT"

    var isDialogOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Surface{

        Image(
            painter = backgroundImage,
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.weight(3.5f))

            KMPersonAdd()

            Spacer(modifier = Modifier.weight(1f))

            KMTextField(
                label = labelName,
                value = firstScreenState.name,
                onValueChange = viewModel::updateName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            KMTextField(
                label = labelPalindrome,
                value = firstScreenState.inputPalindrome,
                onValueChange = viewModel::updateInputPalindrom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.weight(.8f))

            KMButton(
                label = labelCheck,
                onClick = {
                    viewModel.checkPalindrome()
                    isDialogOpen = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            KMButton(
                label = labelNext,
                onClick = {
                    navController.navigate(
                        SecondScreenRoute(
                            name = firstScreenState.name
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.weight(4f))

            if (isDialogOpen) {
                AlertDialog(
                    onDismissRequest = {
                       isDialogOpen = false
                        },
                    confirmButton = {
                        KMButton(
                            label = "OK",
                            onClick = { isDialogOpen = false }
                        )
                    },
                    title = {
                        Text(text = "Result")
                    },
                    text = {
                        if (firstScreenState.isPalindrome) {
                            Text("isPalindrome")
                        } else {
                            Text("not palindrome")
                        }
                    }

                )
            }

        }
    }
}



