package nl.jhvh.kotlin.basics

class ObjectAny {
    val any: Any = Any()
    val anObject: Object = Object()
}

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

    ref.anObject.notify()
    // ref.any.notify() // Does not compile
}