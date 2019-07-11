package dedi.tsm.testdevelopment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import dedi.tsm.testdevelopment.api.ApiService
import dedi.tsm.testdevelopment.api.Retroserver
import dedi.tsm.testdevelopment.model.PostPutDelKontak
import retrofit2.Call
import retrofit2.Response

import retrofit2.Callback


class InsertActivity : AppCompatActivity() {

    lateinit var edtNama: EditText
    lateinit var edtNilai:EditText
    lateinit var edtJurusan:EditText
    lateinit var btInsert: Button
    lateinit var btBack:Button
    lateinit var mApiInterface: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        edtNama =  findViewById(R.id.edtNama) as EditText
        edtNilai =  findViewById(R.id.edtNilai) as EditText
        edtJurusan =  findViewById(R.id.edtJurusan) as EditText

        btInsert = findViewById(R.id.btInserting) as Button
        btBack = findViewById(R.id.btBackGo) as Button
        mApiInterface = Retroserver.getClient.create(ApiService::class.java)

        btInsert.setOnClickListener {


            val postKontakCall = mApiInterface.postKontak(edtNama.text.toString(), edtNilai.text.toString(),edtJurusan.text.toString())
            postKontakCall.enqueue(object : Callback<PostPutDelKontak> {
                override fun onResponse(call: Call<PostPutDelKontak>, response: Response<PostPutDelKontak>) {
                    Toast.makeText(applicationContext, "successs", Toast.LENGTH_LONG).show()
                    finish()
                }

                override fun onFailure(call: Call<PostPutDelKontak>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }
            })

        }

        btBack.setOnClickListener {

            finish()
        }


    }
}
