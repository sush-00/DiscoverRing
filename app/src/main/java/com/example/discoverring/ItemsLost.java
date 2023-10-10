package com.example.discoverring;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ItemsLost
{
    String itemName, itemDesc, itemType, lastSeenLoc, lostDate, userUID;

    public ItemsLost()
    {}

    public ItemsLost(String userUID, String itemName, String itemDesc, String itemType, String lastSeenLoc, String lostDate)
    {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
        this.lastSeenLoc = lastSeenLoc;
        this.lostDate = lostDate;
        this.userUID = userUID;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemDesc()
    {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }

    public String getItemType()
    {
        return itemType;
    }

    public void setItemType(String itemType)
    {
        this.itemType = itemType;
    }

    public String getLastSeenLoc()
    {
        return lastSeenLoc;
    }

    public void setLastSeenLoc(String lastSeenLoc)
    {
        this.lastSeenLoc = lastSeenLoc;
    }

    public String getLostDate()
    {
        return lostDate;
    }

    public void setLostDate(String lostDate)
    {
        this.lostDate = lostDate;
    }

    public String getUserUID()
    {
        return userUID;
    }

    public void setUserUID(String userUID)
    {
        this.userUID = userUID;
    }
}
