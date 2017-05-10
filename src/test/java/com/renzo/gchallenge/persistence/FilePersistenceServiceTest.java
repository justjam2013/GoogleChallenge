package com.renzo.gchallenge.persistence;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.renzo.gchallenge.model.Contact;

public class FilePersistenceServiceTest {

    private PersistenceService persistenceService;

    @Before
    public void init() {
        this.persistenceService = new FilePersistenceService();
    }

    @Test
    public void testReadFileStream() throws PersistenceException {
        Stream<String> stream = ((FilePersistenceService) this.persistenceService).readFileStream();
        long count = stream.count();

        assertEquals(5, count);
    }

    @Test
    public void testGetContacts() throws PersistenceException {
        Collection<Contact> contacts = this.persistenceService.getContacts();

        assertEquals(5, contacts.size());
    }

}
