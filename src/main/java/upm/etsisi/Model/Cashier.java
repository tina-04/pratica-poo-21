package upm.etsisi.Model;

import upm.etsisi.Utility.Rol;

public class Cashier extends User {

    private String cashierId;

    public Cashier(String name, String email, String cashierId) {
        super(name, email);
        this.cashierId = cashierId;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }


}
