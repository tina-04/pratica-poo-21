package upm.etsisi.Control;

import upm.etsisi.Model.*;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.View.ViewProduct;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.io.*;

public class ControlProduct {
    private Map<String, ProductsAndService> ps = new HashMap<>();
    private int serviceCounter = 1;

    private int numProducts;
    private final int MAX_PRODUCT = 200;
    private final int MAX_PEOPLE = 100;
    private ViewProduct viewProduct;
    private static ControlProduct instance;

    private static final String RUTA = "src/main/java/upm/etsisi/Persistence/Products.csv";

    public static ControlProduct getInstance() {
        if (instance == null) {
            instance = new ControlProduct();
        }
        return instance;
    }

    private ControlProduct() {
        this.numProducts = 0;
        this.viewProduct = new ViewProduct();
        loadProducts();
    }




    public boolean addProduct(Integer id, String name, Category category, double price, Integer max_people) {
        boolean result = false;
        if (max_people == null) {
            if (ps.size() < MAX_PRODUCT) {
                if (ps.get(id) == null) {
                    BasicProduct product = new BasicProduct(String.valueOf(id), name, category, price);
                    ps.put(product.getId(), product);
                    numProducts++;
                    result = true;
                    viewProduct.printProduct(product);
                    viewProduct.createOK();
                }
            }
        } else {
            if (ps.size() < MAX_PRODUCT) {
                if (ps.get(id) == null) {
                    BasicProduct product = new BasicProduct(String.valueOf(id), name, category, price, max_people, null);
                    ps.put(product.getId(), product);
                    numProducts++;
                    result = true;
                    viewProduct.printProductP(product);
                    viewProduct.createOK();

                }
            }

        }


        return result;
    }

    public boolean addFood(Integer id, String name, double price, LocalDate expiration, int max_people) {
        boolean resul = false;
        if (validDateFood(expiration)) {
            if (max_people <= MAX_PEOPLE) {
                TimedProduct product = new TimedProduct(String.valueOf(id), name, price, expiration, max_people, ProductType.FOOD);
                viewProduct.printProductFood(product);
                ps.put(product.getId(), product);
                viewProduct.addFoodOk();
                resul = true;
            } else {
                viewProduct.addFoodError();
            }
        } else {
            viewProduct.addFoodError();
        }
        return resul;
    }


