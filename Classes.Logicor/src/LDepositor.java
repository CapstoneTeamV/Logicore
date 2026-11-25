public class LDepositor {
    private int dptId;
    private String dptCode;

    public LDepositor() {
    }

    public LDepositor(int dptId, String dptCode) {
        this.dptId = dptId;
        this.dptCode = dptCode;
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

    @Override
    public String toString() {
        return "LDepositor{dptId=" + dptId
                + ", dptCode='" + dptCode + "'}";
    }
}
