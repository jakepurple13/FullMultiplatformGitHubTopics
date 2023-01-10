package com.programmersbox.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.programmersbox.shared.network.GitHubTopic
import com.programmersbox.shared.screens.GithubTopicUI
import com.programmersbox.shared.utils.LocalAppActions
import com.programmersbox.shared.viewmodels.BaseTopicVM

@Composable
internal fun App(
    vm: BaseTopicVM,
    onCardClick: (GitHubTopic) -> Unit = {},
    onNewTabOpen: (GitHubTopic) -> Unit = {},
    onNewWindow: (GitHubTopic) -> Unit = {},
    onShareClick: (GitHubTopic) -> Unit = {},
    onSettingsClick: () -> Unit = {},
    showLibrariesUsed: () -> Unit = {},
    showFavorites: () -> Unit = {}
) {
    /*Theme(
        true,
        remember {
            AppActions(
                onCardClick,
                onNewTabOpen,
                onNewWindow,
                onShareClick,
                onSettingsClick,
                showLibrariesUsed,
                showFavorites
            )
        }
    ) {*/
    LaunchedEffect(Unit) {
        vm.refresh()
    }

    val uriHandler = LocalUriHandler.current

    CompositionLocalProvider(
        LocalAppActions provides LocalAppActions.current.copy(
            onCardClick = { uriHandler.openUri(it.htmlUrl) }
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            GithubTopicUI(
                page = vm.page,
                topicItems = vm.items,
                onRefresh = vm::refresh,
                onLoadMore = {},
                isLoading = vm.isLoading,
                onTopicClick = vm::addTopic,
                savedTopics = vm.topicList,
                currentTopics = vm.currentTopics,
                favoriteList = emptyList(),
                onFavoriteClick = { topic, isFavorite -> },
                navigationIcon = {},
            )
        }
    }
    //}
}