package upm.etsisi.App;

import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Control.ControlUser;
import upm.etsisi.Model.Product;
import upm.etsisi.Model.User;
import upm.etsisi.Utility.Category;
import upm.etsisi.View.ViewApp;

import java.util.Scanner;


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
            String[] name = line.split("\"");
            System.out.println(line);
            switch (command[0]) {
                case "prod":
                    commandProduct(command, name);
                    System.out.println(command[0] + " " + command[1] + ": ok");
                    break;

                case "ticket":
                    commandTicket(command);
                    System.out.println(command[0] + " " + command[1] + ": ok");
                    break;

                case "client":
                    commandClient(command, name);
                    System.out.println(command[0] + " " + command[1] + ": ok");
                    break;

                case "cash":
                    commandCash(command, name);
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
                        StringBuilder stringName = new StringBuilder(name[1]);
                        for (int i = 2; i < name.length-1; i++) {
                            stringName.append("\"").append(name[i]);
                        }
                        Category category = Category.valueOf(command[command.length - 2].toUpperCase());
                        double price = Double.parseDouble(command[command.length - 1]);
                        Product product = new Product(id, stringName.toString(), category, price);
                        ControlProduct.getInstance().addProduct(product);

                    } catch (Exception e) {
                         System.out.println(e.getMessage());
                    }
                }

                break;
            case "list":
                ControlProduct.getInstance().list();
                break;
            case "update":
                if (name.length > 1) {
                    StringBuilder stringName = new StringBuilder(name[1]);
                    for (int i = 2; i < name.length-1; i++) {
                        stringName.append("\"").append(name[i]);
                    }
                    ControlProduct.getInstance().updateProduct(Integer.parseInt(command[2]), command[3], stringName.toString());
                } else ControlProduct.getInstance().updateProduct(Integer.parseInt(command[2]), command[3], command[4]);
                break;
            case "remove":
                ControlProduct.getInstance().removeProduct(Integer.parseInt(command[2]));
                break;
        }

    }

    private void commandTicket(String[] command) {
        switch (command[1]) {
            case "new":
                ControlTicket.getInstance().newTicket(command[2], command[3]);
                break;
            case "add":
                Product productAdd = ControlProduct.getInstance().searchProduct(Integer.parseInt(command[2]));
                int amount = Integer.parseInt(command[3]);
                ControlTicket.getInstance().addProduct(productAdd, amount);
                break;
            case "remove":
                Product productRemove = ControlProduct.getInstance().searchProduct(Integer.parseInt(command[4]));
                ControlTicket.getInstance().removeProduct(productRemove, command[2], command[3]);
                break;
            case "print":
                ControlTicket.getInstance().printTicket();
                break;
        }
    }

    private void commandClient(String[] command, String[] name) {
        switch (command[1]) {
            case "add":
                String nameClient = name[1];
                String id = command[2];
                String email = command[3];
                String cashId = command[4];
                ControlUser.getInstance().addClient(nameClient, id, email, cashId);
                break;
            case "remove":
                ControlUser.getInstance().removeClient(command[2]);
                break;
            case "list":
                ControlUser.getInstance().clientList();
                break;
        }
    }

    private void commandCash(String[] command, String[] name) {
        switch (command[1]) {
            case "add":
                String id = command[2];
                String nameClient = name[1];
                String email = command[3];
                ControlUser.getInstance().addCashier(id, nameClient, email);
                break;
            case "remove":
                ControlUser.getInstance().removeCashier(command[2]);
                break;
            case "list":
                ControlUser.getInstance().clientList();
                break;
        }
    }

}


