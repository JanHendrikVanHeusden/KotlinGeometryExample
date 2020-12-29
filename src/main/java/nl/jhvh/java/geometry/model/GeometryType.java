package nl.jhvh.java.geometry.model;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum GeometryType {

    PARALLELOGRAM("Par"),
    RECTANGLE("Rct"),
    SQUARE("Sq");

    private final String abbreviation;

    GeometryType(String abbreviation) {
        if (abbreviation == null || abbreviation.isEmpty()) {
            throw new IllegalArgumentException("abbreviation of a " + GeometryType.class.getSimpleName() + " must not be null or empty!");
        }
        this.abbreviation = abbreviation;
    }

    // abbreviation must be unique
    // If not unique, mapping would fail with ExceptionInInitializerError - and that's what we want anyway !
    // Caused by: java.lang.IllegalStateException: Duplicate key .. (attempted merging values ... and ...)
    private static final Map<String, GeometryType> typeMapper = Arrays.stream(values())
            .collect(toMap(g -> g.abbreviation, g -> g));

    @Nullable
    public static GeometryType byAbbreviation(String abbreviation) {
        return typeMapper.get(abbreviation);
    }

}