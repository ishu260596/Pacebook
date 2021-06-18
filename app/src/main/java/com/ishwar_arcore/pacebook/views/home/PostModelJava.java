package com.ishwar_arcore.pacebook.views.home;

class PostModelJava {
    private String date;
    private String description;
    private String postimage;
    private String profileimage;
    private String time;
    private String uid;
    private String username;

    public PostModelJava(String date,
                         String description,
                         String postimage,
                         String profileimage,
                         String time,
                         String uid,
                         String username) {
        this.date = date;
        this.description = description;
        this.postimage = postimage;
        this.profileimage = profileimage;
        this.time = time;
        this.uid = uid;
        this.username = username;
    }

    public PostModelJava() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
