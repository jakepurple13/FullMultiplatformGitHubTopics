package com.programmersbox.shared.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.programmersbox.shared.components.IconsButton
import com.programmersbox.shared.components.InfiniteListHandler
import com.programmersbox.shared.components.TopicItem
import com.programmersbox.shared.network.GitHubTopic
import com.programmersbox.shared.utils.LocalAppActions
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@Composable
internal fun GithubTopicUI(
    page: Int,
    topicItems: List<GitHubTopic>,
    onRefresh: suspend () -> Unit,
    onLoadMore: () -> Unit,
    isLoading: Boolean,
    onTopicClick: (String) -> Unit,
    savedTopics: List<String>,
    currentTopics: List<String>,
    favoriteList: List<GitHubTopic>,
    onFavoriteClick: (GitHubTopic, Boolean) -> Unit,
    navigationIcon: @Composable () -> Unit = {}
) {
    val appActions = LocalAppActions.current
    val scope = rememberCoroutineScope()
    //val state = LocalMainScrollState.current
    //val showButton by remember { derivedStateOf { state.firstVisibleItemIndex > 0 } }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = navigationIcon,
                title = { Text(text = "Github Topics") },
                actions = {
                    Text("Page: $page")

                    IconsButton(
                        onClick = { scope.launch { onRefresh() } },
                        icon = Icons.Default.Refresh
                    )

                    /*IconsButton(
                        onClick = appActions.onSettingsClick,
                        icon = Icons.Default.Settings
                    )*/
                    /*AnimatedVisibility(visible = showButton) {
                        IconsButton(
                            onClick = { scope.launch { state.animateScrollToItem(0) } },
                            icon = Icons.Default.ArrowUpward
                        )
                    }*/
                },
            )
        },
    ) { padding ->
        TopicContent(
            items = topicItems,
            modifier = Modifier,
            padding = padding,
            state = rememberLazyListState(),
            onCardClick = appActions.onCardClick,
            onLoadMore = onLoadMore,
            isLoading = isLoading,
            onTopicClick = onTopicClick,
            savedTopics = savedTopics,
            currentTopics = currentTopics,
            favoriteList = favoriteList,
            onFavoriteClick = onFavoriteClick
        )
    }
}

@Composable
private fun TopicContent(
    items: List<GitHubTopic>,
    padding: PaddingValues,
    state: LazyListState,
    onCardClick: (GitHubTopic) -> Unit,
    onLoadMore: () -> Unit,
    isLoading: Boolean,
    onTopicClick: (String) -> Unit,
    savedTopics: List<String>,
    currentTopics: List<String>,
    favoriteList: List<GitHubTopic>,
    onFavoriteClick: (GitHubTopic, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(padding)
            .padding(vertical = 2.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            state = state
        ) {
            items(items) { item ->
                val isFavorite by remember {
                    derivedStateOf { favoriteList.any { it.htmlUrl == item.htmlUrl } }
                }
                TopicItem(
                    item = item,
                    savedTopics = savedTopics,
                    currentTopics = currentTopics,
                    onCardClick = onCardClick,
                    onTopicClick = onTopicClick,
                    isFavorite = isFavorite,
                    onFavoriteClick = { onFavoriteClick(item, it) }
                )
            }

            item {
                val scope = rememberCoroutineScope()
                Button(
                    onClick = { scope.launch { onLoadMore() } },
                    enabled = !isLoading,
                ) { Text("Load More") }
            }
        }

        InfiniteListHandler(
            listState = state,
            onLoadMore = onLoadMore,
        )
    }
}