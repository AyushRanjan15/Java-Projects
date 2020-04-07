package com.company;

public class Reply {
    private String PostID;
    private int value;
    private String ResponderID;

    public Reply (String PostID, int value, String ResponderID)
    {
        this.PostID=PostID;
        this.value=value;
        this.ResponderID=ResponderID;
    }


    //accessor methods

    public String getResponderID()
    {
        return ResponderID;
    }

    public int getValue()
    {
        return value;
    }
}
