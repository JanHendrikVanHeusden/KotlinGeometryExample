@file:Suppress("unused", "CanBePrimaryConstructorProperty", "UNUSED_VARIABLE")

package nl.jhvh.kotlin.basics

// 1 - Not the Kotlin way! And: using the constructor keyword (optional for a primary constructor)
class MyClassLikeJava constructor(someMutableNumber: Int) {
    var someMutableNumber = someMutableNumber
}

// 2 - Note that the optional parentheses are gone
class MyClassWithVar {
    var someMutableNumber: Int = 0
}

// 3 - Note that the optional brackets (class body) are gone
class MyClassWithVarParam(var someMutableNumber: Int)

@Suppress("UNUSED_VALUE")
fun main() {
    var anIntVal: Int

    // 1
    val classLikeJava = MyClassLikeJava(3)
    classLikeJava.someMutableNumber = 7        // calls setter
    anIntVal = classLikeJava.someMutableNumber // calls getter

    // 2
    val classWithVar = MyClassWithVar()
    classWithVar.someMutableNumber = 5        // calls setter
    anIntVal = classWithVar.someMutableNumber // calls getter

    // 3
    val classWithVarParam = MyClassWithVarParam(11)
    classWithVarParam.someMutableNumber = 10       // calls setter
    anIntVal = classWithVarParam.someMutableNumber // calls getter

    println("Finally! anIntval = $anIntVal") // calls getter
}