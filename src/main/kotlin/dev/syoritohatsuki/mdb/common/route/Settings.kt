package dev.syoritohatsuki.mdb.common.route

import dev.syoritohatsuki.mdb.Build.APP_NAME

val homeDir: String = System.getProperty("user.home")

enum class SettingsRoute(val path: String) {
    LINUX("$homeDir/.local/share/$APP_NAME/"),
    MACOS("$homeDir/Library/Preferences/$APP_NAME"),
    OTHER(APP_NAME) //Next to the file
}

fun getLocalPath(): String {
    return when (System.getProperty("os.name")) {
        "Linux" -> SettingsRoute.LINUX.path
        "Mac OS X" -> SettingsRoute.MACOS.path
        else -> SettingsRoute.OTHER.path
    }
}