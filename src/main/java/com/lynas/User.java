package com.lynas;

import javax.persistence.*;

@Entity
@Table(name = "USR")
public class User {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String username;
    @Column(name = "PASSWD")
    private String password;
    @Transient
    private int groupId;
    @Transient
    private String admin;
    @Transient
    private String fullname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


}
