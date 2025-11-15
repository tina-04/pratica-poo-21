package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;

public class ProductCommandAdd extends Command {
    public ProductCommandAdd() {
        super("add");
    }

    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
