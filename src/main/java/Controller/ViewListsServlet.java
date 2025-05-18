package Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.MoEngageApp.DAO.SavedListDAO;
import com.MoEngageApp.DAOImpl.SavedListDAOImpl;
import com.MoEngageApp.model.SavedList;

@WebServlet("/ViewListsServlet")
public class ViewListsServlet extends HttpServlet {
    SavedListDAO savedListDAO = new SavedListDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        List<SavedList> lists = savedListDAO.getListsByUserId(userId);
        request.setAttribute("lists", lists);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
