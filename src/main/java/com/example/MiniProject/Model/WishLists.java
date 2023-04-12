package com.example.MiniProject.Model;

    public class WishLists {

        //Field of attributes
        private String WishlistName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private int id;

        //Constructor
        public WishLists(String WishlistName) {
            this.WishlistName = WishlistName;
        }

        //Setter and getter
        public String getWishlistName() {
            return WishlistName;
        }

        public void setWishlistName(String wishlistName) {
            this.WishlistName = wishlistName;
        }

    }
