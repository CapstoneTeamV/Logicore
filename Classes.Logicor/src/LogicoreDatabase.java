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

    public void save(
                     LEmployee employee,
                     LProduct product,
                     LTruck truck,
                     LLocation location,
                     LOrder order,
                     LReceipt receipt,
                     LDepositor depositor) throws SQLException {
        try {

            executeUpdate(
                    "INSERT INTO l_products (prd_ID, prd_code, Prd_SSCC, prd_PackType_Code) "
                            + "VALUES (?, ?, ?, ?)",
                    product.getPrdId(), product.getPrdCode(),
                    product.getPrdSscc(), product.getPrdPackTypeCode());

            executeUpdate(
                    "INSERT INTO l_truck (trk_Id, trk_code, trk_area) VALUES (?, ?, ?)",
                    truck.getTrkId(), truck.getTrkCode(), truck.getTrkArea());

            executeUpdate(
                    "INSERT INTO l_location (loc_id, loc_code, loc_Pack_Type) VALUES (?, ?, ?)",
                    location.getLocId(), location.getLocCode(), location.getLocPackType());

            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO l_employees "
                            + "(emp_id, emp_code, emp_name, emp_ord_status_id, emp_rct_status_id) "
                            + "VALUES (?, ?, ?, ?, ?)")) {
                statement.setInt(1, employee.getEmpId());
                statement.setString(2, employee.getEmpCode());
                setNullableString(statement, 3, employee.getEmpName());
                setNullableInt(statement, 4, employee.getEmpOrdStatusId());
                setNullableInt(statement, 5, employee.getEmpRctStatusId());
                statement.executeUpdate();
            }

            executeUpdate(
                    "INSERT INTO l_orders "
                            + "(ord_id, ord_code, ord_status_id, ord_prd_id, ord_truck_id, "
                            + "ord_loc_id, ord_emp_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    order.getOrdId(), order.getOrdCode(), order.getOrdStatusId(),
                    order.getOrdPrdId(), order.getOrdTruckId(),
                    order.getOrdLocId(), order.getOrdEmpId());

            executeUpdate(
                    "INSERT INTO l_receipts "
                            + "(rct_id, rct_code, rct_status_Id, rct_prd_id, rct_truck_id, "
                            + "rct_location_id, rct_emp_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    receipt.getRctId(), receipt.getRctCode(), receipt.getRctStatusId(),
                    receipt.getRctPrdId(), receipt.getRctTruckId(),
                    receipt.getRctLocationId(), receipt.getRctEmpId());

            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO l_depositors "
                            + "(dpt_id, dpt_code, dpt_ord_id, dpt_rct_id) "
                            + "VALUES (?, ?, ?, ?)")) {
                statement.setInt(1, depositor.getDptId());
                statement.setString(2, depositor.getDptCode());
                setNullableInt(statement, 3, depositor.getDptOrdId());
                setNullableInt(statement, 4, depositor.getDptRctId());
                statement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        }
    }

    private void executeUpdate(String sql, Object... values) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
        }
    }

    private void setNullableString(PreparedStatement statement, int index, String value)
            throws SQLException {
        if (value == null) {
            statement.setNull(index, Types.VARCHAR);
        } else {
            statement.setString(index, value);
        }
    }

    private void setNullableInt(PreparedStatement statement, int index, Integer value)
            throws SQLException {
        if (value == null) {
            statement.setNull(index, Types.INTEGER);
        } else {
            statement.setInt(index, value);
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
