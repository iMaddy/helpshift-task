package com.helpshift.contact.factories;

import com.helpshift.contact.Contact;

/**
 * Created by gitanjali on 17/03/17.
 */
public interface ContactFactory
{
    public Contact createContact(String fName, String lName);
}
