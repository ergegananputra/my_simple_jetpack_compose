package com.adielboanerge.interntest.second_screen.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.adielboanerge.interntest.nav.ThirdScreenRoute
import com.adielboanerge.interntest.ui.theme.JetpackCompose062024Theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import java.util.Timer
import java.util.TimerTask

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
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    val secondScreenState by viewModel.state.collectAsState()

    val defaultSelectedUser = "Selected User Name"

    val labelWelcome = "Welcome"
    val labelTopBar = "Second Screen"
    val labelChooseAUser = "Choose a User"

    var isCounting by rememberSaveable {
        mutableStateOf(true)
    }

    var timeLeft by remember {
        mutableIntStateOf(15)
    }

    LaunchedEffect(key1 = isCounting) {
        if (isCounting) {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
            }

            isCounting = false
        }

    }


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

            if (isCounting) {
                Spacer(modifier = Modifier.height(24.dp))
                FlashsaleHorizontal(
                    timeLeft,
                    Modifier.padding(16.dp)
                )
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
                    .padding(32.dp)
            )
        }
    }

}

@Composable
fun FlashsaleHorizontal(
    timeLeft: Int,
    modifier: Modifier
) {
    val dummy = listOf(
        "Item 1",
        "Item 2",
        "Item 3",
        "Item 4",
        "Item 5",
        "Item 6",
        "Item 7",
        "Item 8",
        "Item 9",
        "Item 10",
    )

    Surface {
        Column(
            modifier = modifier
        ) {
            Text(text = "Flash Sale $timeLeft")

            LazyRow {
                items(dummy.size) {
                    ItemBox()
                }
            }


        }
    }
}

@Preview
@Composable
fun ItemBox() {
    Surface (
        color = Color.Green,
        modifier = Modifier
            .padding(8.dp)
            .height(70.dp)
            .width(30.dp)
    ){

    }
}

