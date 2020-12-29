package nl.jhvh.kotlin.geometry.model

enum class GeometryType(val abbreviation: String) {
    PARALLELOGRAM("Par"),
    RECTANGLE("Rct"),
    SQUARE("Sq");

    companion object {
        // abbreviation must be unique, but this mapping does NOT throw an exception when not unique!
        // If we really want so, we have to check uniqueness by ourselves
        private val typeMapper: Map<String, GeometryType> = values().map { it.abbreviation to it }.toMap()

        init {
            // Throws IllegalStateException when not meeting condition
            check(typeMapper.size == values().size) {"Duplicate key in ${
                values().map { "${it.abbreviation} -> $it" } }"}
        }

        fun byAbbreviation(abbreviation: String): GeometryType? = typeMapper[abbreviation]
    }
}