package com.programmersbox.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import com.programmersbox.shared.screens.TopicDrawer
import com.programmersbox.shared.utils.AppActions
import com.programmersbox.shared.viewmodels.BaseTopicVM
import com.programmersbox.shared.viewmodels.BaseTopicViewModel
import platform.UIKit.UIViewController

public actual fun getPlatformName(): String {
    return "iOS"
}

internal actual fun updatedAt(pushedAt: String): String {
    return pushedAt
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal actual fun TopicDrawerLocation(vm: BaseTopicVM) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Theme(
        true,
        remember {
            AppActions(

            )
        }
    ) {
        Surface {
            Column {
                Spacer(Modifier.height(30.dp))
                DismissibleNavigationDrawer(
                    drawerContent = { DismissibleDrawerSheet { TopicDrawer(vm) } },
                    drawerState = drawerState,
                    modifier = Modifier.padding(vertical = 30.dp)
                ) {
                    App(vm)
                }
            }
        }
    }
}

@Composable
internal fun UIShow() {
    TopicDrawerLocation(remember { BaseTopicViewModel() })
}

public fun MainViewController(): UIViewController = Application("GitHub Topics") {
    UIShow()
}