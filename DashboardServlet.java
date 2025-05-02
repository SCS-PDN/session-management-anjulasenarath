import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp"); 
        }
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("MAT1013", "Abstract Algebra", "Dr. Micheal"));
        courses.add(new Course("STA1023", "Probabiltiy Thoery", "Dr. David "));
        courses.add(new Course("CSC1013", "Introduction to Programming", "Dr. Andrew"));
        
        request.setAttribute("courses", courses);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
    public static class Course {
        private String id;
        private String name;
        private String instructor;

        public Course(String id, String name, String instructor) {
            this.id = id;
            this.name = name;
            this.instructor = instructor;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getInstructor() { return instructor; }
    }
}
