@file:JvmName("Log")

package nl.jhvh.java.util

import mu.KLogger
import mu.NamedKLogging

fun logger(name: String): KLogger = NamedKLogging(name).logger
fun logger(clazz: Class<*>): KLogger = logger(clazz.name)
