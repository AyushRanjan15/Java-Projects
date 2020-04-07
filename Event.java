package com.company;

import java.util.Arrays;

public class Event extends Post {
    private String Venue;
    private String Date;
    private int Capacity;
    private int AttendeeCount;
    String[] Attendee_list=new String[getCapacity()]; //should i use an accessor method


    public Event(String Id,String Title,String desc,String CreatorID,String Venue, String Date, int Capacity)
    {
        //ID i.e eventID and CreatorID are auto created
        super(Id,Title,desc,CreatorID);
        this.Venue=Venue;
        this.Date=Date;
        this.Capacity=Capacity;
        AttendeeCount=0;
        this.Attendee_list = new String[getCapacity()];
    }

    //Methods

    public int getCapacity()
    {
        return Capacity;
    }



    @Override
    public String getPostDetails() {
        return super.getPostDetails()+
                "Venue :" + Venue+"\n"+
                "Date :" + Date+"\n"+
                "Capacity :" + Capacity+"\n"+
                "Attendee Count" + AttendeeCount+"\n";
    }


    //If the reply is valid, the event is not full and the student id is not yet recorded in that event,
    // then this handleReply method must add this reply object to the replies collection of this event post and return true,
    @Override
    public boolean handleReply(Reply reply)
    {
        if (AttendeeCount < Capacity & !(Arrays.asList(Attendee_list).contains(reply.getResponderID())))
        {
            Attendee_list[AttendeeCount]=reply.getResponderID();
            getReplyCollection().add(reply);
            AttendeeCount++;
            return true;
        }
        return false;
    }

    //this method should only be called by the post creator
    @Override// might cause problems//ask about execution
    public String getReplyDetails() {
        getPostDetails();

        if (AttendeeCount==0)
        {
            return "Attendee list : Empty";
        }

        else
        {
            System.out.println("Attendee list : ");
            for (int i=0 ;i<AttendeeCount;i++)
            {
                System.out.println(Attendee_list[i]+",");
            }
        }
        return null;
    }

}

