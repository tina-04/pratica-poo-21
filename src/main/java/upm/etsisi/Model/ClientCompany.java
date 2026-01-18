package upm.etsisi.Model;

public class ClientCompany extends User implements IClientCompany {

    private String cashierId;

    public ClientCompany(String name, String NIF, String email, String cashierId) {
        super(NIF, name, email);
        this.cashierId = cashierId;
    }
    public String getNIF(){
        return super.id;
    }

    @Override
    public String getCashierId() {
        return cashierId;
    }
    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }



}
