package model.auth;

import java.util.ArrayList;
import model.BaseModel;

public class Role extends BaseModel{
    private String role;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Feature> features = new ArrayList<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }
    
    
}
