package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;
import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Model.Product;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewApp;

import java.util.Arrays;

public class ProductCommandAdd extends Command {
    private ViewApp viewApp;

    public ProductCommandAdd() {

        super("add");
        this.viewApp = new ViewApp();
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("add") && args.length > 5) {
            try {
                Integer id = null;
                int indexNameStart = 0;

                try {
                    id = Integer.parseInt(args[2]);
                    indexNameStart = 3;
                } catch (NumberFormatException e) {
                    id = Integer.valueOf(Utility.generateId());
                    indexNameStart = 2;
                }

                String rest = String.join(" ", Arrays.copyOfRange(args, indexNameStart, args.length ));
                int firstQuote = rest.indexOf('"');
                int lastQuote = rest.lastIndexOf('"');

                String name = rest.substring(firstQuote + 1, lastQuote);
                String afterName = rest.substring(lastQuote + 1).trim();
                String[] parts = afterName.split(" ");

                Category category = Category.valueOf(parts[0]);
                double price= Double.parseDouble(parts[1]);

                Integer max_people = null;
                if (parts.length >= 3) {
                    max_people = Integer.parseInt(parts[2]);
                }

                result = ControlProduct.getInstance().addProduct(id,name,category,price,max_people);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return result;
    }
}
