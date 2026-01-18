package upm.etsisi.Model;

public class TicketItem <T extends ProductsAndService> {
    private T item;
    private int quantity;

    public TicketItem(T item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public T getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int q) {
        this.quantity += q;
    }
}
