package dedi.tsm.testdevelopment

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


import android.util.Log
import android.view.View
import android.widget.Button



import dedi.tsm.testdevelopment.api.ApiService
import dedi.tsm.testdevelopment.api.Retroserver
import dedi.tsm.testdevelopment.model.Kontak
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.file.Files.size





class MainActivity : AppCompatActivity() {
    lateinit var btIns: Button
    lateinit var btLoad: Button
    var mApiInterface: ApiService? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btIns = findViewById(R.id.btIns) as Button
        btLoad =findViewById(R.id.btLoad) as Button

        btLoad.setOnClickListener {
            refresh()
        }
        btIns.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                startActivity(Intent(this@MainActivity, InsertActivity::class.java))
            }
        })
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        mApiInterface = Retroserver.getClient.create(ApiService::class.java)
        refresh()
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
}


