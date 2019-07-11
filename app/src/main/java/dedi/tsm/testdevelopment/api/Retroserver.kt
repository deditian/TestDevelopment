package dedi.tsm.testdevelopment.api

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object Retroserver {

    val base_url = "http://10.0.2.2/belajar_ci/test/index.php/"

    private var retrofit: Retrofit? = null

    val getClient: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!
        }
}