package Controller;
import com.MoEngageApp.model.ListItem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filter = request.getParameter("filter");
        List<String> images = new ArrayList<>();

        for (int code = 100; code <= 599; code++) {
            if (matchesFilter(code, filter)) {
                images.add("https://http.dog/" + code + ".jpg");
            }
        }

        request.setAttribute("filter", filter);
        request.setAttribute("images", images);
        request.getRequestDispatcher("Search.jsp").forward(request, response);
    }

    private boolean matchesFilter(int code, String pattern) {
        // Convert "2xx" to regex: "2\\d\\d"
        String regex = pattern.replace("x", "\\d");
        return String.valueOf(code).matches(regex);
    }
}

/**
 * Servlet implementation class SearchServlet
 */
//@WebServlet("/SearchServlet")
//public class SearchServlet extends HttpServlet {
//	
//
//	    @Override
//	    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	        String filter = request.getParameter("filter");
//	        List<ListItem> searchResults = new ArrayList<>();
//
//	        if (filter != null && !filter.isEmpty()) {
//	            filter = filter.trim();
//
//	            if (filter.endsWith("xx")) {
//	                try {
//	                    int prefix = Integer.parseInt(filter.substring(0, 1));
//	                    for (int i = 0; i < 100; i++) {
//	                        int code = prefix * 100 + i;
//	                        ListItem item = new ListItem();
//	                        item.setResponseCode(code);
//	                        item.setImage("https://http.dog/" + code + ".jpg");
//	                        searchResults.add(item);
//	                    }
//	                } catch (NumberFormatException e) {
//	                    e.printStackTrace();
//	                }
//	            } else {
//	                try {
//	                    int code = Integer.parseInt(filter);
//	                    ListItem item = new ListItem();
//	                    item.setResponseCode(code);
//	                    item.setImage("https://http.dog/" + code + ".jpg");
//	                    searchResults.add(item);
//	                } catch (NumberFormatException e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	        }
//
//	        request.setAttribute("results", searchResults);
//	        request.getRequestDispatcher("jsp/search.jsp").forward(request, response);
//	    }
//	}
//
//
//



