package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Post {
    private String Id;
    private String Title;
    private String desc;
    private String CreatorID;
    private boolean status;
    private ArrayList<Reply> Replies_collection = new ArrayList<Reply>();

    //Have not mentioned replies collection in this class

    //Constructor

    public Post (String Id,String Title,String desc,String CreatorID)
    {
        this.Id=Id;
        this.Title=Title;
        this.desc=desc;
        this.CreatorID=CreatorID;
        status=true;
    }

    //Methods

    public String getPostDetails()
    {
        String String_to_return = "ID :" + getPostid() +"\n"+
                "Title :" + getTitle() +"\n"+
                "Discription :" + getDesc() +"\n"+
                "Creator ID :" + getCreatorID() +"\n"+
                "Status :" + getPostStatus() +"\n";
        return String_to_return;
    }

    public abstract boolean handleReply(Reply reply);

    public abstract String getReplyDetails();

    //Accessor method

    public ArrayList<Reply> getReplyCollection()
    {
        return Replies_collection;
    }

    public String getDesc()
    {
        return desc;
    }

    public String getTitle()
    {
        return Title;
    }

    public String getPostid()
    {
        return Id;
    }

    public String getPostStatus()
    {
        if (status==true)return "O";
        return "C";
    }

    public String getCreatorID()
    {
        return CreatorID;
    }

    //Extra methods

    public void sort_desc(ArrayList<Reply> obj)
    {
        String val_for_return="";

        Collections.sort(obj, new Comparator<Reply>() {
            @Override
            public int compare(Reply o1, Reply o2) {
                return Integer.valueOf(o1.getValue()).compareTo(o2.getValue());
            }
        });

//        for (int i=0; i<=obj.size(); i++)
//        {
//            val_for_return+=obj.get(i).getResponderID()+" : "+obj.get(i).getValue() + "\n" + val_for_return;// who made offer not added
//        }
    }

    public void change_status()
    {
        status =! status;
        System.out.println("Post Status Changed To : "+getPostStatus());
    }

}