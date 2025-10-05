package upm.etsisi.App;

import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        app.init();
        app.start();
        app.end();
    }

    public void start()
    {
        Scanner sc = new Scanner(System.in);

        /* ES COMO LO HIZO ÉL EN UNA DE LAS PRÁCTICAS,
        NO SÉ SI HACE FALTA PARA PONER UN LÍMITE DE EJECUCIÓN DE COMANDOS O ALGO

        String[] arrayTask = new String[100];
        int num_elements = 0;
        boolean[] arrayTaskStatus = new boolean[100];*/


        ControlProduct controlProduct = new ControlProduct(100); //No sé cuál es el número que hay que poner de size
        Ticket ticket = new Ticket(new ArrayList<>());
        ControlTicket controlTicket = new ControlTicket(ticket);


        boolean continuar = true;
        while (continuar) {  // TODO: Falta imprimir <<"nombre comando" : ok>> después de cada ejecución
            String[] command =sc.nextLine().split(" "); // TODO Arreglar el parser, tal como está cosas como "Libro POO" en el nombre de un producto hace que no parsee bien por el espacio

            switch (command[0]){
                case "prod":
                    commandProduct(command, controlProduct);
                    break;

                case "ticket":
                    commandTicket(command, controlTicket);
                    break;

                case "help":
                    printHelp();
                    break;

                case "echo":
                    for (String s : command) {
                        System.out.print(s + " ");
                    }
                    break;

                case "exit":
                    System.out.println("Closing application.");
                    continuar = false;
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' for list of commands.");
                    break;

            }
        }
    }

    private void commandProduct(String[] command, ControlProduct controlProduct) {
        switch (command[1]) {
            case "add":
                Product product = new Product(Integer.parseInt(command[2]), command[3],
                        Category.valueOf(command[4].toUpperCase()), Double.parseDouble(command[5]));
                controlProduct.addProduct(product);
                break;
            case "list":
                controlProduct.list();
                break;
            case "update":
                controlProduct.updateProduct(Integer.parseInt(command[2]), command[3], command[4]);
                break;
            case "remove":
                controlProduct.removeProduct(Integer.parseInt(command[2]));
                break;
        }
    }

    private void commandTicket(String[] command, ControlTicket controlTicket) {
        switch (command[1]) {
            case "new":
                controlTicket.newTicket();
                break;
            case "add":
                Product productA = new Product(Integer.parseInt(command[2]), command[3],
                        Category.valueOf(command[4].toUpperCase()), Double.parseDouble(command[5]));
                controlTicket.addProduct(productA);
                break;
            case "remove":
                Product productR = new Product(Integer.parseInt(command[2]), command[3],
                        Category.valueOf(command[4].toUpperCase()), Double.parseDouble(command[5]));
                controlTicket.removeProduct(productR);
                break;
            case "print":
                controlTicket.printTicket();
                break;
        }
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("  prod add <id> \"<name>\" <category> <price>");
        System.out.println("  prod list");
        System.out.println("  prod update <id> NAME|CATEGORY|PRICE <value>");
        System.out.println("  prod remove <id>");
        System.out.println("  ticket new");
        System.out.println("  ticket add <prodId> <quantity>");
        System.out.println("  ticket remove <prodId>");
        System.out.println("  ticket print");
        System.out.println("  echo \"<texto>\"");
        System.out.println("  help");
        System.out.println("  exit");
        System.out.println("\nCategories: MERCH, PAPELERIA, ROPA, LIBRO, ELECTRONICA");
        System.out.println("Discounts if there are ≥2 units in the category: MERCH 0%, PAPELERIA 5%, ROPA 7%, LIBRO 10%, ELECTRONICA 3%.");
    }

    public void init()
    {
        System.out.println("Welcome to the ticket module App." +
                "Ticket module. Type 'help' to see commands.");
    }

    public void end()
    {
        System.out.println("Goodbye!");
    }


}


