package dev.syoritohatsuki.mdb

import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.events
import dev.syoritohatsuki.mdb.util.BOT_TOKEN

suspend fun main() {
    bot(BOT_TOKEN) {
        events {
            onGuildCreate {
//                initGuildConfig(it)
            }
            onReady {
                setStatus("${Build.APP_TITLE} ${Build.BOT_VERSION}")
            }
        }
    }
}