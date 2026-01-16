package upm.etsisi.Control;

import upm.etsisi.Model.*;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.View.View;
import upm.etsisi.View.ViewTicket;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class PrinterTicket  implements IPrinter{
    private final ViewTicket viewTicket = new ViewTicket();

    @Override
    public void print(Ticket ticket,String cashierId) {
        if (ticket != null) {
            if (!(ticket instanceof TicketCompany) && ticket.getCashierId().equals(cashierId)) {
                viewTicket.printTicket(ticket);
                viewTicket.product();
                List<Product> products = (List<Product>) ticket.getPs();
                products.sort(Comparator.comparing(p -> p.getName().toLowerCase()));
                double total = 0.0;
                double totalDiscount = 0.0;
                for (Product product : products) {
                    if (product != null){

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
