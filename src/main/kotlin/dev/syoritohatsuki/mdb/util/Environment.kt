package dev.syoritohatsuki.mdb.util

import io.github.cdimascio.dotenv.dotenv
import kotlin.reflect.KProperty

class Environment {
    private var value: String? = null

    operator fun getValue(th: Any?, prop: KProperty<*>): String =
        value ?: dotenv()[prop.name].apply { value = this }
        ?: System.getenv(prop.name).apply { value = this }
        ?: throw Throwable("${prop.name} environment not set")
}

val BOT_TOKEN by Environment()