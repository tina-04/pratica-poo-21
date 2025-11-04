package upm.etsisi.View;

public class ViewApp  implements View {
    public void messageOutput(String output){
        System.out.println(output);
    }
    public  void errorInfo(){
        messageOutput("We need more information .");
    }
    public void close(){
        messageOutput("Closing application.");
    }

   public void error(){
        messageOutput("Unknown command. Type 'help' for list of commands.");
   }
   public void errorCommand(){
        messageOutput("Error: invalid command.");
   }
    public static void printHelp() {
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
        System.out.println("\nCategories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS");
        System.out.println("Discounts if there are ≥2 units in the category: MERCH 0%, PAPELERIA 5%, ROPA 7%, LIBRO 10%, ELECTRONICA 3%.");
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
