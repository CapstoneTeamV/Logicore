import java.time.LocalDate;

public class LOrder {
    private int ordId;
    private String ordCode;
    private LocalDate ordOutputDate;
    private int ordQuantity;
    private int ordStatusId;
    private int ordItemId;
    private int ordTruckId;
    private int ordLocId;
    private int ordEmpId;

    public LOrder() {
    }

    public LOrder(int ordId, String ordCode, LocalDate ordOutputDate, int ordQuantity,
                  int ordStatusId, int ordItemId, int ordTruckId, int ordLocId,
                  int ordEmpId) {
        this.ordId = ordId;
        this.ordCode = ordCode;
        this.ordOutputDate = ordOutputDate;
        this.ordQuantity = ordQuantity;
        this.ordStatusId = ordStatusId;
        this.ordItemId = ordItemId;
        this.ordTruckId = ordTruckId;
        this.ordLocId = ordLocId;
        this.ordEmpId = ordEmpId;
    }

    public int getOrdId() { return ordId; }
    public void setOrdId(int ordId) { this.ordId = ordId; }
    public String getOrdCode() { return ordCode; }
    public void setOrdCode(String ordCode) { this.ordCode = ordCode; }
    public LocalDate getOrdOutputDate() { return ordOutputDate; }
    public void setOrdOutputDate(LocalDate ordOutputDate) { this.ordOutputDate = ordOutputDate; }
    public int getOrdQuantity() { return ordQuantity; }
    public void setOrdQuantity(int ordQuantity) { this.ordQuantity = ordQuantity; }
    public int getOrdStatusId() { return ordStatusId; }
    public void setOrdStatusId(int ordStatusId) { this.ordStatusId = ordStatusId; }
    public int getOrdItemId() { return ordItemId; }
    public void setOrdItemId(int ordItemId) { this.ordItemId = ordItemId; }
    public int getOrdTruckId() { return ordTruckId; }
    public void setOrdTruckId(int ordTruckId) { this.ordTruckId = ordTruckId; }
    public int getOrdLocId() { return ordLocId; }
    public void setOrdLocId(int ordLocId) { this.ordLocId = ordLocId; }
    public int getOrdEmpId() { return ordEmpId; }
    public void setOrdEmpId(int ordEmpId) { this.ordEmpId = ordEmpId; }

    @Override
    public String toString() {
        return "LOrder{ordId=" + ordId
                + ", ordCode='" + ordCode + "'"
                + ", ordOutputDate=" + ordOutputDate
                + ", ordQuantity=" + ordQuantity
                + ", ordStatusId=" + ordStatusId
                + ", ordItemId=" + ordItemId
                + ", ordTruckId=" + ordTruckId
                + ", ordLocId=" + ordLocId
                + ", ordEmpId=" + ordEmpId + '}';
    }
}
