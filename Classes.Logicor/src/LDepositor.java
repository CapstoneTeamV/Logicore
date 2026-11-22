public class LDepositor {
    private int dptId;
    private String dptCode;
    private Integer dptOrdId;
    private Integer dptRctId;

    public LDepositor() {
    }

    public LDepositor(int dptId, String dptCode, Integer dptOrdId, Integer dptRctId) {
        this.dptId = dptId;
        this.dptCode = dptCode;
        this.dptOrdId = dptOrdId;
        this.dptRctId = dptRctId;
    }

    public int getDptId() {
        return dptId;
    }

    public void setDptId(int dptId) {
        this.dptId = dptId;
    }

    public String getDptCode() {
        return dptCode;
    }

    public void setDptCode(String dptCode) {
        this.dptCode = dptCode;
    }

    public Integer getDptOrdId() {
        return dptOrdId;
    }

    public void setDptOrdId(Integer dptOrdId) {
        this.dptOrdId = dptOrdId;
    }

    public Integer getDptRctId() {
        return dptRctId;
    }

    public void setDptRctId(Integer dptRctId) {
        this.dptRctId = dptRctId;
    }

    @Override
    public String toString() {
        return "LDepositor{dptId=" + dptId
                + ", dptCode='" + dptCode + "'"
                + ", dptOrdId=" + dptOrdId
                + ", dptRctId=" + dptRctId + '}';
    }
}
