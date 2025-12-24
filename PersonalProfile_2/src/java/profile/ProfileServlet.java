package profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProfileBean;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    // DERBY (Java DB) CONNECTION DETAILS
    private static final String DB_URL =
            "jdbc:derby://localhost:1527/student_profiles";
    private static final String DB_USER = "app";
    private static final String DB_PASS = "app";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Create JavaBean and set values
        ProfileBean profile = new ProfileBean();
        profile.setName(request.getParameter("name"));
        profile.setStudentId(request.getParameter("studentId"));
        profile.setProgram(request.getParameter("program"));
        profile.setEmail(request.getParameter("email"));
        profile.setHobbies(request.getParameter("hobbies"));
        profile.setIntro(request.getParameter("intro"));

        try {
            // LOAD DERBY DRIVER
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            Connection conn = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASS);

            String sql = "INSERT INTO profile "
                    + "(name, student_id, program, email, hobbies, intro) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, profile.getName());
            ps.setString(2, profile.getStudentId());
            ps.setString(3, profile.getProgram());
            ps.setString(4, profile.getEmail());
            ps.setString(5, profile.getHobbies());
            ps.setString(6, profile.getIntro());

            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            throw new ServletException(e);
        }

        // Forward to profile.jsp
        request.setAttribute("profile", profile);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}