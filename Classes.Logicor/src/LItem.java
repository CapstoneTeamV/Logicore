public class LItem {
    private int itemId;
    private String itemSscc;
    private String itemPackTypeCode;
    private int itemLocId;
    private int itemDptId;

    public LItem() {
    }

    public LItem(int itemId, String itemSscc, String itemPackTypeCode,
                  int itemLocId, int itemDptId) {
        this.itemId = itemId;
        this.itemSscc = itemSscc;
        this.itemPackTypeCode = itemPackTypeCode;
        this.itemLocId = itemLocId;
        this.itemDptId = itemDptId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemSscc() {
        return itemSscc;
    }

    public void setItemSscc(String itemSscc) {
        this.itemSscc = itemSscc;
    }

    public String getItemPackTypeCode() {
        return itemPackTypeCode;
    }

    public void setItemPackTypeCode(String itemPackTypeCode) {
        this.itemPackTypeCode = itemPackTypeCode;
    }

    public int getItemLocId() {
        return itemLocId;
    }

    public void setItemLocId(int itemLocId) {
        this.itemLocId = itemLocId;
    }

    public int getItemDptId() {
        return itemDptId;
    }

    public void setItemDptId(int itemDptId) {
        this.itemDptId = itemDptId;
    }

    @Override
    public String toString() {
        return "LItem{itemId=" + itemId
                + ", itemSscc='" + itemSscc + "'"
                + ", itemPackTypeCode='" + itemPackTypeCode + "'"
                + ", itemLocId=" + itemLocId
                + ", itemDptId=" + itemDptId + '}';
    }
}
