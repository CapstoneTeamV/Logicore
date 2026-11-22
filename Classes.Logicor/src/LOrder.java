public class LOrder {
    private int ordId;
    private String ordCode;
    private int ordStatusId;
    private int ordPrdId;
    private int ordTruckId;
    private int ordLocId;
    private int ordEmpId;

    public LOrder() {
    }

    public LOrder(int ordId, String ordCode, int ordStatusId, int ordPrdId,
                  int ordTruckId, int ordLocId, int ordEmpId) {
        this.ordId = ordId;
        this.ordCode = ordCode;
        this.ordStatusId = ordStatusId;
        this.ordPrdId = ordPrdId;
        this.ordTruckId = ordTruckId;
        this.ordLocId = ordLocId;
        this.ordEmpId = ordEmpId;
    }

    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    public String getOrdCode() {
        return ordCode;
    }

    public void setOrdCode(String ordCode) {
        this.ordCode = ordCode;
    }

    public int getOrdStatusId() {
        return ordStatusId;
    }

    public void setOrdStatusId(int ordStatusId) {
        this.ordStatusId = ordStatusId;
    }

    public int getOrdPrdId() {
        return ordPrdId;
    }

    public void setOrdPrdId(int ordPrdId) {
        this.ordPrdId = ordPrdId;
    }

    public int getOrdTruckId() {
        return ordTruckId;
    }

    public void setOrdTruckId(int ordTruckId) {
        this.ordTruckId = ordTruckId;
    }

    public int getOrdLocId() {
        return ordLocId;
    }

    public void setOrdLocId(int ordLocId) {
        this.ordLocId = ordLocId;
    }

    public int getOrdEmpId() {
        return ordEmpId;
    }

    public void setOrdEmpId(int ordEmpId) {
        this.ordEmpId = ordEmpId;
    }

    @Override
    public String toString() {
        return "LOrder{ordId=" + ordId
                + ", ordCode='" + ordCode + "'"
                + ", ordStatusId=" + ordStatusId
                + ", ordPrdId=" + ordPrdId
                + ", ordTruckId=" + ordTruckId
                + ", ordLocId=" + ordLocId
                + ", ordEmpId=" + ordEmpId + '}';
    }
}
