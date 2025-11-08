package controller;

import dal.RoleDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.auth.Feature;
import model.auth.Role;
import model.auth.User;

public abstract class BaseRequiredAuthorizationController extends BaseRequiredAuthenticationController {

    private boolean isAuthorized(HttpServletRequest req, User user) {

        // KHÔNG KIỂM TRA .isEmpty() NỮA.
        // HÃY LUÔN LUÔN TẢI LẠI QUYỀN (ROLE + FEATURE)
        // ĐỂ ĐẢM BẢO DỮ LIỆU LUÔN MỚI NHẤT
        RoleDBContext db = new RoleDBContext();
        user.setRoles(db.getByUserId(user.getId()));
        // Ghi chú: Bạn không cần update session ở đây,
        // chỉ cần đối tượng 'user' trong bộ nhớ có đủ quyền
         req.getSession().setAttribute("auth", user);

        String url = req.getServletPath();
        for (Role role : user.getRoles()) {
            for (Feature feature : role.getFeatures()) {
                if (feature.getUrl().equals(url)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected abstract void processPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

    protected abstract void processGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        if (isAuthorized(req, user)) {
            processPost(req, resp, user);
        } else {
            resp.getWriter().println("access denied!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        if (isAuthorized(req, user)) {
            processGet(req, resp, user);
        } else {
            resp.getWriter().println("access denied!");
        }
    }

}
