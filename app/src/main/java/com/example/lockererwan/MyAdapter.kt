package com.example.lockererwan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private val userList : ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.KomponenDiambil.text = currentItem.komponen
        holder.JumlahKomponen.text = currentItem.jumlah
        holder.WaktuPengambilan.text = currentItem.waktu
        holder.nama.text = currentItem.nama
        holder.npm.text = currentItem.npm

//        holder.KomponenDiambil.text = currentItem.firstName
//        holder.JumlahKomponen.text = currentItem.lastName
//        holder.namaKomponen.text = currentItem.age
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val KomponenDiambil : TextView = itemView.findViewById(R.id.tvKomponenDiambilVal)
        val JumlahKomponen : TextView = itemView.findViewById(R.id.tvJumlahKomponenVal)
        val WaktuPengambilan : TextView = itemView.findViewById(R.id.tvnamaKomponenVal)
        val nama : TextView = itemView.findViewById(R.id.tvnamaMahasiswaVal)
        val npm : TextView = itemView.findViewById(R.id.tvNpmMahasiswaVal)

    }
}