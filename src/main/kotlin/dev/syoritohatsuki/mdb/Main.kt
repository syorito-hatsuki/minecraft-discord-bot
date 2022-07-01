package dev.syoritohatsuki.mdb

import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.events
import dev.syoritohatsuki.mdb.Build.BOT_VERSION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.System.getenv

val TOKEN: String = getenv("TOKEN")

suspend fun main() {
    bot(TOKEN) {
        events {
            onGuildCreate {
                withContext(Dispatchers.IO) {
                    val path = "${System.getProperty("user.home")}/.local/share/mdb/guilds"
                    File(path).mkdirs()
                    File(path + "${it.id}.json")
                }
            }
            onReady {
                setStatus(BOT_VERSION)
            }
        }
    }
}