package upm.etsisi.Control;

import upm.etsisi.Model.*;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.Utility.Status;
import upm.etsisi.View.ViewTicket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrinterTicketCompany implements IPrinter {
    private final ViewTicket viewTicket = new ViewTicket();

    @Override
    public void print(Ticket ticket, String cashierId,String close) {
        TicketCompany ticketCompany = (TicketCompany) ticket;
        if(close.equals("yes")){
            if (ticketCompany.getStatus() == Status.OPEN || ticketCompany.getStatus() == Status.EMPTY) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-");
                String date = LocalDateTime.now().format(formatter);
                ticketCompany.setId(ticket.getId() + "-" + date);
                ticketCompany.setStatus(Status.CLOSE);
            }
        }


        if (ticketCompany.getType().equals("s") || close.equals("no")) {
            viewTicket.serviceIncluded();
            for (TicketItem<ProductsAndService> service : ticketCompany.getItems()) {
                if (service.getItem() instanceof ProductService) {
                    viewTicket.printProductService((ProductService) service.getItem());
                }
            }

        } else if (ticketCompany.getType().equals("c") || close.equals("no")) {
            int countService = 0;
            viewTicket.serviceIncluded();
            for (TicketItem<ProductsAndService> service : ticketCompany.getItems()) {
                if (service.getItem() instanceof ProductService) {
                    viewTicket.printProductService((ProductService) service.getItem());
                    countService = countService + 1;
                }
            }

            double total = 0.0;
            double totalDiscount = 0.0;
           viewTicket.productIncluded();
            for (TicketItem<ProductsAndService> product : ticketCompany.getItems()) {

                for (int i = 0; i < product.getQuantity(); i++) {
                    if (product.getItem() instanceof Product) {
                        Category category = null;
                        if (product.getItem() instanceof BasicProduct) {
                            category = ((BasicProduct) product.getItem()).getCategory();
                        }

                        int categoryCount = category != null ? ticketCompany.getCategoryCount(category) : 0;
                        boolean hasDiscount = categoryCount >= 2;
                        double discount = calculateDiscount((Product) product.getItem()) ;
                        double price = ((Product) product.getItem()).getPrice();
                        total += price;
                        totalDiscount += discount;

                        if (product.getItem() instanceof BasicProduct) {
                            BasicProduct basicProd = (BasicProduct) product.getItem();
                            if (basicProd.getPersonalizationList() != null && !basicProd.getPersonalizationList().isEmpty()) {
                                if (hasDiscount) {
                                    viewTicket.printProductDiscountPersonlization((BasicProduct) product.getItem(), discount);
                                } else {
                                    viewTicket.printProductPersonalization((BasicProduct) product.getItem());
                                }
                            } else {
                                if (hasDiscount) {
                                    viewTicket.printProductDiscount((BasicProduct) product.getItem(), discount);
                                } else {
                                    viewTicket.printProductBasic((BasicProduct) product.getItem());
                                }
                            }
                        } else if (product.getItem() instanceof TimedProduct) {
                            TimedProduct timedProd = (TimedProduct) product.getItem();
                            if (timedProd.getProductType() == ProductType.FOOD) {
                                viewTicket.printProductFood((TimedProduct) product.getItem(), timedProd.getActualPeople());
                            } else if (timedProd.getProductType() == ProductType.MEETING) {
                                viewTicket.printProductMeeting((TimedProduct) product.getItem(), timedProd.getActualPeople());
                            }
                            total = timedProd.getPrice() * timedProd.getActualPeople();
                        }
                        totalDiscount = discount + countService * 0.15 * total;
                        ticketCompany.setTotal(total);
                        ticketCompany.setDiscount(totalDiscount);
                        ticketCompany.setFinalPrice(total - totalDiscount);


                    }
                }

            }   if(close.equals("yes")){
                viewTicket.pricesWithService(ticketCompany);
            }


        }else if(ticketCompany.getType().equals("p") || close.equals("no")){
            double total = 0.0;
            double totalDiscount = 0.0;
            System.out.println("Product Include: ");
            for (TicketItem<ProductsAndService> product : ticketCompany.getItems()) {
                for (int i = 0; i < product.getQuantity(); i++) {
                    if (product.getItem() instanceof Product) {
                        Category category = null;
                        if (product.getItem() instanceof BasicProduct) {
                            category = ((BasicProduct) product.getItem()).getCategory();
                        }

                        int categoryCount = category != null ? ticketCompany.getCategoryCount(category) : 0;
                        boolean hasDiscount = categoryCount >= 2;
                        double discount =  calculateDiscount((Product) product.getItem()) ;
                        double price = ((Product) product.getItem()).getPrice();
                        total += price;
                        totalDiscount += discount;

                        if (product.getItem() instanceof BasicProduct) {
                            BasicProduct basicProd = (BasicProduct) product.getItem();
                            if (basicProd.getPersonalizationList() != null && !basicProd.getPersonalizationList().isEmpty()) {
                                if (hasDiscount) {
                                    viewTicket.printProductDiscountPersonlization((BasicProduct) product.getItem(), discount);

                                } else {
                                    viewTicket.printProductPersonalization((BasicProduct) product.getItem());
                                }
                            } else {
                                if (hasDiscount) {
                                    viewTicket.printProductDiscount((BasicProduct) product.getItem(), discount);
                                } else {
                                    viewTicket.printProductBasic((BasicProduct) product.getItem());
                                }
                            }
                        } else if (product.getItem() instanceof TimedProduct) {
                            TimedProduct timedProd = (TimedProduct) product.getItem();
                            if (timedProd.getProductType() == ProductType.FOOD) {
                                viewTicket.printProductFood((TimedProduct) product.getItem(), timedProd.getActualPeople());
                            } else if (timedProd.getProductType() == ProductType.MEETING) {
                                viewTicket.printProductMeeting((TimedProduct) product.getItem(), timedProd.getActualPeople());
                            }
                            total = timedProd.getPrice() * timedProd.getActualPeople();
                        }
                        ticketCompany.setTotal(total);
                        ticketCompany.setDiscount(totalDiscount);
                        ticketCompany.setFinalPrice(total - totalDiscount);


                    }
                }

            }
                viewTicket.prices(ticketCompany);


        }
        viewTicket.printOK();


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
            default:
                return 0.0;
        }
        return discount;
    }


}
