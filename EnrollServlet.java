import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")

public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        String courseId = request.getParameter("courseId");
        if (courseId == null || courseId.trim().isEmpty()) {
            response.sendRedirect("DashboardServlet");
            return;
        }
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        List<String> enrolledCourses = (List<String>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }
        

        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
        }

        session.setAttribute("enrolledCourses", enrolledCourses);
        response.sendRedirect("DashboardServlet");
    }
}
