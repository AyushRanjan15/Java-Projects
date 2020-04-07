package com.company;

public class Utlity {
    private int event_count=0;
    private int sale_count=0;
    private int job_count=0;


    public Utlity(){}




    //METHOD 1
    public String generate_postid(int type)
    {
        String output="";
        if(type==1)
        {
            event_count+=1;
            output= "EVE"+event_count;
        }
        else if (type==2)
        {
            sale_count+=1;
            output= "SAL"+sale_count;
        }
        else if (type==3)
        {
            job_count+=1;
            output= "JOB"+job_count;
        }
        return output;
    }
}
