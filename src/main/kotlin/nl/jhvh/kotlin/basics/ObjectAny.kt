@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package nl.jhvh.kotlin.basics

class ObjectAny {
    val any: Any = Any()
    val anObject: Object = Object()
}

@Suppress("USELESS_IS_CHECK")
fun main() {
    val ref = ObjectAny()
    val anyIsAny = (ref.any is Any)
    val anyIsObject = (ref.any is Object)
    val objectIsAny = (ref.anObject is Any)
    val objectIsObject = (ref.anObject is Object)

    println("Any is Any: $anyIsAny")
    println("Any is Object: $anyIsObject")
    println("Object is Any: $objectIsAny")
    println("Object is Object: $objectIsObject")

    try {
        ref.anObject.notifyAll()
    } catch (e: IllegalMonitorStateException) {
        println("Just showing that it compiles, but don't do this!")
    }
    // ref.any.notify() // Does not compile
}