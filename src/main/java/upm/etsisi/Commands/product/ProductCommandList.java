package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;
import upm.etsisi.Control.ControlProduct;

public class ProductCommandList extends Command {
    public ProductCommandList() {
        super("list");
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args.length == 2 && args[1].equals("list")){
            ControlProduct.getInstance().list();
            ControlProduct.getInstance().listPS();
            result = true;
        }
        return result;
    }
}
