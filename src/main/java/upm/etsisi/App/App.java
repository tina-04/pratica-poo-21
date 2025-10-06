package upm.etsisi.App;

import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;
import upm.etsisi.View.ViewApp;

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
        ViewApp.init();
        app.start();
        ViewApp.end();
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
        Ticket ticket = new Ticket();
        ControlTicket controlTicket = new ControlTicket(ticket);


        boolean continuar = true;
        while (continuar) {  // TODO: Falta imprimir <<"nombre comando" : ok>> después de cada ejecución
            String line =sc.nextLine();
            String[] command = line.split(" "); // TODO Arreglar el parser, tal como está cosas como "Libro POO" en el nombre de un producto hace que no parsee bien por el espacio
            switch (command[0]){
                case "prod":
                    String[] name = line.split("\"");
                    commandProduct(command, name, controlProduct);
                    break;

                case "ticket":
                    commandTicket(command, controlTicket, controlProduct);
                    break;

                case "help":
                    ViewApp.printHelp();
                    break;

                case "echo":
                    for (String s : command) {
                        System.out.print(s + " ");
                    }
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
        switch (command[1]) {
            case "add":
                Product product = new Product(Integer.parseInt(command[2]), name[1],
                        Category.valueOf(command[command.length-2].toUpperCase()), Double.parseDouble(command[command.length-1]));
                controlProduct.addProduct(product);
                break;
            case "list":
                controlProduct.list();
                break;
            case "update":
                if (name.length > 1) {
                    controlProduct.updateProduct(Integer.parseInt(command[2]), command[3], name[1]);
                }else controlProduct.updateProduct(Integer.parseInt(command[2]), command[3], command[4]);
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
                int amount = Integer.parseInt(command[3]);
                Product productAdd = controlProduct.getProduct(Integer.valueOf(command[2]) -1);
                for (int i = 0; i < amount; i++) {
                    controlTicket.addProduct(productAdd);
                }
                break;
            case "remove":
                Product productRemove = controlProduct.getProduct(Integer.valueOf(command[2]) -1);
                controlTicket.removeProduct(productRemove);
                break;
            case "print":
                controlTicket.printTicket();
                break;
        }
    }

}


