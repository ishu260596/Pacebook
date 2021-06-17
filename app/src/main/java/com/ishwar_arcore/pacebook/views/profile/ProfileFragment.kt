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
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.ishwar_arcore.pacebook.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private lateinit var userId: String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var profilePicRef: StorageReference
    private lateinit var coverPicRef: StorageReference

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

            }
            tvCoverPhoto.setOnClickListener {
                getContent2.launch("image/*")
                tvCoverPhoto.visibility = View.GONE
                btnUploadCoverImage.visibility = View.VISIBLE

            }
            btnUploadCoverImage.setOnClickListener {
                getContent2.launch("image/*")
            }
            profileImg.setOnClickListener {
                getContent1.launch("image/*")
            }
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("profileimage1")) {
                        val profileImageURL = dataSnapshot.child("profileimage1").value.toString()
                        Glide.with(profileImg).load(profileImageURL)
                            .placeholder(R.drawable.ic_user_1)
                            .into(profileImg)
                    }

                    if (dataSnapshot.hasChild("coverimage1")) {
                        val coverImageURL = dataSnapshot.child("coverimage1").value.toString()
                        Glide.with(coverImg).load(coverImageURL)
                            .placeholder(R.drawable.ic_rectangle_3)
                            .into(coverImg)
                        tvCoverPhoto.visibility = View.GONE
                        btnUploadCoverImage.visibility = View.VISIBLE
                    }

                    val name = dataSnapshot.child("username").value.toString()
                    tvUserName.text = name

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
        profilePicRef = FirebaseStorage.getInstance().reference.child("Profile Images")
        coverPicRef = FirebaseStorage.getInstance().reference.child("Cover Images")
        databaseRef = FirebaseDatabase.getInstance().reference.child("Users").child(userId)

        profileImg = view.findViewById(R.id.ivProfilePicUser)
        coverImg = view.findViewById(R.id.ivCoverPhotoUser)
        tvAddCoverPhoto = view.findViewById(R.id.tvCoverPhoto)

    }


    //ProfileImage
    @RequiresApi(Build.VERSION_CODES.P)
    private val getContent1 =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            profileImg.setImageURI(uri)
            val filePath: StorageReference = profilePicRef.child("$userId.jpg")
            if (uri != null) {
                val uploadTask: UploadTask = filePath.putFile(uri)
                uploadTask.addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot?> {
                    profilePicRef.child("$userId.jpg").downloadUrl.addOnSuccessListener(
                        OnSuccessListener<Uri> { uri ->
                            val url = uri.toString()
                            databaseRef.child("profileimage1").setValue(url)
                            Toast.makeText(context, "Stored successfully!", Toast.LENGTH_SHORT)
                                .show()
                        })
                })

            }
        }

    //CoverImage
    @RequiresApi(Build.VERSION_CODES.P)
    private val getContent2 =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            coverImg.setImageURI(uri)
            val filePath: StorageReference = coverPicRef.child("$userId.jpg")
            if (uri != null) {
                val uploadTask: UploadTask = filePath.putFile(uri)
                uploadTask.addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot?> {
                    coverPicRef.child("$userId.jpg").downloadUrl.addOnSuccessListener(
                        OnSuccessListener<Uri> { uri ->
                            val url = uri.toString()
                            databaseRef.child("coverimage1").setValue(url)
                            Toast.makeText(context, "Stored successfully!", Toast.LENGTH_SHORT)
                                .show()
                        })
                })
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
/**uploadTask.addOnCompleteListener { task ->
if (task.isSuccessful) {
Toast.makeText(context, "Upload successfully!", Toast.LENGTH_SHORT)
.show()
userProfilePhotoRef.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
val url = uri.toString()
databaseRef.child("profileimage1").setValue(url)
Toast.makeText(context, "Stored successfully!", Toast.LENGTH_SHORT)
.show()
})
}
}**/
/**  val uploadTask1: UploadTask = filePath.putFile(uri)
uploadTask.addOnCompleteListener {
userProfilePhotoRef.downloadUrl.addOnCompleteListener(OnCompleteListener {
databaseRef.child("profileimage1").setValue(it.toString())
Toast.makeText(context, "Stored successfully!", Toast.LENGTH_SHORT).show()
})
}**/
