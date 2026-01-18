package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlProduct;

public class ProductCommandRemove extends Command {
    public ProductCommandRemove() {
        super("remove");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result=false;
        if (args[1].equals("remove")) {
            try{
                Integer prodId= Integer.valueOf(args[2]);
                result=ControlProduct.getInstance().removeProduct(prodId);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
        return result;
    }
}
