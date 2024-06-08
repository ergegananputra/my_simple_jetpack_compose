package com.adielboanerge.interntest.second_screen.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adielboanerge.interntest.core.presentation.KMButton
import com.adielboanerge.interntest.core.presentation.KMTopBar
import com.adielboanerge.interntest.first_screen.presentation.FirstScreenState
import com.adielboanerge.interntest.nav.FirstScreenRoute
import com.adielboanerge.interntest.nav.SecondScreenRoute
import com.adielboanerge.interntest.nav.ThirdScreenRoute
import com.adielboanerge.interntest.third_screen.presentation.ThirdScreenState
import com.adielboanerge.interntest.ui.theme.JetpackCompose062024Theme

@Preview(
    name = "Light Mode",
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun SecondScreenDeveloperPreview() {
    JetpackCompose062024Theme{
        SecondScreen(
            viewModel = SecondScreenViewModel(),
            navController = NavController(LocalContext.current),
        )
    }
}


@Composable
fun SecondScreen(
    viewModel: SecondScreenViewModel,
    navController: NavController,
) {
    val secondScreenState by viewModel.state.collectAsState()

    val defaultSelectedUser = "Selected User Name"

    val labelWelcome = "Welcome"
    val labelTopBar = "Second Screen"
    val labelChooseAUser = "Choose a User"

    Scaffold(
        topBar = {
            KMTopBar(
                labelTopBar = labelTopBar,
                backAction = {
                    navController.popBackStack()
                }
            )
                 },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = labelWelcome,
                        fontSize = 12.sp,
                    )
                    Text(
                        text = secondScreenState.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                }
            }



            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = secondScreenState.selectedUser.let {
                    it.ifBlank { defaultSelectedUser }
                },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            KMButton(
                label = labelChooseAUser,
                onClick = {
                    navController.navigate(
                        ThirdScreenRoute(
                        name = secondScreenState.name)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 300.dp)
                    .padding(
                        vertical = 32.dp
                    )
            )
        }
    }

}

