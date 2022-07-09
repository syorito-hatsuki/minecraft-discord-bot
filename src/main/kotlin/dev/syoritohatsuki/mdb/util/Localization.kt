package dev.syoritohatsuki.mdb.util

import java.util.*
import kotlin.reflect.KProperty

class LocalizationManager {
    private var value: String? = null

    companion object {
        private fun getString(prop: String): String {
            // TODO get from json locale for current guild
            val language = ResourceBundle.getBundle("lang", Locale("null"))
            return language.getString(prop) ?: ""
        }

        fun getAvailableLocales(): Set<String> {
            val availableLocales = mutableSetOf<String>()
            Locale.getAvailableLocales().forEach {
                this::class.java.classLoader.getResourceAsStream("lang_${it.language}.properties")?.apply {
                    availableLocales.add(it.language)
                }
            }
            return availableLocales
        }

    }

    operator fun getValue(ref: Any?, property: KProperty<*>): String {
        return getString(property.name).apply { value = this }
    }
}

val textFromResource by LocalizationManager()
