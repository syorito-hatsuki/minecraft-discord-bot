package dev.syoritohatsuki.mdb.bot.event.guild.create

import com.jessecorbett.diskord.api.gateway.events.CreatedGuild
import dev.syoritohatsuki.mdb.common.route.getLocalPath
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

suspend fun initGuildConfig(createdGuild: CreatedGuild) {
    withContext(Dispatchers.IO) {
        val path = getLocalPath() + "/guilds"
        File(path).mkdirs()
        File(path + "${createdGuild.id}.json")
    }
}