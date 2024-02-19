import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

suspend fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            route("/{city}") {
                get {
                    val city = call.parameters["city"].toString()
                    val httpClient = HttpClient(CIO)
                    val client = WeatherServiceClient(httpClient)
                    val weatherInfo = client.getCity(city)
                    call.respondText(weatherInfo)
                }
            }
        }
    }.start(wait = true)
}