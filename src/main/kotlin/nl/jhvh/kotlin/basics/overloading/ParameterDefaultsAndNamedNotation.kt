package nl.jhvh.kotlin.basics.overloading

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.nio.charset.StandardCharsets.ISO_8859_1
import java.nio.charset.StandardCharsets.US_ASCII
import java.util.Locale

@Suppress("unused", "UNUSED_PARAMETER")
        /** In Java this requires 4 overloads! */
fun handleString(
    string: String,
    locale: Locale = Locale.getDefault(),
    charset: Charset = StandardCharsets.UTF_8
) {
    // just do something. locale and charset are ignored, this demo is just
    // to show parameter default values and positional notation
    println(string)
}

fun main() {
    handleString("한ह")

    // You can use named or positional notation, matter of taste (or coding guidelines)
    handleString("01-31-2021", Locale.US)
    handleString("01-31-2021", locale = Locale.US)

    handleString("\u20ac", charset = ISO_8859_1)
    handleString(string ="January 31, 2021", Locale.US, US_ASCII)

    // You can even choose a different order of params - not very useful though
    handleString(charset = US_ASCII, locale = Locale.US, string = "January 31, 2021")
}