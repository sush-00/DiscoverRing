package com.example.discoverring;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User
{
    String email, password, uid;
    String contactNum = "";
    String fullName = "";

    public User()
    {}

    public User(String email, String password, String uid)
    {
        this.email = email;
        this.password = password;
        this.uid = uid;

    }

    public User(String email, String password, String uid, String contactNum, String fullName)
    {
        this.email = email;
        this.password = password;
        this.uid = uid;
        this.contactNum = contactNum;
        this.fullName = fullName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUid()
    {
        return uid;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getContactNum()
    {
        return contactNum;
    }

    public void setContactNum(String contactNum)
    {
        this.contactNum = contactNum;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    @Exclude
    public Map<String, Object> toMap()
    {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("fullName", fullName);
        result.put("email", email);
        result.put("password", password);
        result.put("contactNumber", contactNum);

        return result;
    }
}
