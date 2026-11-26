
import java.time.ZonedDateTime;
import java.util.ArrayList;

// κλάση παραλάβής παραγγελίας

public class Receipt {

    // temporary, must be replaced by database
    private static int id_counter = 0;

    private int receiptID;
    private String receiptCode;

    // customer_id
    private Customer customer;

    // created_by
    private User creator;

    // reveived_by
    private User receiver;

    private ReceiptStatus.Status status;

    private ZonedDateTime announcedAt;
    private ZonedDateTime receivedAt;

    private ArrayList<Palette> palettes;

    public int getReceiptId() {
        return receiptID;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    // constructor

    public Receipt(User creator, Customer customer, ZonedDateTime time) {
        this.receiptID = ++Receipt.id_counter;
        this.announcedAt = time;
        this.creator = creator;
        this.customer = customer;
        this.status = ReceiptStatus.Status.ANNOUNCED;
    }

    // need Palette constructor
    public addPalette(Palette palette) {
        palettes.add(palette);
    }

    // παραλαβή παραγγελίας

    public void ReceiveReceipt() {

    }

}
