package upm.etsisi.View;

public class ViewApp {
    public static void printHelp() {
        System.out.println("\nCommands:");
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

    public static void init()
    {
        System.out.println("Welcome to the ticket module App." +
                "Ticket module. Type 'help' to see commands.");
    }

    public static void end()
    {
        System.out.println("Goodbye!");
    }
    public static final String close = "Closing application.";
    public static final String error = "Unknown command. Type 'help' for list of commands.";

}
