package upm.etsisi.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utility {
    public static String ticketId(){
        StringBuilder ticketId = new StringBuilder();
        Random random = new Random();
        int num = 10000 + random.nextInt(90000);
        ticketId.append(num);
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

    public static boolean correctDNI(String dni) {
        if (dni == null || dni.length() != 9)
            return false;

        dni = dni.toUpperCase();
        char last = dni.charAt(8);

        // Tabla para DNI de 8 números
        final String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

        // ============================
        //   CASO 1 → DNI NORMAL
        // ============================
        if (Character.isDigit(dni.charAt(0))) {
            // 8 números + letra
            String numbers = dni.substring(0, 8);

            // Comprobar que los 8 primeros son dígitos
            for (int i = 0; i < 8; i++) {
                if (!Character.isDigit(numbers.charAt(i)))
                    return false;
            }

            // Comprobar que la última es letra
            if (!Character.isLetter(last))
                return false;

            // Validación oficial del DNI
            int num = Integer.parseInt(numbers);
            return last == letras.charAt(num % 23);
        }

        // ============================
        //   CASO 2 → FORMATO ESPECIAL
        // ============================
        char first = dni.charAt(0);

        // Primera letra y última letra
        if (!Character.isLetter(first) || !Character.isLetter(last))
            return false;

        // Entre medias deben ser exactamente 7 números
        for (int i = 1; i <= 7; i++) {
            if (!Character.isDigit(dni.charAt(i)))
                return false;
        }

        return true; // Formato válido
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

    public static String[] parseCommand(String line){
        String[] separatedArgs = new String[100];
        int iterator = 0;
        boolean quoting = false;
        String current = "";

        for(int i = 0; i < line.length(); i++){
            char actualCharacter = line.charAt(i);
            if(actualCharacter == '"'){
                quoting = !quoting;
                current += actualCharacter;
            } else if (actualCharacter == ' ' && !quoting) {
                if (!current.isEmpty()) {
                    separatedArgs[iterator] = current;
                    iterator++;
                    current = "";
                }
            }
            else current += actualCharacter;
        }
        return separatedArgs;
    }
}
