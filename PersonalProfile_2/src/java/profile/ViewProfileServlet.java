package profile;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.ProfileBean;

@WebServlet("/ViewProfileServlet")
public class ViewProfileServlet extends HttpServlet {

    private static final String DB_URL =
        "jdbc:derby://localhost:1527/student_profiles";
    private static final String DB_USER = "app";
    private static final String DB_PASS = "app";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        ProfileBean p = new ProfileBean();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            PreparedStatement ps =
                conn.prepareStatement("SELECT * FROM profile WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p.setName(rs.getString("name"));
                p.setStudentId(rs.getString("student_id"));
                p.setProgram(rs.getString("program"));
                p.setEmail(rs.getString("email"));
                p.setHobbies(rs.getString("hobbies"));
                p.setIntro(rs.getString("intro"));
            }

            conn.close();

        } catch (Exception e) {
            throw new ServletException(e);
        }

        request.setAttribute("profile", p);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}