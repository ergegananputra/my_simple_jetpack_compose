package com.adielboanerge.interntest.third_screen.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.adielboanerge.interntest.core.presentation.KMTopBar
import com.adielboanerge.interntest.core.presentation.PullToRefreshAndLoadMoreLazyColumn
import com.adielboanerge.interntest.nav.SecondScreenRoute
import com.adielboanerge.interntest.nav.ThirdScreenRoute
import com.adielboanerge.interntest.third_screen.data.model.RegresContact
import com.adielboanerge.interntest.third_screen.presentation.components.ItemPerson
import com.adielboanerge.interntest.ui.theme.JetpackCompose062024Theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(
    name = "Light Mode",
    showSystemUi = true,
    showBackground = true,
)
@Composable
private fun ThirdScreenDeveloperPreview() {

    val person = mutableListOf<RegresContact>()

    for (i in 0..20) {
        val regresContact = RegresContact(
            id = i,
            firstName = "First Name $i",
            lastName = "Last Name $i",
            email = "email $i",
            avatar = "https://reqres.in/img/faces/1-image.jpg"
        )
        person.add(regresContact)
    }

    JetpackCompose062024Theme{
        ThirdScreen(
            navController = NavController(LocalContext.current),
            viewModel = ThirdScreenViewModel()
        )
    }
}

@Composable
fun ThirdScreen(
    navController: NavController,
    viewModel: ThirdScreenViewModel,
    updateSelectedUser: (String) -> Unit = {}
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    Scaffold(
        topBar = {
            KMTopBar(
                labelTopBar = "Third Screen",
                backAction = {
                    navController.popBackStack()
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        RegresContactList(
            innerPadding,
            viewModel = viewModel,
            navController = navController,
            updateSelectedUser = updateSelectedUser
        )
    }
}

@Composable
private fun RegresContactList(
    innerPadding: PaddingValues,
    viewModel: ThirdScreenViewModel,
    navController: NavController,
    updateSelectedUser: (String) -> Unit = {}
) {
    val thirdScreenState by viewModel.state.collectAsState()


    PullToRefreshAndLoadMoreLazyColumn(
        items = thirdScreenState.regresContactList,
        content = { regresContact ->
            if (
                regresContact.email != null &&
                regresContact.firstName != null &&
                regresContact.lastName != null &&
                regresContact.avatar != null
            ) {
                ItemPerson(
                    fullName = "${regresContact.firstName} ${regresContact.lastName}",
                    email = regresContact.email!!,
                    avatar = regresContact.avatar!!,
                    onClick = {
                        updateSelectedUser("${regresContact.firstName} ${regresContact.lastName}")
                        navController.popBackStack()
                    }
                )
            }

        },
        isRefreshing = thirdScreenState.isRefreshing,
        onRefresh = {
            viewModel.refreshContacts()
        },
        isLoadMore = thirdScreenState.isLoadMore,
        onLoadMoreTrigger = 3,
        onLoadMore = {
            viewModel.loadMoreContacts()
        },
        modifier = Modifier.padding(innerPadding)
    )
}

