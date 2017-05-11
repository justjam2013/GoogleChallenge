package com.renzo.gchallenge;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import com.renzo.gchallenge.model.Contact;
import com.renzo.gchallenge.persistence.FilePersistenceService;
import com.renzo.gchallenge.persistence.PersistenceException;
import com.renzo.gchallenge.persistence.PersistenceService;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static Collection<Contact> contacts;

    @BeforeClass
    public static void init() throws PersistenceException {
        PersistenceService persistenceService = new FilePersistenceService();
        contacts = persistenceService.getContacts();
    }

    @Test
    public void testGetMaleCount() {
        long count = App.getMaleCount(contacts);

        assertEquals(3, count);
    }

    @Test
    public void testGetOldestContact() {
        Contact contact = App.getOldestContact(contacts);

        assertEquals("Wes", contact.getFirstName());
        assertEquals("Jackson", contact.getLastName());
    }

    @Test
    public void testGetDaysBetweenDob() {
        long days = App.getDaysBetweenDob(contacts, "Bill", "Paul");

        assertEquals(2862, days);
    }
}
