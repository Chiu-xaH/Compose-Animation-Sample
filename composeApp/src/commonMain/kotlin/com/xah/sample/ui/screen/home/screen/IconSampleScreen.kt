package com.xah.sample.ui.screen.home.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.deployed_code
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.component.APP_HORIZONTAL_DP
import com.xah.sample.ui.component.DividerTextExpandedWith
import com.xah.sample.ui.component.containerShare
import com.xah.sample.ui.component.iconElementShare
import com.xah.sample.ui.screen.home.screen.common.SharedTopBar
import com.xah.sample.ui.style.RowHorizontal
import com.xah.sample.ui.util.navigateAndSaveForTransition
import com.xah.sample.viewmodel.UIViewModel
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun IconSampleScreen(
    vm: UIViewModel,
    navController : NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    boundsTransform: BoundsTransform,
    onItemClick: (String) -> Unit,
) {
    val func2 = remember {
        listOf(
            ScreenRoute.Module11Screen.route,
            ScreenRoute.Module12Screen.route,
            ScreenRoute.Module13Screen.route,
            ScreenRoute.Module14Screen.route,
            ScreenRoute.Module15Screen.route,
            ScreenRoute.Module16Screen.route,
            ScreenRoute.Module17Screen.route,
            ScreenRoute.Module18Screen.route,
            ScreenRoute.Module19Screen.route,
            ScreenRoute.Module20Screen.route,
        )
    }

    val scrollState = rememberScrollState()

    SharedTopBar(
        vm,
        title = "图标与按钮",
        navController = navController,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope,
        boundsTransform
    ) { innerPadding ->
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Spacer(Modifier.height(innerPadding.calculateTopPadding()).statusBarsPadding())
            DividerTextExpandedWith("无边框按钮",vm) {
                RowHorizontal(modifier = Modifier.fillMaxWidth()) {
                    with(sharedTransitionScope) {
                        val route = func2[0]
                        IconButton(
                            onClick = {
                                navController.navigateAndSaveForTransition(route,true)
                            },
                            modifier = containerShare(animatedContentScope = animatedContentScope, boundsTransform = boundsTransform, route = route, resize = true)
//                                Modifier.sharedBounds(
//                                boundsTransform = boundsTransform,
//                                enter = MyAnimationManager.fadeAnimation.enter,
//                                exit = MyAnimationManager.fadeAnimation.exit,
//                                sharedContentState = rememberSharedContentState(key = "container_$route"),
//                                animatedVisibilityScope = animatedContentScope,
//                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
//                            )
                        ) {
                            Icon(
                                painterResource(Res.drawable.deployed_code),
                                null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier =iconElementShare(animatedContentScope=animatedContentScope, boundsTransform = boundsTransform, route = route)
//                                    Modifier.sharedElement(
//                                    boundsTransform = boundsTransform,
//                                    sharedContentState = rememberSharedContentState(key = "title_$route"),
//                                    animatedVisibilityScope = animatedContentScope,
//                                )
                            )
                        }
                    }
                    Spacer(Modifier.width(APP_HORIZONTAL_DP))
                    with(sharedTransitionScope) {
                        val route = func2[1]
                        TextButton(
                            onClick = {
                                navController.navigateAndSaveForTransition(route,true)
                            },
                            modifier = containerShare(animatedContentScope=animatedContentScope, boundsTransform = boundsTransform, route = route)
//                                Modifier.sharedBounds(
//                                boundsTransform = boundsTransform,
//                                enter = MyAnimationManager.fadeAnimation.enter,
//                                exit = MyAnimationManager.fadeAnimation.exit,
//                                sharedContentState = rememberSharedContentState(key = "container_$route"),
//                                animatedVisibilityScope = animatedContentScope,
//                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
//                            )
                        ) {
                            Text("按钮")
                        }
                    }
                }
            }
            DividerTextExpandedWith("有边框按钮",vm) {
                RowHorizontal(modifier = Modifier.fillMaxWidth())  {
                    with(sharedTransitionScope) {
                        val route = func2[5]
                        FloatingActionButton (
                            elevation =  FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                            onClick = {
                                navController.navigateAndSaveForTransition(route)
                            },
                            modifier = containerShare(animatedContentScope=animatedContentScope,boundsTransform=boundsTransform,route=route)
//                                Modifier.sharedBounds(
//                                boundsTransform = boundsTransform,
//                                enter = MyAnimationManager.fadeAnimation.enter,
//                                exit = MyAnimationManager.fadeAnimation.exit,
//                                sharedContentState = rememberSharedContentState(key = "container_$route"),
//                                animatedVisibilityScope = animatedContentScope,
//                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
//                            )
                        ) {
                            Icon(
                                painterResource(Res.drawable.deployed_code),
                                null,
                                modifier = iconElementShare(animatedContentScope=animatedContentScope, boundsTransform = boundsTransform, route = route)
//                                    Modifier.sharedElement(
//                                    boundsTransform = boundsTransform,
//                                    sharedContentState = rememberSharedContentState(key = "title_$route"),
//                                    animatedVisibilityScope = animatedContentScope,
//                                )
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(innerPadding.calculateBottomPadding() + APP_HORIZONTAL_DP).navigationBarsPadding())
        }
    }
}