    public boolean validDateFood(LocalDate expiration) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime preparation = now.plusDays(3);
        LocalDateTime exp = expiration.atStartOfDay();
        return preparation.isBefore(exp);
    }

    public boolean addMeeting(Integer id, String name, double price, LocalDate expiration, int max_people) {
        boolean resul = false;
        if (validDateMeeting(expiration)) {
            if (max_people <= MAX_PEOPLE) {
                TimedProduct product = new TimedProduct(String.valueOf(id), name, price, expiration, max_people, ProductType.MEETING);
                viewProduct.printProductMeeting(product);
                ps.put(String.valueOf(id), product);
                viewProduct.addMeetingOk();
                resul = true;
            } else {
                viewProduct.addMeetingError();
            }
        } else {
            viewProduct.addMeetingError();
        }
        return resul;
    }

    public boolean validDateMeeting(LocalDate expiration) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime preparation = now.plusHours(12);
        LocalDateTime exp = expiration.atStartOfDay();
        return preparation.isBefore(exp);
    }

    public boolean removeProduct(int id) {
        boolean result = false;
        if (ps.get(String.valueOf(id)) !=null) {
            Product product = (Product) ps.get(String.valueOf(id));
            viewProduct.printProduct(product);
            ps.remove(String.valueOf(id));
            viewProduct.removeOK();
            result = true;
        }

        return result;
    }

    public boolean updateProduct(Integer id, String objetive, String newValue) {
        boolean result = false;
        if(getProduct(String.valueOf(id))!=null){
            Product product = getProduct(String.valueOf(id));
            switch (objetive) {
                case "NAME":
                    product.setName(newValue);
                    break;
                case "PRICE":
                    product.setPrice(Double.parseDouble(newValue));
                    break;
                case "CATEGORY":
                    if (product instanceof BasicProduct) {
                        ((BasicProduct) product).setCategory(Category.valueOf(newValue.toUpperCase()));
                    }
                    break;
            }
            result = true;
            viewProduct.printProduct(product);
            viewProduct.updateOk();
        }
        return result;
    }



    public void listPS() {
        viewProduct.printAll(ps.values());
        viewProduct.listOK();
    }

    public boolean addService(LocalDate expiration, Category category) {
        boolean result = true;
        String id = serviceCounter++ + "S";
        ProductService service = new ProductService(category, expiration);
        service.setId(id);
        ps.put(id, service);
        viewProduct.printProductService(service);
        viewProduct.createOK();
        return result;
    }

    public Product getProduct(String id) {
        ProductsAndService item = ps.get(id);
        return (item instanceof Product) ? (Product) item : null;
    }

    public ProductService getService(String id) {
        ProductsAndService item = ps.get(id);
        return (item instanceof ProductService) ? (ProductService) item : null;
    }
    public ProductsAndService getProductOrService(String id){
        return ps.get(id);
    }

    public boolean exists(String id) {
        return ps.containsKey(id);
    }

    public void saveProducts() {
        File file = new File(RUTA);

        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (ProductsAndService item : ps.values()) {
                    StringBuilder sb = new StringBuilder();

                    if (item instanceof BasicProduct) {
                        BasicProduct bp = (BasicProduct) item;
                        sb.append(bp.getProductType()).append(";")
                                .append(bp.getId()).append(";")
                                .append(bp.getName()).append(";")
                                .append(bp.getPrice()).append(";")
                                .append(bp.getCategory());

                        if (bp.getProductType() == ProductType.PERSONALIZATION) {
                            sb.append(";").append(bp.getMaxPersonal())
                                    .append(";").append(bp.getPersonalizationList());
                        }

                    } else if (item instanceof TimedProduct) {
                        TimedProduct tp = (TimedProduct) item;
                        sb.append("TIMED").append(";")
                                .append(tp.getId()).append(";")
                                .append(tp.getName()).append(";")
                                .append(tp.getPrice()).append(";")
                                .append(tp.getExpiration()).append(";")
                                .append(tp.getMaxPersonal()).append(";")
                                .append(tp.getProductType()).append(";")
                                .append(tp.getActualPeople());

                    } else if (item instanceof ProductService) {
                        ProductService s = (ProductService) item;
                        sb.append("SERVICE").append(";")
                                .append(s.getId()).append(";")
                                .append(s.getExpiration()).append(";")
                                .append(s.getCategory());
                    }

                    writer.write(sb.toString());
                    writer.newLine();
                }
                System.out.println("Datos guardados en: " + RUTA);
            }
        } catch (IOException ignored) {

        }
    }

    public void loadProducts() {
        File file = new File(RUTA);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            ps.clear();
            int maxServiceId = 0;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(";");
                String type = data[0];

                try {
                    switch (type) {
                        case "BASIC":
                            BasicProduct bp = new BasicProduct(data[1], data[2], Category.valueOf(data[4]), Double.parseDouble(data[3]));
                            bp.setProductType(ProductType.BASIC);
                            ps.put(bp.getId(), bp);
                            break;

                        case "PERSONALIZATION":
                            BasicProduct pp = new BasicProduct(data[1], data[2], Category.valueOf(data[4]), Double.parseDouble(data[3]), Integer.parseInt(data[5]), data.length > 6 ? data[6] : "");
                            pp.setProductType(ProductType.PERSONALIZATION);
                            ps.put(pp.getId(), pp);
                            break;

                        case "TIMED":
                            TimedProduct tp = new TimedProduct(data[1], data[2], Double.parseDouble(data[3]), LocalDate.parse(data[4]), Integer.parseInt(data[5]), ProductType.valueOf(data[6]));
                            tp.setActualPeople(Integer.parseInt(data[7]));
                            ps.put(tp.getId(), tp);
                            break;

                        case "SERVICE":
                            ProductService s = new ProductService(Category.valueOf(data[3]), LocalDate.parse(data[2]));
                            s.setId(data[1]);
                            ps.put(s.getId(), s);

                            try {
                                String numberPart = data[1].replace("S", "");
                                int idNum = Integer.parseInt(numberPart);
                                if (idNum > maxServiceId) {
                                    maxServiceId = idNum;
                                }
                            } catch (NumberFormatException ignored) {}
                            break;
                    }
                } catch (Exception ignored) {}
            }

            this.numProducts = ps.size();
            this.serviceCounter = maxServiceId + 1;

        } catch (IOException e) {}
    }
}