public class LLocation {
    private int locId;
    private String locCode;
    private String locPackType;

    public LLocation() {
    }

    public LLocation(int locId, String locCode, String locPackType) {
        this.locId = locId;
        this.locCode = locCode;
        this.locPackType = locPackType;
    }

    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public String getLocPackType() {
        return locPackType;
    }

    public void setLocPackType(String locPackType) {
        this.locPackType = locPackType;
    }

    @Override
    public String toString() {
        return "LLocation{locId=" + locId
                + ", locCode='" + locCode + "'"
                + ", locPackType='" + locPackType + "'}";
    }
}
