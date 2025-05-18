package com.MoEngageApp.DAOImpl;

	import com.MoEngageApp.DAO.ListItemDAO;
	import com.MoEngageApp.model.ListItem;

	import java.sql.*;
	import java.util.ArrayList;
	import java.util.List;

	public class ListItemDAOImpl implements ListItemDAO {

	    private static final String INSERT_ITEM = "INSERT INTO list_items (listId, responseCode, image) VALUES (?, ?, ?)";
	    private static final String GET_ITEMS_BY_LIST_ID = "SELECT * FROM list_items WHERE listId = ?";
	    private static final String DELETE_ITEM_BY_RESPONSE_CODE = "DELETE FROM list_items WHERE listId = ? AND responseCode = ?";
	    private static final String DELETE_ITEMS_BY_LIST_ID = "DELETE FROM list_items WHERE listId = ?";

	    private static Connection con;

	    static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moengage_assignment", "root", "root");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public int insertListItem(ListItem item) {
	        int x = -1;
	        try (PreparedStatement pstmt = con.prepareStatement(INSERT_ITEM)) {
	            pstmt.setInt(1, item.getListId());
	            pstmt.setInt(2, item.getResponseCode());
	            pstmt.setString(3, item.getImage());
	            x = pstmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return x;
	    }

	    @Override
	    public int insertListItems(List<ListItem> items) {
	        int x = 0;
	        try (PreparedStatement pstmt = con.prepareStatement(INSERT_ITEM)) {
	            for (ListItem item : items) {
	                pstmt.setInt(1, item.getListId());
	                pstmt.setInt(2, item.getResponseCode());
	                pstmt.setString(3, item.getImage());
	                pstmt.addBatch();
	            }
	            int[] result = pstmt.executeBatch();
	            for (int i : result) {
	                if (i >= 0) x++;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return x;
	    }

	    @Override
	    public List<ListItem> getItemsByListId(int listId) {
	        List<ListItem> items = new ArrayList<>();
	        try (PreparedStatement pstmt = con.prepareStatement(GET_ITEMS_BY_LIST_ID)) {
	            pstmt.setInt(1, listId);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                ListItem item = new ListItem(
	                    rs.getInt("itemId"),
	                    rs.getInt("listId"),
	                    rs.getInt("responseCode"),
	                    rs.getString("image")
	                );
	                items.add(item);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return items;
	    }

	    @Override
	    public boolean deleteItemByResponseCode(int listId, int responseCode) {
	        boolean success = false;
	        try (PreparedStatement pstmt = con.prepareStatement(DELETE_ITEM_BY_RESPONSE_CODE)) {
	            pstmt.setInt(1, listId);
	            pstmt.setInt(2, responseCode);
	            success = pstmt.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return success;
	    }

	    @Override
	    public boolean deleteItemsByListId(int listId) {
	        boolean success = false;
	        try (PreparedStatement pstmt = con.prepareStatement(DELETE_ITEMS_BY_LIST_ID)) {
	            pstmt.setInt(1, listId);
	            success = pstmt.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return success;
	    }
	}



