package nl.jhvh.java.callingkotlinjava;

import nl.jhvh.java.util.Log;
import nl.jhvh.kotlin.geometry.model.Dimensional;
import nl.jhvh.kotlin.geometry.model.GeometryType;
import nl.jhvh.kotlin.geometry.model.twodimensional.Parallelogram;
import nl.jhvh.kotlin.geometry.model.twodimensional.ParallelogramKt;
import org.slf4j.Logger;

public class CallKotlinFromJava {

    private static final Logger logger = Log.logger(CallKotlinFromJava.class);

    public static void getAttributeValues() {
        final Parallelogram kotlinPar = new Parallelogram(3d, 2d, 45d);
        System.out.println("Let's instantiate a Kotlin class!");
        logger.info("Constructed Kotlin '" + Parallelogram.class.getSimpleName() + "' from Java:\n " + kotlinPar);
        System.out.println();
        System.out.println("Let's call some getters!");
        logger.info("Let's call some getters!");
        logger.info("attribute 'angleDegrees': \t" + kotlinPar.getAngleDegrees());
        logger.info("attribute 'angleRadians': \t" + kotlinPar.getAngleRadians());
        logger.info("attribute 'dimensional':  \t" + kotlinPar.getDimensional());
        logger.info("attribute 'area':         \t" + kotlinPar.getArea());
    }

    public static void callStaticStuff() {
        System.out.println("Let's call some package level stuff!");
        // No @JvmName annotation: Kotlin filename + Kt postfix
        logger.info("package level constant 'minAngleDegrees': \t" + ParallelogramKt.minAngleDegrees);
        logger.info("package level constant 'maxAngleDegrees': \t" + ParallelogramKt.maxAngleDegrees);

        // Explicit @JvmName annotation = Log
        Logger aLogger = Log.logger("Bogus logger");
        aLogger.info("Hi! (logger by calling package level method 'Log.logger()')");
    }

    public static void callStaticStuffFromUnnamedCompanionObject() {
        System.out.println("Let's call some class level static methods!");
        // Note that in Kotlin you can avoid the .Companion infix, but not when calling from Java
        logger.info("static class method, unnamed companion object:\n'GeometryType.Companion.byAbbreviation(\"Par\")': \t"
                + GeometryType.Companion.byAbbreviation("Par"));
        // Here the companion object is given a name, makes it more readable when calling from Java:
        logger.info("static class method, companion object named 'Mapping':\n'Dimensional.Mapping.byDimensionCount(3)': \t"
                + Dimensional.Mapping.byDimensionCount(3));
    }

    public static void main(String[] args) {
        System.out.println("\r");
        CallKotlinFromJava.getAttributeValues();

        System.out.println("\r");
        CallKotlinFromJava.callStaticStuff();
        System.out.println("\r");
        CallKotlinFromJava.callStaticStuffFromUnnamedCompanionObject();
    }
}
