import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class WeatherServiceClient {
    suspend fun getCity(city: String): String {
        // https://www.visualcrossing.com/resources/documentation/weather-api/using-the-time-period-parameter-to-specify-dynamic-dates-for-weather-api-requests/
        val token = "<PLACE HERE>"
        println("Hello World!")
        val client = HttpClient(CIO)
        val response: HttpResponse = client.get("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${city}/next3days?unitGroup=metric&key=${token}&contentType=json")
        client.close()
        return response.bodyAsText()
    }
}