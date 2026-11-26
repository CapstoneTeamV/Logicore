import java.time.LocalDate;
import java.util.Scanner;
 
public class Pallet {
 
    // ---------------------------------------------------------
    // ATTRIBUTES (ΠΕΔΙΑ / ΧΑΡΑΚΤΗΡΙΣΤΙΚΑ ΤΗΣ ΠΑΛΕΤΑΣ)
    // Κάθε αντικείμενο Pallet θα έχει τις παρακάτω πληροφορίες.
    // ---------------------------------------------------------
 
    // Μοναδικός κωδικός αναγνώρισης της παλέτας.
    // Με αυτόν τον κωδικό το σύστημα ξεχωρίζει την παλέτα
    // από όλες τις υπόλοιπες παλέτες.
    private String palletId;
 
    // Κωδικός του πελάτη που σχετίζεται με την παλέτα.
    // Χρησιμοποιείται για να γνωρίζουμε σε ποιον πελάτη ανήκει.
    private String customerCode;
 
    // Τύπος παλέτας.
    // Η τιμή θα είναι "EURO_PALLET" ή "HALF_EURO_PALLET".
    // Η τιμή προκύπτει από τη μέθοδο selectPalletType().
    private String palletType;
 
    // Κατάσταση παλέτας.
    // Ενδεικτικές τιμές: "AVAILABLE", "ON_HOLD", "DAMAGED".
    private String status;
 
    // Ημερομηνία εισαγωγής της παλέτας στην αποθήκη.
    // Το LocalDate αποθηκεύει ημερομηνία χωρίς ώρα.
    private LocalDate entryDate;
 
