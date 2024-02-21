import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*

suspend fun main() {
    embeddedServer(Netty, port = 8080) {
        install(CORS) {
            allowMethod(HttpMethod.Get)
            anyHost()
        }
        routing {
            route("/weather/{city}") {
                get {
                    val httpClient = HttpClient(CIO)
                    val client = WeatherServiceClient(httpClient)
                    val city = call.parameters["city"].toString()
                    try {
                        val weatherInfo = client.getCity(city)
                        call.respondText(weatherInfo, ContentType.Application.Json)
                    } catch (e: Exception) {
                        call.respondText("Error fetching weather information", ContentType.Text.Plain)
                    }
                }
            }
        }
    }.start(wait = true)
}