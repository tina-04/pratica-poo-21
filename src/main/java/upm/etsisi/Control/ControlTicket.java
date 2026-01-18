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
            if (!existTicket(id)) {
                Ticket ticket = new Ticket(id, cashierId, userId);
                viewTicket.printTicket(ticket);
                viewTicket.prices(ticket);
                list.put(id, ticket);
                viewTicket.newOk();
                ControlCashier.getInstance().addTicket(cashierId, ticket);
                ControlClient.getInstance().addTicket(userId, ticket);
                resul = true;
            }
        } else if (type.equals("s") || type.equals("c")) {
            TicketCompany ticket = new TicketCompany(id, cashierId, userId, type);
            viewTicket.printTicket(ticket);
            list.put(id, ticket);
            viewTicket.newOk();
            ControlCashier.getInstance().addTicket(cashierId, ticket);
            ControlClient.getInstance().addTicket(userId, ticket);
            resul = true;

        } else {
            TicketCompany ticket = new TicketCompany(id, cashierId, userId, type);
            viewTicket.printTicket(ticket);
            list.put(id, ticket);
            viewTicket.newOk();
            ControlCashier.getInstance().addTicket(cashierId, ticket);
            ControlClient.getInstance().addTicket(userId, ticket);
            resul = true;
        }


        return resul;
    }


    public boolean existTicket(String id) {
        boolean exist = false;
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i) != null) {
                if (ticketList.get(i).getId().equalsIgnoreCase(id)) {
                    exist = true;
                }
            }
        }

        return exist;
    }

    public Ticket searchTicket(String id) {
        Ticket ticket = null;
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i).getId().equalsIgnoreCase(id)) {
                ticket = ticketList.get(i);
            }
        }
        return ticket;
    }


    public void addProduct(String ticketId, String cashierId, String productId, String amount, String[] personalizationsList) {
        Ticket ticket = searchTicket(ticketId);
        Ticket ticket1 = list.get(ticketId);
        int amountInt;
        ProductsAndService ps = ControlProduct.getInstance().getProductOrService(productId);
        if (ticket1 instanceof TicketCompany) {
            TicketCompany ticketCompany1 = (TicketCompany) ticket1;
            if (ticketCompany1.getType().equals("s") && ps instanceof ProductService) {
                if (ticketCompany1.getCashierId().equals(cashierId)) {
                    if (ticketCompany1.getStatus() == Status.EMPTY) {
                        ticketCompany1.setStatus(Status.OPEN);
                    }
                }
                ProductService service = (ProductService) ps;
                ticketCompany1.getPs().put(productId, service);
                viewTicket.printProductService(service);

            } else if (ticketCompany1.getType().equals("c")) {
                if (ticketCompany1.getCashierId().equals(cashierId)) {
                    if (ticketCompany1.getStatus() == Status.EMPTY) {
                        ticketCompany1.setStatus(Status.OPEN);
                    }
                    if (ps instanceof ProductService) {
                        ticketCompany1.getPs().put(productId, ps);
                        viewTicket.printAll(ps);
                    } else {
                        if (ps instanceof TimedProduct) {
                            amountInt = Integer.parseInt(amount);
                            TimedProduct timedProd = (TimedProduct) ps;
                            if (timedProd.getProductType() == ProductType.FOOD || timedProd.getProductType() == ProductType.MEETING) {
                                if (!ticketCompany1.getPs().containsValue(timedProd)) {
                                    timedProd.setActualPeople(amountInt);
                                    double newPrice = amountInt * timedProd.getPrice();
                                    timedProd.setPrice(newPrice);

                                    if (timedProd.getProductType() == ProductType.FOOD) {
                                        if (ControlProduct.getInstance().validDateFood(timedProd.getExpiration())) {
                                            ticketCompany1.getPs().put(timedProd.getId(), timedProd);
                                        }
                                    } else if (ControlProduct.getInstance().validDateMeeting(timedProd.getExpiration())) {
                                        ticketCompany1.getPs().put(timedProd.getId(), timedProd);
                                        viewTicket.printProductMeeting(timedProd,amountInt);
                                    }

                                    timedProd.setMaxPersonal((timedProd.getMaxPersonal()));
                                }
                            }
                        } else if (personalizationsList != null && personalizationsList.length > 0) {
                            if (ps instanceof BasicProduct) {
                                BasicProduct basicProd = (BasicProduct) ps;
                                if (basicProd.getProductType() == ProductType.PERSONALIZATION) {
                                    List<String> pers = new ArrayList<>();

                                    for (String arg : personalizationsList) {
                                        if (arg.startsWith("--p") && arg.length() > 3) {
                                            pers.add(arg.substring(3));
                                        }
                                    }
                                    String[] personalizations = pers.toArray(new String[0]);
                                    String joinedPers = String.join(",", personalizations);
                                    double newPrice = ((basicProd.getPrice() * 0.1) * personalizations.length) + basicProd.getPrice();
                                    ps = new BasicProduct(String.valueOf(basicProd.getId()), basicProd.getName(), basicProd.getCategory(), newPrice, basicProd.getMaxPersonal(), joinedPers);
                                }
                            }
                            amountInt = Integer.parseInt(amount);
                            for (int i = 0; i < amountInt; i++) {
                                if (ticketCompany1.getPs().size() < 100) {
                                    ticketCompany1.getPs().put(productId, ps);
                                    if (ps instanceof BasicProduct) {
                                        ticket.setCategoryCounter(((BasicProduct) ps).getCategory(), 1);
                                    }
                                }

                            }

                        } else {
                            amountInt = Integer.parseInt(amount);
                            for (int i = 0; i < amountInt; i++) {
                                if (ticketCompany1.getPs().size() < 100) {
                                    ticketCompany1.getPs().put(productId, ps);
                                    if (ps instanceof BasicProduct) {
                                        ticket.setCategoryCounter(((BasicProduct) ps).getCategory(), 1);
                                    }
                                }
                            }
                        }
                    }


                }
            }

        } else if (ticket1 instanceof Ticket && ps instanceof Product) {
            Product product = (Product) ps;
            if (ticket != null && product != null) {
                if (ticket.getCashierId().equals(cashierId)) {
                    if (ticket.getStatus() == Status.EMPTY) {
                        ticket.setStatus(Status.OPEN);
                    }
                    amountInt = Integer.parseInt(amount);
                    if (product instanceof TimedProduct) {
                        TimedProduct timedProd = (TimedProduct) product;
                        if (timedProd.getProductType() == ProductType.FOOD || timedProd.getProductType() == ProductType.MEETING) {
                            if (!ticket.getPs().containsValue(timedProd)) {
                                timedProd.setActualPeople(amountInt);
                                double newPrice = amountInt * timedProd.getPrice();
                                timedProd.setPrice(newPrice);

                                if (timedProd.getProductType() == ProductType.FOOD) {
                                    if (ControlProduct.getInstance().validDateFood(timedProd.getExpiration())) {
                                        ticket.getPs().put(timedProd.getId(), timedProd);
                                    }
                                } else if (ControlProduct.getInstance().validDateMeeting(timedProd.getExpiration())) {
                                    ticket.getPs().put(timedProd.getId(), timedProd);
                                }

                                viewTicket.createOK();
                                timedProd.setMaxPersonal((timedProd.getMaxPersonal()));
                            }
                        }
                    } else if (personalizationsList != null && personalizationsList.length > 0) {
                        if (product instanceof BasicProduct) {
                            BasicProduct basicProd = (BasicProduct) product;
                            if (basicProd.getProductType() == ProductType.PERSONALIZATION) {
                                List<String> pers = new ArrayList<>();

                                for (String arg : personalizationsList) {
                                    if (arg.startsWith("--p") && arg.length() > 3) {
                                        pers.add(arg.substring(3));
                                    }
                                }
                                String[] personalizations = pers.toArray(new String[0]);
                                String joinedPers = String.join(",", personalizations);
                                double newPrice = ((basicProd.getPrice() * 0.1) * personalizations.length) + basicProd.getPrice();
                                product = new BasicProduct(String.valueOf(basicProd.getId()), basicProd.getName(), basicProd.getCategory(), newPrice, basicProd.getMaxPersonal(), joinedPers);
                            }
                        }
                        for (int i = 0; i < amountInt; i++) {
                            if (ticket.getPs().size() < 100) {
                                ticket.getPs().put(productId, product);
                                if (product instanceof BasicProduct) {
                                    ticket.setCategoryCounter(((BasicProduct) product).getCategory(), 1);
                                }
                            }

                        }
                        viewTicket.createOK();
                    } else {
                        for (int i = 0; i < amountInt; i++) {
                            if (ticket.getPs().size() < 100) {
                                ticket.getPs().put(productId, product);
                                if (product instanceof BasicProduct) {
                                    ticket.setCategoryCounter(((BasicProduct) product).getCategory(), 1);
                                }
                            }
                        }
                    }
                }
            }
        }


        printTicketP(ticketId, cashierId);

        viewTicket.createOK();

    }

    public void removeTicker(List<Ticket> ticket) {
        for (int i = 0; i < ticket.size(); i++) {
            if (existTicket(ticket.get(i).getId())) {
                ticketList.remove(ticket.get(i));

            }
        }
    }

    public void removeProduct(String ticketId, String cashierId, String productId) {
        Ticket ticket = searchTicket(ticketId);
        if (ticket != null) {
            if (ticket.getCashierId().equals(cashierId)) {
                int id = Integer.parseInt(productId);
                Map<String, ProductsAndService> products = ticket.getPs();
                Product product = ControlProduct.getInstance().getProduct(String.valueOf(id));
                if (product != null) {
                    if (product instanceof BasicProduct) {
                        BasicProduct basicProd = (BasicProduct) product;
                        if (basicProd.getProductType() == ProductType.BASIC) {
                            products.remove(basicProd);
                            Category cat = basicProd.getCategory();
                            ticket.setCategoryCounter(cat, -1);
                        } else {
                            products.remove(basicProd);
                        }
                    } else {
                        products.remove(product);
                    }
                }
                printTicketP(ticketId, cashierId);
                if (products.isEmpty()) {
                    ticket.setStatus(Status.EMPTY);
                }

                viewTicket.removeOK();
            }
        }
    }


    /*public void printTicket(String ticketId, String cashierId) {
        Ticket ticket = searchTicket(ticketId);
        if (ticket != null) {
            if (ticket.getCashierId().equals(cashierId)) {
                if (ticket.getStatus() == Status.OPEN || ticket.getStatus() == Status.EMPTY) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-");
                    String date = LocalDateTime.now().format(formatter);
                    ticket.setId(ticket.getId() + "-" + date);
                    ticket.setStatus(Status.CLOSE);
                }
                viewTicket.printTicket(ticket);
                List<Product> products = (List<Product>) ticket.getPs();
                products.sort(Comparator.comparing(p -> p.getName().toLowerCase()));
                double total = 0.0;
                double totalDiscount = 0.0;
                for (Product product : products) {
                    if (product != null) {
                        Category category = null;
                        if (product instanceof BasicProduct) {
                            category = ((BasicProduct) product).getCategory();
                        }
                        int categoryCount = category != null ? ticket.getCategoryCount(category) : 0;
                        boolean hasDiscount = categoryCount >= 2;
                        double discount = hasDiscount ? calculateDiscount(product) : 0.0;
                        double price = product.getPrice();
                        total += price;
                        totalDiscount += discount;

                        if (product instanceof BasicProduct) {
                            BasicProduct basicProd = (BasicProduct) product;
                            if (basicProd.getPersonalizationList() != null && !basicProd.getPersonalizationList().isEmpty()) {
                                if (hasDiscount) {
                                    viewTicket.printProductDiscountPersonlization(product, discount);
                                } else {
                                    viewTicket.printProductPersonalization(product);
                                }
                            } else {
                                if (hasDiscount) {
                                    viewTicket.printProductDiscount(product, discount);
                                } else {
                                    viewTicket.printProductBasic(product);
                                }
                            }
                        } else if (product instanceof TimedProduct) {
                            TimedProduct timedProd = (TimedProduct) product;
                            if (timedProd.getProductType() == ProductType.FOOD) {
                                viewTicket.printProductFood(product, timedProd.getActualPeople());
                            } else if (timedProd.getProductType() == ProductType.MEETING) {
                                viewTicket.printProductMeeting(product, timedProd.getActualPeople());
                            }
                        }

                        ticket.setTotal(total);
                        ticket.setDiscount(totalDiscount);
                        ticket.setFinalPrice(total - totalDiscount);
                    }
                }
            }
            viewTicket.prices(ticket);
            viewTicket.printOK();
        }

    }*/

    public void printTicket(String ticketId, String cashierId) {
        Ticket ticket = list.get(ticketId);

        if (ticket != null && ticket.getCashierId().equals(cashierId)) {

            if (ticket.getStatus() == Status.OPEN || ticket.getStatus() == Status.EMPTY) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-");
                String date = LocalDateTime.now().format(formatter);
                ticket.setId(ticket.getId() + "-" + date);
                ticket.setStatus(Status.CLOSE);
            }

            if (printer != null) {
                printer.print(ticket, cashierId);
            } else {
                viewTicket.printTicket(ticket);
                viewTicket.prices(ticket);
            }

            viewTicket.printOK();
        }
    }



    public void printTicketP(String ticketId, String cashierId) {
        Ticket ticket = searchTicket(ticketId);
        if (ticket != null) {
            if (ticket.getCashierId().equals(cashierId)) {
                viewTicket.printTicket(ticket);
                List<Product> products = (List<Product>) ticket.getPs();
                products.sort(Comparator.comparing(p -> p.getName().toLowerCase()));
                double total = 0.0;
                double totalDiscount = 0.0;
                for (Product product : products) {
                    if (product != null) {

                        Category category = null;
                        if (product instanceof BasicProduct) {
                            category = ((BasicProduct) product).getCategory();
                        }
                        int categoryCount = category != null ? ticket.getCategoryCount(category) : 0;
                        boolean hasDiscount = categoryCount >= 2;
                        double discount = hasDiscount ? calculateDiscount(product) : 0.0;
                        double price = product.getPrice();
                        total += price;
                        totalDiscount += discount;

                        if (product instanceof BasicProduct) {
                            BasicProduct basicProd = (BasicProduct) product;
                            if (basicProd.getPersonalizationList() != null && !basicProd.getPersonalizationList().isEmpty()) {
                                if (hasDiscount) {
                                    viewTicket.printProductDiscountPersonlization(product, discount);
                                } else {
                                    viewTicket.printProductPersonalization(product);
                                }
                            } else {
                                if (hasDiscount) {
                                    viewTicket.printProductDiscount(product, discount);
                                } else {
                                    viewTicket.printProductBasic(product);
                                }
                            }
                        } else if (product instanceof TimedProduct) {
                            TimedProduct timedProd = (TimedProduct) product;
                            if (timedProd.getProductType() == ProductType.FOOD) {
                                viewTicket.printProductFood(product, timedProd.getActualPeople());
                            } else if (timedProd.getProductType() == ProductType.MEETING) {
                                viewTicket.printProductMeeting(product, timedProd.getActualPeople());
                            }
                        }

                        ticket.setTotal(total);
                        ticket.setDiscount(totalDiscount);
                        ticket.setFinalPrice(total - totalDiscount);
                    }
                }
            }
            viewTicket.prices(ticket);

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
                for (Ticket ticket : list.values()) {
                    StringBuilder sb = new StringBuilder();

                    StringBuilder productsSb = new StringBuilder();
                    Map<String, ProductsAndService> psMap = ticket.getPs();

                    for (String prodId : psMap.keySet()) {
                        productsSb.append(prodId).append(",");
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

                            if (item != null) {
                                ticket.getPs().put(item.getId(), item);
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
