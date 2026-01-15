package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.ProductService;
import upm.etsisi.Model.ProductsAndService;
import upm.etsisi.Model.Ticket;

import java.util.Collection;

public interface IPrinter<T extends Ticket<?>> {
    void print(T ticket, String cashierId);


}
