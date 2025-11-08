package controller.application;

import controller.BaseRequiredAuthenticationController;
import dal.ApplicationDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.Application;
import model.Employee;
import model.auth.User;


@WebServlet(urlPatterns = "/request/create")
public class CreateController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
    
    Application a = new Application();
    
    a.setCreated_by(user.getEmployee()); 
    a.setCreated_time(new Date()); // Lấy ngày giờ hiện tại
    
    a.setFrom(java.sql.Date.valueOf(req.getParameter("datStart")));
    a.setTo(java.sql.Date.valueOf(req.getParameter("datEnd")));
    a.setReason(req.getParameter("reason"));
    
    ApplicationDBContext ad = new ApplicationDBContext();
    ad.insert(a);
    
    resp.sendRedirect(req.getContextPath() + "/view/common/home.jsp");
}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        ApplicationDBContext ad = new ApplicationDBContext();
        ArrayList<Application> appli = ad.getByEmployeeAndSubodiaries(user.getId());
        req.setAttribute("appli", appli);
        req.getRequestDispatcher("../view/application/create.jsp").forward(req, resp);
    }
    
}