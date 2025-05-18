package Controller;

import com.MoEngageApp.DAO.SavedListDAO;
import com.MoEngageApp.DAOImpl.SavedListDAOImpl;
import com.MoEngageApp.model.SavedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditListServlet")
public class EditListServlet extends HttpServlet {

    private final SavedListDAO savedListDAO = new SavedListDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int listId = Integer.parseInt(req.getParameter("listId"));
        SavedList list = savedListDAO.getListById(listId);
        req.setAttribute("list", list);
        req.getRequestDispatcher("editList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int listId = Integer.parseInt(req.getParameter("listId"));
        String newName = req.getParameter("newName");

        savedListDAO.updateListName(listId, newName);
        resp.sendRedirect("ViewListsServlet");
    }
}
