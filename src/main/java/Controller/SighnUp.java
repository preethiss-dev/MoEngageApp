package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MoEngageApp.DAO.UserDAO;
import com.MoEngageApp.DAOImpl.UserDAOImpl;
import com.MoEngageApp.model.User;

/**
 * Servlet implementation class SighnUp
 */
@WebServlet("/SighnUp")
public class SighnUp extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		User u = new User(name,email,password);
		
		UserDAO udao = new UserDAOImpl();
		
	     int x	= udao.insertUser(u);
	     
	     if(x==0) 
	     {
	    	 resp.sendRedirect("failure.html");
	     }
	     
	     else {
	    	 resp.sendRedirect("success.html");
	     }
		
	}

}



