package com.demigod.Zeta_Forum.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Theuser {


        @NotNull
        private String name;

        @NotNull
        @Column(unique = true)
        private String userName;

        @Id
        @NotNull
        private String userId;

        @NotNull
        private String password;

        @NotNull
        @Column(unique = true)
        private String email;



        private int verified=0;

        private String status="Active";

        public Theuser()
        {

        }

    public Theuser(String name, String userName, String password, String email,String userId) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userId=userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }
}
