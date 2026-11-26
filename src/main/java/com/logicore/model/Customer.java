package com.logicore.model;

/**
 * Πελάτης (αποθέτης) της αποθήκης, αντιστοιχεί στον πίνακα customer.
 * Οι έλεγχοι στους setters ακολουθούν τους περιορισμούς της βάσης.
 */
public class Customer {

    private Integer customerId;     // null μέχρι να αποθηκευτεί
    private String  customerCode;   // π.χ. HOMEMARKT
    private String  name;           // επωνυμία
    private String  vatNumber;      // ΑΦΜ, String για να κρατάει τα αρχικά 0
    private String  contactPhone;
    private String  contactEmail;
    private boolean active = true;

    // για τον DAO, που γεμίζει τα πεδία από το ResultSet
    public Customer() {
    }

    // μόνο τα υποχρεωτικά πεδία, τα υπόλοιπα με setters
    public Customer(String customerCode, String name, String vatNumber) {
        this.customerCode = requireText(customerCode, "Κωδικός πελάτη", 20);
        this.name = requireText(name, "Επωνυμία", 150);
        this.vatNumber = checkVat(vatNumber);
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = requireText(customerCode, "Κωδικός πελάτη", 20);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireText(name, "Επωνυμία", 150);
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = checkVat(vatNumber);
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = optionalText(contactPhone, "Τηλέφωνο", 20);
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        String email = optionalText(contactEmail, "Email", 100);
        if (email != null && !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Μη έγκυρο email: " + email);
        }
        this.contactEmail = email;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    // --- βοηθητικοί έλεγχοι ---

    private static String requireText(String value, String field, int maxLen) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + ": είναι υποχρεωτικό");
        }
        String v = value.trim();
        if (v.length() > maxLen) {
            throw new IllegalArgumentException(field + ": έως " + maxLen + " χαρακτήρες");
        }
        return v;
    }

    // κενό πεδίο αποθηκεύεται ως null, όπως στη βάση
    private static String optionalText(String value, String field, int maxLen) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return requireText(value, field, maxLen);
    }

    // ίδιος κανόνας με το CHECK του vat_number
    private static String checkVat(String vat) {
        String v = requireText(vat, "ΑΦΜ", 9);
        if (!v.matches("[0-9]{9}")) {
            throw new IllegalArgumentException("ΑΦΜ: πρέπει να έχει ακριβώς 9 ψηφία");
        }
        return v;
    }

}
