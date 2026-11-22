public class LOrderStatus {
    private int oStatusId;
    private String oStatusCode;

    public LOrderStatus() {
    }

    public LOrderStatus(int oStatusId, String oStatusCode) {
        this.oStatusId = oStatusId;
        this.oStatusCode = oStatusCode;
    }

    public int getOStatusId() {
        return oStatusId;
    }

    public void setOStatusId(int oStatusId) {
        this.oStatusId = oStatusId;
    }

    public String getOStatusCode() {
        return oStatusCode;
    }

    public void setOStatusCode(String oStatusCode) {
        this.oStatusCode = oStatusCode;
    }

    @Override
    public String toString() {
        return "LOrderStatus{oStatusId=" + oStatusId
                + ", oStatusCode='" + oStatusCode + "'}";
    }
}
