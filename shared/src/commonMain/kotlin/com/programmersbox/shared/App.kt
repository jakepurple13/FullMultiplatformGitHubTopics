package com.programmersbox.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.programmersbox.shared.network.GitHubTopic
import com.programmersbox.shared.network.Network
import com.programmersbox.shared.screens.GithubTopicUI
import com.programmersbox.shared.utils.AppActions

@Composable
internal fun App() {
    val uriHandler = LocalUriHandler.current
    Theme(
        true,
        remember {
            AppActions(
                onCardClick = { uriHandler.openUri(it.htmlUrl) }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            var isLoading by remember { mutableStateOf(false) }
            val items by produceState(emptyList<GitHubTopic>()) {
                isLoading = true
                Network.getTopics(1, "jetpack-compose").fold(
                    onSuccess = { value = it },
                    onFailure = { it.printStackTrace() }
                )
                isLoading = false
            }

            GithubTopicUI(
                page = 1,
                topicItems = items,
                onRefresh = {},
                onLoadMore = {},
                isLoading = false,
                onTopicClick = {},
                savedTopics = emptyList(),
                currentTopics = emptyList(),
                favoriteList = emptyList(),
                onFavoriteClick = { topic, isFavorite -> },
                navigationIcon = {},
            )
        }
    }
}