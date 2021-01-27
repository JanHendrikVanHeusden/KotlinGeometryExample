package nl.jhvh.kotlin.basics

import kotlin.reflect.KProperty
import nl.jhvh.kotlin.util.logger

class AttributeDelegator(var aValue: Int) {

    val lazyValue: Int by lazy {
        println("lazyValue set to $aValue")
        aValue
    }
    var delegatedAttribute: Int? by MyAttributeDelegate()
}

fun main() {
    val delegator = AttributeDelegator(aValue = 10)
    delegator.aValue = 15
    println("lazy value: ${delegator.lazyValue}")

    println()
    delegator.delegatedAttribute = 5
    println(delegator.delegatedAttribute)

    delegator.delegatedAttribute = 25
    println(delegator.delegatedAttribute)
}

class MyAttributeDelegate<in R, T> {
    var delegatedValue: T? = null

    operator fun getValue(thisRef: R, property: KProperty<*>): T? {
        return delegatedValue.also { logger(thisRef!!::class.java).info { "getter returned $delegatedValue" } }
    }

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        val oldValue: T? = delegatedValue
        delegatedValue = value
        logger(thisRef!!::class.java).info { "old value: [$oldValue]; Set to: [$value]" }
    }
}
