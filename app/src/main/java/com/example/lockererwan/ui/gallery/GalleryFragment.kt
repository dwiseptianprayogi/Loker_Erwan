package com.example.lockererwan.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.lockererwan.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lockererwan.MyAdapter
import com.example.lockererwan.User
import com.example.lockererwan.databinding.FragmentGalleryBinding
import com.google.firebase.database.*

class GalleryFragment : Fragment() {

    private lateinit var dbRef : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArray: ArrayList<User>
    private var _binding: FragmentGalleryBinding? = null

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>? = null
//
//    private lateinit var routinesViewModel : GalleryFragment
//
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
//        val textView: TextView = root.findViewById(R.id.text_gallery)
//        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView = view?.findViewById(R.id.userList)!!
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)

        userArray = arrayListOf<User>()
        getData()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData() {
        dbRef = FirebaseDatabase.getInstance().getReference("riwayat")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val riwayat = userSnapshot.getValue(User::class.java)
                        userArray.add(riwayat!!)
                    }
                    userRecyclerView.adapter = MyAdapter(userArray)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "failed to get data", Toast.LENGTH_SHORT).show()
            }

        }

        )
    }
}