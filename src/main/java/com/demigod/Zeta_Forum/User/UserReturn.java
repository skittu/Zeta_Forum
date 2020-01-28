package com.demigod.Zeta_Forum.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class UserReturn extends Theuser {




    private String msg="";

    public UserReturn() {

    }
    public UserReturn(Theuser u)
    {
        super(u.getName(), u.getUserName() ,u.getPassword(), u.getEmail(),u.getUserId());

    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}