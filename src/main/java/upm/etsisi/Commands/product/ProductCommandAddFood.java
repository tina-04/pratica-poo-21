package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;
import upm.etsisi.Control.ControlProduct;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ProductCommandAddFood extends Command {
    public ProductCommandAddFood() {
        super("addFood");
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args[1].equals("addFood")){
            try {

                int id = Integer.parseInt(args[2]);

                String line = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
                int firstQuote = line.indexOf('"');
                int lastQuote = line.lastIndexOf('"');
                if (firstQuote == -1 || lastQuote == -1 || lastQuote <= firstQuote) {
                    System.out.println("Error: El nombre debe ir entre comillas.");
                    return false;
                }

                String name = line.substring(firstQuote + 1, lastQuote);
                String afterName = line.substring(lastQuote + 1).trim();
                String[] rest = afterName.split(" ");

                if (rest.length < 3) {
                    System.out.println("Error: faltan argumentos.");
                    return false;
                }

                double price = Double.parseDouble(rest[0]);
                LocalDate expiration = LocalDate.parse(rest[1]);
                int max_people = Integer.parseInt(rest[2]);

                result= ControlProduct.getInstance().addFood(id, name, price, expiration, max_people);


            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
