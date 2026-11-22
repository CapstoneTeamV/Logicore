# LogiCore 3PL

Σύστημα διαχείρισης παλετών και αποστολών για εταιρεία 3PL (Capstone Project, Ομάδα 5).
Υποδοχή παλέτας, ενοποίηση παραγγελιών ανά ΤΚ, επιλογή οχήματος, φύλλο οδηγιών και παλετοημέρες.

## Τι χρειάζεσαι
- Java 17 ή νεότερη (JDK)
- Maven 3.8+ (ή το ενσωματωμένο Maven του IntelliJ / Eclipse)
- MySQL 8+ (σχήμα στο `logicore_db.sql`)

## Πώς τρέχει
```bash
mvn verify          # compile + Checkstyle + tests + SpotBugs + coverage
mvn exec:java       # τρέχει το com.logicore.Main
```
Στο IntelliJ: File → Open → διαλέγεις τον φάκελο. Το αναγνωρίζει αυτόματα ως Maven project.

Για τη βάση: αντιγράφεις το `src/main/resources/db.properties.example` σε `db.properties`
με τους δικούς σου κωδικούς. Το `db.properties` δεν ανεβαίνει στο Git.

## Δομή
```
src/main/java/com/logicore/
├── model/     Οντότητες και enums
├── dao/       Πρόσβαση στη βάση με JDBC
├── service/   Επιχειρησιακοί κανόνες
└── Main.java  Σημείο εκκίνησης
src/main/resources/   Ρυθμίσεις (db.properties.example)
src/test/java/        JUnit 5 tests (ίδια packages με τον κώδικα)
config/               Κανόνες Checkstyle και SpotBugs
.github/              CI (GitHub Actions) και πρότυπο Pull Request
docs/                 Έγγραφα του project (Case Study, σχήμα βάσης)
logicore_db.sql       Σχήμα της βάσης (MySQL)
```

Οι πρώτες κλάσεις του Σταμάτη (`Classes.Logicor`) φυλάσσονται στο branch
`archive/stamatis-classes-22-11` και θα μπουν στα packages όταν ξεκινήσει ο κώδικας.

## Κανόνες ομάδας
1. Κανείς δεν κάνει push απευθείας στο `main`.
2. Κάθε εργασία έχει branch με τον κωδικό Jira: `feature/LC-12-pallet-service`, `fix/LC-30-null-vehicle`.
3. Commit με τον κωδικό Jira μπροστά: `LC-12 Προσθήκη ελέγχου διπλής παλέτας`.
4. Pull Request προς `main`, με 1 έγκριση από άλλο μέλος και πράσινο build.
5. Πριν το PR τρέχεις `mvn verify` τοπικά.
6. Κωδικοί και αρχεία IDE δεν ανεβαίνουν ποτέ.
