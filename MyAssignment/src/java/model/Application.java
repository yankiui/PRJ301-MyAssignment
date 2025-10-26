package model;

import model.auth.User;
import java.sql.Date;

public class Application extends BaseModel{
    private User user;
    private String role;
    private String dept;
    private java.util.Date created_time;
    private Date Start_date;
    private Date End_date;
    private String reason;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public java.util.Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(java.util.Date created_time) {
        this.created_time = created_time;
    }

    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Date getStart_date() {
        return Start_date;
    }

    public void setStart_date(Date Start_date) {
        this.Start_date = Start_date;
    }

    public Date getEnd_date() {
        return End_date;
    }

    public void setEnd_date(Date End_date) {
        this.End_date = End_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    
}
