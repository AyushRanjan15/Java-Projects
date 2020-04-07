package com.company;

public class Job extends Post{
    private int proposed_price;
    private int lowest_price;

    public Job(String Id,String Title,String desc,String CreatorID,int proposed_price)
    {
        super(Id,Title,desc,CreatorID);
        this.proposed_price=proposed_price;
    }

    @Override
    public String getPostDetails() {
        return super.getPostDetails()+
                "Proposed price : " + proposed_price+"\n"+
                "Lowest price : " + lowest_price;
    }

    @Override
    public boolean handleReply(Reply reply) {
        //Accepts only if job is open
        //If the new offer price is greater than or equal to the current lowest offer, then the system rejects that new offer price.
        //new offer price is less than the current lowest offer, then the system accepts the new offer
        if (getPostStatus().equals("O"))
        {
            if (reply.getValue()<lowest_price)
            {
                getReplyCollection().add(reply);
                setLowest_price(reply.getValue());
                return true;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public String getReplyDetails() {
        sort_desc(getReplyCollection());
        String val_for_arr=" ";

        for (int i=0;i<getReplyCollection().size();i++)
        {
            val_for_arr += getReplyCollection().get(i).getResponderID()+" : "+getReplyCollection().get(i).getValue() + "\n" + val_for_arr;
        }
        String return_string = getPostDetails()+"\n" +
                val_for_arr;

        return return_string;
    }

    //
    public void setLowest_price(int val)
    {
        lowest_price=val;
    }
}
