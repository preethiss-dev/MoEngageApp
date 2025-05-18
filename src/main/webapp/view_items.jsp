<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.MoEngageApp.model.ListItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View List Items</title>
    <link rel="stylesheet" href="view_items.css" />
</head>
<body>

<div class="container">
    <h2>🐶 Saved Dog Codes in Your List</h2>

    <%
        List<ListItem> items = (List<ListItem>) request.getAttribute("items");
        System.out.println("list items recived in view: " + items);

        if (items != null && !items.isEmpty()) {
    %>
        <div class="image-grid">
            <% for (ListItem item : items) { %>
                <div class="card">
                    <img src="<%= item.getImage() %>" alt="Dog <%= item.getResponseCode() %>" />
                    <div class="code-label">Code: <%= item.getResponseCode() %></div>

                    <div class="action-buttons">
                        <!-- Delete Button -->
                        <form action="DeleteItemServlet" method="post">
                            <input type="hidden" name="listId" value="<%= item.getListId() %>" />
                            <input type="hidden" name="responseCode" value="<%= item.getResponseCode() %>" />
                            <button type="submit" class="button delete-button">Delete</button>
                        </form>
                    </div>
                </div>
            <% } %>
        </div>
    <%
        } else {
    %>
        <p>No items found for this list.</p>
    <%
        }
    %>
    
    <a href="ViewListsServlet" class="back-link">← Back to Saved Lists</a>
</div>

</body>
</html>
