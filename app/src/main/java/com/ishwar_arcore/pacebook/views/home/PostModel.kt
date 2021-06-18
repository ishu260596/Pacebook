package com.ishwar_arcore.pacebook.views.home

import com.ishwar_arcore.pacebook.utils.NoArg

@NoArg
data class PostModel(
    val date: String,
    val description: String,
    val postimage: String,
    val profileimage: String,
    val time: String,
    val uid: String,
    val username: String
)
