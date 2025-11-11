package upm.etsisi.Model;

import upm.etsisi.Utility.Rol;

public class User {
    private  String name;
    private String email;
    private Client client;
    private Cashier cashier;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public Cashier getCashier() {
        return cashier;
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

}
