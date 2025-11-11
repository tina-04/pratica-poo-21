package upm.etsisi.App;

import upm.etsisi.Commands.HelpCommand;
import upm.etsisi.Commands.IComando;
import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Control.ControlUser;
import upm.etsisi.Model.Product;
import upm.etsisi.Utility.Category;
import upm.etsisi.View.ViewApp;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * Hello world!
 *
 */
public class App {
    private ViewApp viewApp;
    private Map<String, IComando> comandos;

    public static void main(String[] args) {
        App app = new App();
        ViewApp.init();
        app.start();

        // ----------- Instanciación de comandos -------------------
        app.registrarComando("help", new HelpCommand(app.comandos)); // Funciona (quizá)
        // ---------------------------------------------------------



        ViewApp.end();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        this.viewApp = new ViewApp();
        this.comandos = new TreeMap<>();
        boolean continuar = true;
        while (continuar) {
            System.out.print("\ntUPM> ");
            String line = sc.nextLine();
            String[] command = line.split(" ");
            String[] name = line.split("\"");
            System.out.println(line);
            switch (command[0]) {
                case "prod":
                    commandProduct(command, name,viewApp);
                    break;
                case "ticket":
                    commandTicket(command,viewApp);
                    break;

                case "client":
                    commandClient(command,viewApp);
                    break;

                case "cash":
                    commandCahsier(command,viewApp);
                    break;


                case "help":
                    ViewApp.printHelp();
                    break;

                case "echo":
                    System.out.println(line + "\n");
                    break;

                case "exit":
                    viewApp.close();
                    continuar = false;
                    break;

                default:
                    viewApp.error();
                    break;

            }
        }
    }

    private void commandProduct(String[] command, String[] name, ViewApp viewApp) {
        if (command == null || command.length < 2) {
            viewApp.errorCommand();
        }
        switch (command[1]) {
            case "add":
                if (command.length < 5) {
                   viewApp.errorInfo();
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

    private void commandTicket(String[] command, ViewApp viewApp) {
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

                break;
            case "remove":
                break;
            case "list":
                ControlUser.getInstance().clientList();
                break;
        }
    }
    private void commandClient(String [] command, ViewApp viewApp) {

    }
    private void commandCahsier( String [] command, ViewApp viewApp) {

    }




    // Creación de comandos, zona de Alex, de momento la mayoría de cosas son pruebas



    public void registrarComando(String alias, IComando comando) {
        if (alias != null && !alias.trim().isEmpty() && comando != null) {
            comandos.put(alias.toLowerCase(), comando);
        } else throw new RuntimeException("Se deben introducir argumentos validos al registrar un comando.");
    }




}


