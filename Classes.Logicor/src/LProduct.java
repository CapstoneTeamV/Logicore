public class LProduct {
    private int prdId;
    private String prdCode;
    private String prdSscc;
    private String prdPackTypeCode;

    public LProduct() {
    }

    public LProduct(int prdId, String prdCode, String prdSscc, String prdPackTypeCode) {
        this.prdId = prdId;
        this.prdCode = prdCode;
        this.prdSscc = prdSscc;
        this.prdPackTypeCode = prdPackTypeCode;
    }

    public int getPrdId() {
        return prdId;
    }

    public void setPrdId(int prdId) {
        this.prdId = prdId;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public String getPrdSscc() {
        return prdSscc;
    }

    public void setPrdSscc(String prdSscc) {
        this.prdSscc = prdSscc;
    }

    public String getPrdPackTypeCode() {
        return prdPackTypeCode;
    }

    public void setPrdPackTypeCode(String prdPackTypeCode) {
        this.prdPackTypeCode = prdPackTypeCode;
    }

    @Override
    public String toString() {
        return "LProduct{prdId=" + prdId
                + ", prdCode='" + prdCode + "'"
                + ", prdSscc='" + prdSscc + "'"
                + ", prdPackTypeCode='" + prdPackTypeCode + "'}";
    }
}
