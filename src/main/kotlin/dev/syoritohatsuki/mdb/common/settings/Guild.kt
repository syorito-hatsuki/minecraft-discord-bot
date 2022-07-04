package dev.syoritohatsuki.mdb.common.settings

import kotlinx.serialization.Serializable

@Serializable
data class GuildConfig(
    val guildId: Int? = null,
    val admins: Set<Int>? = emptySet(),
    val commandChats: Set<Int>? = emptySet(),
    val servers: List<Server>? = emptyList()
)

@Serializable
data class Server(
    val name: String,
    val ip: String,
    val port: Int = 25565
)