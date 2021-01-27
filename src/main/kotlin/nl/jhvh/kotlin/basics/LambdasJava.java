package nl.jhvh.kotlin.basics;

import kotlin.random.Random;
import mu.KLogger;
import nl.jhvh.kotlin.util.LogKt;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("StandardVariableNames")
public class LambdasJava {

    private static void println() {
        System.out.println();
    }

    private static void println(String input) {
        System.out.println(input);
    }

    // () -> Integer
    public static final Supplier<Integer> randomIntSupplier = Random.Default::nextInt;
    public static final Supplier<Integer> millisIntSupplier = () -> (int) System.currentTimeMillis();

    // () -> String
    public static final Supplier<String> doubleLetterSupplier = () -> {
        Character letter = (char) Random.Default.nextInt(33, 127);
        return letter.toString() + letter.toString();
    };
    public static final Supplier<String> dateTimeStringSupplier =
            () -> LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replace('T', ' ');

    // (String) -> String
    public static final Function<String, String> inverter =
            (String inputString) -> new StringBuffer(inputString).reverse().toString();
    public static final Function<String, String> doubler = (String inputString) -> inputString + inputString;

    // (Integer, Integer) -> Integer
    public static final BiFunction<Integer, Integer, Integer> multiplier = (Integer a, Integer b) -> a * b;
    public static final BiFunction<Integer, Integer, Integer> subtractor = (Integer a, Integer b) -> a - b;

    public static final MyQuadFunction<Integer> addFourNumbers = (Integer a, Integer b, Integer c, Integer d) -> a + b + c + d;

    private final KLogger logger = LogKt.logger(this.getClass().getName());

    public int getNumber(Supplier<Integer> numberSupplier) {
        return numberSupplier.get();
    }

    public String getString(Supplier<String> stringSupplier) {
        return stringSupplier.get();
    }

    public String handleString(String inputString, Function<String, String> stringHandler) {
        String result = stringHandler.apply(inputString);
        logger.info(() -> inputString + " -> " + result);
        return result;
    }

    public int calculate(int int1, int int2, BiFunction<Integer, Integer, Integer> calculator) {
        Integer result = calculator.apply(int1, int2);
        logger.info(() -> "" + int1 + " " + int2 + " -> " + result);
        return result;
    }

    public static void main(String[] args) {
        final LambdasJava lambdas = new LambdasJava();

        println("Random: " + lambdas.getNumber(randomIntSupplier));
        println("Millis: " + lambdas.getNumber(millisIntSupplier));

        println();
        println("2 letters: " + lambdas.getString(doubleLetterSupplier));
        println("date+time: " + lambdas.getString(dateTimeStringSupplier));

        println();
        println("doubled:  " + lambdas.handleString("hoi", doubler));
        println("inverted: " + lambdas.handleString("hallo", inverter));

        println();
        println("multiplied: " + lambdas.calculate(7, 12, multiplier));
        println("subtracted: " + lambdas.calculate(69, 14, subtractor));

        println("4 numbers added: " + addFourNumbers.calcFourInts(2, 4, 6, 8));
    }
}

@SuppressWarnings("StandardVariableNames")
interface MyQuadFunction<T extends Integer> {
    Integer calcFourInts(T a, T b, T c, T d);
}