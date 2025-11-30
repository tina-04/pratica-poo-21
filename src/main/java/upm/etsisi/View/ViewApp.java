package upm.etsisi.View;

public class ViewApp  implements View {
    public void messageOutput(String output){
        System.out.println(output);
    }
    public  void errorInfo(){
        messageOutput("We need more information .");
    }

    public void print(String output){
        messageOutput(output);
    }

   public void error(){
        messageOutput("Unknown command. Type 'help' for list of commands.");
   }

    public static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  client add \"<name>\" <DNI> <email> <cashId>");
        System.out.println("  client remove <DNI>");
        System.out.println("  client list");
        System.out.println("  cash add [<id>] \"<name>\" <email>");
        System.out.println("  cash remove <id>");
        System.out.println("  cash list");
        System.out.println("  cash tickets <id>");
        System.out.println("  ticket new [<id>] <cashId> <userId>");
        System.out.println("  ticket add <ticketId> <cashId> <prodId> <amount> [--p<txt> --p<txt>]");
        System.out.println("  ticket remove <ticketId> <cashId> <prodId>");
        System.out.println("  ticket print <ticketId> <cashId>");
        System.out.println("  ticket list");
        System.out.println("  prod add <id> \"<name>\" <category> <price>");
        System.out.println("  prod update <id> NAME|CATEGORY|PRICE <value>");
        System.out.println("  prod addFood [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>");
        System.out.println("  prod addMeeting [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>");
        System.out.println("  prod list");
        System.out.println("  prod remove <id>");
        System.out.println("  help");
        System.out.println("  echo “<text>”");
        System.out.println("  exit");
    }


    public static void init()
    {
        System.out.print("Welcome to the ticket module App." +
                "\nTicket module. Type 'help' to see commands.");
    }

    public static void end()
    {
        System.out.println("Goodbye!");
    }





}
