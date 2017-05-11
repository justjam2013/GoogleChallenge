package com.renzo.gchallenge;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.renzo.gchallenge.model.Contact;
import com.renzo.gchallenge.persistence.FilePersistenceService;
import com.renzo.gchallenge.persistence.PersistenceException;
import com.renzo.gchallenge.persistence.PersistenceService;
import com.renzo.gchallenge.util.DobComparator;
import com.renzo.gchallenge.util.GenderCounter;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        try {
            PersistenceService persistenceService = new FilePersistenceService();
            Collection<Contact> contacts = persistenceService.getContacts();

            System.out.println("\n************************************************************\n");
            System.out.println("\t\tGoogle Challenge\n");

            System.out.println("\tQ1. How many males are in the address book?");
            long maleCount = getMaleCount(contacts);
            System.out.println("\tMale count: " + maleCount);

            System.out.println();

            System.out.println("\tQ2. Who is the oldest person in the address book?");
            Contact oldestContact = getOldestContact(contacts);
            System.out.println("\tOldest contact: " + oldestContact.getFirstName() + " " + oldestContact.getLastName());

            System.out.println();

            System.out.println("\tQ3. How many days older is Bill than Paul?");
            long daysBetween = getDaysBetweenDob(contacts, "Bill", "Paul");
            System.out.println("\tDays Bill is older than Paul: " + daysBetween);

            System.out.println("\n\n************************************************************\n");
        }
        catch (PersistenceException pe) {
            System.out.println("An error occurred: ");
            pe.printStackTrace();
        }
    }

    protected static long getMaleCount(Collection<Contact> contacts) {
        GenderCounter genderCounter = new GenderCounter();
        long count = genderCounter.getMaleCount(contacts);

        return count;
    }

    protected static Contact getOldestContact(Collection<Contact> contacts) {
        List<Contact> sortedContacts = new ArrayList<Contact>(contacts);
        Collections.sort(sortedContacts, new DobComparator());
        Contact oldestContact = sortedContacts.get(0);

        return oldestContact;
    }

    protected static long getDaysBetweenDob(Collection<Contact> contacts, String firstName1, String firstName2) {
        Contact first = findContactByName(contacts, firstName1);
        Contact second = findContactByName(contacts, firstName2);

        long daysBetween = ChronoUnit.DAYS.between(first.getDob(), second.getDob());

        return daysBetween;
    }

    private static Contact findContactByName(Collection<Contact> contacts, String firstName) {
        Predicate<Contact> predicate = (contact -> contact.getFirstName().equals(firstName));
        Optional<Contact> firstMatch = contacts.stream().filter(predicate).findFirst();

        return firstMatch.get();
    }
}
