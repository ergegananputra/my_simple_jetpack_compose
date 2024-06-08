package com.adielboanerge.interntest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.adielboanerge.interntest.nav.MainNavigation
import com.adielboanerge.interntest.ui.theme.JetpackCompose062024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackCompose062024Theme {
                MainNavigation()
            }
        }
    }
}