    // Ημερομηνία εξόδου της παλέτας από την αποθήκη.
    // Μπορεί να είναι null αν η παλέτα δεν έχει εξέλθει ακόμη.
    private LocalDate exitDate;
 
 
    // ---------------------------------------------------------
    // CONSTRUCTOR (ΚΑΤΑΣΚΕΥΑΣΤΗΣ)
    // Δημιουργεί ένα νέο αντικείμενο Pallet και αρχικοποιεί
    // τα βασικά χαρακτηριστικά του με τις τιμές που δίνονται.
    // ---------------------------------------------------------
    public Pallet(String palletId, String customerCode,
                  String palletType, String status,
                  LocalDate entryDate, LocalDate exitDate) {
 
        // this.palletId αναφέρεται στο attribute του αντικειμένου.
        // palletId είναι η τιμή που δίνεται ως παράμετρος.
        this.palletId = palletId;
 
        // Αποθηκεύουμε τον κωδικό του πελάτη.
        this.customerCode = customerCode;
 
        // Αποθηκεύουμε τον τύπο παλέτας που επέλεξε ο χρήστης.
        this.palletType = palletType;
 
        // Αποθηκεύουμε την αρχική κατάσταση της παλέτας.
        this.status = status;
 
        // Αποθηκεύουμε την ημερομηνία εισαγωγής.
        this.entryDate = entryDate;
 
        // Αποθηκεύουμε την ημερομηνία εξόδου.
        this.exitDate = exitDate;
    }
 
 
    // ---------------------------------------------------------
    // METHOD: selectPalletType
    // Εμφανίζει τις διαθέσιμες επιλογές τύπου παλέτας.
    // Ελέγχει την απάντηση και επιστρέφει την αντίστοιχη τιμή.
    // ---------------------------------------------------------
    public static String selectPalletType(Scanner scanner) {
 
        // Μεταβλητή στην οποία αποθηκεύεται προσωρινά
        // η απάντηση που πληκτρολογεί ο χρήστης.
        String choice;
 
        // Η while επαναλαμβάνει τη διαδικασία συνεχώς.
        // Θα σταματήσει όταν δοθεί σωστή επιλογή και εκτελεστεί return.
        while (true) {
 
            // Εμφανίζουμε τις δύο διαθέσιμες επιλογές.
            System.out.println("\\nΕπιλέξτε τον τύπο της παλέτας:");
 
            // Επιλογή Α: Ευρωπαλέτα με διαστάσεις 120 x 80 cm.
            System.out.println("Α. Ευρωπαλέτα (Euro Pallet)");
            System.out.println("   Διαστάσεις: 120 x 80 cm");
 
            // Επιλογή Β: Half Pallet με διαστάσεις 60 x 80 cm.
            System.out.println("Β. Half Pallet");
            System.out.println("   Διαστάσεις: 60 x 80 cm");
 
            // Ζητάμε από τον χρήστη να πληκτρολογήσει Α ή Β.
            System.out.print("Δώστε την επιλογή σας (Α ή Β): ");
 
            // nextLine() διαβάζει ολόκληρη τη γραμμή που πληκτρολογήθηκε.
            // trim() αφαιρεί τυχόν κενά πριν και μετά από την απάντηση.
            choice = scanner.nextLine().trim();
 
            // equalsIgnoreCase() συγκρίνει κείμενα χωρίς να
            // ξεχωρίζει κεφαλαία από πεζά γράμματα.
            // Δεχόμαστε και ελληνικό Α και λατινικό A.
            if (choice.equalsIgnoreCase("Α") ||
                choice.equalsIgnoreCase("A")) {
 
                // Ενημερώνουμε τον χρήστη για την επιλογή του.
                System.out.println("Επιλέξατε Ευρωπαλέτα.");
 
                // Επιστρέφουμε την εσωτερική τιμή που θα αποθηκευτεί.
                // Το return τερματίζει αμέσως τη μέθοδο.
                return "EURO_PALLET";
            }
 
            // Ελέγχουμε αν ο χρήστης επέλεξε ελληνικό Β ή λατινικό B.
            else if (choice.equalsIgnoreCase("Β") ||
                     choice.equalsIgnoreCase("B")) {
 
                // Ενημερώνουμε τον χρήστη για την επιλογή του.
                System.out.println("Επιλέξατε Half Pallet.");
 
                // Επιστρέφουμε την εσωτερική τιμή για τη μισή ευρωπαλέτα.
                return "HALF_EURO_PALLET";
            }
 
            // Αν δεν ισχύει καμία από τις δύο προηγούμενες περιπτώσεις,
            // η απάντηση δεν είναι αποδεκτή.
            else {
 
                // Εμφανίζουμε μήνυμα λάθους.
                System.out.println("Μη έγκυρη επιλογή!");
 
                // Ζητάμε από τον χρήστη να δώσει ξανά σωστή τιμή.
                // Η while θα επαναλάβει την εμφάνιση των επιλογών.
                System.out.println("Παρακαλώ δώστε σωστή τιμή Α ή Β.");
            }
        }
    }
 
 
    // ---------------------------------------------------------
    // MAIN METHOD
    // Είναι το σημείο από το οποίο ξεκινά η εκτέλεση του
    // συγκεκριμένου παραδείγματος.
    // ---------------------------------------------------------
    public static void main(String[] args) {
 
        // Δημιουργούμε Scanner για να διαβάζουμε δεδομένα
        // από το πληκτρολόγιο (System.in).
        Scanner scanner = new Scanner(System.in);
 
        // Καλούμε τη μέθοδο επιλογής τύπου παλέτας.
        // Η τιμή που επιστρέφει αποθηκεύεται στη μεταβλητή selectedType.
        String selectedType = selectPalletType(scanner);
 
        // Εμφανίζουμε την τελική εσωτερική τιμή που επιλέχθηκε.
        System.out.println("Ο τύπος παλέτας που καταχωρήθηκε είναι: "
                           + selectedType);
 
        // Κλείνουμε τον Scanner όταν ολοκληρωθεί η χρήση του.
        scanner.close();
    }
 
 
    // ---------------------------------------------------------
    // GETTERS AND SETTERS
    // Οι μέθοδοι αυτές επιτρέπουν την ελεγχόμενη ανάγνωση
    // και τροποποίηση των private attributes.
    // ---------------------------------------------------------
 
    // Επιστρέφει τον μοναδικό κωδικό της παλέτας.
    public String getPalletId() {
        return palletId;
    }
 
    // Αλλάζει τον κωδικό της παλέτας.
    public void setPalletId(String palletId) {
        this.palletId = palletId;
    }
 
    // Επιστρέφει τον κωδικό πελάτη.
    public String getCustomerCode() {
        return customerCode;
    }
 
    // Αλλάζει τον κωδικό πελάτη.
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
 
    // Επιστρέφει τον τύπο παλέτας.
    public String getPalletType() {
        return palletType;
    }
 
    // Αλλάζει τον τύπο παλέτας.
    public void setPalletType(String palletType) {
        this.palletType = palletType;
    }
 
    // Επιστρέφει την κατάσταση της παλέτας.
    public String getStatus() {
        return status;
    }
 
    // Αλλάζει την κατάσταση της παλέτας.
    public void setStatus(String status) {
        this.status = status;
    }
 
    // Επιστρέφει την ημερομηνία εισαγωγής.
    public LocalDate getEntryDate() {
        return entryDate;
    }
 
    // Αλλάζει την ημερομηνία εισαγωγής.
    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
 
    // Επιστρέφει την ημερομηνία εξόδου.
    public LocalDate getExitDate() {
        return exitDate;
    }
 
    // Αλλάζει την ημερομηνία εξόδου.
    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }
}
