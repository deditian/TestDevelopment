package dedi.tsm.testdevelopment

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dedi.tsm.testdevelopment.model.Kontak


class KontakAdapter(var mKontakList: List<Kontak>) : RecyclerView.Adapter<KontakAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.kontak_list, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mTextViewId.text = "Id = " + mKontakList[position].id
        Log.i("KontakAdapter","adaga "+mKontakList[position].id)
        holder.mTextViewNama.text = "Nama = " + mKontakList[position].nama
        holder.mTextViewNilai.text = "Nilai = " + mKontakList[position].nilai
        holder.mTextViewJurusan.text = "Jurusan = " + mKontakList[position].jurusan
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val mIntent = Intent(view.getContext(), EditActivity::class.java)
                mIntent.putExtra("Id", mKontakList[position].id)
                mIntent.putExtra("Nama", mKontakList[position].nama)
                mIntent.putExtra("Nilai", mKontakList[position].nilai)
                mIntent.putExtra("Jurusan", mKontakList[position].jurusan)
                view.getContext().startActivity(mIntent)
            }
        })
    }

    override fun getItemCount(): Int {
        return mKontakList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextViewId : TextView
        var mTextViewNama: TextView
        var mTextViewNilai: TextView
        var mTextViewJurusan: TextView

        init {
            mTextViewId = itemView.findViewById(R.id.tvId)
            mTextViewNama = itemView.findViewById(R.id.tvNama)
            mTextViewNilai = itemView.findViewById(R.id.tvNilai)
            mTextViewJurusan = itemView.findViewById(R.id.tvJurusan)
        }

    }
}