package mx.com.exploremars.data.request

data class RequestResponse(
    val statusCode: Int,
    val response: String?,
    val errorMessage: String?
)
