package com.helpshift.contact;

import com.helpshift.exception.DuplicateDataException;
import com.helpshift.tries.Tries;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by gitanjali on 17/03/17.
 */
public class TriesBasedContactBook implements ContactBook
{
    Tries fNameLNameContactStore;
    Tries lNameFNameContactStore;
    Map<String, Contact> contactMap;
    public TriesBasedContactBook()
    {
        fNameLNameContactStore = new Tries();
        lNameFNameContactStore = new Tries();
        contactMap = new Hashtable<String, Contact>();
    }

    public void addContact(Contact contact) throws DuplicateDataException
    {
        String fullName = getSpaceSeparateName(contact.getFirstName(), contact.getLastName());
        if(contactMap.containsKey(fullName.toLowerCase()))
            throw new DuplicateDataException();

        // add full name in tries for searching
        fNameLNameContactStore.insert(fullName.toLowerCase());
        // add contact object to map with key as lowercase(fullname)
        contactMap.put(fullName.toLowerCase(),contact);

        // add to reverse full name in tries to search by sirname
        // sirname is not valid don't add in this tries
        if(contact.getLastName().length() > 1)
        {
            String reverseFullName = getSpaceSeparateName(contact.getLastName(), contact.getFirstName());
            lNameFNameContactStore.insert(reverseFullName.toLowerCase());
        }

    }

    public void searchContact(String prefix)
    {
        List<Contact> contactList = searchContactUtil(prefix);
        for(Contact contact: contactList)
        {
            contact.printContactDetails();
        }
    }

    public List<Contact> searchContactUtil(String prefix)
    {
        if(prefix == null)
            return new LinkedList<Contact>();

        List<Contact> contactsList = new LinkedList<Contact>();
        List<String> fNameList = fNameLNameContactStore.search(prefix);
        for(String item: fNameList)
        {
            if(contactMap.containsKey(item))
                contactsList.add(contactMap.get(item));
        }

        List<String> lNameList = lNameFNameContactStore.search(prefix);

        for(String item: lNameList)
        {
            String reverseString = reverseName(item);
            // remove item if that is already in fNameList
            if(!reverseString.equalsIgnoreCase("") && !fNameList.contains(reverseString))
            {
                if(contactMap.containsKey(reverseString))
                    contactsList.add(contactMap.get(reverseString));
            }
        }

        return contactsList;
    }

    public String getSpaceSeparateName(String str1, String str2)
    {
        if(str1 == null || str2 == null)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(str1);
        if(str2.length()>1)
            sb.append(" ").append(str2);
        return sb.toString();
    }


    public String reverseName(String str)
    {
        String[] split = str.split("\\s+");
        if(split.length >2)
            return "";
        if(split.length == 1)
            return getSpaceSeparateName(split[0],"");
        else
            return getSpaceSeparateName(split[1],split[0]);
    }
}
