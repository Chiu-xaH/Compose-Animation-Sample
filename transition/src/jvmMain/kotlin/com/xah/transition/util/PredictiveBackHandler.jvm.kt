package com.xah.transition.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
actual fun TransitionPredictiveBackHandler(navController : NavHostController,onScale: (Float) -> Unit) = onScale(1f)