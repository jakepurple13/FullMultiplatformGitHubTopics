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
            Column {
                Spacer(Modifier.height(30.dp))
                PermanentNavigationDrawer(
                    drawerContent = { PermanentDrawerSheet { TopicDrawer(vm) } },
                    modifier = Modifier.padding(vertical = 30.dp)
                ) {
                    App(vm)
                }
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