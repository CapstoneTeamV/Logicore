public class LLocation {
    private int locId;
    private String locCode;
    private String locPackType;
    private String locType;

    public LLocation() {
    }

    public LLocation(int locId, String locCode, String locPackType, String locType) {
        this.locId = locId;
        this.locCode = locCode;
        this.locPackType = locPackType;
        this.locType = locType;
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

    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        this.locType = locType;
    }

    @Override
    public String toString() {
        return "LLocation{locId=" + locId
                + ", locCode='" + locCode + "'"
                + ", locPackType='" + locPackType + "'"
                + ", locType='" + locType + "'}";
    }
}
