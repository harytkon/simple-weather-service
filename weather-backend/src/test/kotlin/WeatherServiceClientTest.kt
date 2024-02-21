import io.ktor.client.*
import io.mockk.coEvery
import io.ktor.client.utils.EmptyContent.status
import io.ktor.http.*
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WeatherServiceClientTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `test getWeather`() {
        val httpClientMock = mockk<HttpClient>()

        coEvery { httpClientMock.get("New York") } coAnswers {
            mockk {
                coEvery { status } returns HttpStatusCode.OK
            }
        }
        val weatherService = WeatherServiceClient(httpClientMock)
        runBlocking {
            val resultNewYork = weatherService.getCity("New York")

            assertEquals("Mocked weather response", resultNewYork)
        }
    }
}