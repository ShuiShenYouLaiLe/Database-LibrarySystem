package com.databaseproject.library_system.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Admin {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Admin() {
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        return password;
    }
}
