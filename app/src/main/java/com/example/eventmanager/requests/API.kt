package requests

import adapter.Info
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API{

    @GET
    fun request() : Call<List<Info>>

}