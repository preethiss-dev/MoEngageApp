package com.MoEngageApp.DAOImpl;



import  com.MoEngageApp.DAO.UserDAO;
import com.MoEngageApp.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class UserDAOImpl implements UserDAO {
	
	int x=-1;
	User user;
    static Connection con;
   ArrayList<User> userList = new ArrayList<User>();
   private static String INSERT_USER = "insert into user(name,email,password) value(?,?,?)";
   private static String GET_ALL_USER = "select * from user";
   private static String GET_USER_BY_ID ="select * from user where userId =?";
   private static String GET_USER_BY_Email ="select * from user where email =?";
   private static String DELETE_USER_BY_ID ="delete from user where userId =?";
   private PreparedStatement pstmt;
   private Statement stmt;
   private ResultSet resultSet;

    static 
    {
    	try 
    	{
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moengage_assignment","root","root");
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
	

	@Override
	public int insertUser(User user) {
		try 
		{
			pstmt = con.prepareStatement(INSERT_USER);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			
			
			x = pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return x;
		
		
	}

	@Override
	public List<User> getAllUser() {
		try 
		{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(GET_ALL_USER);
			userList = (ArrayList<User>) extractUserFromResultSet(resultSet);
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return userList;
		
	}

	@Override
	public User getUserById(int id) {
		try 
		{
			pstmt = con.prepareStatement(GET_USER_BY_ID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			userList = (ArrayList<User>) extractUserFromResultSet(resultSet);
			user = userList.get(0);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return user;
		
	}

	@Override
	public User getUserByEmail(String email) {
		
		try 
		{
			pstmt = con.prepareStatement(GET_USER_BY_Email);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			userList = (ArrayList<User>) extractUserFromResultSet(resultSet);
			user = userList.get(0);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return user;
	}

	@Override
	public int deleteUserById(int id) {
		try 
		{
			pstmt = con.prepareStatement(DELETE_USER_BY_ID);
			pstmt.setInt(1, id);
			x = pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return x;
		
	}
	List<User> extractUserFromResultSet(ResultSet resultSet)
	{
		try 
		{
			while(resultSet.next())
			{
			userList.add(new User (resultSet.getInt(1),
					              resultSet.getString(2),
					              resultSet.getString(3),
					              resultSet.getString(4)));
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userList;
	}

}

