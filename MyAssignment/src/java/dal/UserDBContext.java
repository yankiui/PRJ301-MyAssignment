package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.auth.Role;
import model.auth.User;

public class UserDBContext extends DBContext<User> {

    public User get(String username, String password) {
        try {
            String sql = """
             SELECT
                 u.uid,
                 u.username,
                 u.displayname,
                 e.eid,
                 e.ename,
                 d.did,       -- THÊM DÒNG NÀY
                 d.dname      -- THÊM DÒNG NÀY
             FROM [User] u 
             INNER JOIN [Enrollment] en ON u.[uid] = en.[uid]
             INNER JOIN [Employee] e ON e.eid = en.eid
             INNER JOIN [Division] d ON e.did = d.did -- THÊM DÒNG NÀY
             WHERE
                 u.username = ? AND u.[password] = ?
                 AND en.active = 1""";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();

                model.Department d = new model.Department();
                d.setId(rs.getInt("did"));
                d.setDname(rs.getString("dname"));

                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setEname(rs.getString("ename"));

                e.setDept(d);

                u.setEmployee(e);

                u.setUsername(username);
                u.setId(rs.getInt("uid"));
                u.setDisplayname(rs.getString("displayname"));

                ArrayList<Role> roles = getRolesByUserID(u.getId());
                u.setRoles(roles);

                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }

    private ArrayList<Role> getRolesByUserID(int uid) {
        ArrayList<Role> roles = new ArrayList<>();
        String sql = """
                 SELECT r.rid, r.rname 
                 FROM [Role] r 
                 INNER JOIN [UserRole] ur ON r.rid = ur.rid
                 WHERE ur.uid = ?""";
        try {
            // Dùng 'connection' có sẵn, không tạo mới
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("rid"));
                // Giả sử model Role của bạn có setRname
                // r.setRname(rs.getString("rname")); 
                roles.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
