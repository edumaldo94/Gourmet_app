package com.softulp.appgmaldonado.modelo;

public class Post {
    private int userId;
    private String userName;
    private String userProfilePicUrl;
    private String postImageUrl;
    private String postDescription;
    private int likesCount;
    private int commentsCount;

    public Post() {
    }

    public Post(int userId, String userName, String userProfilePicUrl, String postImageUrl, String postDescription, int likesCount, int commentsCount) {
        this.userId = userId;
        this.userName = userName;
        this.userProfilePicUrl = userProfilePicUrl;
        this.postImageUrl = postImageUrl;
        this.postDescription = postDescription;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfilePicUrl() {
        return userProfilePicUrl;
    }

    public void setUserProfilePicUrl(String userProfilePicUrl) {
        this.userProfilePicUrl = userProfilePicUrl;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }
}
