<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ProfileBean"%>

<%
    ProfileBean p = (ProfileBean) request.getAttribute("profile");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Profile</title>
    <style>
        body { font-family: Arial; padding: 40px; 
               background-image: url("https://e7.pngegg.com/pngimages/15/702/png-clipart-miku-chibi-hatsune-miku-vocaloid-chibi-art-hatsune-miku-fictional-characters-chibi-thumbnail.png"); 
               background-repeat: repeat; background-size: 75px 75px; }
        .card { max-width: 700px; margin: auto; background: white; padding: 25px; 
               border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.15); }
        .label { font-weight: bold; margin-top: 12px; }
        .value { margin-bottom: 8px; }
        .tag { background: #e3f2fd; padding: 5px 10px;
            margin-right: 5px; border-radius: 8px; display: inline-block; }
        a { background-color: #15EFBF; color:black; text-decoration: none; 
                border-radius: 10px; padding: 15px; }
        a:hover { box-shadow: 0 0 15px rgba(0,0,0,1); }
    </style>
</head>

<body>

<div class="card">
    <h2><%= p.getName() %>'s Profile</h2>

    <div class="label">Student ID:</div>
    <div class="value"><%= p.getStudentId() %></div>

    <div class="label">Program:</div>
    <div class="value"><%= p.getProgram() %></div>

    <div class="label">Email:</div>
    <div class="value"><%= p.getEmail() %></div>

    <div class="label">Hobbies:</div>
    <div class="value">
        <%
            if (p.getHobbies() != null && !p.getHobbies().trim().isEmpty()) {
                for (String one : p.getHobbies().split(",")) {
        %>
                    <span class="tag"><%= one.trim() %></span>
        <%
                }
            } else {
        %>
                <i>No hobbies listed.</i>
        <%
            }
        %>
    </div>

    <div class="label">About Me:</div>
    <div class="value"><%= p.getIntro() %></div>
    
    <br><br><br>

    <a href="viewProfiles.jsp">View All Profiles</a>
</div>

</body>
</html>