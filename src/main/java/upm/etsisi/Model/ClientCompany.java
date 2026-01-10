package upm.etsisi.Model;

public class ClientCompany extends User implements ClientAndCompany {
    private String NIF;
    private String cashierId;

    public ClientCompany(String name, String NIF, String email, String cashierId) {
        super(NIF, name, email);
        this.NIF = NIF;
        this.cashierId = cashierId;
    }

    @Override
    public String getCashierId() {
        return this.cashierId;
    }
    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }
    public String getNIF() {
        return this.NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

}
