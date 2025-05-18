package com.MoEngageApp.DAO;

import java.util.List;

import com.MoEngageApp.model.ListItem;

public interface ListItemDAO {
	  int insertListItem(ListItem item);  // insert one item
	    int insertListItems(List<ListItem> items); // insert multiple
	    List<ListItem> getItemsByListId(int listId);
	    boolean deleteItemByResponseCode(int listId, int responseCode);
	    boolean deleteItemsByListId(int listId);

}
