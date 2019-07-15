package dedi.tsm.testdevelopment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast


import dedi.tsm.testdevelopment.api.ApiService
import dedi.tsm.testdevelopment.api.Retroserver
import dedi.tsm.testdevelopment.model.Kontak
import dedi.tsm.testdevelopment.model.PostPutDelKontak
import kotlinx.android.synthetic.main.dialogedittext.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.file.Files.size





class MainActivity : AppCompatActivity() {
    var mApiInterface: ApiService? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        mApiInterface = Retroserver.getClient.create(ApiService::class.java)
        refresh()

        val mFab = findViewById<FloatingActionButton>(R.id.fab)
        mFab.setOnClickListener {
            alertdialogshowinsert()
        }
    }

    private fun alertdialogshowinsert() {
        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialogedittext, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Insert Data")
        //show dialog
        val  mAlertDialog = mBuilder.show()
        //login button click of custom layout
        mDialogView.dialogOKBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
            //get text from EditTexts of custom layout
            val name = mDialogView.dialogNameEt.text.toString()
            val nilai = mDialogView.dialogNilaiEt.text.toString()
            val jurusan = mDialogView.dialogJurusanEt.text.toString()
            //set the input text in TextView
            insertdata(name,nilai,jurusan)

        }
        //cancel button click of custom layout
        mDialogView.dialogCancelBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }

    fun insertdata(name: String, nilai :String, jurusan: String){
        val postKontakCall = mApiInterface?.postKontak(name,nilai,jurusan)
        postKontakCall?.enqueue(object : Callback<PostPutDelKontak> {
            override fun onResponse(call: Call<PostPutDelKontak>, response: Response<PostPutDelKontak>) {
                Toast.makeText(applicationContext, "successs", Toast.LENGTH_LONG).show()
                refresh()
            }

            override fun onFailure(call: Call<PostPutDelKontak>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }






    fun refresh() {
        val kontakCall = mApiInterface?.getKontak()
        kontakCall?.enqueue(object : Callback<List<Kontak>> {

            override fun onResponse(call: Call<List<Kontak>>, response: Response<List<Kontak>>) {
                val KontakList = response.body()
                Log.d("Retrofit Get", "Jumlah data Kontak: "+KontakList )
                mAdapter = KontakList?.let { KontakAdapter(it) }
                mRecyclerView?.setAdapter(mAdapter)
            }

            override fun onFailure(call: Call<List<Kontak>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }


    override fun onResume() {
        super.onResume()
        refresh()
    }
}


