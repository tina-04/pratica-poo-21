package upm.etsisi.Model;

import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.Status;
import upm.etsisi.Utility.Utility;

import java.time.LocalDateTime;
import java.util.*;

public class Ticket<T extends ProductsAndService> {


    private List<TicketItem<ProductsAndService>> items = new ArrayList<>();

    private HashMap<Category, Integer> categoryCounter = new HashMap<>();
    private double total;
    private double discount;
    private double finalPrice;

    private String id;
    private String cashierId;
    private String clientId;
    private Status  status;


    public Ticket(String id, String cashierId, String clientId) {
        this.total = 0;
        this.discount = 0;
        this.finalPrice = 0;
        this.cashierId = cashierId;
        this.clientId = clientId;
        this.status=Status.EMPTY;
        this.id =id;
    }
    public void addItem(ProductsAndService item, int quantity, boolean canRepeat) {
        if (canRepeat) {
            // Si es repetible (BasicProduct), agregar como cantidad
            TicketItem<ProductsAndService> existing = findItemById(item);
            if (existing != null) {
                existing.addQuantity(quantity);
            } else {
                items.add(new TicketItem<>(item, quantity));
            }
        } else {
            // No repetible: agregar solo una vez
            if (findItemById(item) == null) {
                items.add(new TicketItem<>(item, 1));
            }
        }
    }

    private TicketItem<ProductsAndService> findItemById(ProductsAndService item) {
        for (TicketItem<ProductsAndService> ti : items) {
            ProductsAndService existingItem = ti.getItem();
            // Si ambos son BasicProduct
            if (existingItem instanceof BasicProduct && item instanceof BasicProduct) {
                BasicProduct existingBP = (BasicProduct) existingItem;
                BasicProduct newBP = (BasicProduct) item;

                boolean bothPersonalized = existingBP.getPersonalizationList() != null && !existingBP.getPersonalizationList().isEmpty()
                        && newBP.getPersonalizationList() != null && !newBP.getPersonalizationList().isEmpty();

                boolean bothNormal = (existingBP.getPersonalizationList() == null || existingBP.getPersonalizationList().isEmpty())
                        && (newBP.getPersonalizationList() == null || newBP.getPersonalizationList().isEmpty());

                // Solo si son el mismo tipo (ambos normales o ambos mismo personalizado)
                if ((bothNormal || bothPersonalized)
                        && existingBP.getId().equals(newBP.getId())
                        && Objects.equals(existingBP.getPersonalizationList(), newBP.getPersonalizationList())) {
                    return ti;
                }
            } else {
                // Otros tipos: ProductService o TimedProduct
                if (existingItem.getId().equals(item.getId())) {
                    return ti;
                }
            }
        }
        return null;

    }
    public List<TicketItem<ProductsAndService>> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {return discount;}

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setCashierId(String id) {this.cashierId = id;}

    public String getCashierId() {return cashierId;}

    public String getClientId(){return clientId;}

    public void setClientId(String id) {this.clientId = id;}


    public int getCategoryCount(Category category) { // Este da cuantos tiene esa categoria
        return categoryCounter.getOrDefault(category, 0);
    }

    public void setCategoryCounter(Category category, int variation) { // Este es para sumar o restarle a esa categoría
        categoryCounter.put(category, categoryCounter.getOrDefault(category, 0) + variation);
    }
}
