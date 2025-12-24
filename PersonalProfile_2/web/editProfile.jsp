<%@page import="java.sql.*"%>
<%
    int id = Integer.parseInt(request.getParameter("id"));

    Class.forName("org.apache.derby.jdbc.ClientDriver");
    Connection conn = DriverManager.getConnection(
        "jdbc:derby://localhost:1527/student_profiles", "app", "app");

    PreparedStatement ps =
        conn.prepareStatement("SELECT * FROM profile WHERE id=?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    rs.next();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
    <style>
        body { font-family: Arial; padding:40px; 
                   background-image: url("https://static.wikia.nocookie.net/vocaloid/images/e/e4/MIKU_SP.png");
                   background-repeat: repeat; background-size: 75px 75px }
        .card { max-width:600px; margin:auto; background:white;
                padding:25px; border-radius:10px; box-shadow:0 0 15px rgba(0,0,0,0.1) }
        input, textarea { width:100%; padding:8px; margin-bottom:10px; }
        button { padding:10px; background:#15EFBF; border:none; border-radius:10px;}
    </style>
</head>
<body>

<div class="card">
    <h2>Edit Profile</h2>

    <form method="post" action="UpdateProfileServlet">
        <input type="hidden" name="id" value="<%= id %>">

        Name:
        <input name="name" value="<%= rs.getString("name") %>">

        Student ID:
        <input name="studentId" value="<%= rs.getString("student_id") %>">

        Program:
        <input name="program" value="<%= rs.getString("program") %>">

        Email:
        <input name="email" value="<%= rs.getString("email") %>">

        Hobbies:
        <input name="hobbies" value="<%= rs.getString("hobbies") %>">

        About:
        <textarea name="intro"><%= rs.getString("intro") %></textarea>

        <button type="submit">Update</button>
    </form>
</div>

</body>
</html>

<%
    conn.close();
%>