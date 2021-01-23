package nl.jhvh.kotlin.geometry.client.concurrent.polling

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withTimeout
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
@ExperimentalCoroutinesApi
internal class CoroutinePollerTest {

    private val repositoryMock: DataRepository<String> = mockk()
    private val testDispatcher = TestCoroutineDispatcher()

    private val subject: Poller<String> = CoroutinePoller(repositoryMock, testDispatcher)
    private val testData = "coroutine poller data"
    private var counter = 0

    private val testScope = TestCoroutineScope(testDispatcher)

    @BeforeEach
    fun setUp() {
        // Note the coEvery (instead of every), required when mocking coroutine stuff
        coEvery { repositoryMock.getData() } answers { "$testData ${++counter}" }
    }

    @Test
    fun close() {
    }

    @Test
    // @Timeout(2, unit = TimeUnit.SECONDS) // Timeout does not work! -> wanna try? comment out job.cancel()
    fun `should poll every second`() {
        testScope.runBlockingTest {
            // assertTimeoutPreemptively(Duration.ofMillis(500)) /* Timeout does not work! */ {

            // withTimeout works! Note that this uses the "virtual" time of the testDispatcher,
            // timeMillis should be >= time as used in the advanceTimeBy() calls
            // (and probably allow some processing time to avoid flaky tests?)
            withTimeout(timeMillis = 5000) {
                val flow = subject.poll(1.toDuration(DurationUnit.SECONDS))
                var result: String? = null

                val job = launch {
                    flow.collect {
                        result = it
                    }
                }
                assertThat(result).isEqualTo("$testData 1")
                testDispatcher.advanceTimeBy(delayTimeMillis = 1000)
                assertThat(result).isEqualTo("$testData 2")

                testDispatcher.advanceTimeBy(delayTimeMillis = 980)
                assertThat(result).isEqualTo("$testData 2")
                testDispatcher.advanceTimeBy(delayTimeMillis = 35)
                assertThat(result).isEqualTo("$testData 3")

                testDispatcher.advanceTimeBy(delayTimeMillis = 2000)
                assertThat(result).isEqualTo("$testData 5")

                // Test would run infinitely when not cancelled!
                job.cancel()
                println("Done")
            }
        }
    }

}