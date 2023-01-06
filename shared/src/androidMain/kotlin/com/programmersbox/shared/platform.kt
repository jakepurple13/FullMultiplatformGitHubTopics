package com.programmersbox.shared

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import org.ocpsoft.prettytime.PrettyTime
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.*

public actual fun getPlatformName(): String {
    return "Android"
}

private object TimeEditor {
    val timePrinter = PrettyTime()
    val format: DateFormat = SimpleDateFormat.getDateTimeInstance()
}

@SuppressLint("NewApi")
internal actual fun updatedAt(pushedAt: String): String {
    val date = Instant.parse(pushedAt).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    return "Updated " + TimeEditor.timePrinter.format(Date(date)) + " on\n" + TimeEditor.format.format(date)
}

@Composable
public fun UIShow() {
    App()
}