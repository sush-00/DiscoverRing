package com.example.discoverring;

public class ItemsFound
{
    String itemName, itemDesc, itemType, foundLoc, foundDate, userUID;

    public ItemsFound()
    {}

    public ItemsFound(String userUID, String itemName, String itemDesc, String itemType, String foundLoc, String foundDate)
    {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
        this.foundLoc = foundLoc;
        this.foundDate = foundDate;
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

    public String getFoundLoc()
    {
        return foundLoc;
    }

    public void setFoundLoc(String foundLoc)
    {
        this.foundLoc = foundLoc;
    }

    public String getFoundDate()
    {
        return foundDate;
    }

    public void setFoundDate(String foundDate)
    {
        this.foundDate = foundDate;
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
