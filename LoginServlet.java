import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final Map<String, String> s20515db= new HashMap<>();

    @Override
    public void init() throws ServletException {
        s20515db.put("Anjula", "Anjula123");
        s20515db.put("Shalin", "Shalin123");
        s20515db.put("Asantha", "Asantha123");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null && userDB.containsKey(username)
                && userDB.get(username).equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);

            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60 * 60);
            response.addCookie(userCookie);

            response.sendRedirect("DashboardServlet");
        } else {
            response.sendRedirect("login.html?error=1");
        }
    }
}
