import java.time.LocalDateTime;
public class MovementPalletFinal {

    // Attributes..
    private int movementId;
    //private Pallet pallet;
    private MovementKind kind;
    private LocalDateTime movedAt;
    //private Order order;
    //private User user;

    // Default constructor
   /*  public PalletMovement() {
    }*/

    // Parameterized constructor
    /*public PalletMovement(int movementId,
                          Pallet pallet,
                          MovementKind kind,
                          Order order,
                          User user) {

        this.movementId = movementId;
        this.pallet = pallet;
        this.kind = kind;
        this.movedAt = LocalDateTime.now();
        this.order = order;
        this.user = user;
    }*/

    // Getters and Setters

    public int getMovementId() {
        return movementId;
    }

    public void setMovementId(int movementId) {
        this.movementId = movementId;
    }

    /*public Pallet getPallet() {
        //return pallet;
    }*/

    /*
    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }*/

    public MovementKind getKind() {
        return kind;
    }

    public void setKind(MovementKind kind) {
        this.kind = kind;
    }

    public LocalDateTime getMovedAt() {
        return movedAt;
    }

    /*
    public Order getOrder() {
        return order;
    }*/

    /*public void setOrder(Order order) {
        this.order = order;
    }*/

    /*public User getUser() {
        return user;
    }*/

    /*public void setUser(User user) {
        this.user = user;
    }*/
}