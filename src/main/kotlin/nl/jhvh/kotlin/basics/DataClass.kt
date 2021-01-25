package nl.jhvh.kotlin.basics

data class NestedDataClass(val nestedVar: String)

data class DataClass(val var1: String, val var2: Int, val var3: NestedDataClass)

fun main() {
    val nested = NestedDataClass("hoi")
    val data = DataClass("1st var", 7, nested)

    // toString() out of the box for data classes
    println(data)

    // == in Kotlin is "null safe equals()"
    // You get it out of the box too
    println("Equals? ${data == DataClass("1st var", 7, nested)}") // true

    // For exact same reference, use ===
    // This is equivalent to Java ==
    // We use it to show that the copy method makes a shallow copy, not a deep copy
    println("Shallow copy? ${data.copy().var3 === nested}") // true

    // Nice! Copy but with 1 value different
    val data2 = data.copy(var2 = -1)
    println(data2)
}