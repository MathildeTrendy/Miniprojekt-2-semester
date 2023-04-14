package com.example.MiniProject.DTO;

public class ItemFormDTO {

    private long item_id;
    private String itemName;
    private String itemDescription;
    private int itemPrice;
    private int itemQuantity;
    private String itemUrl;

    public ItemFormDTO(long item_id, String itemName, String itemDescription, int itemPrice, int itemQuantity, String itemUrl) {
        this.item_id = item_id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemUrl = itemUrl;
    }

    public long getItem_id() {
        return item_id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
