package com.helpshift.contact;

/**
 * Created by gitanjali on 17/03/17.
 */
public class GeneralContact implements Contact
{
    String firstName;
    String lastName;

    public GeneralContact(String fName, String lName)
    {
        firstName = fName;
        lastName = lName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void printContactDetails()
    {
        System.out.print(firstName);
        if(lastName.length() >1)
            System.out.print(" "+lastName);
        System.out.println();
    }
}
