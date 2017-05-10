package com.renzo.gchallenge.util;

import java.util.Comparator;

import com.renzo.gchallenge.model.Contact;

public class DobComparator implements Comparator<Contact> {

    public DobComparator() {
    }

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getDob().compareTo(o2.getDob());
    }
}
