package nl.jhvh.kotlin.basics

fun String.draaiom(): String = StringBuffer(this).reverse().toString()

fun <T : MutableList<*>> T.keepFirstTwo() {
    if (this.size > 2) this.subList(2, this.size).clear()
}

fun Number?.numToBoolean(): Boolean = this != 0

private fun Any.enhancedToString(): String = "#${this.hashCode()}# - $this"

fun main() {
    val myMutableList = mutableListOf("een", "twee", "drie", "vier", "vijf")
    println(myMutableList)
    myMutableList.keepFirstTwo()
    println(myMutableList)

    println("a String".enhancedToString())
    println("a String".draaiom().enhancedToString())
    println(myMutableList.enhancedToString())

    println(with(0) { "$this => ${this.numToBoolean()}" })
    println(with(-3) { "$this => ${this.numToBoolean()}" })
    println(with(3.7) { "$this => ${this.numToBoolean()}" })

    val aNullNum: Number? = null
    println(with(aNullNum) { "$this => ${this.numToBoolean()}" })

    println(with("true") { "$this => ${this.toBoolean()}" })
    println(with("tRue") { "$this => ${this.toBoolean()}" })
    println(with("TRUE") { "$this => ${this.toBoolean()}" })

    println(with("false") { "$this => ${this.toBoolean()}" })
    println(with("fAlse") { "$this => ${this.toBoolean()}" })
    println(with("") { "$this => ${this.toBoolean()}" })

    val aNullString: String? = null
    println(with(aNullString) { "$this => ${this.toBoolean()}" })
}