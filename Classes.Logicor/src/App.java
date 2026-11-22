import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Καταχώρηση δεδομένων Logicore 3PL WMS ===");

        System.out.println("\nΚατάσταση παραγγελίας:");
        int orderStatusId = readInt(scanner, "O_Status_ID");
        String orderStatusCode = readString(scanner, "O_Status_Code");
        LOrderStatus orderStatus = new LOrderStatus(orderStatusId, orderStatusCode);

        System.out.println("\nΚατάσταση παραλαβής:");
        int receiptStatusId = readInt(scanner, "R_Status_ID");
        String receiptStatusCode = readString(scanner, "R_Status_Code");
        LReceiptStatus receiptStatus = new LReceiptStatus(receiptStatusId, receiptStatusCode);

        System.out.println("\nΕργαζόμενος:");
        int employeeId = readInt(scanner, "emp_id");
        String employeeCode = readString(scanner, "emp_code");
        String employeeName = readNullableString(scanner, "emp_name");
        int employeeOrderStatusId = readInt(scanner, "emp_ord_status_id");
        int employeeReceiptStatusId = readInt(scanner, "emp_rct_status_id");
        LEmployee employee = new LEmployee(
                employeeId, employeeCode, employeeName,
                employeeOrderStatusId, employeeReceiptStatusId);

        System.out.println("\nΠροϊόν:");
        int productId = readInt(scanner, "prd_ID");
        String productCode = readString(scanner, "prd_code");
        String sscc = readString(scanner, "Prd_SSCC");
        String packType = readString(scanner, "prd_PackType_Code (EU/ST)");
        LProduct product = new LProduct(productId, productCode, sscc, packType);

        System.out.println("\nΦορτηγό:");
        int truckId = readInt(scanner, "trk_Id");
        String truckCode = readString(scanner, "trk_code");
        String truckArea = readString(scanner, "trk_area");
        LTruck truck = new LTruck(truckId, truckCode, truckArea);

        System.out.println("\nΘέση αποθήκης:");
        int locationId = readInt(scanner, "loc_id");
        String locationCode = readString(scanner, "loc_code");
        String locationPackType = readString(scanner, "loc_Pack_Type (EU/ST)");
        LLocation location = new LLocation(locationId, locationCode, locationPackType);

        System.out.println("\nΠαραγγελία:");
        int orderId = readInt(scanner, "ord_id");
        String orderCode = readString(scanner, "ord_code");
        int orderStatusIdForOrder = readInt(scanner, "ord_status_id");
        int orderProductId = readInt(scanner, "ord_prd_id");
        int orderTruckId = readInt(scanner, "ord_truck_id");
        int orderLocationId = readInt(scanner, "ord_loc_id");
        int orderEmployeeId = readInt(scanner, "ord_emp_id");
        LOrder order = new LOrder(
                orderId, orderCode, orderStatusIdForOrder, orderProductId,
                orderTruckId, orderLocationId, orderEmployeeId);

        System.out.println("\nΠαραλαβή:");
        int receiptId = readInt(scanner, "rct_id");
        String receiptCode = readString(scanner, "rct_code");
        int receiptStatusIdForReceipt = readInt(scanner, "rct_status_Id");
        int receiptProductId = readInt(scanner, "rct_prd_id");
        int receiptTruckId = readInt(scanner, "rct_truck_id");
        int receiptLocationId = readInt(scanner, "rct_location_id");
        int receiptEmployeeId = readInt(scanner, "rct_emp_id");
        LReceipt receipt = new LReceipt(
                receiptId, receiptCode, receiptStatusIdForReceipt, receiptProductId,
                receiptTruckId, receiptLocationId, receiptEmployeeId);

        System.out.println("\nΑποθέτης:");
        int depositorId = readInt(scanner, "dpt_id");
        String depositorCode = readString(scanner, "dpt_code");
        Integer depositorOrderId = readNullableInt(scanner, "dpt_ord_id");
        Integer depositorReceiptId = readNullableInt(scanner, "dpt_rct_id");
        LDepositor depositor = new LDepositor(
                depositorId, depositorCode,
                depositorOrderId, depositorReceiptId);

        System.out.println("\n=== Τα δεδομένα καταχωρήθηκαν ===");
        System.out.println(orderStatus);
        System.out.println(receiptStatus);
        System.out.println(employee);
        System.out.println(product);
        System.out.println(truck);
        System.out.println(location);
        System.out.println(order);
        System.out.println(receipt);
        System.out.println(depositor);

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

    private static Integer readNullableInt(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print(fieldName + " (κενό αν δεν υπάρχει): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Παρακαλώ εισάγετε έναν ακέραιο αριθμό ή αφήστε το πεδίο κενό.");
            }
        }
    }

    private static String readString(Scanner scanner, String fieldName) {
        String value;
        do {
            System.out.print(fieldName + ": ");
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Το πεδίο δεν μπορεί να είναι κενό.");
            }
        } while (value.isEmpty());
        return value;
    }

    private static String readNullableString(Scanner scanner, String fieldName) {
        System.out.print(fieldName + " (κενό αν δεν υπάρχει): ");
        String value = scanner.nextLine().trim();
        return value.isEmpty() ? null : value;
    }
}
