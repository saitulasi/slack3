package com.example.dellpc.slack;

/**
 * Created by Dell PC on 05-07-2018.
 */

public class UserProfile {
    public String user_email;
    public String user_name;
    public String user_bldgrp,user_con;

    public UserProfile(String user_name,String user_email,String user_bldgrp,String user_con)
    {
        this.user_bldgrp=user_bldgrp;
        this.user_email=user_email;
        this.user_name=user_name;
        this.user_con=user_con;
    }
    public UserProfile(){}


    public String getUser_email() {
        return user_email;
    }

    public String getUser_bldgrp() {
        return user_bldgrp;
    }

    public String getUser_con() {
        return user_con;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_bldgrp(String user_bldgrp) {
        this.user_bldgrp = user_bldgrp;
    }

    public void setUser_con(String user_con) {
        this.user_con = user_con;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
