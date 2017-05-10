package com.renzo.gchallenge.persistence;

import java.util.Collection;

import com.renzo.gchallenge.model.Contact;

public interface PersistenceService {

    public Collection<Contact> getContacts() throws PersistenceException;
}
