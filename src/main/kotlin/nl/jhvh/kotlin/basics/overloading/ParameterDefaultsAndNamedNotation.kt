package nl.jhvh.kotlin.basics.overloading

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.ISO_8859_1
import java.nio.charset.StandardCharsets.US_ASCII
import java.nio.charset.StandardCharsets.UTF_8
import java.util.Locale

val defaultLocale: Locale = Locale.getDefault()

/** In Java this requires 4 overloads! */
fun handleString(
    string: String,
    locale: Locale? = null,
    charset: Charset = UTF_8
) {
    println("${locale ?: "\t"} \t\t${string.toByteArray(charset).size} bytes \t\t $string")
}

fun main() {
    handleString("한ह")

    // You can use named or positional notation, matter of taste (or coding guidelines)
    handleString("01-31-2021", Locale.US)
    handleString("31-01-2021", locale = defaultLocale)

    handleString("\u20ac", charset = ISO_8859_1)
    handleString(string = "January 31, 2021", Locale.US, US_ASCII)

    // You can even choose a different order of params - not very useful though
    handleString(charset = US_ASCII, locale = Locale.US, string = "January 31, 2021")
}