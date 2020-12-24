package nl.jhvh.kotlin.util

import mu.KLogger
import mu.NamedKLogging

internal fun logger(name: String): KLogger = NamedKLogging(name).logger
internal fun logger(clazz: Class<*>): KLogger = logger(clazz.name)

internal inline fun <reified T : Any> T.logger(): KLogger = logger(T::class.java)
