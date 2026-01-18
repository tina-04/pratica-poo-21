package upm.etsisi.Model;

public class Client extends User implements IClientCompany {
    private String DNI;
    private String cashierId;

    public Client(String name, String DNI, String email, String cashierId) {
        super(DNI, name, email);
        this.DNI = DNI;
        this.cashierId = cashierId;
    }
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }
}