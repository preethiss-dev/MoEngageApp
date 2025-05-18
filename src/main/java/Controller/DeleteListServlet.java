package Controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MoEngageApp.DAO.SavedListDAO;
import com.MoEngageApp.DAO.ListItemDAO;
import com.MoEngageApp.DAOImpl.SavedListDAOImpl;
import com.MoEngageApp.DAOImpl.ListItemDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;




@WebServlet("/DeleteListServlet")
public class DeleteListServlet extends HttpServlet {
    private final SavedListDAO savedListDAO = new SavedListDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int listId = Integer.parseInt(request.getParameter("listId"));
        savedListDAO.deleteListById(listId);
        response.sendRedirect("ViewListsServlet");
    }
}

/**
 * Servlet implementation class DeleteListServlet
 */
//@WebServlet("/DeleteListServlet")
//public class DeleteListServlet extends HttpServlet {
//
//    SavedListDAO savedListDAO = new SavedListDAOImpl();
//    ListItemDAO listItemDAO = new ListItemDAOImpl();
//
//    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int listId = Integer.parseInt(request.getParameter("listId"));
//
//        // Delete related list items first
//        listItemDAO.deleteItemsByListId(listId);
//
//        // Then delete the list itself
//        savedListDAO.deleteListById(listId);
//
//        response.sendRedirect("ViewListsServlet");
//    }
//}
//
//
