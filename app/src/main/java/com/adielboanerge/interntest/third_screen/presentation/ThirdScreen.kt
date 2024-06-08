package com.adielboanerge.interntest.third_screen.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.adielboanerge.interntest.R
import com.adielboanerge.interntest.core.presentation.KMTopBar
import com.adielboanerge.interntest.core.presentation.PullToRefreshAndLoadMoreLazyColumn
import com.adielboanerge.interntest.nav.SecondScreenRoute
import com.adielboanerge.interntest.third_screen.data.model.RegresContact
import com.adielboanerge.interntest.ui.theme.JetpackCompose062024Theme

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
    viewModel: ThirdScreenViewModel
) {
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
            navController = navController
        )
    }
}

@Composable
private fun RegresContactList(
    innerPadding: PaddingValues,
    viewModel: ThirdScreenViewModel,
    navController: NavController
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
                        navController.navigate(
                            SecondScreenRoute(
                                selectedUser = "${regresContact.firstName} ${regresContact.lastName}"
                            )
                        )
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

//    LazyColumn(
//        modifier = Modifier.padding(innerPadding)
//    ) {
//        itemsIndexed(thirdScreenState.regresContactList) { index, regresContact ->
//            ItemPerson(
//                fullName = "${regresContact.firstName} ${regresContact.lastName}",
//                email = regresContact.email,
//                avatar = regresContact.avatar
//            )
//
//            if (index == thirdScreenState.regresContactList.size - 3) {
//                viewModel.updateEndReached(true)
//            }
//        }
//    }
//
//    LaunchedEffect(thirdScreenState.endReached) {
//        if (thirdScreenState.endReached) {
//            viewModel.loadMoreContacts()
//        }
//    }
}

@Composable
private fun ItemPerson(
    fullName: String = "",
    email: String = "",
    avatar: String = "",
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp
            )
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    vertical = 18.dp
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
                    modifier = Modifier,
                )
                Text(
                    text = email,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                )
            }

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
            .padding(
                vertical = 16.dp,
                horizontal = 18.dp
            )
    ) {
        AsyncImage(
            model = profilePictureUrl,
            placeholder = painterResource(id = R.drawable.baseline_person_24),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop
        )
    }
}