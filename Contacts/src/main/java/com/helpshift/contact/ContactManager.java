package com.helpshift.contact;

import com.helpshift.contact.factories.ContactBookFactory;
import com.helpshift.contact.factories.ContactFactory;
import com.helpshift.contact.factories.GeneralContactFactory;
import com.helpshift.contact.factories.TriesContactBookFactory;
import com.helpshift.exception.DuplicateDataException;

import java.util.Scanner;

/**
 * Created by gitanjali on 17/03/17.
 */
public class ContactManager
{
    ContactBook cBook;
    ContactBookFactory contactBookFactory;
    ContactFactory contactFactory;
    final static int maxLength = 50;

    public boolean validContactString(String str)
    {
        return str != null && str.length() <=50 && str.split("\\s+").length <=2;
    }

    public ContactManager()
    {
        cBook = contactBookFactory.createContactBook();
    }

    public ContactManager(ContactBookFactory cbf, ContactFactory cf)
    {
        contactBookFactory = cbf;
        contactFactory = cf;
        cBook = contactBookFactory.createContactBook();
    }

    public void performContactOperations()
    {
        while(true)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("1) Add contact 2) Search 3) Exit");
            int choice =0;
            try
            {
                choice = Integer.valueOf(sc.nextLine());
            }
            catch (Exception e)
            {
                System.out.println("Invalid operation. Try Again");
            }

            if(choice == 3)
                break;

            if(choice == 1)
            {
                System.out.print("Enter Name: ");
                addContact(sc.nextLine());
            }
            else if(choice == 2)
            {
                System.out.print("Enter Name: ");
                searchContact(sc.nextLine());
            }
            else
            {
                System.out.println("Invalid operation. try again");
            }
        }

        System.out.println("Happy Searching");
    }

    protected void addContact(String name)
    {
        if(!validContactString(name))
        {
            System.out.println("Invalid name. Try again..");
            return;
        }

        String[] split = name.split("\\s+");
        String fName = split[0];
        String lName = "";
        if(split.length == 2)
            lName = split[1];
        Contact contact = contactFactory.createContact(fName, lName);
        try
        {
            cBook.addContact(contact);
        } catch (DuplicateDataException e)
        {
            System.out.println(e.getMessage() + " Try Again");

        }
    }

    protected void searchContact(String prefix)
    {
        if(!validContactString(prefix))
        {
            System.out.println("Invalid name. Try again..");
            return;
        }

        cBook.searchContact(prefix);
    }

    public static void main(String[] args)
    {
        ContactManager cm = new ContactManager(new TriesContactBookFactory(),new GeneralContactFactory());
        cm.performContactOperations();
    }
}
