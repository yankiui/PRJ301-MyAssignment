package model.auth;

import java.util.ArrayList;
import model.BaseModel;

public class Feature extends BaseModel {

    private String url;
    private ArrayList<Role> roles = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
}
