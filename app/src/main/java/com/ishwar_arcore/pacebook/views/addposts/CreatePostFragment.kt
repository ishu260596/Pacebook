package com.ishwar_arcore.pacebook.views.addposts

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.mainactivity.MainActivity
import kotlinx.android.synthetic.main.fragment_create_post.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class CreatePostFragment : Fragment() {

    private lateinit var userId: String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userRef: DatabaseReference
    private lateinit var postRef: DatabaseReference
    private lateinit var postUploadRef: StorageReference

    private lateinit var chooseImage: ImageView
    private lateinit var imageUri: Uri

    private lateinit var saveCurrentDate: String
    private lateinit var saveCurrentTime: String
    private lateinit var savePostName: String
    private lateinit var downloadURL: String
    private lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("profileimage1")) {
                        val profileImageURL = dataSnapshot.child("profileimage1").value.toString()
                        Glide.with(view.ivProfilePicSmallCreatePost).load(profileImageURL)
                            .placeholder(R.drawable.ic_user_1)
                            .into(view.ivProfilePicSmallCreatePost)
                    }
                    if (dataSnapshot.hasChild("username")) {
                        val name = dataSnapshot.child("username").value.toString()
                        view.nameCreatePost.text = name
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        userRef.addValueEventListener(postListener)
        view.apply {
            postVideosPhotosButton.setOnClickListener {
                getContent1.launch("image/*")
            }
            PostButton.setOnClickListener {
                progressBarCreatePost.visibility = View.VISIBLE
                validatePostInfo(view)
            }
        }
    }

    private fun validatePostInfo(view: View) {
        description = view.ETWriteHere.text.toString()
        if (imageUri == null) {
            Toast.makeText(context, "Select Image from gallery", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(description)) {
            Toast.makeText(context, "say something about this image", Toast.LENGTH_SHORT).show()
        } else {
            storingImageToFirebase(view)
        }

    }

    private fun storingImageToFirebase(view: View) {
        val calenderForDate = Calendar.getInstance()
        val currentDate = SimpleDateFormat("dd-MMMM-yyyy")
        saveCurrentDate = currentDate.format(calenderForDate.time)

        val calenderForTime = Calendar.getInstance()
        val currentTime = SimpleDateFormat("HH:mm")
        saveCurrentTime = currentTime.format(calenderForTime.time)

        savePostName = saveCurrentTime + saveCurrentDate

        val filePath: StorageReference = postUploadRef
            .child("Post Images")
            .child(imageUri.lastPathSegment + savePostName + ".jpg")

        val uploadTask: UploadTask = filePath.putFile(imageUri)
        uploadTask.addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot?> {
            Toast.makeText(context, "ImageUpload successfully", Toast.LENGTH_SHORT).show()
            postUploadRef.child("Post Images")
                .child(imageUri.lastPathSegment + savePostName + ".jpg").downloadUrl
                .addOnSuccessListener(
                    OnSuccessListener<Uri> { uri ->
                        downloadURL = uri.toString()

                        Toast.makeText(context, "Stored successfully!", Toast.LENGTH_SHORT)
                            .show()

                        saveToDataBase(view)
                    })
        })

    }

    private fun saveToDataBase(view: View) {
        var postMap = HashMap<String, String>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("profileimage1")) {
                        val profileImageURL = dataSnapshot.child("profileimage1").value.toString()
                        postMap.put("profileimage", profileImageURL)
                    }
                    if (dataSnapshot.hasChild("username")) {
                        val name = dataSnapshot.child("username").value.toString()
                        postMap.put("username", name)
                    }
                    postMap.put("uid", userId)
                    postMap.put("date", saveCurrentDate)
                    postMap.put("time", saveCurrentTime)
                    postMap.put("description", description)
                    postMap.put("postimage", downloadURL)
                    postRef.child(userId + savePostName).updateChildren(postMap as Map<String, Any>)
                        .addOnCompleteListener(
                            OnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(context, "Success !", Toast.LENGTH_SHORT).show()
                                    view.progressBarCreatePost.visibility = View.GONE
                                    val intent = Intent(requireActivity(), MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(context, "Failed !", Toast.LENGTH_SHORT).show()
                                    view.progressBarCreatePost.visibility = View.GONE
                                }
                            })

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
            }
        }
        userRef.addValueEventListener(postListener)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private val getContent1 =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                imageUri = uri
                chooseImage.setImageURI(uri)
            }

        }

    private fun initView(view: View) {
        mAuth = FirebaseAuth.getInstance()
        userId = mAuth.currentUser?.uid.toString()
        postUploadRef = FirebaseStorage.getInstance().reference
        userRef = FirebaseDatabase.getInstance().reference.child("Users").child(userId)
        postRef = FirebaseDatabase.getInstance().reference.child("UploadPosts")
        chooseImage = view.findViewById(R.id.chooseImage)
    }
}