package com.MoEngageApp.DAOImpl;

import com.MoEngageApp.DAO.SavedListDAO;
import com.MoEngageApp.model.SavedList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SavedListDAOImpl implements SavedListDAO {

    private static Connection con;

    private static final String INSERT_SAVEDLIST = "INSERT INTO saved_list(userId, listName, createdDate) VALUES (?, ?, ?)";
    private static final String GET_LISTS_BY_USERID = "SELECT * FROM saved_list WHERE userId = ?";
    private static final String GET_LIST_BY_ID = "SELECT * FROM saved_list WHERE listId = ?";
    private static final String DELETE_LIST_BY_ID = "DELETE FROM saved_list WHERE listId = ?";
    private static final String UPDATE_LIST_NAME = "UPDATE saved_list SET listName = ? WHERE listId = ?";

    private PreparedStatement pstmt;
    private ResultSet resultSet;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moengage_assignment", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertSavedList(SavedList savedList) {
        int generatedId = -1;
        try {
            pstmt = con.prepareStatement(INSERT_SAVEDLIST, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, savedList.getUserId());
            pstmt.setString(2, savedList.getListName());
            pstmt.setDate(3, new java.sql.Date(savedList.getCreatedDate().getTime()));

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1); // ✅ Get auto-generated listId
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    @Override
    public List<SavedList> getListsByUserId(int userId) {
        List<SavedList> savedListArray = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(GET_LISTS_BY_USERID);
            pstmt.setInt(1, userId);
            resultSet = pstmt.executeQuery();
            savedListArray = extractSavedListFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedListArray;
    }

    @Override
    public SavedList getListById(int listId) {
        try {
            pstmt = con.prepareStatement(GET_LIST_BY_ID);
            pstmt.setInt(1, listId);
            resultSet = pstmt.executeQuery();
            List<SavedList> savedListArray = extractSavedListFromResultSet(resultSet);
            if (!savedListArray.isEmpty()) {
                return savedListArray.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteListById(int listId) {
        try {
            pstmt = con.prepareStatement(DELETE_LIST_BY_ID);
            pstmt.setInt(1, listId);
            int x = pstmt.executeUpdate();
            return x > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateListName(int listId, String newName) {
        try {
            pstmt = con.prepareStatement(UPDATE_LIST_NAME);
            pstmt.setString(1, newName);
            pstmt.setInt(2, listId);
            int x = pstmt.executeUpdate();
            return x > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<SavedList> extractSavedListFromResultSet(ResultSet resultSet) {
        List<SavedList> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                SavedList sl = new SavedList();
                sl.setListId(resultSet.getInt("listId"));
                sl.setUserId(resultSet.getInt("userId"));
                sl.setListName(resultSet.getString("listName"));
                sl.setCreatedDate(resultSet.getDate("createdDate"));
                list.add(sl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}




//package com.MoEngageApp.DAOImpl;
//
//import java.util.List;
//
//import com.MoEngageApp.DAO.SavedListDAO;
//import com.MoEngageApp.model.SavedList;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//	public class SavedListDAOImpl implements SavedListDAO {
//
//	    int x = -1;
//	    SavedList savedList;
//	    static Connection con;
//	    ArrayList<SavedList> savedListArray = new ArrayList<>();
//
//	    private static final String INSERT_SAVEDLIST = "INSERT INTO saved_list(userId, listName, createdDate) VALUES (?, ?, ?)";
//	    private static final String GET_LISTS_BY_USERID = "SELECT * FROM saved_list WHERE userId = ?";
//	    private static final String GET_LIST_BY_ID = "SELECT * FROM saved_list WHERE listId = ?";
//	    private static final String DELETE_LIST_BY_ID = "DELETE FROM saved_list WHERE listId = ?";
//	    private static final String UPDATE_LIST_NAME = "UPDATE saved_list SET listName = ? WHERE listId = ?";
//
//	    private PreparedStatement pstmt;
//	    private Statement stmt;
//	    private ResultSet resultSet;
//
//	    static {
//	        try {
//	            Class.forName("com.mysql.cj.jdbc.Driver");
//	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moengage_assignment", "root", "root");
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    @Override
//	    public int insertSavedList(SavedList savedList) {
//	        try {
//	            pstmt = con.prepareStatement(INSERT_SAVEDLIST);
//	            pstmt.setInt(1, savedList.getUserId());
//	            pstmt.setString(2, savedList.getListName());
//	            pstmt.setDate(3, new java.sql.Date(savedList.getCreatedDate().getTime()));
//
//	            x = pstmt.executeUpdate();
//
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        return x;
//	    }
//
//	    @Override
//	    public List<SavedList> getListsByUserId(int userId) {
//	        savedListArray.clear();
//	        try {
//	            pstmt = con.prepareStatement(GET_LISTS_BY_USERID);
//	            pstmt.setInt(1, userId);
//	            resultSet = pstmt.executeQuery();
//	            savedListArray = (ArrayList<SavedList>) extractSavedListFromResultSet(resultSet);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        return savedListArray;
//	    }
//
//	    @Override
//	    public SavedList getListById(int listId) {
//	        savedListArray.clear();
//	        try {
//	            pstmt = con.prepareStatement(GET_LIST_BY_ID);
//	            pstmt.setInt(1, listId);
//	            resultSet = pstmt.executeQuery();
//	            savedListArray = (ArrayList<SavedList>) extractSavedListFromResultSet(resultSet);
//	            if (!savedListArray.isEmpty()) {
//	                savedList = savedListArray.get(0);
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        return savedList;
//	    }
//
//	    @Override
//	    public boolean deleteListById(int listId) {
//	        try {
//	            pstmt = con.prepareStatement(DELETE_LIST_BY_ID);
//	            pstmt.setInt(1, listId);
//	            x = pstmt.executeUpdate();
//	            return x > 0;
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            return false;
//	        }
//	    }
//
//	    @Override
//	    public boolean updateListName(int listId, String newName) {
//	        try {
//	            pstmt = con.prepareStatement(UPDATE_LIST_NAME);
//	            pstmt.setString(1, newName);
//	            pstmt.setInt(2, listId);
//	            x = pstmt.executeUpdate();
//	            return x > 0;
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            return false;
//	        }
//	    }
//
//	    private List<SavedList> extractSavedListFromResultSet(ResultSet resultSet) {
//	        List<SavedList> list = new ArrayList<>();
//	        try {
//	            while (resultSet.next()) {
//	                SavedList sl = new SavedList();
//	                sl.setListId(resultSet.getInt("listId"));
//	                sl.setUserId(resultSet.getInt("userId"));
//	                sl.setListName(resultSet.getString("listName"));
//	                sl.setCreatedDate(resultSet.getDate("createdDate"));
//	                list.add(sl);
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        return list;
//	    }
//	}
//
//
//
