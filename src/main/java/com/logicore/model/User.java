package com.logicore.model;

/**
 * Χρήστης της εφαρμογής, αντιστοιχεί στον πίνακα app_user.
 */
public class User {

    /** Ρόλος χρήστη, ίδιες τιμές με το CHECK της στήλης app_user.role (Τ23). */
    public enum Role {
        CLERK("Υπάλληλος καταχώρισης"),
        WAREHOUSE("Αποθηκάριος"),
        ADMIN("Διαχειριστής"),
        MANAGER("Διευθυντής");

        private final String label;   // ό,τι δείχνουμε στις οθόνες

        Role(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    private Integer userId;         // null μέχρι να αποθηκευτεί
    private String  username;       // για τη σύνδεση, μοναδικό
    private String  fullName;
    private String  password;       // απλό κείμενο προς το παρόν (στήλη password_hash)
    private Role    role;
    private boolean active = true;

    public User() {
    }

    // μόνο τα υποχρεωτικά πεδία, τα υπόλοιπα με setters
    public User(String username, String fullName, String password, Role role) {
        this.username = requireText(username, "Όνομα χρήστη", 50);
        this.fullName = requireText(fullName, "Ονοματεπώνυμο", 100);
        this.password = requireText(password, "Κωδικός", 255);
        this.role = requireRole(role);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = requireText(username, "Όνομα χρήστη", 50);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = requireText(fullName, "Ονοματεπώνυμο", 100);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = requireText(password, "Κωδικός", 255);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = requireRole(role);
    }

    /** Στη σύνδεση: true αν ο κωδικός που έγραψε ο χρήστης ταιριάζει. */
    public boolean checkPassword(String typedPassword) {
        return password != null && password.equals(typedPassword);
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

    private static Role requireRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Ο χρήστης πρέπει να έχει ρόλο");
        }
        return role;
    }
}
