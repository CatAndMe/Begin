package com.mjl.model;

/**
 * Created by alvin on 15/9/7.
 */
public class User {
    private Integer EmplId;
    private String EmplName;
    private String EmplPassword;
    private String EmplEmail;
    private Integer EmplAdmin;

    public String getEmail() {
        return EmplEmail;
    }

    public void setEmail(String EmplEmail) {
        this.EmplEmail = EmplEmail;
    }

    public String getPassword() {
        return EmplPassword;
    }

    public void setPassword(String password) {
        this.EmplPassword = password;
    }

    public Integer getId() {
        return EmplId;
    }

    public void setId(Integer id) {
        this.EmplId = id;
    }

    public String getUsername() {
        return EmplName;
    }

    public void setUsername(String username) {
        this.EmplName = username;
    }

    public Integer getEmplAdmin() {
        return EmplAdmin;
    }

    public void setEmplAdmin(Integer EmplAdmin) {
        this.EmplAdmin = EmplAdmin;
    }

}
