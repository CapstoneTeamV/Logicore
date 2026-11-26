// κλάση για την κατάσταση παραλαβής
public class ReceiptStatus {
    private int statusID;
    private int code;
    private String labelGR;

    public enum Status {
        ANNOUNCED,
        RECEIVED,
        CANCELLED,
    }

    public int getStatusID() {
        return statusID;
    }

    public String getLabel() {
        return labelGR;
    }

    // αλλαγή κατάστασης

    public void ChangeStatus() {

    }

}
