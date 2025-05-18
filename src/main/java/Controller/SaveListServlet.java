package Controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.MoEngageApp.DAO.ListItemDAO;
import com.MoEngageApp.DAO.SavedListDAO;
import com.MoEngageApp.DAOImpl.ListItemDAOImpl;
import com.MoEngageApp.DAOImpl.SavedListDAOImpl;
import com.MoEngageApp.model.ListItem;
import com.MoEngageApp.model.SavedList;

@WebServlet("/SaveListServlet")
public class SaveListServlet extends HttpServlet {

    SavedListDAO savedListDAO = new SavedListDAOImpl();
    ListItemDAO listItemDAO = new ListItemDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	 // Get user ID from session
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");
        
        // Get list name and selected codes from form
        String listName = request.getParameter("listName");
        String[] selectedCodes = request.getParameterValues("codes");
        
        System.out.println("List Name: " + listName);
        System.out.println("User ID: " + userId);


        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        int uid = userId.intValue();
        System.out.println("Saving list for userId: " + uid);

        // Create and save the list
        SavedList savedList = new SavedList();
        savedList.setUserId(uid);
        savedList.setListName(listName);
        savedList.setCreatedDate(new Date());

        int listId = savedListDAO.insertSavedList(savedList);
        System.out.println("list id: " + listId);
        // Save each image (response code and image URL)
        if (selectedCodes != null) {
            for (String codeStr : selectedCodes) {
                try {
                    int responseCode = Integer.parseInt(codeStr);
                    String image = "https://http.dog/" + responseCode + ".jpg";

                    ListItem item = new ListItem();
                    item.setListId(listId);
                    item.setResponseCode(responseCode);
                    item.setImage(image);

                    listItemDAO.insertListItem(item);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid response code: " + codeStr);
                }
            }
        }

        // Redirect after saving
//        response.sendRedirect("list.jsp"); // Make sure this file exists
          response.sendRedirect("ViewListsServlet");

    }
}








//package Controller;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.MoEngageApp.DAO.SavedListDAO;
//import com.MoEngageApp.DAO.ListItemDAO;
//import com.MoEngageApp.DAOImpl.SavedListDAOImpl;
//import com.MoEngageApp.DAOImpl.ListItemDAOImpl;
//import com.MoEngageApp.model.SavedList;
//import com.MoEngageApp.model.ListItem;
//import java.util.Date;
//
///**
// * Servlet implementation class SaveListServlet
// */
//@WebServlet("/SaveListServlet")
//public class SaveListServlet extends HttpServlet {
//	SavedListDAO savedListDAO = new SavedListDAOImpl();
//    ListItemDAO listItemDAO = new ListItemDAOImpl();
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Get list name from form
//        String listName = request.getParameter("listName");
//
//        // Get selected response codes from checkboxes
//        String[] selectedCodes = request.getParameterValues("codes");
//
//        // Get userId from session (set at login time)
////        HttpSession session = request.getSession(false);
////        int userId = (int) session.getAttribute("userId");
////        System.out.println("Saving list for userId: " + userId);
////        
////        if (userId == null) {
////            response.sendRedirect("login.html"); // or error.jsp
////            return;
////        }
//
//        
//        HttpSession session = request.getSession(false);
//        Integer userId = (Integer) session.getAttribute("userId");
//
//        if (userId == null) {
//            response.sendRedirect("login.html");
//            return;
//        }
//
//        // Now safe to convert
//        int uid = userId.intValue();
//        System.out.println("Saving list for userId: " + uid);
//        
//        // Create and save new list
//        SavedList savedList = new SavedList();
//        savedList.setUserId(userId);
//        savedList.setListName(listName);
//        savedList.setCreatedDate(new Date());
//
//        // Insert into saved_lists and get generated listId
//        int listId = savedListDAO.insertSavedList(savedList);
//
//        // Save each selected response code and its image URL
//        if (selectedCodes != null) {
//            for (String codeStr : selectedCodes) {
//                int responseCode = Integer.parseInt(codeStr);
//                String image = "https://http.dog/" + responseCode + ".jpg";
//
//                ListItem item = new ListItem();
//                item.setListId(listId);
//                item.setResponseCode(responseCode);
//                item.setImage(image);
//
//                listItemDAO.insertListItem(item);
//            }
//        }
//
//        // Redirect to saved lists page after saving
//        response.sendRedirect("jsp/lists.jsp");
//    }
//}