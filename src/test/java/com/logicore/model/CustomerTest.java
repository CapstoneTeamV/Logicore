package com.logicore.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void newCustomerKeepsValuesAndIsActive() {
        Customer c = new Customer("HOMEMARKT", "Home Market", "094123456");

        assertEquals("HOMEMARKT", c.getCustomerCode());
        assertEquals("Home Market", c.getName());
        assertEquals("094123456", c.getVatNumber());
        assertNull(c.getCustomerId());
        assertTrue(c.isActive());
    }

    @Test
    void vatMustHaveNineDigits() {
        Customer c = new Customer("HOMEMARKT", "Home Market", "094123456");

        assertThrows(IllegalArgumentException.class, () -> c.setVatNumber("12345"));
        assertThrows(IllegalArgumentException.class, () -> c.setVatNumber("12345678A"));
        assertEquals("094123456", c.getVatNumber());   // η παλιά τιμή δεν άλλαξε
    }

    @Test
    void nameIsRequired() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("HOMEMARKT", "  ", "094123456"));
    }

    @Test
    void emptyPhoneBecomesNullAndBadEmailIsRejected() {
        Customer c = new Customer("HOMEMARKT", "Home Market", "094123456");

        c.setContactPhone("");
        assertNull(c.getContactPhone());

        assertThrows(IllegalArgumentException.class, () -> c.setContactEmail("abc"));
        c.setContactEmail("info@homemarket.gr");
        assertEquals("info@homemarket.gr", c.getContactEmail());
    }

    @Test
    void deactivateAndActivate() {
        Customer c = new Customer("HOMEMARKT", "Home Market", "094123456");

        c.deactivate();
        assertFalse(c.isActive());
        c.activate();
        assertTrue(c.isActive());
    }
}
