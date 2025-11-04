package data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("alunos/presencas")
    suspend fun getPresences(): List<Student>
}

object RetrofitClient {
    private const val BASE_URL = "https://sua-api.com/"

    val instance: ApiService by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
