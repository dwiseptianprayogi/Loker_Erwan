package com.example.lockererwan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.lockererwan.R
import com.google.android.material.navigation.NavigationBarView


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

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

        val tvKomponen = view.findViewById<TextView>(R.id.textView3)
        val spinner = view.findViewById<Spinner>(R.id.spinner2).selectedItem.toString()
        val btnLoker = view.findViewById<Button>(R.id.bukalocker)


        tvKomponen.text =  view.findViewById<Spinner>(R.id.spinner2).selectedItem.toString()

        view.findViewById<Button?>(R.id.simpan)?.setOnClickListener(View.OnClickListener {
            tvKomponen.text =  view.findViewById<Spinner>(R.id.spinner2).selectedItem.toString()
            Toast.makeText(context, "Berhasil Simpan " + view.findViewById<Spinner>(R.id.spinner2).selectedItem.toString(), Toast.LENGTH_SHORT).show()
        })
        view.findViewById<Button?>(R.id.ambil)?.setOnClickListener(View.OnClickListener {
            tvKomponen.text =  view.findViewById<Spinner>(R.id.spinner2).selectedItem.toString()
            Toast.makeText(context, "Berhasil Ambil " + view.findViewById<Spinner>(R.id.spinner2).selectedItem.toString(), Toast.LENGTH_SHORT).show()
        })
        btnLoker.setOnClickListener {
            tvKomponen.text =  view.findViewById<Spinner>(R.id.spinner2).selectedItem.toString()
            Toast.makeText(context,"Password Benar Loker Terbuka",Toast.LENGTH_SHORT).show()
        }

    }




//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}