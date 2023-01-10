package com.programmersbox.shared.utils

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.programmersbox.shared.network.GitHubTopic

//TODO: IOS doesn't work with custom public CompositionLocals
internal val LocalAppActions: ProvidableCompositionLocal<AppActions> =
    staticCompositionLocalOf<AppActions> { error("No Actions") }

@Immutable
public data class AppActions(
    val onCardClick: (GitHubTopic) -> Unit = {},
    val onNewTabOpen: (GitHubTopic) -> Unit = {},
    val onNewWindow: (GitHubTopic) -> Unit = {},
    val onShareClick: (GitHubTopic) -> Unit = {},
    val onSettingsClick: () -> Unit = {},
    val showLibrariesUsed: () -> Unit = {},
    val showFavorites: () -> Unit = {}
)