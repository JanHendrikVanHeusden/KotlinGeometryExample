package nl.jhvh.kotlin.basics

import kotlin.reflect.KProperty

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
        return delegatedValue
            .also { println("'${thisRef!!::class.java.simpleName}.${property.name}' (getter) returned $delegatedValue") }
    }

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        val oldValue: T? = delegatedValue
        delegatedValue = value
        println("'${thisRef!!::class.java.simpleName}.${property.name}' (setter) changed old value: [$oldValue] to: [$value]")
    }
}
