package com.company;

public class Sale extends Post{
    private int Asking_price;
    private int Highest_offer;
    private int Minimum_raise;

    public Sale(String Id,String Title,String desc,String CreatorID, int Asking_price, int Minimum_raise)
    {
        super(Id,Title,desc,CreatorID);
        this.Asking_price=Asking_price;
        this.Minimum_raise=Minimum_raise;
    }

    @Override
    public String getPostDetails() {
        return super.getPostDetails()+
                "Minimum Raise : " + Minimum_raise + "\n"+
                "Highest Offer : " + Empty_Highest_offer(Highest_offer);
    }

    @Override
    public boolean handleReply(Reply reply) {
        //1-if the post is open or not
        //new offer should be > current highest offer(reject <=)
        //check for minimum raise
        //if new offer price is > or = asking price close the post
        if(getPostStatus().equals("O"))
        {
            if (reply.getValue()>getHighest_offer() && reply.getValue()-getHighest_offer()>=Minimum_raise)
            {
                getReplyCollection().add(reply);
                setHighest_offer(reply.getValue());
                if (getHighest_offer()>Asking_price)//use highest offer of reply.value as they are same
                {
                    change_status();
                }
                return true;
            }
            else return false;
        }
        else return false;
    }


    @Override//should give offers in desc
    public String getReplyDetails() {
        sort_desc(getReplyCollection());
        String val_for_arr=" ";

        for (int i=0;i<getReplyCollection().size();i++)
        {
            val_for_arr += getReplyCollection().get(i).getResponderID()+" : "+getReplyCollection().get(i).getValue() + "\n" + val_for_arr;
        }
        String return_string = getPostDetails()+"\n" +
                "Asking Price :" + Asking_price+"\n" +
                val_for_arr;

        return return_string;
    }

    //Method

    public String Empty_Highest_offer(int offer)
    {
        if (offer == 0)
            return "NO OFFER";
        else
            return Integer.toString(offer);
    }

    public int getHighest_offer()
    {
        return Highest_offer;
    }

    public void setHighest_offer(int val)
    {
        Highest_offer=val;
    }

}
