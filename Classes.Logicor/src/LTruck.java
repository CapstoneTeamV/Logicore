public class LTruck {
    private int trkId;
    private String trkCode;
    private String trkArea;

    public LTruck() {
    }

    public LTruck(int trkId, String trkCode, String trkArea) {
        this.trkId = trkId;
        this.trkCode = trkCode;
        this.trkArea = trkArea;
    }

    public int getTrkId() {
        return trkId;
    }

    public void setTrkId(int trkId) {
        this.trkId = trkId;
    }

    public String getTrkCode() {
        return trkCode;
    }

    public void setTrkCode(String trkCode) {
        this.trkCode = trkCode;
    }

    public String getTrkArea() {
        return trkArea;
    }

    public void setTrkArea(String trkArea) {
        this.trkArea = trkArea;
    }

    @Override
    public String toString() {
        return "LTruck{trkId=" + trkId
                + ", trkCode='" + trkCode + "'"
                + ", trkArea='" + trkArea + "'}";
    }
}
