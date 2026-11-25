import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Καταχώρηση δεδομένων Logicore 3PL WMS ===");

        System.out.println("\nΕργαζόμενος:");
        LEmployee employee = new LEmployee(
                readInt(scanner, "emp_id"),
                readString(scanner, "emp_code"),
                readNullableString(scanner, "emp_name"));

        System.out.println("\nΑποθέτης:");
        LDepositor depositor = new LDepositor(
                readInt(scanner, "dpt_id"),
                readString(scanner, "dpt_code"));

        System.out.println("\nΘέση αποθήκης:");
        LLocation location = new LLocation(
                readInt(scanner, "loc_id"),
                readString(scanner, "loc_code"),
                readLocationPackType(scanner, "loc_Pack_Type"),
                readLocationType(scanner, "loc_type"));

        System.out.println("\nΕίδος:");
        LItem item = new LItem(
                readInt(scanner, "item_id"),
                readString(scanner, "item_SSCC"),
                readItemPackType(scanner, "item_PackType_Code"),
                location.getLocId(),
                depositor.getDptId());

        System.out.println("\nΦορτηγό:");
        LTruck truck = new LTruck(
                readInt(scanner, "trk_Id"),
                readString(scanner, "trk_code"),
                readString(scanner, "trk_area"));

        System.out.println("\nΚατάσταση παραγγελίας:");
        LOrderStatus orderStatus = new LOrderStatus(
                readInt(scanner, "O_Status_ID"),
                readString(scanner, "O_Status_Code"));

        System.out.println("\nΠαραγγελία:");
        LOrder order = new LOrder(
                readInt(scanner, "ord_id"),
                readString(scanner, "ord_code"),
                readDate(scanner, "ord_output_date"),
                readPositiveInt(scanner, "ord_quantity"),
                orderStatus.getOStatusId(),
                item.getItemId(),
                truck.getTrkId(),
                location.getLocId(),
                employee.getEmpId());

        System.out.println("\nΚατάσταση παραλαβής:");
        LReceiptStatus receiptStatus = new LReceiptStatus(
                readInt(scanner, "R_Status_ID"),
                readString(scanner, "R_Status_Code"));

        System.out.println("\nΠαραλαβή:");
        LReceipt receipt = new LReceipt(
                readInt(scanner, "rct_id"),
                readString(scanner, "rct_code"),
                readDate(scanner, "rct_input_date"),
                readPositiveInt(scanner, "rct_quantity"),
                receiptStatus.getRStatusId(),
                item.getItemId(),
                truck.getTrkId(),
                location.getLocId(),
                employee.getEmpId());

        System.out.println("\n=== Τα δεδομένα καταχωρήθηκαν ===");
        System.out.println(employee);
        System.out.println(depositor);
        System.out.println(location);
        System.out.println(item);
        System.out.println(truck);
        System.out.println(orderStatus);
        System.out.println(order);
        System.out.println(receiptStatus);
        System.out.println(receipt);

        System.out.print("\nΟνομα χρήστη MySQL (ή Enter για root): ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            username = "root";
        }

        System.out.print("Κωδικός MySQL: ");
        String password = scanner.nextLine();

        try (LogicoreDatabase database = new LogicoreDatabase(
                "localhost", 3306, "logicore", username, password)) {
            database.save(employee, item, truck, location, depositor,
                    orderStatus, receiptStatus, order, receipt);
            System.out.println("Τα δεδομένα αποθηκεύτηκαν στη βάση logicore.");
        } catch (java.sql.SQLException exception) {
            System.out.println("Αποτυχία σύνδεσης ή αποθήκευσης: " + exception.getMessage());
        }

        scanner.close();
    }

    private static int readInt(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print(fieldName + ": ");
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            System.out.println("Παρακαλώ εισάγετε έναν ακέραιο αριθμό.");
            scanner.nextLine();
        }
    }

    private static int readPositiveInt(Scanner scanner, String fieldName) {
        while (true) {
            int value = readInt(scanner, fieldName);
            if (value > 0) {
                return value;
            }
            System.out.println("Η ποσότητα πρέπει να είναι θετική.");
        }
    }

    private static String readString(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print(fieldName + ": ");
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Το πεδίο δεν μπορεί να είναι κενό.");
        }
    }

    private static String readNullableString(Scanner scanner, String fieldName) {
        System.out.print(fieldName + " (κενό αν δεν υπάρχει): ");
        String value = scanner.nextLine().trim();
        return value.isEmpty() ? null : value;
    }

    private static String readItemPackType(Scanner scanner, String fieldName) {
        while (true) {
            String value = readString(scanner, fieldName + " (EUR/QU)").toUpperCase();
            if (value.equals("EUR") || value.equals("QU")) {
                return value;
            }
            System.out.println("Μη έγκυρο pack type. Επιτρέπονται μόνο οι τιμές EUR ή QU.");
        }
    }

    private static String readLocationPackType(Scanner scanner, String fieldName) {
        while (true) {
            String value = readString(scanner, fieldName + " (EUR/QU)").toUpperCase();
            if (value.equals("EUR") || value.equals("QU")) {
                return value;
            }
            System.out.println("Μη έγκυρο pack type. Επιτρέπονται μόνο οι τιμές EUR ή QU.");
        }
    }

    private static String readLocationType(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print(fieldName + " (ADR/SENS ή Enter για NULL): ");
            String value = scanner.nextLine().trim().toUpperCase();
            if (value.isEmpty()) {
                return null;
            }
            if (value.equals("ADR") || value.equals("SENS")) {
                return value;
            }
            System.out.println("Μη έγκυρο loc_type. Επιτρέπονται οι τιμές ADR, SENS ή κενό.");
        }
    }

    private static LocalDate readDate(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print(fieldName + " (YYYY-MM-DD): ");
            String value = scanner.nextLine().trim();
            try {
                return LocalDate.parse(value);
            } catch (DateTimeParseException exception) {
                System.out.println("Μη έγκυρη ημερομηνία.");
            }
        }
    }
}
