package upm.etsisi.Model;

import upm.etsisi.Utility.Rol;

public class Cashier  extends User{


    private String name;
    private String email;
    private String cashierId;
    public Cashier(String name,String email, String cashierId) {
        super(name, email, Rol.CASHIER);
        this.name = name;
        this.email = email;
        this.cashierId = cashierId;
    }

    public String getNameCashier() {
        return name;
    }

    public void setNameCashier(String name) {
        this.name = name;
    }

    public String getEmailCashier() {
        return email;
    }

    public void setEmailCashier(String email) {
        this.email = email;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }



}
