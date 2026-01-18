package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.ProductService;
import upm.etsisi.Model.ProductsAndService;
import upm.etsisi.Model.Ticket;

import java.util.Collection;

public interface IPrinter {
    void print(Ticket<?> ticket, String cashierId,String close);


}
