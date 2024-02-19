import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun main() {
    val token = "PLACE IT HERE"
    println("Hello World!")
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Helsinki/next3days?unitGroup=metric&key=${token}&contentType=json")
    println(response.bodyAsText())
    client.close()
}