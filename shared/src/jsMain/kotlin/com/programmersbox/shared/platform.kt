package com.programmersbox.shared

import androidx.compose.runtime.Composable

public actual fun getPlatformName(): String {
    return "JavaScript"
}

internal actual fun updatedAt(pushedAt: String): String {
    return pushedAt
}

@Composable
public fun UIShow() {
    App()
}