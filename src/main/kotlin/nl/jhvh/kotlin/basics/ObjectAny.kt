@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package nl.jhvh.kotlin.basics

class ObjectAny {
    // The root of the Kotlin class hierarchy. Every Kotlin class has Any as a superclass.
    val myAny: Any = Any()          // kotlin.Any

    // The root of the Java class hierarchy. Every Java class has Object as a superclass.
    val myObject: Object = Object() // java.lang.Object
}

@Suppress("USELESS_IS_CHECK")
fun main() {
    println()
    println("Do not use java.lang.Object in Kotlin!! Here for this (somewhat weird) demo only!!")
    println()
    val ref = ObjectAny()

    val anyIsAny = (ref.myAny is Any)
    val anyIsObject = (ref.myAny is Object)
    val objectIsAny = (ref.myObject is Any)
    val objectIsObject = (ref.myObject is Object)

    println("Any is Any: $anyIsAny")             // true
    println("Any is Object: $anyIsObject")       // true
    println("Object is Any: $objectIsAny")       // true
    println("Object is Object: $objectIsObject") // true
    println()

    // Object.notify() - compiles
    try {
        ref.myObject.notifyAll()
    } catch (e: IllegalMonitorStateException) {
        println("Just showing that Object.notifyAll() compiles, but don't do this!")
    }

    // Any.notify() - Does not compile
    println("Any.notifyAll() does NOT compile...!")
}