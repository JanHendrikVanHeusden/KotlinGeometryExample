package nl.jhvh.java.callingkotlinjava

import nl.jhvh.java.geometry.util.GeometryUtil.radiansToDegrees
import java.io.InputStream

fun main() {
    // Kotlin can figure out that this returns a non-nullable value
    val radiansToDegrees: Double = radiansToDegrees(1.0)
    println(radiansToDegrees)

    // Kotlin NOT figure out whether this returns a nullable value.
    // Return type is InputStream! (which means that you have to decide yourself)
    // We know it will be not null in not-embedded systems
    val inputStream: InputStream = System.`in`
    @Suppress("SENSELESS_COMPARISON")
    println("Is inputStream null? " + (inputStream == null))

    // Kotlin can not figure out whether this returns a nullable value
    // So return type is String! (which means that you have to decide yourself)
    // Let's take it safely first...!
    val nullableProperty: String? = System.getenv("a non existing env value")
    println("getEnv returned: $nullableProperty")

    // Again, return type is String!
    // Assuming a non nullable return value -> causes a NPE !
    val npeProperty: String = System.getProperty("a non existing property")
    println("getProperty returned: $npeProperty") // will never be executed due to NPE

}