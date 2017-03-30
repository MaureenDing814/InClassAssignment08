package com.example.android.inclassassignmentafterclass_mengqid;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Maureen_Ding on 3/29/17.
 */

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}