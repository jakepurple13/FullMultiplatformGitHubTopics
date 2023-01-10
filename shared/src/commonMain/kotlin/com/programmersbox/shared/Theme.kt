package com.programmersbox.shared

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.programmersbox.shared.utils.AppActions
import com.programmersbox.shared.utils.LocalAppActions

@Composable
internal fun Theme(
    isDarkMode: Boolean,
    appActions: AppActions,
    content: @Composable () -> Unit
) {
    MaterialTheme(darkColorScheme()) {
        CompositionLocalProvider(
            LocalAppActions provides appActions,
            LocalMainScrollState provides rememberLazyListState(),
            content = content
        )
    }
}

internal val LocalMainScrollState: ProvidableCompositionLocal<LazyListState> =
    staticCompositionLocalOf<LazyListState> { error("No Actions") }
