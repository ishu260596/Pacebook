package com.ishwar_arcore.pacebook.views.profile

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.ishwar_arcore.pacebook.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private lateinit var userId: String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var userProfilePhotoRef: StorageReference
    private lateinit var userCoverPhotoRef: StorageReference

    private lateinit var profileImg: CircleImageView
    private lateinit var coverImg: ImageView
    private lateinit var tvAddCoverPhoto: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        view.apply {
            btnUploadDP.setOnClickListener {
                getContent1.launch("image/*")
                btnUploadDP.visibility = View.GONE
                viewGreenDotProfile.visibility = View.VISIBLE
            }
            tvCoverPhoto.setOnClickListener {
                getContent2.launch("image/*")
                tvCoverPhoto.visibility = View.GONE
                btnUploadCoverImage.visibility = View.VISIBLE
            }
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val profileImage = dataSnapshot.child("profileimage").value.toString()
                    Picasso.get().load(profileImage).placeholder(R.drawable.ic_user_1)
                        .into(profileImg)

                    val coverImage = dataSnapshot.child("coverimage").value.toString()
                    Picasso.get().load(coverImage).placeholder(R.drawable.ic_rectangle_3)
                        .into(coverImg)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        databaseRef.addValueEventListener(postListener)
    }

    private fun initViews(view: View) {
        mAuth = FirebaseAuth.getInstance()
        userId = mAuth.currentUser?.uid.toString()
        userProfilePhotoRef = FirebaseStorage.getInstance().reference.child("Profile Images")
        userCoverPhotoRef = FirebaseStorage.getInstance().reference.child("Cover Images")
        databaseRef = FirebaseDatabase.getInstance()
            .reference.child("Users")
            .child(userId)

        profileImg = view.findViewById(R.id.ivProfilePicUser)
        coverImg = view.findViewById(R.id.ivCoverPhotoUser)
        tvAddCoverPhoto = view.findViewById(R.id.tvCoverPhoto)


    }

    @RequiresApi(Build.VERSION_CODES.P)
    private val getContent1 =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            profileImg.setImageURI(uri)
            val filePath: StorageReference = userProfilePhotoRef.child("$userId.jpg")
            if (uri != null) {
                val uploadTask: UploadTask = filePath.putFile(uri)
                val urlTask = uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }

                    userProfilePhotoRef.downloadUrl

                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val downloadUri = task.result

                        Toast.makeText(
                            context,
                            "ProfilePic Upload successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        val profileURL = userCoverPhotoRef.downloadUrl.toString()

                        databaseRef.child("profileimage").setValue(profileURL)
                            .addOnCompleteListener {
                                Toast.makeText(
                                    context,
                                    "$profileURL firebase database",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                    } else {
                        Toast.makeText(
                            context,
                            " Error in uploading firebase database",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

        }

    @RequiresApi(Build.VERSION_CODES.P)
    private val getContent2 =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            coverImg.setImageURI(uri)
            val filePath: StorageReference = userCoverPhotoRef.child("$userId.jpg")
            if (uri != null) {
                val uploadTask: UploadTask = filePath.putFile(uri)
                val urlTask = uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    userCoverPhotoRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUriCover = task.result
                        Toast.makeText(
                            context,
                            "CoverPic Upload successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        val coverURL = userProfilePhotoRef.downloadUrl
                        databaseRef.child("coverimage").setValue(coverURL)
                            .addOnCompleteListener {
                                Toast.makeText(
                                    context,
                                    "$coverURL firebase database",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    } else {
                        Toast.makeText(
                            context,
                            " Error in uploading firebase database",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

        }

    companion object {
        fun newInstance() = ProfileFragment()
    }

}

/** filePath.putFile(uri)
.addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot?> {
Toast.makeText(
context,
"ProfilePic Upload successfully",
Toast.LENGTH_SHORT
).show()
userProfilePhotoRef.downloadUrl
})
 **/