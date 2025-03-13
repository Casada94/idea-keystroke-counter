package org.anilmisirlioglu.keystroke.converters

import com.intellij.util.xmlb.Converter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LocalDateTimeConverter : Converter<LocalDateTime?>() {

    override fun toString(value: LocalDateTime?): String? {
        if (value != null) {
            return value.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
                .toString()
        }
        return null
    }

    override fun fromString(value: String): LocalDateTime? = Instant
        .ofEpochMilli(value.toLong())
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

}