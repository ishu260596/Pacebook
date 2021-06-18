package com.ishwar_arcore.pacebook.views.home

import com.ishwar_arcore.pacebook.utils.NoArg

@NoArg
data class PostModel(
    val date: String? = null,
    val description: String? = null,
    val postimage: String? = null,
    val profileimage: String? = null,
    val time: String? = null,
    val uid: String? = null,
    val username: String? = null
) {

}
