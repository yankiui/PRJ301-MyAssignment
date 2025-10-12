package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Application;
import model.Department;

public class ApplicationDBContext extends DBContext<Application> {

    @Override
    public ArrayList<Application> list() {
        ArrayList<Application> appli = new ArrayList<>();
        try {
            String sql = "select a.aid, e.ename, r.Role,d.dname, a.Start_date, a.End_date, a.reason\n"
                    + "from dbo.[Application] a\n"
                    + "inner join Employee e on a.uid = e.uid\n"
                    + "inner join Role r on a.rid = r.rid\n"
                    + "inner join Department d on a.did = d.did";
            PreparedStatement stm = connection.prepareStatement(sql);
            //ORM - Object Relation Mapping
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Application a = new Application();
                a.setId(rs.getInt("aid"));
                a.setUser(rs.getNString("ename"));
                a.setRole(rs.getNString("Role"));
                a.setDept(rs.getString("dname"));
                a.setStart_date(rs.getDate("Start_date"));
                a.setEnd_date(rs.getDate("End_date"));
                a.setReason(rs.getNString("reason"));
                appli.add(a);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return appli;
    }

    @Override
    public Application get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Application model) {
        try {
            //begin transaction
            connection.setAutoCommit(false);
            //insert employee
            String sql_insert_application = "insert into Application\n"
                    + "(displayname,\n"
                    + "role,\n"
                    + "dept,\n"
                    + "Start_date,\n"
                    + "End_date,\n"
                    + "reason\n"
                    + ")\n"
                    + "values\n"
                    + "(?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?\n"
                    + ")";
            PreparedStatement stm = connection.prepareStatement(sql_insert_application);
            stm.setString(1, model.getUser());
            stm.setString(2, model.getRole());
            stm.setString(3, model.getDept());
            stm.setDate(4, model.getStart_date());
            stm.setDate(5, model.getEnd_date());
            stm.setString(6, model.getReason());
            stm.executeUpdate();
            //get eid

            String sql_select_aid = "SELECT @@IDENTITY as aid";
            PreparedStatement stm_select_aid = connection.prepareStatement(sql_select_aid);
            ResultSet rs = stm_select_aid.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("aid"));
            }
            //commit transaction
            connection.commit();
        } catch (SQLException ex) {
            try {
                //rollback transaction
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ApplicationDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ApplicationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ApplicationDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        }
    }

    @Override
    public void update(Application model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Application model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
