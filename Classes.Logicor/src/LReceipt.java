public class LReceipt {
    private int rctId;
    private String rctCode;
    private int rctStatusId;
    private int rctPrdId;
    private int rctTruckId;
    private int rctLocationId;
    private int rctEmpId;

    public LReceipt() {
    }

    public LReceipt(int rctId, String rctCode, int rctStatusId, int rctPrdId,
                    int rctTruckId, int rctLocationId, int rctEmpId) {
        this.rctId = rctId;
        this.rctCode = rctCode;
        this.rctStatusId = rctStatusId;
        this.rctPrdId = rctPrdId;
        this.rctTruckId = rctTruckId;
        this.rctLocationId = rctLocationId;
        this.rctEmpId = rctEmpId;
    }

    public int getRctId() {
        return rctId;
    }

    public void setRctId(int rctId) {
        this.rctId = rctId;
    }

    public String getRctCode() {
        return rctCode;
    }

    public void setRctCode(String rctCode) {
        this.rctCode = rctCode;
    }

    public int getRctStatusId() {
        return rctStatusId;
    }

    public void setRctStatusId(int rctStatusId) {
        this.rctStatusId = rctStatusId;
    }

    public int getRctPrdId() {
        return rctPrdId;
    }

    public void setRctPrdId(int rctPrdId) {
        this.rctPrdId = rctPrdId;
    }

    public int getRctTruckId() {
        return rctTruckId;
    }

    public void setRctTruckId(int rctTruckId) {
        this.rctTruckId = rctTruckId;
    }

    public int getRctLocationId() {
        return rctLocationId;
    }

    public void setRctLocationId(int rctLocationId) {
        this.rctLocationId = rctLocationId;
    }

    public int getRctEmpId() {
        return rctEmpId;
    }

    public void setRctEmpId(int rctEmpId) {
        this.rctEmpId = rctEmpId;
    }

    @Override
    public String toString() {
        return "LReceipt{rctId=" + rctId
                + ", rctCode='" + rctCode + "'"
                + ", rctStatusId=" + rctStatusId
                + ", rctPrdId=" + rctPrdId
                + ", rctTruckId=" + rctTruckId
                + ", rctLocationId=" + rctLocationId
                + ", rctEmpId=" + rctEmpId + '}';
    }
}
