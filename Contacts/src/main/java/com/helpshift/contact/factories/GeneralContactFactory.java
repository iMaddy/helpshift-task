package com.helpshift.contact.factories;

import com.helpshift.contact.Contact;
import com.helpshift.contact.GeneralContact;

/**
 * Created by gitanjali on 17/03/17.
 */
public class GeneralContactFactory implements ContactFactory
{
    public Contact createContact(String fName, String lName)
    {
        return new GeneralContact(fName,lName);
    }
}
