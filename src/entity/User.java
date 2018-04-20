package entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer uid;
    private String username;
    private String password;
    private String address;
    //表示拜访信息
    private Set<Visit> setVisit=new HashSet<Visit>();

    public Set<Visit> getSetVisit() {
        return setVisit;
    }

    public void setSetVisit(Set<Visit> setVisit) {
        this.setVisit = setVisit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
