package com.programmersbox.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import platform.UIKit.UIViewController

public actual fun getPlatformName(): String {
    return "iOS"
}

internal actual fun updatedAt(pushedAt: String): String {
    return pushedAt
}

@Composable
internal fun UIShow() {
    App()
}

public fun MainViewController(): UIViewController = Application("GitHub Topics") {
    Column {
        Spacer(Modifier.height(30.dp))
        UIShow()
    }
}