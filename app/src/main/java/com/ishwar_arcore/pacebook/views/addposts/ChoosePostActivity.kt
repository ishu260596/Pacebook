package com.ishwar_arcore.pacebook.views.addposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.ishwar_arcore.pacebook.R
import kotlinx.android.synthetic.main.activity_choose_post.*
import kotlinx.android.synthetic.main.activity_choose_post.tvCheckIn
import kotlinx.android.synthetic.main.fragment_profile.*

class ChoosePostActivity : AppCompatActivity() {
    private lateinit var userId: String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private var fragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_post)
        mAuth = FirebaseAuth.getInstance()
        userId = mAuth.currentUser?.uid.toString()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Users").child(userId)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("username")) {
                        val name = dataSnapshot.child("username").value.toString()
                        tvNamePost.text = "Hi $name!"
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ChoosePostActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        databaseRef.addValueEventListener(postListener)


        writeStatus.setOnClickListener {
            launchFragment()
        }
        writeStatus.setOnClickListener {
            launchFragment()
        }
        tvPhotosPost.setOnClickListener {
            launchFragment()
        }
        tvTagePeople.setOnClickListener {
            launchFragment()
        }
        tvFeelingPost.setOnClickListener {
            launchFragment()
        }
        tvCheckIn.setOnClickListener {
            launchFragment()
        }
        tvPSeeMore.setOnClickListener {
            launchFragment()
        }
        EmotionsButton.setOnClickListener {
            launchFragment()
        }


    }

    private fun launchFragment() {
        fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val createPostFragment = CreatePostFragment()
        fragmentTransaction?.replace(R.id.postContainer, createPostFragment, "Create Post")
            ?.commit()
    }
}