package mx.com.exploremars

import mx.com.exploremars.data.request.Request
import mx.com.exploremars.data.request.RequestInfo
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RequestUnitTest {
    @Test
    fun loadRovers() {
        val requestInfo = RequestInfo(
            baseUrl = "https://mars-photos.herokuapp.com/",
            url = "https://mars-photos.herokuapp.com/api/v1/rovers"
        )
        val result = Request().request(requestInfo)
        assert(result.statusCode == 200)
    }
}