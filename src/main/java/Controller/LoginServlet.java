package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MoEngageApp.DAO.UserDAO;
import com.MoEngageApp.DAOImpl.UserDAOImpl;
import com.MoEngageApp.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	private HttpSession session;

 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 String  email =  req.getParameter("email");
	 String  password = req.getParameter("password");
	  
	 UserDAO udao = new UserDAOImpl();
	 User user =udao.getUserByEmail(email);
	 
	 if(user!=null) {
		if(password.equals(user.getPassword())) 
		{    
			session = req.getSession();
			
			session.setAttribute("user", user);
			session.setAttribute("userId", user.getUserId());
			
			//resp.sendRedirect("home.jsp");
			//resp.sendRedirect("HomeServlet");
			resp.sendRedirect("Search.jsp");
		}
		else
		{
			resp.sendRedirect("incorrectPassword.jsp");
		}
	 }
	 else
	 {
		 resp.sendRedirect("sighnUp.html");
	 }
	 
	}
		
	}


