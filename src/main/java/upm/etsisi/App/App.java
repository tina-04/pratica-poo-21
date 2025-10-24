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

        boolean continuar = true;
        while (continuar) {
            System.out.print("\ntUPM> ");
            String line = sc.nextLine();
            String[] command = line.split(" ");
            System.out.println(line);
            switch (command[0]) {
                case "prod":
                    String[] name = line.split("\"");
                    commandProduct(command, name);
                    System.out.println(command[0] + " " + command[1] + ": ok");
                    break;

                case "ticket":
                    commandTicket(command);
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

    private void commandProduct(String[] command, String[] name) {
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
                        String name1 = name[1];
                        Category category = Category.valueOf(command[command.length - 2].toUpperCase());
                        double price = Double.parseDouble(command[command.length - 1]);
                        Product product = new Product(id, name1, category, price);
                        ControlProduct.getInstancia().addProduct(product);

                    } catch (NumberFormatException e1) {
                        System.out.println(e1.getMessage());
                    } catch (IllegalArgumentException e3){
                        System.out.println(e3.getMessage());
                    } catch (Exception e) {
                         System.out.println(e.getMessage());
                    }
                }

                break;
            case "list":
                ControlProduct.getInstancia().list();
                break;
            case "update":
                if (name.length > 1) {
                    ControlProduct.getInstancia().updateProduct(Integer.parseInt(command[2]), command[3], name[1]);
                } else ControlProduct.getInstancia().updateProduct(Integer.parseInt(command[2]), command[3], command[4]);
                break;
            case "remove":
                ControlProduct.getInstancia().removeProduct(Integer.parseInt(command[2]));
                break;
        }

    }

    private void commandTicket(String[] command) {
        switch (command[1]) {
            case "new":
                ControlTicket.getInstance().newTicket();
                break;
            case "add":
                Product producAdd = ControlProduct.getInstancia().searchProduct(Integer.parseInt(command[2]));
                int amount = Integer.parseInt(command[3]);
                ControlTicket.getInstance().addProduct(producAdd, amount);
                break;
            case "remove":
                Product productRemove = ControlProduct.getInstancia().searchProduct(Integer.parseInt(command[2]));
                ControlTicket.getInstance().removeProduct(productRemove);
                break;
            case "print":
                ControlTicket.getInstance().printTicket();
                break;
        }
    }

}


