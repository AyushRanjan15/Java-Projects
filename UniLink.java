package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class UniLink {
    private ArrayList<Post> all_post = new ArrayList<Post>(5);

    public UniLink (){}

//    supporting methods

    private int serch_post(String postid, ArrayList<Post> all_post)
    {
        for(int i=0; i<all_post.size();i++)
        {
            if(all_post.get(i).getPostid().equals(postid))
            {
                return i;
            }
            else System.out.println("No post with such id");
        }
        return -1;
    }

    public ArrayList<Post> getAll_post() {
        return all_post;
    }

    private String string_validate()
    {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        do {
            String check = sc.nextLine();
            if (!check.equals(""))return check;
            else System.out.println("Not a valid input");

        }while (!exit);
        return "";
    }

    private int int_validate()
    {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        do {
            int check = sc.nextInt();
            if (check<=0)System.out.println("Not a valid input");
            else return check;

        }while (!exit);
        return 0;
    }

    private String date_validate()
    {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        do {
            String check = sc.nextLine();
            if (check.matches("\\d{2}/\\d{2}/\\d{4}"))return check;
            else System.out.println("Not a valid input" +"\n"+
                    "valid input in form dd/mm/yyyy");

        }while (!exit);
        return "";
    }

    private Post event_creator_input(Utlity post_utility, String calling_student)
    {
        System.out.println("Enter details of the event below :"+"\n");
        System.out.println("Name :");
        String title = string_validate();
        System.out.println("Description: ");
        String desc = string_validate();
        System.out.println("Venue: ");
        String venue = string_validate();
        System.out.println("Date: ");
        String date = date_validate();
        System.out.println("Capacity: ");
        int capacity = int_validate();

        String input_postid=post_utility.generate_postid(1);//to print later otherwise would have put inside constr.

        Event my_event = new Event(input_postid,title,desc,calling_student,venue,date,capacity);

        return my_event;
    }


    private Sale sale_creator_input(Utlity post_utility, String calling_student)
    {

        System.out.println("Enter details of the event below :"+"\n");
        System.out.println("Name :");
        String title = string_validate();
        System.out.println("Description: ");
        String desc = string_validate();
        System.out.println("Asking price :");
        int asking_price = int_validate();
        System.out.println("Minimum Raise :");
        int min_raise = int_validate();

        String input_postid=post_utility.generate_postid(2);//to print later otherwise would have put inside constr.

        Sale my_event = new Sale(input_postid,title,desc,calling_student,asking_price,min_raise);

        return my_event;
    }

    private Job job_creator_input(Utlity post_utility, String calling_student)
    {

        System.out.println("Enter details of the event below :"+"\n");
        System.out.println("Name :");
        String title = string_validate();
        System.out.println("Description: ");
        String desc = string_validate();
        System.out.println("Proposed price: ");
        int prop_price = int_validate();


        String input_postid=post_utility.generate_postid(3);//to print later otherwise would have put inside constr.

        Job my_event = new Job(input_postid,title,desc,calling_student,prop_price);
        return my_event;
    }

    private void post_reply(ArrayList<Post> all_post, String calling_student)
    {
        System.out.println("Enter Post id or Q to Quit :");
        Scanner sc = new Scanner(System.in);
        String post_id = sc.nextLine();

        try {
            all_post.get(serch_post(post_id,all_post));//searching and getting post
        }catch (IndexOutOfBoundsException ex){
            System.out.println("Post not found");
        }

        Post mypost = all_post.get(serch_post(post_id,all_post));

        mypost.getPostDetails();//not using 3 letter//REVIEW

        if (mypost instanceof Event) System.out.println("Enter value = 1 to reply ");
        else System.out.println("Enter your offer or 'Q' to quit :");

        int value = sc.nextInt();
        Reply my_reply = new Reply(mypost.getPostid(),value,calling_student);
        if (mypost.getPostStatus()=="O" && mypost.handleReply(my_reply))//ask about exception
        {
            System.out.println("Offer Excepted ! ");
        }
        else
        {
            System.out.println("Some error occurred");
            menu(calling_student);
        }
    }


//    MAIN MENU

    public void menu(String calling_student)
    {

        System.out.println("Welcome " + calling_student);

        System.out.println(" * * Student Menue * * " + "\n" +
                "1. New Event Post" + "\n" +
                "2. New Sale Post" + "\n" +
                "3. New Job Post" + "\n" +
                "4. Reply to Post" + "\n" +
                "5. Display my Post" + "\n" +
                "6. Display all Post" + "\n" +
                "7. Close Post" + "\n" +
                "8. Delete Post" + "\n" +
                "9. Log Out" + "\n" +
                "Enter Your Choice : ");

        Scanner input_level_2 = new Scanner(System.in);
        int responce_level_2 = input_level_2.nextInt();
        Utlity post_utility = new Utlity();

        switch (responce_level_2)
        {
            case 1:
            {
                Post my_event=event_creator_input(post_utility,calling_student);

                if (all_post.add(my_event))
                    System.out.println("Success ! The post has been created with id " + my_event.getPostid());
                else System.out.println("Some error will creating post");
                menu(calling_student);
                break;
            }
            case 2:
            {
                Sale my_event = sale_creator_input(post_utility,calling_student);

                if (all_post.add(my_event))
                    System.out.println("Success ! The post has been created with id " + my_event.getPostid() + "\n" + calling_student);
                else System.out.println("Some error will creating post");
                menu(calling_student);
                break;

            }
            case 3://no break statement yet
            {
                Job my_event = job_creator_input(post_utility,calling_student);

                if (all_post.add(my_event))
                    System.out.println("Success ! The post has been created with id " + my_event.getPostid() + "\n" + calling_student);
                else System.out.println("Some error will creating post");
                menu(calling_student);
                break;
            }
            case 4://ask about try catch
            {
                post_reply(all_post,calling_student);
                menu(calling_student);
                break;
            }
            case 5:
            {

                for (int i=0; i<all_post.size(); i++)
                {
                    if (all_post.get(i).getCreatorID().equals(calling_student))
                    {
                        System.out.println(all_post.get(i).getReplyDetails());
                    }
                    else System.out.println("No post yet created by you");
                }
                menu(calling_student);
                break;
            }
            case 6:
            {

                for (int i=0; i<all_post.size(); i++)
                {
                    System.out.println(all_post.get(i).getPostDetails());
                }

                menu(calling_student);
                break;
            }
            case 7:// for time being this method opens and close posts
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Post id :");

                String post_id=sc.nextLine();

                for (int i=0; i<all_post.size();i++)
                {
                    if (all_post.get(i).getPostid().equals(post_id) && all_post.get(i).getCreatorID().equals(calling_student)&&all_post.get(i).getPostStatus().equals("O"))
                    {
                        all_post.get(i).change_status();
                    }
                }
                menu(calling_student);
                break;
            }
            case 8:
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Post id :");

                String post_id=sc.nextLine();


                for (int i=0; i<all_post.size();i++)
                {
                    if (all_post.get(i).getPostid().equals(post_id) && all_post.get(i).getCreatorID().equals(calling_student))
                    {
                        System.out.println("Post with post ID :" + all_post.get(i).getPostid() + " is being removed");
                        all_post.remove(i);
                    }
                }
                menu(calling_student);
                break;
            }
            case 9:
            {
                break;
            }
            default: System.out.println("Not a valid selection TRY AGAIN "); menu(calling_student);
        }

    }

}
