package dev.syoritohatsuki.mdb

import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.events
import dev.syoritohatsuki.mdb.Build.BOT_VERSION
import dev.syoritohatsuki.mdb.bot.event.guild.create.initGuildConfig
import dev.syoritohatsuki.mdb.web.Server
import java.lang.System.getenv

val TOKEN: String = getenv("TOKEN")

suspend fun main() {
    bot(TOKEN) {
        events {
            onGuildCreate {
                initGuildConfig(it)
            }
            onReady {
                setStatus(BOT_VERSION)
            }
        }
    }
}