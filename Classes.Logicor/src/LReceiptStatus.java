public class LReceiptStatus {
    private int rStatusId;
    private String rStatusCode;

    public LReceiptStatus() {
    }

    public LReceiptStatus(int rStatusId, String rStatusCode) {
        this.rStatusId = rStatusId;
        this.rStatusCode = rStatusCode;
    }

    public int getRStatusId() {
        return rStatusId;
    }

    public void setRStatusId(int rStatusId) {
        this.rStatusId = rStatusId;
    }

    public String getRStatusCode() {
        return rStatusCode;
    }

    public void setRStatusCode(String rStatusCode) {
        this.rStatusCode = rStatusCode;
    }

    @Override
    public String toString() {
        return "LReceiptStatus{rStatusId=" + rStatusId
                + ", rStatusCode='" + rStatusCode + "'}";
    }
}
