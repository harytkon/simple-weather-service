import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class WeatherServiceClient (private val httpClient: HttpClient) {
    suspend fun getCity(city: String): String {
        // https://www.visualcrossing.com/resources/documentation/weather-api/using-the-time-period-parameter-to-specify-dynamic-dates-for-weather-api-requests/
        val token = "<PLACE HERE>"
        try {
            val response: HttpResponse = httpClient.get("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${city}/next3days?unitGroup=metric&key=${token}&contentType=json")
            return response.bodyAsText()
        } catch (e: Exception) {
            return "An unexpected error occurred: ${e.message}"
        } finally {
            httpClient.close()
        }
    }
}