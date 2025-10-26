package controller.application;

import controller.BaseRequiredAuthenticationController;
import dal.ApplicationDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Application;
import model.Department;
import model.Employee;
import model.auth.User;

@WebServlet(urlPatterns = "/request/create")
public class CreateController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        Application a = new Application();
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("auth") != null) {
            
            // Bước 2: Lấy attribute có tên là "auth" từ session.
            // session.getAttribute() luôn trả về một đối tượng kiểu Object.
            Object authObject = session.getAttribute("auth");
            
            // Bước 3: Ép kiểu đối tượng đó về đúng kiểu User ban đầu.
            User loggedInUser = (User) authObject;
            
            a.setUser(loggedInUser.getDisplayname());
            a.setRole(loggedInUser.getRole());
            a.setDept(loggedInUser.getDept());
            a.setStart_date(Date.valueOf(req.getParameter("datStart")));
            a.setEnd_date(Date.valueOf(req.getParameter("datEnd")));
            a.setReason(req.getParameter("reason"));
            ApplicationDBContext ad = new ApplicationDBContext();
            ad.insert(a);
            
            req.setAttribute("application", a);
            req.getRequestDispatcher("../view/application/detail.jsp").forward(req, resp);
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        ApplicationDBContext ad = new ApplicationDBContext();
        ArrayList<Application> appli = ad.list();
        req.setAttribute("appli", appli);
        req.getRequestDispatcher("../view/application/create.jsp").forward(req, resp);
    }

}
