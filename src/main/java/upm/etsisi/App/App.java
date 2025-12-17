package upm.etsisi.App;

import upm.etsisi.Commands.Command;
import upm.etsisi.Commands.app.EchoCommand;
import upm.etsisi.Commands.app.ExitCommand;
import upm.etsisi.Commands.app.HelpCommand;
import upm.etsisi.Commands.cashier.CashierCommand;
import upm.etsisi.Commands.client.ClientCommand;
import upm.etsisi.Commands.product.ProductCommand;
import upm.etsisi.Commands.ticket.TicketCommand;
import upm.etsisi.View.ViewApp;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        InputStream is = null;


        try {
            is = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        is = App.class.getResourceAsStream("/input2.txt");
            ViewApp.init();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                List<Command> list = new ArrayList<Command>();
                list.add(new HelpCommand("help"));
                list.add(new EchoCommand("echo"));
                list.add(new ProductCommand("prod"));
                list.add(new TicketCommand("ticket"));
                list.add(new CashierCommand("cashier"));
                list.add(new ClientCommand("client"));
                list.add(new ExitCommand("exit"));


                Scanner sc = new Scanner(System.in);
                boolean continuar = true;
                while (continuar) {
                    System.out.print("\ntUPM> ");
                    String line = br.readLine();
                    String[] command = line.split(" ");
                    System.out.println(line);
                    boolean found = false;
                    for (int i = 0; i < list.size(); i++) {
                        found = found || list.get(i).apply(command);
                        if (found) {
                            if (command[0].equals("exit")) {
                                continuar = false;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        ViewApp.end();
    }

}


