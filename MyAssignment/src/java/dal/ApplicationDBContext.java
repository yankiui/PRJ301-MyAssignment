package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Application;
import model.Employee;

public class ApplicationDBContext extends DBContext<Application>{
    
    
    public ArrayList<Application> getByEmployeeAndSubodiaries(int eid) {
        ArrayList<Application> rfls = new ArrayList<>();
        try {
            String sql = """
                                     WITH Org AS (
                                     \t-- get current employee - eid = @eid
                                     \tSELECT *, 0 as lvl FROM Employee e WHERE e.eid = ?
                                     \t
                                     \tUNION ALL
                                     \t-- expand to other subodinaries
                                     \tSELECT c.*,o.lvl + 1 as lvl FROM Employee c JOIN Org o ON c.supervisorid = o.eid
                                     )
                                     SELECT
                                     \t\t[rid]
                                     \t  ,[created_by]
                                     \t  ,e.ename as [created_name]
                                           ,[created_time]
                                           ,[from]
                                           ,[to]
                                           ,[reason]
                                           ,[status]
                                           ,[processed_by]
                                     \t  ,p.ename as [processed_name]
                                     FROM Org e INNER JOIN [RequestForLeave] r ON e.eid = r.created_by
                                     \t\t\tLEFT JOIN Employee p ON p.eid = r.processed_by""";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, eid);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Application rfl = new Application();
                
                rfl.setId(rs.getInt("rid"));
                
                rfl.setCreated_time(rs.getTimestamp("created_time"));
                rfl.setFrom(rs.getDate("from"));
                rfl.setTo(rs.getDate("to"));
                rfl.setReason(rs.getString("reason"));
                rfl.setStatus(rs.getInt("status"));
                
                Employee created_by = new Employee();
                created_by.setId(rs.getInt("created_by"));
                created_by.setEname(rs.getString("created_name"));
                rfl.setCreated_by(created_by);
                
                int processed_by_id = rs.getInt("processed_by");
                if(processed_by_id!=0)
                {
                    Employee processed_by = new Employee();
                    processed_by.setId(rs.getInt("processed_by"));
                    processed_by.setEname(rs.getString("processed_name"));
                    rfl.setProcessed_by(processed_by);
                }
                
                rfls.add(rfl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeConnection();
        }
        return rfls;
    }

    @Override
    public ArrayList<Application> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            String sql_insert_application = """
                                            insert into [RequestForLeave]
                                                    (
                                                    created_by,
                                                    created_time,
                                                    [from],
                                                    [to],
                                                    [reason],
                                                    status,
                                                    processed_by)
                                                    values
                                                    (
                                                    ?,
                                                    ?,
                                                    ?,
                                                    ?,
                                                    ?,
                                                    0,
                                                    null)""";
            PreparedStatement stm = connection.prepareStatement(sql_insert_application);
            stm.setInt(1, model.getCreated_by().getId());
            stm.setTimestamp(2, new java.sql.Timestamp(model.getCreated_time().getTime()));
            stm.setDate(3, model.getFrom());
            stm.setDate(4, model.getTo());
            stm.setString(5, model.getReason());
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
        try {
            String sql = """
                                     update [RequestForLeave]
                                     Set status = ?,
                                     	processed_by = ?
                                     where rid = ?""";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getStatus());
            stm.setInt(2, model.getProcessed_by().getId());
            stm.setInt(3, model.getId());
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ApplicationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeConnection();
        }
                 
    }
    


    @Override
    public void delete(Application model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
