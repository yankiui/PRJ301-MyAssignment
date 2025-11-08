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
import model.auth.User;

@WebServlet(urlPatterns = "/request/list")
public class ListController extends BaseRequiredAuthorizationController {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, User user) 
        throws ServletException, IOException {
    
    int pagesize = 10;
    String page = req.getParameter("page");
    page = (page == null) ? "1" : page;
    int pageindex = Integer.parseInt(page);
    
    ApplicationDBContext db = new ApplicationDBContext();
    
    ArrayList<Application> applications = db.list(pageindex, pagesize);
    
    db = new ApplicationDBContext(); 
    int count = db.count(); 
    int pagecount = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
    
    req.setAttribute("pagecount", pagecount);
    req.setAttribute("pageindex", pageindex);
    
    req.setAttribute("rfls", applications); 
    
    
    req.getRequestDispatcher("../view/application/list.jsp").forward(req, resp);
}

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }
    

}