package profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/student_profiles", "app", "app");

            PreparedStatement ps = conn.prepareStatement(
                "UPDATE profile SET name=?, student_id=?, program=?, email=?, hobbies=?, intro=? WHERE id=?");

            ps.setString(1, request.getParameter("name"));
            ps.setString(2, request.getParameter("studentId"));
            ps.setString(3, request.getParameter("program"));
            ps.setString(4, request.getParameter("email"));
            ps.setString(5, request.getParameter("hobbies"));
            ps.setString(6, request.getParameter("intro"));
            ps.setInt(7, Integer.parseInt(request.getParameter("id")));

            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            throw new ServletException(e);
        }

        response.sendRedirect("viewProfiles.jsp");
    }
}