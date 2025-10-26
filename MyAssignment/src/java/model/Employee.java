package model;

import java.sql.Date;

public class Employee extends BaseModel{
    private String ename;
    private Department dept;
    private Employee manage_by;

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Employee getManage_by() {
        return manage_by;
    }

    public void setManage_by(Employee manage_by) {
        this.manage_by = manage_by;
    }

    

   
    
    
    
    
}
