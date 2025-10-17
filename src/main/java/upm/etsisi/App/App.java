package upm.etsisi.App;

import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Model.Product;
import upm.etsisi.Utility.Category;
import upm.etsisi.View.ViewApp;

import java.util.Scanner;

import static java.lang.String.*;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        ViewApp.init();
        app.start();
        ViewApp.end();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        ControlProduct controlProduct = new ControlProduct(100);
        ControlTicket controlTicket = new ControlTicket();

        boolean continuar = true;
        while (continuar) {
            System.out.print("\ntUPM> ");
            String line = sc.nextLine();
            String[] command = line.split(" ");
            System.out.println(line);
            switch (command[0]) {
                case "prod":
                    String[] name = line.split("\"");
                    commandProduct(command, name, controlProduct);
                    System.out.println(command[0] + " " + command[1] + ": ok");
                    break;

                case "ticket":
                    commandTicket(command, controlTicket, controlProduct);
                    System.out.println(command[0] + " " + command[1] + ": ok");
                    break;

                case "help":
                    ViewApp.printHelp();
                    break;

                case "echo":
                    System.out.println(line + "\n");
                    break;

                case "exit":
                    System.out.println(ViewApp.close);
                    continuar = false;
                    break;

                default:
                    System.out.println(ViewApp.error);
                    break;

            }
        }
    }

    private void commandProduct(String[] command, String[] name, ControlProduct controlProduct) {
        if (command == null || command.length < 2) {
            System.out.println("Error: invalid command.");
        }
        switch (command[1]) {
            case "add":
                if (command.length < 5) {
                    System.out.println("Error: we need more information");
                } else {
                    try {
                        int id = Integer.parseInt(command[2]);

                        String name1 = name[1].trim();

                        Category category = Category.valueOf(command[command.length - 2].toUpperCase());
                        double price = Double.parseDouble(command[command.length - 1]);
                        Product product = new Product(id, name1, category, price);
                        controlProduct.addProduct(product);

                    } catch (NumberFormatException e1) {
                        System.out.println(e1.getMessage());
                    }catch (Exception e) {
                         System.out.println(e.getMessage());
                    }
                }

                break;
            case "list":
                controlProduct.list();
                break;
            case "update":
                if (name.length > 1) {
                    controlProduct.updateProduct(Integer.parseInt(command[2]), command[3], name[1]);
                } else controlProduct.updateProduct(Integer.parseInt(command[2]), command[3], command[4]);
                break;
            case "remove":
                controlProduct.removeProduct(Integer.parseInt(command[2]));
                break;
        }

    }

    private void commandTicket(String[] command, ControlTicket controlTicket, ControlProduct controlProduct) {
        switch (command[1]) {
            case "new":
                controlTicket.newTicket();
                break;
            case "add":
                Product producAdd = controlProduct.searchProduct(Integer.parseInt(command[2]));
                int amount = Integer.parseInt(command[3]);
                controlTicket.addProduct(producAdd, amount);
                break;
            case "remove":
                Product productRemove = controlProduct.searchProduct(Integer.parseInt(command[2]));
                controlTicket.removeProduct(productRemove);
                break;
            case "print":
                controlTicket.printTicket();
                break;
        }
    }

}


