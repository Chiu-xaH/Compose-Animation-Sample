package com.xah.sample.ui.screen.settings

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.animation
import animationsample.composeapp.generated.resources.blur_off
import animationsample.composeapp.generated.resources.blur_on
import animationsample.composeapp.generated.resources.deployed_code
import animationsample.composeapp.generated.resources.settings
import animationsample.composeapp.generated.resources.swipe_left
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.logic.util.CAN_MOTION_BLUR
import com.xah.sample.ui.component.DividerTextExpandedWith
import com.xah.sample.ui.component.TransplantListItem
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.transition.component.TransitionScaffold
import com.xah.transition.component.containerShare
import com.xah.transition.component.iconElementShare
import com.xah.transition.state.TransitionState
import com.xah.transition.style.DefaultTransitionStyle
import com.xah.transition.util.navigateAndSaveForTransition
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController : NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onBackPressed: () -> Unit
) {
    val route = remember { ScreenRoute.SettingsScreen.route }
    var motionBlur by remember { mutableStateOf(TransitionState.transitionBackgroundStyle.motionBlur) }
    LaunchedEffect(motionBlur) {
        TransitionState.transitionBackgroundStyle.motionBlur = motionBlur
    }

    var forceAnimation by remember { mutableStateOf(TransitionState.transitionBackgroundStyle.forceTransition) }
    LaunchedEffect(forceAnimation) {
         TransitionState.transitionBackgroundStyle.forceTransition = forceAnimation
    }

    var isCenterAnimation by remember { mutableStateOf((TransitionState.curveStyle.boundsTransform != DefaultTransitionStyle.defaultBoundsTransform)) }
    LaunchedEffect(isCenterAnimation) {
        if(!isCenterAnimation) {
            // 恢复默认速度
            TransitionState.curveStyle.speedMs = DefaultTransitionStyle.DEFAULT_ANIMATION_SPEED
        }
        TransitionState.curveStyle.boundsTransform = if(!isCenterAnimation) DefaultTransitionStyle.defaultBoundsTransform else MyAnimationManager.getCenterBoundsTransform()
    }

    var animationSpeed by remember { mutableStateOf(TransitionState.curveStyle.speedMs.toFloat()) }
    LaunchedEffect(animationSpeed) {
        TransitionState.curveStyle.speedMs = animationSpeed.toInt()
    }

    with(sharedTransitionScope) {
        TransitionScaffold(
            route = route,
            navHostController = navController,
            animatedContentScope = animatedContentScope,
            topBar = {
                TopAppBar(
                    title = { Text("设置") },
                    navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                painterResource(Res.drawable.settings),
                                null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = iconElementShare(animatedContentScope=animatedContentScope, route = route)
                            )
                        }
                    },
                    colors = topBarTransplantColor(),
                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
            ){
                DividerTextExpandedWith("模糊") {
                    TransplantListItem(
                        headlineContent = { Text("运动模糊") },
                        leadingContent = {
                            Icon(painterResource(if(motionBlur) Res.drawable.blur_on else Res.drawable.blur_off),null)
                        },
                        trailingContent = {
                            Switch(enabled = CAN_MOTION_BLUR, checked = motionBlur, onCheckedChange = { motionBlur = !motionBlur })
                        },
                        supportingContent = { Text("一些组件在运动中会伴随模糊效果" + if(CAN_MOTION_BLUR) "(Android 12+)" else "")},
                        modifier = Modifier.clickable { motionBlur = !motionBlur }
                    )
                }
                DividerTextExpandedWith("动效") {
                    TransplantListItem(
                        headlineContent = { Text("增强转场动画") },
                        leadingContent = {
                            Icon(painterResource(Res.drawable.animation),null)
                        },
                        trailingContent = {
                            Switch(checked = forceAnimation, onCheckedChange = { forceAnimation = !forceAnimation })
                        },
                        supportingContent = { Text("转场时启用背景模糊和缩放")},
                        modifier = Modifier.clickable { forceAnimation = !forceAnimation }
                    )
                    TransplantListItem(
                        headlineContent = { Text(text = "动画曲线") },
                        supportingContent = {
                            Row {
                                FilterChip(
                                    onClick = {
                                        // 暂时停用
                                        isCenterAnimation = true
                                    },
                                    label = { Text(text = "向中间运动") }, selected = isCenterAnimation
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                FilterChip(
                                    onClick = { isCenterAnimation = false },
                                    label = { Text(text = "直接展开") }, selected = !isCenterAnimation
                                )
                            }
                        },
                        leadingContent = { Icon(painterResource(Res.drawable.deployed_code), null) },
                        modifier = Modifier.clickable { isCenterAnimation = !isCenterAnimation }
                    )
                    TransplantListItem(
                        headlineContent = { Text(text = "动画时长 ${animationSpeed.toInt()}ms") },
                        supportingContent = {
                            Slider(
                                enabled = isCenterAnimation,
                                value = animationSpeed,
                                onValueChange = {
                                    animationSpeed = it
                                },
                                colors = SliderDefaults.colors(
                                    thumbColor = MaterialTheme.colorScheme.secondary,
                                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                                ),
                                steps = DefaultTransitionStyle.DEFAULT_ANIMATION_SPEED*4-1,
                                valueRange = 0f..DefaultTransitionStyle.DEFAULT_ANIMATION_SPEED*4f,
                                modifier = Modifier.padding(horizontal = 25.dp)
                            )
                        },
                        leadingContent = { Icon(painterResource(Res.drawable.deployed_code), null) },
                        modifier = Modifier.clickable {  }
                    )
                }
                DividerTextExpandedWith("其它") {
                    TransplantListItem(
                        headlineContent = { Text("预测式返回") },
                        leadingContent = {
                            Icon(painterResource(Res.drawable.swipe_left),null)
                        },
                        trailingContent = {
                            Switch(enabled = false, checked = false, onCheckedChange = { })
                        },
                        supportingContent = { Text("加入预测式返回的动画反而不连贯，需打开请自行修改清单文件")},
                        modifier = Modifier.clickable { }
                    )
                }
                DividerTextExpandedWith("三级界面") {
                    with(sharedTransitionScope) {

                        val r = ScreenRoute.Module31Screen.route
                        TransplantListItem(
                            modifier = containerShare(animatedContentScope=animatedContentScope,route=r)
                                .clickable {
                                    navController.navigateAndSaveForTransition(r)
                                },
                            headlineContent = { Text(r) },
                            leadingContent = {
                                Icon(
                                    painterResource(Res.drawable.deployed_code),
                                    null,
                                    modifier = iconElementShare(animatedContentScope=animatedContentScope, route = r)
                                )
                            },
                            supportingContent = {
                                Text("内容")
                            }
                        )
                    }
                }
            }
        }
    }
}


