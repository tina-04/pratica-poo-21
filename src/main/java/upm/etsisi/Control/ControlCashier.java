package upm.etsisi.Control;

import upm.etsisi.Model.Cashier;
import upm.etsisi.Model.Client;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewCashier;
import upm.etsisi.View.ViewClient;

import java.util.ArrayList;
import java.util.List;

public class ControlCashier {

    private List<Cashier> cashierList;
    private ViewCashier viewCashier;
    private static ControlCashier instance;
    public static ControlCashier getInstance() {
        if (instance == null) {
            instance = new ControlCashier();
        }
        return instance;
    }
    private ControlCashier() {
        this.cashierList = new ArrayList<>();
        this.viewCashier = new ViewCashier();
    }
    public void addCashier(String id, String name, String email) {
        if(Utility.correctEmail(email) && Utility.correctCashierId(id) && !existCashie(id)){
            Cashier cashier = new Cashier(id, name, email);
            cashierList.add(cashier);
        }
    }
    public boolean existCashie(String id){
        boolean exist = false;
        for(int i =0; i<cashierList.size();i++){
            if(cashierList.get(i).getCashierId().equals(id)){
                exist=true;
            }
        }
        return exist;
    }
    public Cashier searchCashier(String DNI) {
        Cashier cashier = null;
        for(int i =0;i<cashierList.size();i++) {
            if(cashierList.get(i).getDNI().equals(DNI)) {
                cashier = cashierList.get(i);
            }

        }
        return cashier;
    }
    public boolean removeCashier(String id) {
        boolean result = false;
        for (int i = 0; i < cashierList.size(); i++) {
            if (cashierList.get(i).getCashierId()==id) {
                cashierList.remove(cashierList.get(i));
                result = true;

            }
        }

        return result;
    }

    public void cashTikcet(String id){

    }
    public void cashiedList(){

    }


}
