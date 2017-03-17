package com.helpshift.test;

import com.helpshift.contact.*;
import com.helpshift.exception.DuplicateDataException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit helpshift for simple App.
 */
public class ContactsTest
    extends TestCase
{
    /**
     * Create the helpshift case
     *
     * @param testName name of the helpshift case
     */
    public ContactsTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ContactsTest.class );
    }

    /**
     * Test cases
     */
    @org.junit.Test
    public void testApp()
    {
        assertTrue(true);
    }

    /*
        search in empty contact book
     */

    @org.junit.Test
    public void testSearchEmptyContactBook() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException
    {
        TriesBasedContactBook contactBook = new TriesBasedContactBook();
        List<Contact> contactList = contactBook.searchContactUtil("madhukar");
        assertTrue("List is not empty",contactList.size() == 0);
    }

        /*
        search in contact that haven't
     */

    @org.junit.Test
    public void testSearchInvalidContactBook() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException
    {
        TriesBasedContactBook contactBook = new TriesBasedContactBook();
        try
        {
            contactBook.addContact(new GeneralContact("madhukar", "bangar"));
            contactBook.addContact(new GeneralContact("maddy", "bangar"));
        } catch (DuplicateDataException e)
        {
            e.printStackTrace();
        }
        List<Contact> contactList = contactBook.searchContactUtil("sachin tendulkar");
        assertTrue("List is not empty",contactList.size() == 0);
    }

    /*
    Search with name - exact match
     */
    @org.junit.Test
    public void testSearchWithNameExactMatch()
    {
        TriesBasedContactBook contactBook = new TriesBasedContactBook();
        try
        {
            contactBook.addContact(new GeneralContact("madhukar", "bangar"));
            contactBook.addContact(new GeneralContact("maddy", "bangar"));
        } catch (DuplicateDataException e)
        {
            e.printStackTrace();
        }
        List<Contact> contactList = contactBook.searchContactUtil("madhukar bangar");
        boolean correctSize = contactList.size() == 1;
        boolean correctContactMatch = contactList.get(0).getFirstName() == "madhukar" &&
                contactList.get(0).getLastName() == "bangar";

        assertTrue("incorrect search output",correctSize && correctContactMatch);
    }

    /*
    partial search match
     */
    @org.junit.Test
    public void testSearchWithNamePartialMatch()
    {
        TriesBasedContactBook contactBook = new TriesBasedContactBook();
        try
        {
            contactBook.addContact(new GeneralContact("madhukar", "bangar"));
            contactBook.addContact(new GeneralContact("maddy", "bangar"));
            contactBook.addContact(new GeneralContact("gitanjali","Sonawane"));
        } catch (DuplicateDataException e)
        {
            e.printStackTrace();
        }
        List<Contact> contactList = contactBook.searchContactUtil("mad");
        boolean correctSize = contactList.size() == 2;
        List<String> nameList =Arrays.asList("madhukar", "maddy");
        List<String> lastName =Arrays.asList("bangar");
        boolean correctContactMatch = nameList.contains(contactList.get(0).getFirstName())
        && lastName.contains(contactList.get(0).getLastName())
        && nameList.contains(contactList.get(1).getFirstName())
        && lastName.contains(contactList.get(1).getLastName());
        assertTrue("incorrect search output",correctSize && correctContactMatch);
    }

    /*
    exact match first
 */
    @org.junit.Test
    public void testExactMatchFirst()
    {
        TriesBasedContactBook contactBook = new TriesBasedContactBook();
        try
        {
            contactBook.addContact(new GeneralContact("madhukar", "bangar"));
            contactBook.addContact(new GeneralContact("madhukar", ""));
            contactBook.addContact(new GeneralContact("maddy", "bangar"));
        } catch (DuplicateDataException e)
        {
            e.printStackTrace();
        }
        List<Contact> contactList = contactBook.searchContactUtil("madhukar");
        boolean correctSize = contactList.size() == 2;
        boolean correctContactMatch = contactList.get(0).getFirstName() == "madhukar"
                && contactList.get(0).getLastName() == "";
        assertTrue("incoorect search output",correctSize && correctContactMatch);
    }


    /*
     search with lastname
 */
    @org.junit.Test
    public void testSearchWithLastName()
    {
        TriesBasedContactBook contactBook = new TriesBasedContactBook();
        try
        {
            contactBook.addContact(new GeneralContact("madhukar", "bangar"));
            contactBook.addContact(new GeneralContact("maddy", "bangar"));
            contactBook.addContact(new GeneralContact("gitanjali",""));
        } catch (DuplicateDataException e)
        {
            e.printStackTrace();
        }
        List<Contact> contactList = contactBook.searchContactUtil("bangar");
        boolean correctSize = contactList.size() == 2;
        List<String> nameList =Arrays.asList("madhukar", "maddy");
        List<String> lastName =Arrays.asList("bangar");
        boolean correctContactMatch = nameList.contains(contactList.get(0).getFirstName())
                && lastName.contains(contactList.get(0).getLastName())
                && nameList.contains(contactList.get(1).getFirstName())
                && lastName.contains(contactList.get(1).getLastName());
        assertTrue("incorrect search output",correctSize && correctContactMatch);
    }

    /*
    search with partial last name
*/
    @org.junit.Test
    public void testSearchWithPartialLastName()
    {
        TriesBasedContactBook contactBook = new TriesBasedContactBook();
        try
        {
            contactBook.addContact(new GeneralContact("madhukar", "bangar"));
            contactBook.addContact(new GeneralContact("maddy", "bangar"));
            contactBook.addContact(new GeneralContact("sachin","tendulkar"));
        } catch (DuplicateDataException e)
        {
            e.printStackTrace();
        }
        List<Contact> contactList = contactBook.searchContactUtil("ban");
        boolean correctSize = contactList.size() == 2;
        List<String> nameList =Arrays.asList("madhukar", "maddy");
        List<String> lastName =Arrays.asList("bangar");
        boolean correctContactMatch = nameList.contains(contactList.get(0).getFirstName())
                && lastName.contains(contactList.get(0).getLastName())
                && nameList.contains(contactList.get(1).getFirstName())
                && lastName.contains(contactList.get(1).getLastName());
        assertTrue("incorrect search output",correctSize && correctContactMatch);
    }
}
