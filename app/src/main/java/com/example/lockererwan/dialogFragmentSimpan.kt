package com.example.lockererwan

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.coroutines.coroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [dialogFragmentSimpan.newInstance] factory method to
 * create an instance of this fragment.
 */
class dialogFragmentSimpan : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var database: DatabaseReference
    private lateinit var database2: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_simpan, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mArgs = arguments
        val myValue = mArgs!!.getString("key")
        val userName = mArgs?.getString("judul")
        val tvJudul = view.findViewById<TextView>(R.id.tvDialogFragment)

        tvJudul.text = userName.toString()

        val btnCancel = view.findViewById<Button>(R.id.btnCancelDialogFragment)
        val btnSave = view.findViewById<Button>(R.id.btnSimpanDialogFragment)
        val btnAmbil = view.findViewById<Button>(R.id.btnAmbilDialogFragment)

        val etJumlahKomponen = view.findViewById<TextInputEditText>(R.id.etJumlahKomponenDialogFragment)
        val etSecurity = view.findViewById<TextInputEditText>(R.id.etPasswordDialogFragmentVal)
        val etNama = view.findViewById<TextInputEditText>(R.id.etNamaDialogFragment)
        val etNpm = view.findViewById<TextInputEditText>(R.id.etNpmDialogFragment)

        val jumlahkomponen = etJumlahKomponen.text.toString()
        val password = etSecurity.text.toString()
        val nama = etNama.text.toString()
        val npm = etNpm.text.toString()

        database = Firebase.database.reference
        database2 = Firebase.database.reference

        if (myValue == "ambilTransistor"||myValue == "ambilResistor"||myValue=="ambilKapasitor"){
            btnSave.visibility = View.INVISIBLE
        } else{
            btnAmbil.visibility = View.INVISIBLE
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
        database.child("lokerErwan").child("lokerSecurity").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")

            val securtiy = it.value.toString()
            database = FirebaseDatabase.getInstance().getReference("lokerErwan/"+myValue)
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
//                    Toast.makeText(context, it.value.toString(), Toast.LENGTH_SHORT).show()
                    btnSave.setOnClickListener {
                        val jumlahkomponen = etJumlahKomponen.text.toString()
                        val password = etSecurity.text.toString()
                        val nama = etNama.text.toString()
                        val npm = etNpm.text.toString()

                        if(nama.isEmpty()){
                            etNama.error = "Masukan Nama"
                            return@setOnClickListener
                        }
                        if(npm.isEmpty()){
                            etNpm.error = "Masukan NPM"
                            return@setOnClickListener
                        }
                        if(jumlahkomponen.isEmpty()){
                            etJumlahKomponen.error = "Masukan Jumlah Komponen"
                            return@setOnClickListener
                        }
                        if(password.isEmpty()){
                            etSecurity.error = "Masukan Password"
                            return@setOnClickListener
                        }
                        else{
                            if (password != securtiy){
                                Toast.makeText(context, "Passwod Salah Loker Tidak Terbuka", Toast.LENGTH_SHORT).show()
                            } else{
                                val getValue = snapshot.value.toString()
                                val jumlahkomponen = etJumlahKomponen.text.toString()
                                val data1:Int? = getValue.toInt()
                                val data2:Int? = jumlahkomponen.toInt()
                                val updateData = data1!! + data2!!
                                database2 = Firebase.database.reference
                                database2.child("lokerErwan/"+myValue).setValue(updateData)
                                    .addOnSuccessListener {
//                            Toast.makeText(this, "Succsess Update Data", Toast.LENGTH_SHORT).show()
                                        dismiss()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(context, "Failed Update Data", Toast.LENGTH_SHORT).show()
                                    }
                                historyData()
                            }
                        }


                        }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }

            })
            val myValue2 = mArgs!!.getString("keySave")
            database = FirebaseDatabase.getInstance().getReference("lokerErwan/"+myValue2)
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    btnAmbil.setOnClickListener{
                        val jumlahkomponen = etJumlahKomponen.text.toString()
                        val password = etSecurity.text.toString()
                        val nama = etNama.text.toString()
                        val npm = etNpm.text.toString()

                        if(nama.isEmpty()){
                            etNama.error = "Masukan Nama"
                            return@setOnClickListener
                        }
                        if(npm.isEmpty()){
                            etNpm.error = "Masukan NPM"
                            return@setOnClickListener
                        }
                        if(jumlahkomponen.isEmpty()){
                            etJumlahKomponen.error = "Masukan Jumlah Komponen"
                            return@setOnClickListener
                        }
                        if(password.isEmpty()){
                            etSecurity.error = "Masukan Password"
                            return@setOnClickListener
                        }
                        else{
                            if (password != securtiy){
                                Toast.makeText(context, "Passwod Salah Loker Tidak Terbuka", Toast.LENGTH_SHORT).show()
                            } else{

                                val getValue = snapshot.value.toString()
                                val jumlahkomponen = etJumlahKomponen.text.toString()

                                val data1:Int? = getValue.toInt()
                                val data2:Int? = jumlahkomponen.toInt()
                                val myValue2 = mArgs!!.getString("keySave")
                                val updateData2 = data1!! - data2!!
                                database2 = Firebase.database.reference
                                database2.child("lokerErwan/"+myValue2).setValue(updateData2)
                                    .addOnSuccessListener {
//                            Toast.makeText(this, "Succsess Update Data", Toast.LENGTH_SHORT).show()
                                        dismiss()
                                    }
                                    .addOnFailureListener {
//                            Toast.makeText(context, "Failed Update Data", Toast.LENGTH_SHORT).show()
                                    }
                                historyData()

                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }

            })

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

