package nl.jhvh.kotlin.util

class ExplicitGetterSetter {

    private var internalNumber = 0

    var someMutableNumber: Int
        get() = internalNumber
        private set(newValue: Int) {
            print("Before: $someMutableNumber")
            internalNumber = newValue
            println("\t After: $someMutableNumber")
        }

    fun setIt(aValue: Int) {
        someMutableNumber = aValue
    }
}

fun main() {
    val getSetDemo = ExplicitGetterSetter()
    // getSetDemo.someMutableNumber = 10 // Cannot assign to 'someMutableNumber': the setter is private in 'ExplicitGetterSetter'
    getSetDemo.setIt(3)
    getSetDemo.setIt(10)
}
