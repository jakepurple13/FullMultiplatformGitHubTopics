package com.programmersbox.shared.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.programmersbox.shared.components.CustomNavigationDrawerItem
import com.programmersbox.shared.components.IconsButton
import com.programmersbox.shared.utils.LocalAppActions
import com.programmersbox.shared.viewmodels.BaseTopicVM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
internal fun TopicDrawer(vm: BaseTopicVM) {
    var topicText by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            val actions = LocalAppActions.current
            TopAppBar(
                title = { Text("Topics") },
                actions = { IconsButton(onClick = actions.showFavorites, icon = Icons.Default.Favorite) }
            )
        },
        bottomBar = {
            BottomAppBar {
                OutlinedTextField(
                    value = topicText,
                    onValueChange = { topicText = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .onPreviewKeyEvent {
                            if (it.type == KeyEventType.KeyUp) {
                                if (it.key == Key.Enter) {
                                    vm.addTopic(topicText)
                                    topicText = ""
                                    true
                                } else false
                            } else false
                        },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            vm.addTopic(topicText)
                            topicText = ""
                        }
                    ),
                    label = { Text("Enter Topic") },
                    trailingIcon = {
                        IconsButton(
                            onClick = {
                                vm.addTopic(topicText)
                                topicText = ""
                            },
                            icon = Icons.Default.Add
                        )
                    }
                )
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 2.dp),
        ) {
            stickyHeader {
                val scope = rememberCoroutineScope()
                ElevatedCard(
                    onClick = { scope.launch { vm.toggleSingleTopic() } }
                ) {
                    ListItem(
                        headlineText = { Text("Use ${if (vm.singleTopic) "Single" else "Multiple"} Topic(s)") },
                        trailingContent = {
                            Switch(
                                checked = vm.singleTopic,
                                onCheckedChange = { scope.launch { vm.toggleSingleTopic() } }
                            )
                        }
                    )
                }
            }
            items(vm.topicList) {
                CustomNavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    label = { Text(it) },
                    selected = it in vm.currentTopics,
                    onClick = { vm.setTopic(it) },
                    badge = { IconsButton(onClick = { vm.removeTopic(it) }, icon = Icons.Default.Close) }
                )
            }
        }
    }
}