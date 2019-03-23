package me.lindanpeng.qunawan.api.dto;

import java.io.Serializable;

public class SessionData implements Serializable {

    private String email;
    private Long userId;

    @Override
    public String toString() {
        return "SessionData{" +
                "email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
