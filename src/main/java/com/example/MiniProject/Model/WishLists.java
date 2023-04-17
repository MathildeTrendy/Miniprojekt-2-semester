package com.example.MiniProject.Model;

public class WishLists {

    //Field of attributes
    private String wishListName;
    private String userEmail;
    private int id;

    public WishLists(String wishListName, String userEmail, int id) {
        this.wishListName = wishListName;
        this.userEmail = userEmail;
        this.id = id;
    }

    public WishLists(int id, String wishListName, String userEmail) {
    }


    public String getWishListName() {
        return wishListName;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}