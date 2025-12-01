package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.Utility.Status;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewTicket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControlTicket {

    private ArrayList<Ticket> ticketList;

    private ViewTicket viewTicket;
    private static ControlTicket instance;

    public static ControlTicket getInstance() {
        if (instance == null) {
            instance = new ControlTicket();
        }
        return instance;
    }

    private ControlTicket() {
        this.ticketList = new ArrayList<>();
        this.viewTicket = new ViewTicket();
    }


    public boolean newTicket(String id, String cashierId, String userId) {
        boolean resul = false;
        if (!existTicket(id)) {
            Ticket ticket = new Ticket(id, cashierId, userId);
            viewTicket.printTicket(ticket);
            viewTicket.prices(ticket);
            ticketList.add(ticket);
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
        Product product = ControlProduct.getInstance().searchProduct(Integer.parseInt(productId));
        int amountInt = Integer.parseInt(amount);

        if (ticket != null && product != null) {
            if (ticket.getCashierId().equals(cashierId)) {
                if (ticket.getStatus() == Status.EMPTY) {
                    ticket.setStatus(Status.OPEN);
                }
                if (personalizationsList != null && personalizationsList.length > 0) {
                    if (product.getProductType() == ProductType.PERSONLIZATION) {
                        List<String> pers = new ArrayList<>();

                        for (String arg : personalizationsList) {
                            if (arg.startsWith("--p") && arg.length() > 3) {
                                pers.add(arg.substring(3));
                            }
                        }
                        String[] personalizations = pers.toArray(new String[0]);
                        String joinedPers = String.join(",", personalizations);
                        double newPrice =( (product.getPrice()*0.1) * personalizations.length )+product.getPrice() ;
                        product = new Product(product.getId(), product.getName(), product.getCategory(), newPrice, product.getExpiration(), personalizations.length, joinedPers);
                    }
                    for (int i = 0; i < amountInt; i++) {
                        if (ticket.getProducts().size() < 100) {
                            ticket.getProducts().add(product);
                            ticket.setCategoryCounter(product.getCategory(), 1);
                        }

                    }
                    viewTicket.createOK();

                } else if (product.getProductType() == ProductType.FOOD || product.getProductType() == ProductType.MEETING) {
                    if (!ticket.getProducts().contains(product)) {
                        product.setActualPeople(amountInt);
                        double newPrice = amountInt * product.getPrice();
                        product.setPrice(newPrice);

                        ticket.getProducts().add(product);

                        viewTicket.createOK();
                        product.setMaxPersonal((product.getMaxPersonal()));
                    }
                } else {
                    for (int i = 0; i < amountInt; i++) {
                        if (ticket.getProducts().size() < 100) {
                            ticket.getProducts().add(product);
                            ticket.setCategoryCounter(product.getCategory(), 1);

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

    public boolean removeProduct(String ticketId, String cashierId, String productId) {
        boolean resul = false;
        Ticket ticket = searchTicket(ticketId);
        if (ticket != null) {
            if (ticket.getCashierId().equals(cashierId)) {
                int id = Integer.parseInt(productId);
                List<Product> products = ticket.getProducts();
                Product product = ControlProduct.getInstance().searchProduct(id);
                if(products.contains(product)){
                    if ( product.getProductType() == ProductType.BASIC) {
                        products.remove(product);
                        Category category = product.getCategory();
                        ticket.setCategoryCounter(category, -1);
                    } else {
                        products.remove(product);
                    }
                }
                printTicketP(ticketId,cashierId);
                if (products.isEmpty()) {
                    ticket.setStatus(Status.EMPTY);
                }
                resul = true;
                viewTicket.removeOK();
            }
        }
        return resul;
    }


    public void printTicket(String ticketId, String cashierId) {
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
                List<Product> products = ticket.getProducts();
                products.sort(Comparator.comparing(p -> p.getName().toLowerCase()));
                double total = 0.0;
                double totalDiscount = 0.0;
                for (Product product : products) {
                    if (product != null) {
                        int categoryCount = ticket.getCategoryCount(product.getCategory());
                        boolean hasDiscount = categoryCount >= 2;
                        double discount = hasDiscount ? calculateDiscount(product) : 0.0;
                        double price = product.getPrice();
                        total += price;
                        totalDiscount += discount;
                        if (product.getPersonalizationList() != null && !product.getPersonalizationList().isEmpty()) {
                            if (hasDiscount) {
                                viewTicket.printProductDiscountPersonlization(product, discount);
                            } else {
                                viewTicket.printProductPersonalization(product);
                            }
                        } else if (product.getProductType() == ProductType.FOOD) {
                            viewTicket.printProductFood(product, product.getActualPeople());
                        } else if (product.getProductType() == ProductType.MEETING) {
                            viewTicket.printProductMeeting(product, product.getActualPeople());
                        } else {
                            if (hasDiscount) {
                                viewTicket.printProductDiscount(product, discount);
                            } else {
                                viewTicket.printProductBasic(product);
                            }

                        }
                        ticket.setTotal(total);
                        ticket.setDiscount(totalDiscount);
                        ticket.setFinalPrice(total - totalDiscount);
                    }
                }
                viewTicket.prices(ticket);
                viewTicket.printOK();
            }

        }
    }
        public void printTicketP (String ticketId, String cashierId){
            Ticket ticket = searchTicket(ticketId);
            if (ticket != null) {
                if (ticket.getCashierId().equals(cashierId)) {
                    viewTicket.printTicket(ticket);
                    List<Product> products = ticket.getProducts();
                    products.sort(Comparator.comparing(p -> p.getName().toLowerCase()));
                    double total = 0.0;
                    double totalDiscount = 0.0;
                    for (Product product : products) {
                        if (product == null) continue;
                        int categoryCount = ticket.getCategoryCount(product.getCategory());
                        boolean hasDiscount = categoryCount >= 2;
                        double discount = hasDiscount ? calculateDiscount(product) : 0.0;
                        double price = product.getPrice();
                        total += price;
                        totalDiscount += discount;
                        if (product.getPersonalizationList() != null && !product.getPersonalizationList().isEmpty()) {
                            if (hasDiscount) {
                                viewTicket.printProductDiscountPersonlization(product, discount);
                            } else {
                                viewTicket.printProductPersonalization(product);
                            }
                        } else if (product.getProductType() == ProductType.FOOD) {
                            viewTicket.printProductFood(product, product.getActualPeople());
                        } else if (product.getProductType() == ProductType.MEETING) {
                            viewTicket.printProductMeeting(product, product.getActualPeople());
                        } else {
                            if (hasDiscount) {
                                viewTicket.printProductDiscount(product, discount);
                            } else {
                                viewTicket.printProductBasic(product);
                            }

                        }
                        ticket.setTotal(total);
                        ticket.setDiscount(totalDiscount);
                        ticket.setFinalPrice(total - totalDiscount);
                    }
                }
                viewTicket.prices(ticket);

            }

        }

        public void ticketList () {
            viewTicket.ticketList(ticketList);
            viewTicket.listOK();
        }

        public double calculateDiscount (Product product){
            double discount = 0.0;
            Category category = product.getCategory();
            switch (category) {
                case MERCH:
                    discount = 0 * product.getPrice();
                    break;
                case STATIONERY:
                    discount = 0.05 * product.getPrice();
                    break;
                case CLOTHES:
                    discount = 0.07 * product.getPrice();
                    break;
                case BOOK:
                    discount = 0.10 * product.getPrice();
                    break;
                case ELECTRONICS:
                    discount = 0.03 * product.getPrice();
                    break;
            }
            return discount;
        }



}
