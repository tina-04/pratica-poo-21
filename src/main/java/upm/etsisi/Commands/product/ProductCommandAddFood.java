package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;

public class ProductCommandAddFood extends Command {
    public ProductCommandAddFood() {
        super("addFood");
    }

    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
