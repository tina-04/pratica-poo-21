package upm.etsisi.Model;

import upm.etsisi.Utility.Rol;

public class Cashier  {

    private String name;
    private String email;

    private String cashierId;

    public Cashier(String name, String email, String cashierId) {
        this.name = name;
        this.email = email;
        this.cashierId = cashierId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }


}
