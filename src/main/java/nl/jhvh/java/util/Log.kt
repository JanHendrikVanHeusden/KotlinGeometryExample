@file:JvmName("Log")

package nl.jhvh.java.util

import mu.KLogger
import mu.NamedKLogging

internal fun logger(name: String): KLogger = NamedKLogging(name).logger
internal fun logger(clazz: Class<*>): KLogger = logger(clazz.name)
