import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LogicoreDatabase implements AutoCloseable {
    private final Connection connection;

    public LogicoreDatabase(String host, int port, String database,
                            String username, String password) throws SQLException {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database
                + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
    }

    public void save(LEmployee employee, LItem item, LTruck truck,
                     LLocation location, LDepositor depositor,
                     LOrderStatus orderStatus, LReceiptStatus receiptStatus,
                     LOrder order, LReceipt receipt) throws SQLException {
        try {
            executeUpdate(
                    "INSERT INTO l_orderstatus (O_Status_ID, O_Status_Code) VALUES (?, ?)",
                    orderStatus.getOStatusId(), orderStatus.getOStatusCode());

            executeUpdate(
                    "INSERT INTO l_receiptstatus (R_Status_ID, R_Status_Code) VALUES (?, ?)",
                    receiptStatus.getRStatusId(), receiptStatus.getRStatusCode());

            executeUpdate(
                    "INSERT INTO l_location (loc_id, loc_code, loc_Pack_Type, loc_type) "
                            + "VALUES (?, ?, ?, ?)",
                    location.getLocId(), location.getLocCode(),
                    location.getLocPackType(), location.getLocType());

            executeUpdate(
                    "INSERT INTO l_depositors (dpt_id, dpt_code) VALUES (?, ?)",
                    depositor.getDptId(), depositor.getDptCode());

            executeUpdate(
                    "INSERT INTO l_employees (emp_id, emp_code, emp_name) VALUES (?, ?, ?)",
                    employee.getEmpId(), employee.getEmpCode(), employee.getEmpName());

            executeUpdate(
                    "INSERT INTO l_truck (trk_Id, trk_code, trk_area) VALUES (?, ?, ?)",
                    truck.getTrkId(), truck.getTrkCode(), truck.getTrkArea());

            executeUpdate(
                    "INSERT INTO l_items "
                            + "(item_id, item_SSCC, item_PackType_Code, item_loc_id, item_dpt_id) "
                            + "VALUES (?, ?, ?, ?, ?)",
                    item.getItemId(), item.getItemSscc(),
                    item.getItemPackTypeCode(), item.getItemLocId(), item.getItemDptId());

            executeUpdate(
                    "INSERT INTO l_orders "
                            + "(ord_id, ord_code, ord_output_date, ord_quantity, ord_status_id, "
                            + "ord_Item_id, ord_truck_id, ord_loc_id, ord_emp_id) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    order.getOrdId(), order.getOrdCode(), order.getOrdOutputDate(),
                    order.getOrdQuantity(), order.getOrdStatusId(), order.getOrdItemId(),
                    order.getOrdTruckId(), order.getOrdLocId(), order.getOrdEmpId());

            executeUpdate(
                    "INSERT INTO l_receipts "
                            + "(rct_id, rct_code, rct_input_date, rct_quantity, rct_status_Id, "
                            + "rct_Item_id, rct_truck_id, rct_loc_id, rct_emp_id) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    receipt.getRctId(), receipt.getRctCode(), receipt.getRctInputDate(),
                    receipt.getRctQuantity(), receipt.getRctStatusId(), receipt.getRctItemId(),
                    receipt.getRctTruckId(), receipt.getRctLocId(), receipt.getRctEmpId());

            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        }
    }

    private void executeUpdate(String sql, Object... values) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == null) {
                    statement.setNull(i + 1, Types.NULL);
                } else {
                    statement.setObject(i + 1, values[i]);
                }
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
