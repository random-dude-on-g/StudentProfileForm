package profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ProfileActionServlet")
public class ProfileActionServlet extends HttpServlet {

    private static final String DB_URL =
        "jdbc:derby://localhost:1527/student_profiles";
    private static final String DB_USER = "app";
    private static final String DB_PASS = "app";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            if ("delete".equals(action)) {
                PreparedStatement ps =
                    conn.prepareStatement("DELETE FROM profile WHERE id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            conn.close();

        } catch (Exception e) {
            throw new ServletException(e);
        }

        response.sendRedirect("viewProfiles.jsp");
    }
}