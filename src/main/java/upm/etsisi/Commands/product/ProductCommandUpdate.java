package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;
import upm.etsisi.Control.ControlProduct;

import java.util.Arrays;

public class ProductCommandUpdate extends Command {

    public ProductCommandUpdate() {
        super("update");
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;

        if(args[1].equals("update")){
            try{
                Integer id = Integer.parseInt(args[2]);
                String type = args[3];
                String value;
                if(type.equalsIgnoreCase("name")){
                    String rest = String.join(" ", Arrays.copyOfRange(args, 4, args.length ));
                    int firstQuote = rest.indexOf('"');
                    int lastQuote = rest.lastIndexOf('"');
                    value = rest.substring(firstQuote + 1, lastQuote);
                    ControlProduct.getInstance().updateProduct(id, type, value);
                }else{
                     value = args[4];
                    ControlProduct.getInstance().updateProduct(id, type, value);
                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        return result;
    }
}
