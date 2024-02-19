import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.mockk.coEvery
import io.ktor.client.utils.EmptyContent.status
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
        // Mock the HttpClient
        val httpClientMock = mockk<HttpClient>()

        coEvery { httpClientMock.get("fgfgf") } coAnswers {
            mockk {
                coEvery { status } returns io.ktor.http.HttpStatusCode.OK
            }
        }
        val weatherService = WeatherServiceClient(httpClientMock)
        runBlocking {
            val resultNewYork = weatherService.getCity("NewYork")
            val resultLondon = weatherService.getCity("London")
            val resultParis = weatherService.getCity("Paris")

            // Verify the results
            assertEquals("Mocked weather response", resultNewYork)
            assertEquals("Mocked weather response", resultLondon)
            assertEquals("Mocked weather response", resultParis)
        }
    }
}