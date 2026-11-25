import java.time.LocalDate;

public class LReceipt {
    private int rctId;
    private String rctCode;
    private LocalDate rctInputDate;
    private int rctQuantity;
    private int rctStatusId;
    private int rctItemId;
    private int rctTruckId;
    private int rctLocId;
    private int rctEmpId;

    public LReceipt() {
    }

    public LReceipt(int rctId, String rctCode, LocalDate rctInputDate, int rctQuantity,
                    int rctStatusId, int rctItemId, int rctTruckId, int rctLocId,
                    int rctEmpId) {
        this.rctId = rctId;
        this.rctCode = rctCode;
        this.rctInputDate = rctInputDate;
        this.rctQuantity = rctQuantity;
        this.rctStatusId = rctStatusId;
        this.rctItemId = rctItemId;
        this.rctTruckId = rctTruckId;
        this.rctLocId = rctLocId;
        this.rctEmpId = rctEmpId;
    }

    public int getRctId() { return rctId; }
    public void setRctId(int rctId) { this.rctId = rctId; }
    public String getRctCode() { return rctCode; }
    public void setRctCode(String rctCode) { this.rctCode = rctCode; }
    public LocalDate getRctInputDate() { return rctInputDate; }
    public void setRctInputDate(LocalDate rctInputDate) { this.rctInputDate = rctInputDate; }
    public int getRctQuantity() { return rctQuantity; }
    public void setRctQuantity(int rctQuantity) { this.rctQuantity = rctQuantity; }
    public int getRctStatusId() { return rctStatusId; }
    public void setRctStatusId(int rctStatusId) { this.rctStatusId = rctStatusId; }
    public int getRctItemId() { return rctItemId; }
    public void setRctItemId(int rctItemId) { this.rctItemId = rctItemId; }
    public int getRctTruckId() { return rctTruckId; }
    public void setRctTruckId(int rctTruckId) { this.rctTruckId = rctTruckId; }
    public int getRctLocId() { return rctLocId; }
    public void setRctLocId(int rctLocId) { this.rctLocId = rctLocId; }
    public int getRctEmpId() { return rctEmpId; }
    public void setRctEmpId(int rctEmpId) { this.rctEmpId = rctEmpId; }

    @Override
    public String toString() {
        return "LReceipt{rctId=" + rctId
                + ", rctCode='" + rctCode + "'"
                + ", rctInputDate=" + rctInputDate
                + ", rctQuantity=" + rctQuantity
                + ", rctStatusId=" + rctStatusId
                + ", rctItemId=" + rctItemId
                + ", rctTruckId=" + rctTruckId
                + ", rctLocId=" + rctLocId
                + ", rctEmpId=" + rctEmpId + '}';
    }
}
