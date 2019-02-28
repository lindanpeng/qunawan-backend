package me.lindanpeng.qunawan.web.dto;

import java.io.Serializable;

public class SessionData implements Serializable {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "email='" + email + '\'' +
                '}';
    }
}
