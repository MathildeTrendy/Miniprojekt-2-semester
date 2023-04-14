package com.example.MiniProject.DTO;

public class WishlistFormDTO {

    private long list_id;
    private String listName;
    private String listUrl;

    public WishlistFormDTO(long list_id, String listName, String listUrl) {
        this.list_id = list_id;
        this.listName = listName;
        this.listUrl = listUrl;
    }

    public long getList_id() {
        return list_id;
    }

    public String getListName() {
        return listName;
    }

    public String getListUrl() {
        return listUrl;
    }

    public void setList_id(long list_id) {
        this.list_id = list_id;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setListUrl(String listUrl) {
        this.listUrl = listUrl;
    }
}
