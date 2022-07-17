package com.example.lockererwan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class UserListActivity : AppCompatActivity() {

    private lateinit var dbRef : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArray: ArrayList<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userRecyclerView = findViewById(R.id.userList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArray = arrayListOf<User>()
        getData()
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
                Toast.makeText(this@UserListActivity, "failed to get data", Toast.LENGTH_SHORT).show()
            }

        }

        )
    }
}