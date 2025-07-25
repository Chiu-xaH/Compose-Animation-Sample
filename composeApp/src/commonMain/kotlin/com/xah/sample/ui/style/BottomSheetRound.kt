package com.xah.sample.ui.style

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.transition.state.TransitionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomSheetRound(sheetState: SheetState, autoShape : Boolean = true) : RoundedCornerShape {
    return RoundedCornerShape(if(autoShape) roundDp(sheetState) else 28.dp)
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun roundDp(sheetState : SheetState) : Dp {
    val dpAnimation by animateDpAsState(
        targetValue = if (sheetState.currentValue != SheetValue.Expanded) 28.dp else 0.dp, label = ""
        ,animationSpec = tween(TransitionState.curveStyle.speedMs / 2, easing = LinearOutSlowInEasing),
    )
    return dpAnimation
}