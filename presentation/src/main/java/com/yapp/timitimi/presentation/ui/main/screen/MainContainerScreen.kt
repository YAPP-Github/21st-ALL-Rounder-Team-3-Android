package com.yapp.timitimi.presentation.ui.main.screen

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yapp.timitimi.designsystem.R
import com.yapp.timitimi.presentation.ui.mypage.MyPageScreen
import com.yapp.timitimi.presentation.ui.projectlist.ProjectListActivity
import com.yapp.timitimi.ui.startActivityWithAnimation

@Composable
fun MainContainerScreen() {
    val navController = rememberNavController()
    val activity = LocalContext.current as Activity
    val items = listOf(
        BottomNavigationItem.HOME,
        BottomNavigationItem.MY_PAGE,
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(screen.icon), contentDescription = null) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavigationItem.HOME.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavigationItem.HOME.route) {
                HomeScreen(
                    onBackPressed = {
                        activity.startActivityWithAnimation<ProjectListActivity>()
                    }
                )
            }
            composable(BottomNavigationItem.MY_PAGE.route) { MyPageScreen() }
        }
    }
}

enum class BottomNavigationItem(
    val route: String,
    @DrawableRes val icon: Int,
) {
    HOME(
        "home",
        R.drawable.icon_home
    ),
    MY_PAGE(
        "mypage",
        R.drawable.icon_mypage
    );
}