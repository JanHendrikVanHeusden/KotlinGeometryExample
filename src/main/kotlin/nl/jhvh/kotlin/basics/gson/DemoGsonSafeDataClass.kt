package nl.jhvh.kotlin.basics

import com.google.gson.Gson
import nl.jhvh.kotlin.geometry.model.twodimensional.GsonSafeParallelogram
import nl.jhvh.kotlin.geometry.model.twodimensional.Parallelogram

fun main() {
    val gson = Gson()
    val parJson = gson.toJson(Parallelogram(s1 = 7.0, s2 = 4.0, angleDegrees = 30.0))
    println(parJson)

    var jsonParString = """{"s1":1.0,"s2":2.0,"angleRadians":0.7853981633974483,"angleDegrees":45.0,"length":1.0}"""
    var parFromJson: Parallelogram = gson.fromJson(jsonParString, Parallelogram::class.java)
    println(parFromJson)

    // jsonString = """{"s1":1.0,"s2":2.0,"angleRadians":0.7853981633974483,"angleDegrees":45.0,"length":1.0}"""
    // leaving angleRadians and length out!
    jsonParString = """{"s1":1.0,"s2":2.0,"angleDegrees":45.0}"""
    parFromJson = gson.fromJson(jsonParString, Parallelogram::class.java)

    println()
    println(">>> Incorrect! Not Gson safe!")
    println()
    println("angleRadians:  ${parFromJson.angleRadians}")    // 0.0 !!
    println("length:        ${parFromJson.length}")          // 0.0 !!
//    println("width:         ${parFromJson.width}")           // NPE !!
    println("dimensional:   ${parFromJson.dimensional}")     // null !! No NPE!
    println("geometryType:  ${parFromJson.geometryType}")    // null !! No NPE!
    println("circumference: ${parFromJson.circumference}")   // 0.0 !!
//    println("area:          ${parFromJson.area}")            // NPE !!

    println()
    println(">>> Correct!")
    println()

    val jsonSafeParFromJson = gson.fromJson(jsonParString, GsonSafeParallelogram::class.java)

    println("angleRadians:  ${jsonSafeParFromJson.angleRadians}")
    println("length:        ${jsonSafeParFromJson.length}")
    println("width:         ${jsonSafeParFromJson.width}")
    println("dimensional:   ${jsonSafeParFromJson.dimensional}")
    println("geometryType:  ${jsonSafeParFromJson.geometryType}")
    println("circumference: ${jsonSafeParFromJson.circumference}")
    println("area:          ${jsonSafeParFromJson.area}")

}