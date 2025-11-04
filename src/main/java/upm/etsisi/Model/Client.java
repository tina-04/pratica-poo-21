package upm.etsisi.Model;

import upm.etsisi.Utility.Rol;

public class Client extends User {
  private String name;
    private String email;
    private String DNI;

    public Client(String name, String email, String DNI) {
        super(name, email, Rol.CLIENT);
        this.name = name;
        this.email = email;
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }





}
