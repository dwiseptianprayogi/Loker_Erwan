package com.example.lockererwan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.lockererwan.R
import com.example.lockererwan.dialogFragmentSimpan
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.database.reference

        val btnsimpanTransistor = view.findViewById<Button>(R.id.btnSimpanTransistor)
        val btnsimpanResistor = view.findViewById<Button>(R.id.btnSimpanResistor)
        val btnsimpanKapasitor = view.findViewById<Button>(R.id.btnSimpanKapasitor)

        val btnAmbilTransistor = view.findViewById<Button>(R.id.btnAmbilTransistor)
        val btnAmbilResistor = view.findViewById<Button>(R.id.btnAmbilResistor)
        val btnAmbilKapasitor = view.findViewById<Button>(R.id.btnAmbilKpasitor)

        val tvTransistor = view.findViewById<TextView>(R.id.tvJumlahKomponenTransistorVal)
        val tvResistor = view.findViewById<TextView>(R.id.tvJumlahKomponenResistorVal)
        val tvKapaitor = view.findViewById<TextView>(R.id.tvJumlahKomponenKapasitorVal)

        btnsimpanTransistor.setOnClickListener {
            val simpanTransistor = dialogFragmentSimpan()
            val fragmenManager = childFragmentManager
            val args = Bundle()
            args.putString("key", "simpanTransistor")
            args.putString("komponen", "Simpan Transistor")
            args.putString("judul", "Tambah Komponen Loker Transistor")
            simpanTransistor.setArguments(args)
            simpanTransistor.show(fragmenManager, dialogFragmentSimpan::class.java.simpleName)
        }
        btnsimpanResistor.setOnClickListener {
            val simpanTransistor = dialogFragmentSimpan()
            val fragmenManager = childFragmentManager
            val args = Bundle()
            args.putString("key", "simpanResistor")
            args.putString("komponen", "Simpan Resistor")
            args.putString("judul", "Tambah Komponen Loker Resistor")
            simpanTransistor.setArguments(args)
            simpanTransistor.show(fragmenManager, dialogFragmentSimpan::class.java.simpleName)
        }
        btnsimpanKapasitor.setOnClickListener {
            val simpanTransistor = dialogFragmentSimpan()
            val fragmenManager = childFragmentManager
            val args = Bundle()
            args.putString("key", "simpanKapasitor")
            args.putString("komponen", "Simpan Kapasitor")
            args.putString("judul", "Tambah Komponen Loker Kapasitor")
            simpanTransistor.setArguments(args)
            simpanTransistor.show(fragmenManager, dialogFragmentSimpan::class.java.simpleName)
        }
        btnAmbilTransistor.setOnClickListener {
            val simpanTransistor = dialogFragmentSimpan()
            val fragmenManager = childFragmentManager
            val args = Bundle()
            args.putString("key", "ambilTransistor")
            args.putString("komponen", "Ambil Transistor")
            args.putString("keySave", "simpanTransistor")
            args.putString("judul", "Ambil Komponen Loker Transistor")
            simpanTransistor.setArguments(args)
            simpanTransistor.show(fragmenManager, dialogFragmentSimpan::class.java.simpleName)
        }
        btnAmbilResistor.setOnClickListener {
            val simpanTransistor = dialogFragmentSimpan()
            val fragmenManager = childFragmentManager
            val args = Bundle()
            args.putString("key", "ambilResistor")
            args.putString("komponen", "Ambil Resistor")
            args.putString("keySave", "simpanResistor")
            args.putString("judul", "Ambil Komponen Loker Resistor")
            simpanTransistor.setArguments(args)
            simpanTransistor.show(fragmenManager, dialogFragmentSimpan::class.java.simpleName)
        }
        btnAmbilKapasitor.setOnClickListener {
            val simpanTransistor = dialogFragmentSimpan()
            val fragmenManager = childFragmentManager
            val args = Bundle()
            args.putString("key", "ambilKapasitor")
            args.putString("komponen", "Ambil Kapasitor")
            args.putString("keySave", "simpanKapasitor")
            args.putString("judul", "Ambil Komponen Loker Kapasitor")
                simpanTransistor.setArguments(args)
                simpanTransistor.show(fragmenManager, dialogFragmentSimpan::class.java.simpleName)
        }

        database = FirebaseDatabase.getInstance().getReference("lokerErwan/simpanTransistor")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val getValue = snapshot.value.toString()
                Toast.makeText(context, "Succsess Update Data", Toast.LENGTH_SHORT).show()
                tvTransistor.text = getValue
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
            }

        })
        database = FirebaseDatabase.getInstance().getReference("lokerErwan/simpanResistor")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val getValue = snapshot.value.toString()
                Toast.makeText(context, "Succsess Update Data", Toast.LENGTH_SHORT).show()
                tvResistor.text = getValue
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
            }

        })
        database = FirebaseDatabase.getInstance().getReference("lokerErwan/simpanKapasitor")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val getValue = snapshot.value.toString()
                Toast.makeText(context, "Succsess Update Data", Toast.LENGTH_SHORT).show()
                tvKapaitor.text = getValue
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
            }

        })

    }




//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}