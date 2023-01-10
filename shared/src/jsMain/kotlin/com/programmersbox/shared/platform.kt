package com.programmersbox.shared

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.programmersbox.shared.screens.TopicDrawer
import com.programmersbox.shared.utils.AppActions
import com.programmersbox.shared.viewmodels.BaseTopicVM
import com.programmersbox.shared.viewmodels.BaseTopicViewModel

public actual fun getPlatformName(): String {
    return "JavaScript"
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
            PermanentNavigationDrawer(
                drawerContent = { PermanentDrawerSheet { TopicDrawer(vm) } },
            ) {
                App(vm)
            }
        }
    }
}

@Composable
public fun UIShow() {
    TopicDrawerLocation(
        remember { BaseTopicViewModel() },
    )
}