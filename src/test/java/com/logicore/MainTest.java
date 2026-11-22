package com.logicore;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/** Πρώτο test: ελέγχει ότι η εφαρμογή ξεκινά. Πρότυπο για τα επόμενα tests. */
class MainTest {

    @Test
    void mainRunsWithoutErrors() {
        assertDoesNotThrow(() -> Main.main(new String[0]));
    }
}
