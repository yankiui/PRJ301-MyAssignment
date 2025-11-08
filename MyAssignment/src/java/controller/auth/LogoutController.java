package controller.auth;

import controller.BaseRequiredAuthenticationController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.auth.User;

@WebServlet(urlPatterns = "/request/logout")
public class LogoutController extends BaseRequiredAuthenticationController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        if (session != null) {
            session.invalidate();
        }
        
        resp.sendRedirect(req.getContextPath() + "/login");
    }

}
