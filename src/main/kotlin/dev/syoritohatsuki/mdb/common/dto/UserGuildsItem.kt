package dev.syoritohatsuki.mdb.common.dto

@kotlinx.serialization.Serializable
data class UserGuildsItem(
    val features: List<String> = emptyList(),
    val icon: String? = null,
    val id: String? = null,
    val name: String? = null,
    val owner: Boolean? = null,
    val permissions: Long,
    val permissions_new: String? = null
)