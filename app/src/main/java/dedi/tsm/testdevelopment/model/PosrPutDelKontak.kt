package dedi.tsm.testdevelopment.model

import com.google.gson.annotations.SerializedName
import dedi.tsm.testdevelopment.model.Kontak


class PostPutDelKontak {
    @SerializedName("status")
    lateinit var status: String
    @SerializedName("result")
    lateinit var kontak: Kontak
    @SerializedName("message")
    lateinit var message: String

}

