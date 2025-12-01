package upm.etsisi.View;

import upm.etsisi.Model.BasicProduct;
import upm.etsisi.Model.Product;
import upm.etsisi.Model.TimedProduct;

import java.awt.event.ActionListener;
import java.util.List;

public class ViewProduct implements View{
    public String exceptionArguments = "The number of people is maximum";
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public void printProduct(Product product) {
        if(product instanceof BasicProduct){
            BasicProduct basicProd = (BasicProduct) product;
            messageOutput("{class:Product, id:" + basicProd.getId()+ ", name: '" + basicProd.getName()+
                    "', category:" + basicProd.getCategory()+ ", price:" + basicProd.getPrice() + "}");
        }
    }
    public void printProductP(Product product) {
        if(product instanceof BasicProduct){
            BasicProduct basicProd = (BasicProduct) product;
            messageOutput("{class:Product, id:" + basicProd.getId()+ ", name: '" + basicProd.getName()+
                    "', category:" + basicProd.getCategory()+ ", price:" + basicProd.getPrice() +" ,maxpersonal:"+ basicProd.getMaxPersonal()+ "}");
        }
    }
    public void printProductFood(Product product) {
        if(product instanceof TimedProduct){
            TimedProduct timedProd = (TimedProduct) product;
            messageOutput("{class:Food, id:" + timedProd.getId()+ ", name: '" + timedProd.getName()+
                    ", price: 0.0"  +  ", date of Event:"+timedProd.getExpiration()+
                    ",max people allowed:"+timedProd.getMaxPersonal()+"}");
        }
    }
    public void printProductMeeting(Product product) {
        if(product instanceof TimedProduct){
            TimedProduct timedProd = (TimedProduct) product;
            messageOutput("{class:Meeting, id:" + timedProd.getId()+ ", name: '" + timedProd.getName()+
                    "', price: 0.0" +  ", date of Event:"+timedProd.getExpiration()+
                    ",max people allowed:"+timedProd.getMaxPersonal()+"}");
        }
    }

    public void listProduct(List<Product> productList){
        messageOutput("Catalog:");
        for(Product product : productList){
            if(product instanceof BasicProduct){
                BasicProduct basicProd = (BasicProduct) product;
                switch(basicProd.getProductType()){
                    case BASIC:
                        if (basicProd.getPersonalizationList()!= null){
                            printProductP(basicProd);
                        }else{
                            printProduct(basicProd);
                        }
                        break;
                    case PERSONLIZATION:
                        printProductP(basicProd);
                        break;
                }
            } else if(product instanceof TimedProduct){
                TimedProduct timedProd = (TimedProduct) product;
                switch(timedProd.getProductType()){
                    case FOOD:
                        printProductFood(timedProd);
                        break;
                    case MEETING:
                        printProductMeeting(timedProd);
                        break;
                }
            }
        }
    }
    public void addFoodError(){
        messageOutput("Error processing ->prod addFood ->Error adding product");
    }
    public void addMeetingError(){
        messageOutput("Error processing ->prod addMeeting ->Error adding product");
    }
    public void createOK(){
        messageOutput("prod add: ok");
    }

    public void removeOK(){
        messageOutput("prod remove: ok");
    }
    public void listOK(){
        messageOutput("prod list: ok");
    }
    public void addFoodOk(){
        messageOutput("prod addFood: ok");
    }
    public void addMeetingOk(){
        messageOutput("prod addMeeting: ok");
    }
    public void updateOk() {messageOutput("prod update: ok");}
}
