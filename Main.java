package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean flag1 = true;
        UniLink link = new UniLink();
        create_startup_loading(link);

        //For testing purposes when we mark your program, you should hard-code some data. For example, when your program executes, there are the following:
//          • One event with a few participants
//          • One sale with a few offers and is still open
//          • One job with a few offers and is still open

        while (flag1 == true)
        {
            System.out.println("**** UniLink System ****" + "\n" + "1. Login" + "\n" + "2. Quit");

            Scanner input_level_1 = new Scanner(System.in);
            int responce_level_1=input_level_1.nextInt();

            switch (responce_level_1)
            {
                case 1:{
                    System.out.println("Enter Username : ");

                    Scanner input_level_1_1 = new Scanner(System.in);
                    String response_level_1_1=input_level_1_1.nextLine();
                    link.menu(response_level_1_1);
                    break;
                }
                case 2:{
                    System.exit(0);
                }
                default:{
                    System.out.println("Please Try Again" + "\n" + "Invalid input");
                }
            }

        }

    }

    public static void create_startup_loading(UniLink link)
    {
        Utlity post_utility = new Utlity();

        //EVENT POST
        String input_postid_event1=post_utility.generate_postid(1);
        Event event_1=new Event(input_postid_event1, "Resume review","Free resume review for students","S9","RMIT city campus","14/06/20",5);
        new Reply("EVE1",1,"S1");
        new Reply("EVE1",1,"S8");
        link.getAll_post().add(event_1);

        //SALE POST
        String input_postid_sale1=post_utility.generate_postid(2);
        Sale sale_1=new Sale(input_postid_sale1,"Garage sale","Really cool painting","S8",90,5);
        new Reply("SAL1",50,"S2");
        new Reply("SAL1",70,"S1");
        link.getAll_post().add(sale_1);
        //JOB POST
        String input_postid_job1=post_utility.generate_postid(3);
        Job job_1=new Job(input_postid_job1,"Waiting","Waiting staff needed - Casual position","S3",20);
        new Reply("JOB1",22,"S2");
        new Reply("JOB1",21,"S1");
        link.getAll_post().add(job_1);
    }

}

