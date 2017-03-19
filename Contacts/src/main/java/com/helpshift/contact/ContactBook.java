package com.helpshift.contact;

import com.helpshift.exception.DuplicateDataException;

import java.util.List;

/**
 * Created by gitanjali on 17/03/17.
 */
public interface ContactBook
{
    /*
        adds contact in contact book, throws exception if similar contact already added
     */
    public void addContact(Contact contact) throws DuplicateDataException;

    /*
        list all contacts with matching prefix
     */
    public List<Contact> searchContact(String prefix);
}
