package com.ishwar_arcore.pacebook.views.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.loginandsignup.LoginActivity
import com.ishwar_arcore.pacebook.views.loginandsignup.RegisterActivity
import com.ishwar_arcore.pacebook.views.mainactivity.MainActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var userId: String

    private lateinit var profileImg: CircleImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        getActionBar()?.title = "Pacebook"
        mAuth = FirebaseAuth.getInstance()
        userId = mAuth.currentUser?.uid.toString()
        profileImg = view.findViewById(R.id.ivProfilePicHome)
        databaseRef = FirebaseDatabase.getInstance().reference.child("Users").child(userId)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {

                    if (dataSnapshot.hasChild("profileimage1")) {
                        val profileImage = dataSnapshot.child("profileimage1").value.toString()
                        Glide.with(profileImg).load(profileImage)
                            .placeholder(R.drawable.ic_user_1)
                            .into(profileImg)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        databaseRef.addValueEventListener(postListener)

    }

    @JvmName("getActionBar1")
    private fun getActionBar(): androidx.appcompat.app.ActionBar? {
        return (activity as MainActivity?)?.getSupportActionBar()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}