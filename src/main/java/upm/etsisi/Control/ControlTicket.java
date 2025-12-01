package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.BasicProduct;
import upm.etsisi.Model.TimedProduct;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.Utility.Status;
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

        if (ticket != null) {
            if (ticket.getCashierId().equals(cashierId)) {
                if (ticket.getStatus() == Status.EMPTY) {
                    ticket.setStatus(Status.OPEN);
                }
                if (personalizationsList != null && personalizationsList.length > 0) {
                    if (product instanceof BasicProduct) {
                        BasicProduct basicProd = (BasicProduct) product;
                        if (basicProd.getProductType() == ProductType.PERSONLIZATION) {
                            List<String> pers = new ArrayList<>();

                            for (String arg : personalizationsList) {
                                if (arg.startsWith("--p") && arg.length() > 3) {
                                    pers.add(arg.substring(3));
                                }
                            }
                            String[] personalizations = pers.toArray(new String[0]);
                            String joinedPers = String.join(",", personalizations);
                            double newPrice = ((basicProd.getPrice() * 0.1) * personalizations.length) + basicProd.getPrice();
                            product = new BasicProduct(basicProd.getId(), basicProd.getName(), basicProd.getCategory(), newPrice, personalizations.length, joinedPers);
                        }
                    }
                    for (int i = 0; i < amountInt; i++) {
                        if (ticket.getProducts().size() < 100) {
                            ticket.getProducts().add(product);
                            if (product instanceof BasicProduct) {
                                ticket.setCategoryCounter(((BasicProduct) product).getCategory(), 1);
                            }
                        }

                    }
                    viewTicket.createOK();

                } else if (product instanceof TimedProduct) {
                    TimedProduct timedProd = (TimedProduct) product;
                    if (timedProd.getProductType() == ProductType.FOOD || timedProd.getProductType() == ProductType.MEETING) {
                        if (!ticket.getProducts().contains(timedProd)) {
                            timedProd.setAcutalPeople(amountInt);
                            double newPrice = amountInt * timedProd.getPrice();
                            timedProd.setPrice(newPrice);

                            ticket.getProducts().add(timedProd);

                            viewTicket.createOK();
                            timedProd.setMaxPersonal((timedProd.getMaxPersonal()));
                        }
                    }
                } else {
                    for (int i = 0; i < amountInt; i++) {
                        if (ticket.getProducts().size() < 100) {
                            ticket.getProducts().add(product);
                            if (product instanceof BasicProduct) {
                                ticket.setCategoryCounter(((BasicProduct) product).getCategory(), 1);
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
                List<Product> products = ticket.getProducts();
                Product product = ControlProduct.getInstance().searchProduct(Integer.parseInt(productId));
                if (products.contains(product)) {
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
                                viewTicket.printProductFood(product, timedProd.getAcutalPeople());
                            } else if (timedProd.getProductType() == ProductType.MEETING) {
                                viewTicket.printProductMeeting(product, timedProd.getAcutalPeople());
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

    }

    public void printTicketP(String ticketId, String cashierId) {
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
                            viewTicket.printProductFood(product, timedProd.getAcutalPeople());
                        } else if (timedProd.getProductType() == ProductType.MEETING) {
                            viewTicket.printProductMeeting(product, timedProd.getAcutalPeople());
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


}