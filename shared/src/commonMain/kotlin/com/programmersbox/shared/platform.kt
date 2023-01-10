package com.programmersbox.shared

import androidx.compose.runtime.Composable
import com.programmersbox.shared.viewmodels.BaseTopicVM

public expect fun getPlatformName(): String

internal expect fun updatedAt(pushedAt: String): String

@Composable
internal expect fun TopicDrawerLocation(vm: BaseTopicVM)