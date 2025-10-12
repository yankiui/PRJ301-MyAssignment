package model;

import java.sql.Date;

public class Application extends BaseModel{
    private String user;
    private String role;
    private String dept;
    private Date Start_date;
    private Date End_date;
    private String reason;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
