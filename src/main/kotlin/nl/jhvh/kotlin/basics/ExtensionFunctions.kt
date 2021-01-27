package nl.jhvh.kotlin.basics

fun String.draaiom(): String = StringBuffer(this).reverse().toString()

fun <T : MutableList<*>> T.houdEersteTwee() {
    if (this.size > 2) this.subList(2, this.size).clear()
}

private fun Any.enhancedToString(): String = "#${this.hashCode()}# - $this"

fun main() {
    val myMutableList = mutableListOf("een", "twee", "drie", "vier", "vijf")
    println(myMutableList)
    myMutableList.houdEersteTwee()
    println(myMutableList)

    println("a String".enhancedToString())
    println("a String".draaiom().enhancedToString())
    println(myMutableList.enhancedToString())
}