package com.programmersbox.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.programmersbox.shared.screens.TopicDrawer
import com.programmersbox.shared.utils.AppActions
import com.programmersbox.shared.viewmodels.BaseTopicVM
import com.programmersbox.shared.viewmodels.BaseTopicViewModel
import org.ocpsoft.prettytime.PrettyTime
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.*

public actual fun getPlatformName(): String {
    return "Desktop"
}

private object TimeEditor {
    val timePrinter = PrettyTime()
    val format: DateFormat = SimpleDateFormat.getDateTimeInstance()
}

@Suppress("NewApi")
internal actual fun updatedAt(pushedAt: String): String {
    val date = Instant.parse(pushedAt).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    return "Updated " + TimeEditor.timePrinter.format(Date(date)) + " on\n" + TimeEditor.format.format(date)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal actual fun TopicDrawerLocation(vm: BaseTopicVM) {
    val scope = rememberCoroutineScope()
    Theme(
        true,
        remember {
            AppActions(

            )
        }
    ) {
        Surface {
            Column {
                Spacer(Modifier.height(30.dp))
                PermanentNavigationDrawer(
                    drawerContent = { PermanentDrawerSheet { TopicDrawer(vm) } },
                    modifier = Modifier.padding(vertical = 30.dp)
                ) {
                    App(vm)
                }
            }
        }
    }
}

@Composable
public fun UIShow() {
    TopicDrawerLocation(
        remember { BaseTopicViewModel() },
    )
}