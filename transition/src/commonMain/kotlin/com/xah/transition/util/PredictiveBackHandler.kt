package com.xah.transition.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
expect fun TransitionPredictiveBackHandler(navController : NavHostController,onScale : (Float) -> Unit)

