@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package nl.jhvh.kotlin.basics

class ObjectAny {
    val any: Any = Any()
    val anObject: Object = Object()
}

@Suppress("USELESS_IS_CHECK")
fun main() {
    println()
    println("Do not use Object in Kotlin!! Here for this (somewhat weird) demo only!!")
    println()
    val ref = ObjectAny()

    val anyIsAny = (ref.any is Any)
    val anyIsObject = (ref.any is Object)
    val objectIsAny = (ref.anObject is Any)
    val objectIsObject = (ref.anObject is Object)

    println("Any is Any: $anyIsAny")             // true
    println("Any is Object: $anyIsObject")       // true
    println("Object is Any: $objectIsAny")       // true
    println("Object is Object: $objectIsObject") // true
    println()

    // Object.notify() - compiles
    try {
        ref.anObject.notifyAll()
    } catch (e: IllegalMonitorStateException) {
        println("Just showing that Object.notifyAll() compiles, but don't do this!")
    }

    // Any.notify() - Does not compile
    println("Any.notifyAll() does NOT compile...!")
}