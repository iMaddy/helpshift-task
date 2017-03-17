package com.helpshift.contact.factories;

import com.helpshift.contact.ContactBook;
import com.helpshift.contact.TriesBasedContactBook;

/**
 * Created by gitanjali on 17/03/17.
 */
public class TriesContactBookFactory implements ContactBookFactory
{
    public ContactBook createContactBook()
    {
        return new TriesBasedContactBook();
    }
}
