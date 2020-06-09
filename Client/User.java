package Client;

import Route.Route;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable {
    private String name;
    private String password;
    private boolean status;
    private String action;
    private Set<Integer> ids;;
    private List<Route> arr;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        ids = new HashSet<Integer>();
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void addId(int id){ ids.add(id); }

    public void removeId(int id){ ids.remove(id); }

    public Set<Integer> getIds() { return ids; }

    public List<Route> getArr() {
        return arr;
    }

    public void setArr(List<Route> arr) {
        this.arr = arr;
    }

}
