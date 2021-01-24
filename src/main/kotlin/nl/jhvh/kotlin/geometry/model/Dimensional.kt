package nl.jhvh.kotlin.geometry.model

enum class Dimensional(val dimensionCount: Int) {
    oneDimensional(1),
    twoDimensional(2),
    threeDimensional(3);

    companion object Mapping {
        // dimensionCount must be unique, but this mapping does NOT throw an exception when not unique!
        // If we really want so, we have to check uniqueness by ourselves
        private val dimensionalMapper: Map<Int, Dimensional> = values().map { it.dimensionCount to it }.toMap()

        init {
            // Throws IllegalStateException when not meeting condition
            check(dimensionalMapper.size == values().size) {"Duplicate key in ${values().map { "${it.dimensionCount} -> $it" } }"}
        }

        fun byDimensionCount(dimensionCount: Int): Dimensional? = dimensionalMapper[dimensionCount]
    }
}
