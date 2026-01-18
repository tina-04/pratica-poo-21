package upm.etsisi.Control;

import upm.etsisi.Model.*;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.Utility.Status;
import upm.etsisi.View.ViewTicket;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControlTicket {

    private ArrayList<Ticket> ticketList;

    private ViewTicket viewTicket;
    private static ControlTicket instance;
    private Map<String, Ticket> list = new HashMap<>();

    private IPrinter printer;

    private static final String RUTA = "src/main/java/upm/etsisi/Persistence/Tickets.csv";


    public static ControlTicket getInstance() {
        if (instance == null) {
            instance = new ControlTicket();
        }
        return instance;
    }

    private ControlTicket() {
        this.ticketList = new ArrayList<>();
        this.viewTicket = new ViewTicket();
        loadTickets();
    }

    public void setPrinter(IPrinter printer) {
        this.printer = printer;
    }

    public boolean newTicket(String id, String cashierId, String userId, String type) {
        boolean resul = false;
        if (type == null) {
            if (!list.containsKey(id)) {
                Ticket ticket = new Ticket(id, cashierId, userId);
                viewTicket.printTicket(ticket);
                viewTicket.prices(ticket);
                list.put(id, ticket);
                viewTicket.newOk();
                ControlCashier.getInstance().addTicket(cashierId, ticket);
                ControlClient.getInstance().addTicket(userId, ticket);
                ticketList.add(ticket);
                resul = true;
            }
        } else if (type.equals("s") || type.equals("c")) {
            TicketCompany ticket = new TicketCompany(id, cashierId, userId, type);
            viewTicket.printTicket(ticket);
            list.put(id, ticket);
            viewTicket.newOk();
            ControlCashier.getInstance().addTicket(cashierId, ticket);
            ControlClient.getInstance().addTicket(userId, ticket);
            ticketList.add(ticket);
            resul = true;

        } else {
            TicketCompany ticket = new TicketCompany(id, cashierId, userId, type);
            viewTicket.printTicket(ticket);
            list.put(id, ticket);
            viewTicket.newOk();
            ControlCashier.getInstance().addTicket(cashierId, ticket);
            ControlClient.getInstance().addTicket(userId, ticket);
            ticketList.add(ticket);
            resul = true;
        }


        return resul;
    }


    public <T extends ProductsAndService> void addProduct(String ticketId, String cashierId, String productId, String amount, String[] personalizationsList) {
        Ticket ticket = list.get(ticketId);
        int amountInt;

        ProductsAndService ps = ControlProduct.getInstance().getProductOrService(productId);
        boolean canRepeat = false;
        if (ticket != null) {
            if (ticket.getStatus() == Status.EMPTY) {
                ticket.setStatus(Status.OPEN);
            }

            if (ticket instanceof TicketCompany) {
                TicketCompany ticketCompany = (TicketCompany) ticket;
                if (ticketCompany.getType().equals("c")) {

                    if (ps instanceof ProductService) {
                        ProductService service = (ProductService) ps;
                        ticket.addItem(service, 1, canRepeat);
                    }else if (ps instanceof TimedProduct) {
                        amountInt = Integer.parseInt(amount);
                        TimedProduct timedProduct = (TimedProduct) ps;
                        if (timedProduct.getProductType() == ProductType.FOOD && ControlProduct.getInstance().validDateFood(timedProduct.getExpiration())) {
                            ticket.addItem(timedProduct, 1, canRepeat);

                        } else if (timedProduct.getProductType() == ProductType.MEETING && ControlProduct.getInstance().validDateMeeting(timedProduct.getExpiration())) {
                            ticket.addItem(timedProduct, 1, canRepeat);
                        }
                        timedProduct.setActualPeople(amountInt);
                    } else if (ps instanceof BasicProduct) {
                        BasicProduct basicProd = (BasicProduct) ps;
                        amountInt = Integer.parseInt(amount);
                        if (basicProd.getProductType() == ProductType.PERSONALIZATION && personalizationsList != null) {
                            List<String> pers = new ArrayList<>();
                            for (String arg : personalizationsList) {
                                if (arg.startsWith("--p") && arg.length() > 3) {
                                    pers.add(arg.substring(3));
                                }
                            }
                            String[] personalizations = pers.toArray(new String[0]);
                            String joinedPers = String.join(",", personalizations);
                            double newPrice = ((basicProd.getPrice() * 0.1) * personalizations.length) + basicProd.getPrice();
                            basicProd = new BasicProduct(String.valueOf(basicProd.getId()), basicProd.getName(), basicProd.getCategory(), newPrice, basicProd.getMaxPersonal(), joinedPers);
                            canRepeat = true;
                            ticket.addItem(basicProd, amountInt, canRepeat);
                        } else {
                            canRepeat = true;
                            ticket.addItem(basicProd, amountInt, canRepeat);
                        }
                    }

                } else if (ticketCompany.getType().equals("s") && ps instanceof ProductService) {
                    ProductService service = (ProductService) ps;
                    ticket.addItem(service, 1, canRepeat);
                }
            } else if (ps instanceof Product) {
                amountInt = Integer.parseInt(amount);
                if (ps instanceof TimedProduct) {
                    TimedProduct timedProduct = (TimedProduct) ps;
                    if (timedProduct.getProductType() == ProductType.FOOD && ControlProduct.getInstance().validDateFood(timedProduct.getExpiration())) {
                        ticket.addItem(timedProduct, 1, canRepeat);

                    } else if (timedProduct.getProductType() == ProductType.MEETING && ControlProduct.getInstance().validDateMeeting(timedProduct.getExpiration())) {
                        ticket.addItem(timedProduct, 1, canRepeat);
                    }
                    timedProduct.setActualPeople(amountInt);
                } else if (ps instanceof BasicProduct) {
                    BasicProduct basicProd = (BasicProduct) ps;
                    if (basicProd.getProductType() == ProductType.PERSONALIZATION && personalizationsList != null) {
                        List<String> pers = new ArrayList<>();
                        for (String arg : personalizationsList) {
                            if (arg.startsWith("--p") && arg.length() > 3) {
                                pers.add(arg.substring(3));
                            }
                        }
                        String[] personalizations = pers.toArray(new String[0]);
                        String joinedPers = String.join(",", personalizations);
                        double newPrice = ((basicProd.getPrice() * 0.1) * pers.size()) + basicProd.getPrice();
                        BasicProduct newBasic = new BasicProduct(String.valueOf(basicProd.getId()), basicProd.getName(), basicProd.getCategory(), newPrice, basicProd.getMaxPersonal(), joinedPers);
                        canRepeat = true;
                        ticket.addItem(newBasic, amountInt, canRepeat);
                    } else {
                        canRepeat = true;
                        ticket.addItem(basicProd, amountInt, canRepeat);
                    }
                }
            }
        }



            printer =getPrinter(ticket);
            printer.print(ticket,cashierId,"no");
            viewTicket.createOK();

    }

    public void removeTicker(List<Ticket> ticket) {
        for (int i = 0; i < ticket.size(); i++) {
            if (list.containsKey(ticket.get(i).getId())) {
                ticketList.remove(ticket.get(i));

            }
        }
    }

    public void removeProduct(String ticketId, String cashierId, String productId) {
        Ticket<?> ticket = list.get(ticketId);
        if (ticket != null) {
            if (ticket.getCashierId().equals(cashierId)) {
                TicketItem<ProductsAndService> itemToRemove = null;
                int id = Integer.parseInt(productId);
                for (TicketItem<ProductsAndService> ti : ticket.getItems()) {
                    if (ti.getItem().getId().equals(productId)) {
                        itemToRemove = ti;
                        break;
                    }
                }
                if (itemToRemove != null) {
                    ProductsAndService item = itemToRemove.getItem();
                    if (item instanceof BasicProduct) {
                        BasicProduct bp = (BasicProduct) item;
                        ticket.setCategoryCounter(bp.getCategory(), -itemToRemove.getQuantity());
                    }

                    ticket.getItems().remove(itemToRemove);
                }

                if (ticket.getItems().isEmpty()) {
                    ticket.setStatus(Status.EMPTY);
                }
                printer =getPrinter(ticket);
                printer.print(ticket,cashierId,"no");
                viewTicket.removeOK();
            }
        }
    }

    public IPrinter getPrinter(Ticket ticket) {
        if (ticket instanceof TicketCompany) {
            printer = new PrinterTicketCompany();
        } else {
            printer = new PrinterTicket();
        }
        return printer;
    }
    public boolean hasProductAndService(TicketCompany ticket) {
        boolean hasProduct = false;
        boolean hasService = false;

        for (TicketItem<ProductsAndService> ti : ticket.getItems()) {
            ProductsAndService item = ti.getItem();

            if (item instanceof Product) {
                hasProduct = true;
            }
            if (item instanceof ProductService) {
                hasService = true;
            }

            if (hasProduct && hasService) {
                return true;
            }
        }
        return false;
    }
    public void printTicket(String ticketId, String cashierId) {
        Ticket ticket = list.get(ticketId);
        if (ticket instanceof TicketCompany ) {
            TicketCompany ticketCompany = (TicketCompany) ticket;

            if (ticketCompany.getType().equals("c") && hasProductAndService(ticketCompany)) {
                printer=getPrinter(ticket);
                printer.print(ticket, cashierId, "yes");
            }else{
                printer=getPrinter(ticket);
                printer.print(ticket, cashierId, "yes");
            }
        }else{
            printer=getPrinter(ticket);
            printer.print(ticket, cashierId, "yes");
        }

    }



    public void ticketList() {
        viewTicket.ticketList(ticketList);
        viewTicket.listOK();
    }

    public double calculateDiscount(Product product) {
        if (!(product instanceof BasicProduct)) {
            return 0.0;
        }

        BasicProduct basicProd = (BasicProduct) product;
        double discount = 0.0;
        Category category = basicProd.getCategory();
        switch (category) {
            case MERCH:
                discount = 0 * basicProd.getPrice();
                break;
            case STATIONERY:
                discount = 0.05 * basicProd.getPrice();
                break;
            case CLOTHES:
                discount = 0.07 * basicProd.getPrice();
                break;
            case BOOK:
                discount = 0.10 * basicProd.getPrice();
                break;
            case ELECTRONICS:
                discount = 0.03 * basicProd.getPrice();
                break;
        }
        return discount;
    }

    public void saveTickets(){
        File file = new File(RUTA);

        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Ticket<?> ticket : list.values()) {
                    StringBuilder sb = new StringBuilder();

                    StringBuilder productsSb = new StringBuilder();

                    for (TicketItem<ProductsAndService> product : ticket.getItems()) {
                        productsSb.append(product.getItem().getId()).append(",").append(product.getQuantity()).append(",");
                    }
                    String productIds = productsSb.length() > 0 ?
                            productsSb.substring(0, productsSb.length() - 1) : "NONE";

                    if (ticket instanceof TicketCompany) {
                        TicketCompany tc = (TicketCompany) ticket;
                        // TICKET_COMPANY;ID;CASHIER;CLIENT;STATUS;TYPE;PRODUCTOS
                        sb.append("TICKET_COMPANY").append(";")
                                .append(tc.getId()).append(";")
                                .append(tc.getCashierId()).append(";")
                                .append(tc.getClientId() != null ? tc.getClientId() : "null").append(";") // Safety check
                                .append(tc.getStatus()).append(";")
                                .append(tc.getType()).append(";") // "s" o "c"
                                .append(productIds);

                    } else {
                        // TICKET;ID;CASHIER;CLIENT;STATUS;PRODUCTOS
                        sb.append("TICKET").append(";")
                                .append(ticket.getId()).append(";")
                                .append(ticket.getCashierId()).append(";")
                                .append(ticket.getClientId() != null ? ticket.getClientId() : "null").append(";")
                                .append(ticket.getStatus()).append(";")
                                .append(productIds);
                    }

                    writer.write(sb.toString());
                    writer.newLine();
                }
                System.out.println("Datos guardados en: " + RUTA);
            }
        } catch (IOException ignored) {}
    }

    public void loadTickets(){
        File file = new File(RUTA);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            list.clear();
            ticketList.clear();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(";");
                String rowType = data[0];

                try {
                    Ticket ticket = null;
                    String productsString = "";

                    if (rowType.equals("TICKET_COMPANY")) {
                        String id = data[1];
                        String cashierId = data[2];
                        String clientId = data[3].equals("null") ? null : data[3];
                        Status status = Status.valueOf(data[4]);
                        String type = data[5]; // "s" o "c"
                        productsString = (data.length > 6) ? data[6] : "NONE";

                        TicketCompany tc = new TicketCompany(id, cashierId, clientId, type);
                        tc.setStatus(status);
                        ticket = tc;

                    } else if (rowType.equals("TICKET")) {
                        String id = data[1];
                        String cashierId = data[2];
                        String clientId = data[3].equals("null") ? null : data[3];
                        Status status = Status.valueOf(data[4]);
                        productsString = (data.length > 5) ? data[5] : "NONE";

                        ticket = new Ticket(id, cashierId, clientId);
                        ticket.setStatus(status);
                    }

                    if (ticket != null && !productsString.equals("NONE") && !productsString.isEmpty()) {
                        String[] prodIds = productsString.split(",");

                        for (String prodId : prodIds) {
                            ProductsAndService item = ControlProduct.getInstance().getProductOrService(prodId);
                            boolean canRepeat = item instanceof BasicProduct;
                            int quantity = 1;
                            if (item instanceof BasicProduct) {

                                quantity = 1;
                            } else if (item instanceof TimedProduct) {
                                quantity = 1;
                            }

                            if (item != null) {
                                ticket.addItem(item,quantity,canRepeat);
                            }
                        }

                        list.put(ticket.getId(), ticket);
                        ticketList.add(ticket);

                        ControlCashier.getInstance().addTicket(ticket.getCashierId(), ticket);
                        if (ticket.getClientId() != null) {
                            ControlClient.getInstance().addTicket(ticket.getClientId(), ticket); // Revisa que este método acepte (id, ticket)
                        }
                    }

                } catch (Exception e) {
                    System.err.println("Error cargando ticket: " + line + " -> " + e.getMessage());
                }
            }
        } catch (IOException ignored) {}
    }
}
