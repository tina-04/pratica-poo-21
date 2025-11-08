package upm.etsisi.Model;

import upm.etsisi.Utility.Rol;

public class User {
    private  String name;
    private String email;
    private String DNI;
    private String cashierID;
    private Client client;
    private Cashier cashier;

    public User(String name, String email, Rol rol){
        this.name = name;
        this.DNI = DNI;
        this.email = email;
        this.cashierID = cashierID;


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


    public String getCashierID() {
        return cashierID;
    }

    public void setCashierID(String cashierID) {
        this.cashierID = cashierID;
    }



    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    protected  String id;
    protected  String name;
    protected  String email;
    protected  String cashId;

    public User(String id, String name, String email, String cashId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cashId = cashId;
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
