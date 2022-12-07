package mx.com.exploremars.data.request

import android.util.Log
import retrofit2.Retrofit
import java.net.URLEncoder

class Request {

    private val utf8 = "UTF-8"

    fun request(requestInfo: RequestInfo): RequestResponse {

        val url = getFormedUrl(requestInfo.url, requestInfo.params)
        val retrofit = Retrofit.Builder()
            .baseUrl(requestInfo.baseUrl)
            .build()

        val call = retrofit.create(RetrofitInterface::class.java)
        val request = call.get(url, requestInfo.headers)
        Log.d("Request", url)
        return try {
            val response = request.execute()
            var body = ""
            if (response.body() != null) {
                body = response.body()!!.string()
            } else if (response.errorBody() != null) {
                body = response.errorBody()!!.string()
            }
            Log.d("Request", "${response.code()} $body")
            RequestResponse(response.code(), body, "")
        } catch (e: Exception) {
            Log.d("Request", "500 ${e.message}")
            RequestResponse(500, "", e.message)
        }
    }

    private fun getFormedUrl(url: String, params: Map<String, String>?): String {
        var formedUrl = url
        params?.let {
            var x = 0
            for (param in it) {
                formedUrl += (if (x == 0) "?" else "&") +
                        URLEncoder.encode(param.key, utf8) + "=" +
                        URLEncoder.encode(param.value, utf8)
                x = 1
            }
        }
        return formedUrl
    }

}