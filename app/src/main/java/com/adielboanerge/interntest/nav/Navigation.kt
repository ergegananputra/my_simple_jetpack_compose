package com.adielboanerge.interntest.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.adielboanerge.interntest.first_screen.presentation.FirstScreen
import com.adielboanerge.interntest.first_screen.presentation.FirstScreenViewModel
import com.adielboanerge.interntest.second_screen.presentation.SecondScreen
import com.adielboanerge.interntest.second_screen.presentation.SecondScreenViewModel
import com.adielboanerge.interntest.third_screen.presentation.ThirdScreen
import com.adielboanerge.interntest.third_screen.presentation.ThirdScreenViewModel
import kotlinx.serialization.Serializable

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    val secondScreenViewModel = SecondScreenViewModel()
    val thirdScreenViewModel = ThirdScreenViewModel()
    thirdScreenViewModel.refreshContacts()

    NavHost(
        navController = navController,
        startDestination = FirstScreenRoute
    ) {
        composable<FirstScreenRoute> {
            FirstScreen(
                navController = navController,
                viewModel = FirstScreenViewModel()
            )
        }

        composable<SecondScreenRoute> {
            val args = it.toRoute<SecondScreenRoute>()

            args.name?.let { name ->
                secondScreenViewModel.updateName(name)
            }
            args.selectedUser?.let { selectedUser ->
                secondScreenViewModel.updateSelectedUser(selectedUser)
            }

             SecondScreen(
                 navController = navController,
                 viewModel = secondScreenViewModel,
             )
        }

        composable<ThirdScreenRoute> {
             ThirdScreen(
                 navController = navController,
                 viewModel = thirdScreenViewModel,
                 updateSelectedUser = { selectedUser ->
                     secondScreenViewModel.updateSelectedUser(selectedUser)
                 }
             )
        }

    }
}

@Serializable object FirstScreenRoute
@Serializable data class SecondScreenRoute(
    val name : String? = null,
    val selectedUser : String? = null
)
@Serializable data class ThirdScreenRoute(
    val name : String? = null
)
