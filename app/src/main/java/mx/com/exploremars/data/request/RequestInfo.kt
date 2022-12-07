package mx.com.exploremars.data.request

import java.util.*

data class RequestInfo(
    val baseUrl: String = "",
    val method: String = RequestConstants.GET,
    val url: String = "",
    val params: Map<String, String> = TreeMap(),
    val headers: Map<String, String> = TreeMap(),
)

object RequestConstants {
    const val GET = "GET"
}
