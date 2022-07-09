//package dev.syoritohatsuki.mdb.web
//
//import com.jessecorbett.diskord.api.common.Permission
//import com.jessecorbett.diskord.api.common.Permissions
//import dev.syoritohatsuki.mdb.common.dto.UserGuildsItem
//import dev.syoritohatsuki.mdb.common.route.getLocalPath
//import dev.syoritohatsuki.mdb.web.principal.UserSession
//import io.ktor.application.*
//import io.ktor.auth.*
//import io.ktor.client.*
//import io.ktor.client.request.*
//import io.ktor.features.*
//import io.ktor.http.*
//import io.ktor.response.*
//import io.ktor.routing.*
//import io.ktor.serialization.*
//import io.ktor.server.cio.*
//import io.ktor.server.engine.*
//import io.ktor.sessions.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.json.Json
//import java.nio.file.Files
//import kotlin.io.path.Path
//import kotlin.io.path.name
//
//object Server {
//
//    private val httpClient = HttpClient()
//    private val json = Json { ignoreUnknownKeys = true }
//
//    fun start() {
//        embeddedServer(CIO, host = "0.0.0.0", port = 8080) {
//            install(Sessions) {
//                cookie<UserSession>("user_session")
//            }
//            install(Authentication) {
//                oauth("auth-oauth-discord") {
//                    urlProvider = { "http://0.0.0.0:8080/callback" }
//                    providerLookup = {
//                        OAuthServerSettings.OAuth2ServerSettings(
//                            name = "discord",
//                            authorizeUrl = "https://discord.com/api/oauth2/authorize",
//                            accessTokenUrl = "https://discord.com/api/oauth2/token",
//                            requestMethod = HttpMethod.Post,
//                            clientId = System.getenv("CLIENT_ID"),
//                            clientSecret = System.getenv("CLIENT_SECRET"),
//                            defaultScopes = listOf("identify guilds")
//                        )
//                    }
//                    client = httpClient
//                }
//                install(ContentNegotiation) {
//                    json(json)
//                }
//            }
//            routing {
//                authenticate("auth-oauth-discord") {
//                    route("auth") {
//                        get("login") {}
//
//                        get("callback") {
//                            val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
//                            call.sessions.set(UserSession(principal?.accessToken.toString()))
//                            call.respondRedirect("servers")
//                        }
//                    }
//                }
//                route("servers") {
//                    get {
//                        val userSession: UserSession? = call.sessions.get<UserSession>()
//                        if (userSession != null) {
//                            val data: String = httpClient.get("https://discord.com/api/users/@me/guilds") {
//                                headers {
//                                    append(HttpHeaders.Authorization, "Bearer ${userSession.authToken}")
//                                }
//                            }
//
//
//                            val files = withContext(Dispatchers.IO) {
//                                val t = mutableListOf<String>()
//                                json.decodeFromString<List<UserGuildsItem>>(data)
//                                    .filter { Permission.ADMINISTRATOR in Permissions(it.permissions) }
//                                    .forEach {
//                                        Files.list(Path(getLocalPath() + "/guilds")).forEach { path ->
//                                            if (path.name.contains(it.id.toString())) {
//                                                t.add(it.id.toString())
//                                            }
//                                        }
//                                    }
//                                return@withContext t
//                            }
//
//
//                            call.respond(files.sort())
//                        } else {
//                            call.respondRedirect("/")
//                        }
//                    }
//                }
//            }
//        }.start(wait = false)
//    }
//}
