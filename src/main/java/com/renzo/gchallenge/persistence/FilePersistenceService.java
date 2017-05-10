package com.renzo.gchallenge.persistence;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import com.renzo.gchallenge.model.Contact;
import com.renzo.gchallenge.model.Gender;

public class FilePersistenceService implements PersistenceService {

    private static final String ADDRESS_BOOK = "AddressBook";

    private static final int FULL_NAME_FIELD = 0;
    private static final int GENDER_FIELD = 1;
    private static final int DOB_FIELD = 2;

    private static final int FIRST_NAME_FIELD = 0;
    private static final int LAST_NAME_FIELD = 1;

    private static final DateTimeFormatter dobFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    public FilePersistenceService() {
    }

    protected Stream<String> readFileStream() throws PersistenceException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL url = classLoader.getResource(ADDRESS_BOOK);
        Stream<String> stream = null;

        try {
            stream = Files.lines(Paths.get(url.toURI()), Charset.defaultCharset());
        }
        catch (IOException | URISyntaxException ex) {
            throw new PersistenceException(ex);
        }

        return stream;
    }

    @Override
    public Collection<Contact> getContacts() throws PersistenceException {
        Collection<Contact> contacts = new ArrayList<Contact>();

        Stream<String> stream = this.readFileStream();
        stream.forEach(line -> this.populateContact(line, contacts));

        return contacts;
    }

    private void populateContact(String line, final Collection<Contact> contacts) {
        String[] fields = line.split(",");
        String[] names = fields[FULL_NAME_FIELD].split(" ");

        Gender gender = Gender.valueOf(fields[GENDER_FIELD].trim().toUpperCase());
        LocalDate dob = LocalDate.parse(fields[DOB_FIELD].trim(), dobFormatter);

        Contact contact = new Contact(names[FIRST_NAME_FIELD], names[LAST_NAME_FIELD], gender, dob);
        contacts.add(contact);

        return;
    }
}
