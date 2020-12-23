package nl.jhvh.kotlin.util

import mu.KLogger
import mu.NamedKLogging

fun logger(name: String): KLogger = NamedKLogging(name).logger
fun logger(clazz: Class<*>): KLogger = logger(clazz.name)

inline fun <reified T: Any> T.logger(): KLogger = logger(T::class.java)
