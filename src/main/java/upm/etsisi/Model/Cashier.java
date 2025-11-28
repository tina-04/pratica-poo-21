package upm.etsisi.Model;

import java.util.ArrayList;
import java.util.List;

public class Cashier  {

    private String name;
    private String email;

    private String cashierId;


    private List<Ticket> tikcetList;

    public Cashier(String cashierId, String name, String email) {
        this.cashierId = cashierId;
        this.name = name;
        this.email = email;
        this.tikcetList= new ArrayList<>();
    }
    public List<Ticket> getTikcetList() {
        return tikcetList;
    }

    public void setTikcetList(List<Ticket> tikcetList) {
        this.tikcetList = tikcetList;
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
