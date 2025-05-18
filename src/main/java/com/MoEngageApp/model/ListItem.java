package com.MoEngageApp.model;

public class ListItem {

	
    private int itemId;
    private int listId;
    private int responseCode;
    private String image;

    public ListItem() {}

    public ListItem(int itemId, int listId, int responseCode, String image) {
        this.itemId = itemId;
        this.listId = listId;
        this.responseCode = responseCode;
        this.image = image;
    }

    // Getters and Setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getListId() { return listId; }
    public void setListId(int listId) { this.listId = listId; }

    public int getResponseCode() { return responseCode; }
    public void setResponseCode(int responseCode) { this.responseCode = responseCode; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return "ListItem [itemId=" + itemId + ", listId=" + listId + ", responseCode=" + responseCode + "]";
    }
}


