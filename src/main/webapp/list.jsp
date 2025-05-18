<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.MoEngageApp.model.SavedList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>My Saved Lists</title>
    <link rel="stylesheet" href="list.css" />
</head>
<body>
    <div class="container">
        <h2>All Your Saved Lists</h2>

        <%
            List<SavedList> lists = (List<SavedList>) request.getAttribute("lists");
            System.out.println("SavedLists received: " + lists);

            if (lists != null && !lists.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>List Name</th>
                    <th>Actions</th>
                </tr>
                <%
                    for (SavedList list : lists) {
                %>
                    <tr>
                        <td><%= list.getListName() %></td>
                        <td>
                            <form action="ViewListItemsServlet" method="get" style="display:inline;">
                                <input type="hidden" name="listId" value="<%= list.getListId() %>">
                                <input type="submit" value="View" class="button">
                            </form>
                            
                            <form action="EditListServlet" method="get" style="display:inline;">
							    <input type="hidden" name="listId" value="<%= list.getListId() %>">
							    <input type="submit" value="Edit" class="button">
							</form>
							
						    <form action="DeleteListServlet" method="post" style="display:inline;" 
						          onsubmit="return confirm('Are you sure you want to delete this list?');">
						        <input type="hidden" name="listId" value="<%= list.getListId() %>">
						        <input type="submit" value="Delete" class="button delete">
						    </form>
                        </td>
                    </tr>
                <%
                    }
                %>
            </table>
        <%
            } else {
        %>
            <p>No lists saved yet.</p>
        <%
            }
        %>

        <a href="Search.jsp">← Back to Search</a>
    </div>
</body>
</html>

