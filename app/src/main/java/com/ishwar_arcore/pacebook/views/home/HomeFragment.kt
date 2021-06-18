package com.ishwar_arcore.pacebook.views.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ishwar_arcore.pacebook.views.addposts.ChoosePostActivity
import com.ishwar_arcore.pacebook.views.mainactivity.MainActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.user_profile_timeline_item_layout_photo.*


class HomeFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userRef: DatabaseReference
    private lateinit var userId: String

    private lateinit var profileImg: CircleImageView
    private lateinit var rvPostsHome: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var postAdapter: FirebaseRecyclerAdapter<PostModelJava, PostViewHolder?>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.ishwar_arcore.pacebook.R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        getActionBar()?.title = "Pacebook"
        initViews(view)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {

                    if (dataSnapshot.hasChild("profileimage1")) {
                        val profileImage = dataSnapshot.child("profileimage1").value.toString()
                        Glide.with(profileImg).load(profileImage)
                            .placeholder(com.ishwar_arcore.pacebook.R.drawable.ic_user_1)
                            .into(profileImg)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        userRef.addValueEventListener(postListener)

        view.addPostsFloatingBtn.setOnClickListener {
            val intent = Intent(requireActivity(), ChoosePostActivity::class.java)
            startActivity(intent)
        }
        view.etWriteStatus.setOnClickListener {
            val intent = Intent(requireActivity(), ChoosePostActivity::class.java)
            startActivity(intent)
        }

        displayAllPosts()

    }

    /**override fun onStart() {
    super.onStart()
    val postListener = object : ValueEventListener {
    override fun onDataChange(dataSnapshot: DataSnapshot) {
    if (dataSnapshot.exists()) {
    if (dataSnapshot.hasChild("profileimage1")) {
    val profileImage = dataSnapshot.child("profileimage1").value.toString()
    Glide.with(profileImg).load(profileImage)
    .placeholder(com.ishwar_arcore.pacebook.R.drawable.ic_user_1)
    .into(profileImg)
    }
    }
    }
    override fun onCancelled(databaseError: DatabaseError) {
    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
    }
    userRef.addValueEventListener(postListener)
    }**/

    private fun displayAllPosts() {
        val query = FirebaseDatabase.getInstance()
            .reference
            .child("UploadPosts")

        val options: FirebaseRecyclerOptions<PostModelJava> =
            FirebaseRecyclerOptions.Builder<PostModelJava>()
                .setQuery(query, PostModelJava::class.java)
                .build()

        postAdapter = object : FirebaseRecyclerAdapter<PostModelJava, PostViewHolder?>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

                val view: View = LayoutInflater.from(parent.context)
                    .inflate(
                        com.ishwar_arcore.pacebook.R.layout.user_profile_timeline_item_layout_photo,
                        parent,
                        false
                    )
                return PostViewHolder(view)
            }

            override fun onBindViewHolder(
                holder: PostViewHolder,
                position: Int,
                model: PostModelJava
            ) {
                holder.setData(model)
            }
        }
        rvPostsHome.layoutManager = linearLayoutManager
        rvPostsHome.adapter = postAdapter

    }

    override fun onStart() {
        super.onStart()
        postAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        postAdapter.stopListening()
    }

    inner class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(model: PostModelJava) {
            view.apply {
                Glide.with(ivProfilePicSmallRv).load(model.profileimage)
                    .placeholder(com.ishwar_arcore.pacebook.R.drawable.ic_user_1)
                    .into(ivProfilePicSmallRv)
                Glide.with(tvStatusRv).load(model.postimage)
                    .placeholder(com.ishwar_arcore.pacebook.R.drawable.ic_user_1)
                    .into(tvStatusRv)
                tvUserNameRv.text = model.username
                tvPostDateRv.text = model.date
            }
        }

    }

    private fun initViews(view: View) {
        rvPostsHome = view.findViewById(com.ishwar_arcore.pacebook.R.id.rvPostsHome)
        mAuth = FirebaseAuth.getInstance()
        userId = mAuth.currentUser?.uid.toString()
        profileImg = view.findViewById(com.ishwar_arcore.pacebook.R.id.ivProfilePicHome)
        userRef = FirebaseDatabase.getInstance().reference.child("Users")
        linearLayoutManager = LinearLayoutManager(context)
    }

    /**override fun onResume() {
    super.onResume()
    val postListener = object : ValueEventListener {
    override fun onDataChange(dataSnapshot: DataSnapshot) {
    if (dataSnapshot.exists()) {

    if (dataSnapshot.hasChild("profileimage1")) {
    val profileImage = dataSnapshot.child("profileimage1").value.toString()
    Glide.with(profileImg).load(profileImage)
    .placeholder(com.ishwar_arcore.pacebook.R.drawable.ic_user_1)
    .into(profileImg)
    }
    }
    }

    override fun onCancelled(databaseError: DatabaseError) {
    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
    }

    userRef.addValueEventListener(postListener)
    }**/

    @JvmName("getActionBar1")
    private fun getActionBar(): androidx.appcompat.app.ActionBar? {
        return (activity as MainActivity?)?.supportActionBar
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}