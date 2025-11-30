package upm.etsisi.Model;

import java.util.List;

public class Cashier extends User {

    public Cashier(String cashierId, String name, String email) {
        super(cashierId, name, email);
    }

    public String getCashierId() {
        return this.id;
    }

    public void setCashierId(String cashierId) {
        this.id = cashierId;
    }
}