package upm.etsisi.Utility;

import java.util.Random;

public class Utility {
    public static String ticketId(){
        StringBuilder ticketId = new StringBuilder();
        Random random = new Random();
        int num = 10000 + random.nextInt(90000);

        return  ticketId.toString();
    }
    public static String generateCashierId() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        stringBuilder.append("UW");
        for (int i = 0; i < 7; i++) {
            int digito = random.nextInt(10);
            stringBuilder.append(digito);
        }
        return stringBuilder.toString();
    }
    public static boolean correctCashierId(String id) {
        boolean result = false;
        String uw  = id.substring(0,2);
        String number = id.substring(3,8);
        if(uw.equals("UW") && (number.matches("[0-9]+"))){
            result =true;
        }


        return result;
    }

    public static String nameProduct(String name) {
        int inicio = name.indexOf('"');
        int fin = name.lastIndexOf('"');
        String string = "";
        if (inicio != -1 && fin != -1 && inicio < fin) {
            string = name.substring(inicio + 1, fin);
        }
       return string;
    }
    public static boolean idProduct(String id) {
       boolean result = false;
       if(id.matches("[0-9]+")){
           result = true;
        }
        return result;
    }

    public static boolean correctDNI(String DNI){//do -while app
        boolean resul = true;
        String num = DNI.substring(0, 8);
        char letter = DNI.toUpperCase().charAt(8);
        for (int i = 0; i <8; i++) {
            if(!Character.isDigit(DNI.indexOf(i))){
                resul = false;
            };
        }
        if(Character.isLetter(letter)){
            resul= false;
        }
        return resul;
    }
    public static boolean correctEmail(String email){//hacer do-while en app
        boolean resul = true;
        String [] correct = email.split("@");
        if (correct.length != 2) resul= false;
        String name = correct[0];
        String point = correct[1];
        if (!name.matches("[a-zA-Z0-9._-]+")) resul= false;
        if (!point.contains(".")) resul= false;

        String[] parts = point.split("\\.");
        if (parts.length < 2) return false;
        String extension = parts[parts.length - 1];
        if (!extension.matches("[a-zA-Z]{2,}")) return false;
        return resul;
    }

}
