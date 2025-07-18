package com.xah.sample.ui.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// isDark = true表示状态栏颜色为黑色，此时背景应该为浅色  即浅色主题时isDark应该为true
// isDark = false表示状态栏颜色为白色，此时背景应该为深色 即深色主题时isDark应该为false
@Composable
fun TransparentSystemBar(isDark : Boolean = !isSystemInDarkTheme()) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = isDark,
            isNavigationBarContrastEnforced = false,)
    }
}