package upm.etsisi.Control;

import upm.etsisi.Model.*;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.Utility.Status;
import upm.etsisi.View.View;
import upm.etsisi.View.ViewTicket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PrinterTicket implements IPrinter {
    private final ViewTicket viewTicket = new ViewTicket();

    @Override
    public void print(Ticket<?> ticket, String cashierId,String close) {
        if(close.equals("yes")){
            if (ticket.getStatus() == Status.OPEN || ticket.getStatus() == Status.EMPTY) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-");
                String date = LocalDateTime.now().format(formatter);
                ticket.setId(ticket.getId() + "-" + date);
                ticket.setStatus(Status.CLOSE);
            }
        }

        viewTicket.productIncluded();
        double total = 0.0;
        double totalDiscount = 0.0;
        for (TicketItem<ProductsAndService> product : ticket.getItems()) {

            for (int i = 0; i < product.getQuantity(); i++) {
                if (product.getItem() instanceof Product) {
                    Category category = null;
                    if (product.getItem() instanceof BasicProduct) {
                        category = ((BasicProduct) product.getItem()).getCategory();
                    }


                    if (product.getItem() instanceof BasicProduct) {

                        BasicProduct basicProd = (BasicProduct) product.getItem();
                        double price = ((Product) product.getItem()).getPrice();
                        double discount = calculateDiscount(basicProd);

                        int categoryCount = category != null ? ticket.getCategoryCount(category) : 0;
                        boolean hasDiscount = categoryCount >= 2;

                        total += price;
                        totalDiscount += discount;

                        if (hasDiscount) {
                            discount = calculateDiscount(basicProd);
                        }
                        if (basicProd.getPersonalizationList() != null && basicProd.getProductType()==ProductType.PERSONALIZATION) {

                            viewTicket.printProductDiscountPersonlization((Product) product.getItem(), discount);

                        } else {

                            viewTicket.printProductDiscount((Product) product.getItem(), discount);

                        }
                    } else if (product.getItem() instanceof TimedProduct) {
                        TimedProduct timedProd = (TimedProduct) product.getItem();
                        if (timedProd.getProductType() == ProductType.FOOD) {
                            viewTicket.printProductFood((TimedProduct) product.getItem(), timedProd.getActualPeople());
                        } else if (timedProd.getProductType() == ProductType.MEETING) {
                            viewTicket.printProductMeeting((TimedProduct) product.getItem(), timedProd.getActualPeople());
                        }
                        total+=timedProd.getPrice()*timedProd.getActualPeople();
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
