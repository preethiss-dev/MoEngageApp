<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Response Code Dogs</title>
    <link rel="stylesheet" type="text/css" href="search.css">
</head>
<body>

<div class="container">
    <h2>Search for Response Code Images 🐶</h2>

    <form action="SearchServlet" method="get">
        <div class="field">
            <input class="input" type="text" name="filter" placeholder="Enter code (e.g. 203, 2xx, 21x)" required />
        </div>
        <button type="submit" class="button">Search</button>
    </form>

    <hr style="margin: 30px 0;" />

    <div class="results">
        <%
            List<String> images = (List<String>) request.getAttribute("images");
            String filter = (String) request.getAttribute("filter");

            if (images != null && !images.isEmpty()) {
        %>
            <h3>Results for "<%= filter %>":</h3>
            <form action="SaveListServlet" method="post">
                <label>List Name:</label><br/>
                <div class="field">
                    <input class="input" type="text" name="listName" required />
                </div>
                <input type="hidden" name="filter" value="<%= filter %>" />

                <% for (String url : images) {
                    String code = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
                %>
                    <div>
                        <img src="<%= url %>" alt="HTTP Dog <%= code %>" width="250" /><br/>
                        <label>
                            <input type="checkbox" name="codes" value="<%= code %>" checked />
                            Code <%= code %>
                        </label>
                    </div>
                    <br/>
                <% } %>

                <button type="submit" class="button">💾 Save This List</button>
            </form>

        <% } else if (filter != null) { %>
            <p>No results found for "<%= filter %>". Try a different input.</p>
        <% } %>
    </div>
</div>

</body>
</html>
