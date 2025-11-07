package controller.application;

import controller.BaseRequiredAuthorizationController;
import dal.ApplicationDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Application;
import model.auth.User;

public class ListController extends BaseRequiredAuthorizationController {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        ApplicationDBContext db = new ApplicationDBContext();
        ArrayList<Application> rfls = db.getByEmployeeAndSubodiaries(user.getId());
        req.setAttribute("rfls", rfls);
        req.getRequestDispatcher("../view/request/list.jsp").forward(req, resp);
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