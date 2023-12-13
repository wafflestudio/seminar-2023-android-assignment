package com.wafflestudio.assignment5.components.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.wafflestudio.assignment5.components.compose.tutorial.Greetings
import com.wafflestudio.assignment5.components.compose.tutorial.Onboarding

@Composable
fun Tutorial(modifier: Modifier) {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnboarding) {
            Onboarding(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }

}
