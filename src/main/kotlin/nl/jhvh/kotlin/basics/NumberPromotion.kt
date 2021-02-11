package nl.jhvh.kotlin.basics

fun main() {
    val myShort: Short = -1

    println("This is the same as in Java!")

    println()
    println("myShort is a short             : ${myShort.javaClass}")
    println("-myShort is promoted to an int : ${(-myShort).javaClass}")
}