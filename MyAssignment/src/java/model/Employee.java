package model;

import java.sql.Date;

public class Employee extends BaseModel{
    private String ename;
    private boolean gender;
    private Date dob;
    private int rid;
    private int did;
    private int manage_by;
    private int uid;

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getManage_by() {
        return manage_by;
    }

    public void setManage_by(int manage_by) {
        this.manage_by = manage_by;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    
    
    
}
