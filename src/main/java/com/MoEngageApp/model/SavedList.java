package com.MoEngageApp.model;

import java.util.Date;

public class SavedList {

	

	
    private int listId;
    private int userId;
    private String listName;
    private Date createdDate;

    public SavedList() {}

    public SavedList(int listId, int userId, String listName, Date createdDate) {
        this.listId = listId;
        this.userId = userId;
        this.listName = listName;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public int getListId() { return listId; }
    public void setListId(int listId) { this.listId = listId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getListName() { return listName; }
    public void setListName(String listName) { this.listName = listName; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    @Override
    public String toString() {
        return "SavedList [listId=" + listId + ", userId=" + userId + ", listName=" + listName + "]";
    }
}
