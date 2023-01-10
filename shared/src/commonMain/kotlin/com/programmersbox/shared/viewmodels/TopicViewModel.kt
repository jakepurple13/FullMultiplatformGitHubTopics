package com.programmersbox.shared.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.programmersbox.shared.network.GitHubTopic
import com.programmersbox.shared.network.Network

internal class BaseTopicViewModel : BaseTopicVM {
    override val items = mutableStateListOf<GitHubTopic>()
    override var isLoading by mutableStateOf(true)
    override var singleTopic by mutableStateOf(true)
    override val currentTopics = mutableStateListOf<String>("jetpack-compose")
    override val topicList = mutableStateListOf<String>()
    override var page by mutableStateOf(1)

    private suspend fun loadTopics() {
        isLoading = true
        Network.getTopics(page, *currentTopics.toTypedArray()).fold(
            onSuccess = { items.addAll(it) },
            onFailure = { it.printStackTrace() }
        )
        isLoading = false
    }

    override suspend fun refresh() {
        items.clear()
        page = 1
        loadTopics()
    }

    override suspend fun newPage() {
        page++
        loadTopics()
    }

}

internal interface BaseTopicVM {
    fun setTopic(topic: String) = Unit
    fun addTopic(topic: String) = Unit
    fun removeTopic(topic: String) = Unit
    suspend fun refresh() = Unit
    suspend fun newPage() = Unit
    suspend fun toggleSingleTopic() = Unit

    val items: SnapshotStateList<GitHubTopic>
    var isLoading: Boolean
    val currentTopics: SnapshotStateList<String>
    val topicList: SnapshotStateList<String>
    val page: Int
    var singleTopic: Boolean
}