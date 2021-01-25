package nl.jhvh.java.geometry.model;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum Dimensional {
    oneDimensional(1),
    twoDimensional(2),
    threeDimensional(3);

    // dimensionCount must be unique
    // If not unique, mapping would fail with ExceptionInInitializerError - and that's what we want anyway !
    // Caused by: java.lang.IllegalStateException: Duplicate key .. (attempted merging values ... and ...)
    private static final Map<Integer, Dimensional> dimensionMapper = Arrays.stream(values())
            .collect(toMap(dm -> dm.dimensionCount, dm -> dm));

    public final int dimensionCount;

    Dimensional(int dimensionCount) {
        this.dimensionCount = dimensionCount;
    }

    @Nullable
    public static Dimensional byDimensionCount(int dimensionCount) {
        return dimensionMapper.get(dimensionCount);
    }
}
