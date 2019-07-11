package dedi.tsm.testdevelopment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import dedi.tsm.testdevelopment.api.ApiService
import android.content.Intent
import dedi.tsm.testdevelopment.api.Retroserver
import android.widget.Toast
import dedi.tsm.testdevelopment.model.PostPutDelKontak

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class EditActivity : AppCompatActivity() {
    lateinit var edtid: EditText
    lateinit var edtNama: EditText
    lateinit var edtNilai: EditText
    lateinit var edtJurusan: EditText

    lateinit var btUpdate: Button

    lateinit var btDelete: Button
    lateinit var mApiInterface: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        edtid =  findViewById(R.id.edtId) as EditText
        edtNama =  findViewById(R.id.edtNama) as EditText
        edtNilai =  findViewById(R.id.edtNilai) as EditText
        edtJurusan =  findViewById(R.id.edtJurusan) as EditText

        btUpdate =findViewById(R.id.btUpdate2) as Button
        btDelete =findViewById(R.id.btDelete2) as Button


        val mIntent = intent
        edtid.setText(Integer.toString(mIntent.getIntExtra("Id",0)) )
        edtNama.setText(mIntent.getStringExtra("Nama"))
        edtNilai.setText(Integer.toString(mIntent.getIntExtra("Nilai",0)))
        edtJurusan.setText(mIntent.getStringExtra("Jurusan"))

        mApiInterface = Retroserver.getClient.create(ApiService::class.java)


        btUpdate.setOnClickListener {

            val updateKontakCall = mApiInterface.putKontak(
                edtid.text.toString(),
                edtNama.text.toString(),
                edtNilai.text.toString(),
                edtJurusan.text.toString()
            )
            updateKontakCall.enqueue(object : Callback<PostPutDelKontak> {
               override fun onResponse(call: Call<PostPutDelKontak>, response: Response<PostPutDelKontak>) {
                   Toast.makeText(applicationContext, "Success Update", Toast.LENGTH_LONG).show()
                    finish()
                }

               override fun onFailure(call: Call<PostPutDelKontak>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }
            })

        }




        btDelete.setOnClickListener {

            if (edtid.text.toString().trim().isEmpty() == false) {
                val deleteKontak = mApiInterface.deleteKontak(edtid.getText().toString())
                deleteKontak.enqueue(object : Callback<PostPutDelKontak> {
                    override fun onResponse(call: Call<PostPutDelKontak>, response: Response<PostPutDelKontak>) {
                        Toast.makeText(applicationContext, "Success Delete", Toast.LENGTH_LONG).show()
                        finish()
                    }

                    override fun onFailure(call: Call<PostPutDelKontak>, t: Throwable) {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

        }

    }
}
