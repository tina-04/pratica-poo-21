package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlProduct;

public class ProdcutCommandRemove extends Command {
    public ProdcutCommandRemove() {
        super("remove");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result=false;
        if (args[1].equals("remove")) {
            result=ControlProduct.getInstance().removeProduct(Integer.parseInt(args[2]));

        }
        return result;
    }
}
