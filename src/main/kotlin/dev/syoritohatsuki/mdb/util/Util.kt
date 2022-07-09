package dev.syoritohatsuki.mdb.util

import dev.syoritohatsuki.mdb.Build

object Util {
    val homeDir: String = System.getProperty("user.home")

    enum class Folder(val path: String) {
        LINUX("$homeDir/.local/share/${Build.APP_NAME}/"),
        MACOS("$homeDir/Library/Preferences/${Build.APP_NAME}"),
        OTHER(Build.APP_NAME) //Next to the file
    }

    fun getLocalFolder(): String {
        return when (System.getProperty("os.name")) {
            "Linux" -> Folder.LINUX.path
            "Mac OS X" -> Folder.MACOS.path
            else -> Folder.OTHER.path
        }
    }
}