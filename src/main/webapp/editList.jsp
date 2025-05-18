<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.MoEngageApp.model.SavedList" %>
<%
    SavedList list = (SavedList) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit List</title>
    <link rel="stylesheet" type="text/css" href="editList.css">
</head>
<body>
    <div class="container">
        <h2>Edit List Name</h2>
        <form action="EditListServlet" method="post">
            <input type="hidden" name="listId" value="<%= list.getListId() %>" />
            <input type="text" name="newName" value="<%= list.getListName() %>" required />
            <button type="submit">Save</button>
        </form>
        <a href="ViewListsServlet">Cancel</a>
    </div>
</body>
</html>
