package com.logicore;

import com.logicore.model.Customer;
import java.util.Scanner;

/** Δοκιμή από την κονσόλα: ζητά τα στοιχεία ενός πελάτη και τα ελέγχει. */
public final class CustomerDemo {

    private CustomerDemo() {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, "UTF-8");
        Customer c = new Customer();

        while (true) {
            try {
                c.setCustomerCode(ask(in, "Κωδικός πελάτη: "));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  Λάθος: " + e.getMessage());
            }
        }

        while (true) {
            try {
                c.setName(ask(in, "Επωνυμία: "));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  Λάθος: " + e.getMessage());
            }
        }

        while (true) {
            try {
                c.setVatNumber(ask(in, "ΑΦΜ (9 ψηφία): "));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  Λάθος: " + e.getMessage());
            }
        }

        c.setContactPhone(ask(in, "Τηλέφωνο (Enter για κενό): "));

        while (true) {
            try {
                c.setContactEmail(ask(in, "Email (Enter για κενό): "));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  Λάθος: " + e.getMessage());
            }
        }

        System.out.println();
        System.out.println("Καταχωρίστηκε ο πελάτης:");
        System.out.println("  Κωδικός:  " + c.getCustomerCode());
        System.out.println("  Επωνυμία: " + c.getName());
        System.out.println("  ΑΦΜ:      " + c.getVatNumber());
        System.out.println("  Τηλέφωνο: " + c.getContactPhone());
        System.out.println("  Email:    " + c.getContactEmail());
        System.out.println("  Ενεργός:  " + c.isActive());
    }

    // τυπώνει την ερώτηση και επιστρέφει ό,τι γράψει ο χρήστης
    private static String ask(Scanner in, String question) {
        System.out.print(question);
        return in.nextLine();
    }
}
