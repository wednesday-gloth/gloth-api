package com.glothapp.api.config.mvc

import com.glothapp.api.id.AbstractId
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

class AbstractIdConverterFactory: ConverterFactory<String, AbstractId> {

    override fun <T : AbstractId> getConverter(targetType: Class<T>): Converter<String, T> {
        return AbstractIdConverter(targetType)
    }

    private class AbstractIdConverter<T: AbstractId>(private val targetType: Class<T>) : Converter<String, T> {

        @Suppress("UNCHECKED_CAST")
        override fun convert(source: String): T {
            return targetType.constructors.first().newInstance(source.toInt()) as T
        }
    }
}
