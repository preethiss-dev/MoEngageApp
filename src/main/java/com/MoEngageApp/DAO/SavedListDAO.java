package com.MoEngageApp.DAO;


	

	import java.util.List;
	import com.MoEngageApp.model.SavedList;

	public interface SavedListDAO {
	    int insertSavedList(SavedList savedList);
	    List<SavedList> getListsByUserId(int userId);
	    SavedList getListById(int listId);
	    boolean deleteListById(int listId);
	    boolean updateListName(int listId, String newName);
	}