//            val dataJumlah = FirebaseDatabase.getInstance().getReference("lokerErwan/simpanTransistor")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun historyData(){
        val londonZone = ZoneId.of("Asia/Jakarta")
        val londonCurrentDateTime = ZonedDateTime.now(londonZone)
//        println(londonCurrentDateTime)
        /*
            2018-01-25T07:41:02.296Z[Europe/London]
        */
        val londonDateAndTime = londonCurrentDateTime.format(DateTimeFormatter.ofLocalizedDateTime(
            FormatStyle.FULL, FormatStyle.MEDIUM))

        val etJumlahKomponen = requireView().findViewById<TextInputEditText>(R.id.etJumlahKomponenDialogFragment)
        val etSecurity = requireView().findViewById<TextInputEditText>(R.id.etPasswordDialogFragmentVal)
        val etNama = requireView().findViewById<TextInputEditText>(R.id.etNamaDialogFragment)
        val etNpm = requireView().findViewById<TextInputEditText>(R.id.etNpmDialogFragment)

        val jumlahkomponen = etJumlahKomponen.text.toString()
        val password = etSecurity.text.toString()
        val nama = etNama.text.toString()
        val npm = etNpm.text.toString()

        if(nama.isEmpty()){
            etNama.error = "Masukan Nama"
        }
        if(npm.isEmpty()){
            etNpm.error = "Masukan NPM"
        }
        if(jumlahkomponen.isEmpty()){
            etJumlahKomponen.error = "Masukan Jumlah Komponen"
        }
        if(password.isEmpty()){
            etSecurity.error = "Masukan Password"
        }

        val mArgs = arguments
        val myValue2 = mArgs!!.getString("komponen")
//        val empId = database2.push().key!!
        val history = User(nama,npm,myValue2,jumlahkomponen,londonDateAndTime)
        database2 = Firebase.database.reference
        database2.child("lokerErwan/history").child(londonDateAndTime).setValue(history)
            .addOnSuccessListener {

            }.addOnFailureListener {
                Toast.makeText(context, "Failed Send data history", Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment dialogFragmentSimpan.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            dialogFragmentSimpan().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}