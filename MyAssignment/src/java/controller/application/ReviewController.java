package controller.application;

import controller.BaseRequiredAuthorizationController;
import dal.ApplicationDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Application;
import model.Employee;
import model.auth.User;

@WebServlet(urlPatterns = "/request/review")
public class ReviewController extends BaseRequiredAuthorizationController{

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int application_id = Integer.parseInt(req.getParameter("id"));
            int new_status = Integer.parseInt(req.getParameter("status"));
            
            Employee processor = user.getEmployee();

            Application a = new Application();
            
            a.setId(application_id);
            a.setStatus(new_status);
            a.setProcessed_by(processor);

            ApplicationDBContext db = new ApplicationDBContext();
            db.update(a);
            
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        
        resp.sendRedirect("list"); 
    }
    
}
