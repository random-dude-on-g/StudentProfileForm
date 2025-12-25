<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Profiles</title>

    <style>
        body { font-family: Arial; padding: 40px; background-color: #15EFBF; }
        .card { max-width: 1000px; margin: auto; background: white; padding: 25px;
                border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.15); }
        h2 { margin-top: 0; text-align: center; }
        table { width: 100%;  border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #e3f2fd; }
        tr:hover { background-color: #f5faff; }
        a { background-color: #15EFBF; color: black; text-decoration: none; 
            border-radius: 10px; padding: 12px 18px; display: inline-block; 
            margin:auto; }

        a:hover { box-shadow: 0 0 15px rgba(0,0,0,1); }
    </style>
</head>

<body>

    <div class="card">
        <h2>All Student Profiles</h2>


        <table>
        <tr>
            <th>Name</th>
            <th>Student ID</th>
            <th>Program</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>

    <%
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn = DriverManager.getConnection(
            "jdbc:derby://localhost:1527/student_profiles", "app", "app");

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM profile");

        boolean hasData = false;

        while (rs.next()) {
            hasData = true;
    %>
        <tr>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("student_id") %></td>
            <td><%= rs.getString("program") %></td>
            <td><%= rs.getString("email") %></td>
            <td>
                <a href="ViewProfileServlet?id=<%= rs.getInt("id") %>">View</a>
                |
                <a href="editProfile.jsp?id=<%= rs.getInt("id") %>">Edit</a>
                |
                <a href="ProfileActionServlet?action=delete&id=<%= rs.getInt("id") %>"
                   onclick="return confirm('Delete this profile?');">
                   Delete
                </a>
            </td>

    <%
        }
        conn.close();
    %>
    </table>

        <% if (!hasData) { %>
            <p style="text-align:center; color:#777; margin-top:15px;">
                No profiles found.
            </p>
        <% } %>
        
        <br><br>

        <div style="text-align:center;">
            <a href="index.html">Back to Form</a>
        </div>
    </div>

</body>
</html>
