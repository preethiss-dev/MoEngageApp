package Controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.MoEngageApp.DAO.ListItemDAO;
import com.MoEngageApp.DAO.SavedListDAO;
import com.MoEngageApp.DAOImpl.ListItemDAOImpl;
import com.MoEngageApp.DAOImpl.SavedListDAOImpl;

@WebServlet("/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ListItemDAO listItemDAO = new ListItemDAOImpl();
    SavedListDAO savedListDAO = new SavedListDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int listId = Integer.parseInt(request.getParameter("listId"));
            int responseCode = Integer.parseInt(request.getParameter("responseCode"));

            // 1. Delete the selected item
            boolean success = listItemDAO.deleteItemByResponseCode(listId, responseCode);

            // 2. Check if any items are left in the list
            boolean isEmpty = listItemDAO.getItemsByListId(listId).isEmpty();

            // 3. If list is empty, delete it from saved_lists table
            if (isEmpty) {
                savedListDAO.deleteListById(listId);
                System.out.println("Deleted listId " + listId + " as it became empty.");
                response.sendRedirect("ViewListsServlet");  // redirect to list view
            } else {
                System.out.println("Deleted item " + responseCode + ", list still has items.");
                response.sendRedirect("ViewListItemsServlet?listId=" + listId); // reload the current list
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("ViewListsServlet");
        }
    }
}
