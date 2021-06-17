package com.ishwar_arcore.pacebook.views.addfriemds;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SearchFriends {

    public String username, city, college, profileimage;

    public SearchFriends() {

    }

    public SearchFriends(String username, String city, String college, String profileimage) {
        this.username = username;
        this.city = city;
        this.college = college;
        this.profileimage = profileimage;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

}