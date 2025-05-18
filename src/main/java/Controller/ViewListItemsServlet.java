
    
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.MoEngageApp.DAO.SavedListDAO;
import com.MoEngageApp.DAOImpl.SavedListDAOImpl;
import com.MoEngageApp.model.SavedList;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import com.MoEngageApp.DAO.ListItemDAO;
import com.MoEngageApp.DAOImpl.ListItemDAOImpl;
import com.MoEngageApp.model.ListItem;

@WebServlet("/ViewListItemsServlet")
public class ViewListItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // DAO to fetch items
    ListItemDAO listItemDAO = new ListItemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the listId from request parameter
        String listIdParam = request.getParameter("listId");
        
        if (listIdParam == null || listIdParam.isEmpty()) {
            response.sendRedirect("ViewListsServlet"); // fallback
            return;
        }

        try {
            int listId = Integer.parseInt(listIdParam);

            // Fetch items for the given listId
            List<ListItem> items = listItemDAO.getItemsByListId(listId);

            // Set the items in request scope
            request.setAttribute("items", items);

            // Forward to view_items.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("view_items.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("ViewListsServlet");
        }
    }
}



/**
 * Servlet implementation class ViewListItemsServlet
 */
//@WebServlet("/ViewListItemsServlet")
//public class ViewListItemsServlet extends HttpServlet {
//	
//	    SavedListDAO savedListDAO = new SavedListDAOImpl();
//
//	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	        // Get user ID from session
//	        HttpSession session = request.getSession();
//	        int userId = (int) session.getAttribute("userId");
//
//	        // Fetch all saved lists for that user
//	        List<SavedList> listOfSavedLists = savedListDAO.getListsByUserId(userId);
//
//	        // Set in request scope
//	        request.setAttribute("lists", listOfSavedLists);
//
//	        // Forward to lists.jsp
//	        RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
//	        rd.forward(request, response);
//	    }
//	}
