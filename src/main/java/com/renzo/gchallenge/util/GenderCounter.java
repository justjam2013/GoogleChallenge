package com.renzo.gchallenge.util;

import java.util.Collection;
import java.util.function.Predicate;

import com.renzo.gchallenge.model.Contact;
import com.renzo.gchallenge.model.Gender;

public class GenderCounter {

    public GenderCounter() {
    }

    public long getFemaleCount(Collection<Contact> contacts) {
        long femaleCount = this.getCount(contacts, Gender.FEMALE);

        return femaleCount;
    }

    public long getMaleCount(Collection<Contact> contacts) {
        long maleCount = this.getCount(contacts, Gender.MALE);

        return maleCount;
    }

    private long getCount(Collection<Contact> contacts, Gender gender) {
        long count = 0;

        Predicate<Contact> predicate = (contact -> contact.getGender() == gender);
        count = contacts.stream().filter(predicate).count();

        return count;
    }
}
