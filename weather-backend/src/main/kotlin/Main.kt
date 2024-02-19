import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

suspend fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                val client = WeatherServiceClient();
                val weatherInfo = client.getCity("Helsinki")
                call.respondText(weatherInfo)
            }
        }
    }.start(wait = true)
}