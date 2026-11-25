package com.logicore.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {

    private User newUser() {
        return new User("maria", "Μαρία Παπαδοπούλου", "1234", User.Role.CLERK);
    }

    @Test
    void newUserKeepsValuesAndIsActive() {
        User u = newUser();

        assertEquals("maria", u.getUsername());
        assertEquals(User.Role.CLERK, u.getRole());
        assertTrue(u.isActive());
    }

    @Test
    void roleIsRequired() {
        User u = newUser();

        assertThrows(IllegalArgumentException.class, () -> u.setRole(null));
        assertEquals(User.Role.CLERK, u.getRole());
    }

    @Test
    void usernameIsRequired() {
        assertThrows(IllegalArgumentException.class,
                () -> new User(" ", "Μαρία", "1234", User.Role.CLERK));
    }

    @Test
    void checkPasswordAcceptsOnlyTheRightPassword() {
        User u = newUser();

        assertTrue(u.checkPassword("1234"));
        assertFalse(u.checkPassword("12345"));
        assertFalse(u.checkPassword(null));
    }

    @Test
    void setPasswordChangesThePassword() {
        User u = newUser();

        u.setPassword("nea-lexi");
        assertTrue(u.checkPassword("nea-lexi"));
        assertFalse(u.checkPassword("1234"));
    }

    @Test
    void emptyPasswordIsRejected() {
        User u = newUser();

        assertThrows(IllegalArgumentException.class, () -> u.setPassword(""));
    }
}
