# KotlinGeometryExample

This project features code to support Fortezza Guild Night "Kotlin - 10 reasons why".

### Same code in 2 languages
It contains 2 source root directories / packages directories, with obvious names:
* **Kotlin sources:** `nl.jhvh.kotlin` (`src/main/kotlin/nl/jhvh/kotlin`)
* **Java sources:** `nl.jhvh.java` (`src/main/java/nl/jhvh/java`)

The 2 packages have the same code, with the "same" code in Kotlin and in Java, respectively.

Spot the difference!

#### Conversions
Some classes / files contain conversions, e.g. from degrees to radians, or from feet to meters.
This is just to show the use of *extension methods* and **`infix`** functions.

*Of course one should NOT code such conversions yourself normally; instead use the stuff from
the **`javax.measure`** for such requirements!*

#### Tests
Like the source code, the tests are also divided in 2 separate root directories:
* **Kotlin tests:** `nl.jhvh.kotlin` (`src/test/kotlin/nl/jhvh/kotlin`)
* **Java sources:** `nl.jhvh.java` (`src/test/java/nl/jhvh/java`)

The tests use the typical mocking libraries: **`Mockito`** for the Java tests, **`mockk`** for the Kotlin tests.
> Note that you can equally well use Mockito in Kotlin tests.
> Or you can write tests in Kotlin to test your Java classes. Or vice versa (well... not always).
>
> The strict separation (Mockito in Java tests to test Java classes, mockk in Kotlin test to test Kotlin classes)
> is not technically imposed, but only exists for the sake of the demo.