package nl.jhvh.kotlin.basics

fun main() {
    var nullableString: String? = "some text"
    // var notNullable: String = nullableString  // Does not compile -> Required: String   Found: String?
    var notNullable: String = nullableString?:"" // elvis operator

    nullableString = null // OK
    println(nullableString)          // null
    println(notNullable.isEmpty())   // false

    notNullable = nullableString?:""
    println(notNullable.isEmpty())   // true
}