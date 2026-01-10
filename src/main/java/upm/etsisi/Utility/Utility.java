package upm.etsisi.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utility {
    public static String generateId(){
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


    public static boolean correctDNI(String dni) {
        if (dni == null || dni.length() != 9)
            return false;

        dni = dni.toUpperCase();
        char last = dni.charAt(8);

        final String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        if (Character.isDigit(dni.charAt(0))) {
            String numbers = dni.substring(0, 8);
            for (int i = 0; i < 8; i++) {
                if (!Character.isDigit(numbers.charAt(i)))
                    return false;
            }
            if (!Character.isLetter(last))
                return false;
            int num = Integer.parseInt(numbers);
            return last == letras.charAt(num % 23);
        }

        char first = dni.charAt(0);
        if (!Character.isLetter(first) || !Character.isLetter(last))
            return false;
        for (int i = 1; i <= 7; i++) {
            if (!Character.isDigit(dni.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean correctEmail(String email){
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
    public static boolean isNifNumValid(String nif){
        //Si el largo del NIF es diferente a 9, acaba el método.
        if (nif.length()!=9){
            return false;
        }

        String secuenciaLetrasNIF = "TRWAGMYFPDXBNJZSQVHLCKE";
        nif = nif.toUpperCase();

        //Posición inicial: 0 (primero en la cadena de texto).
        //Longitud: cadena de texto menos última posición. Así obtenemos solo el número.
        String numeroNIF = nif.substring(0, nif.length()-1);

        //Si es un NIE reemplazamos letra inicial por su valor numérico.
        numeroNIF = numeroNIF.replace("X", "0").replace("Y", "1").replace("Z", "2");

        //Obtenemos la letra con un char que nos servirá también para el índice de las secuenciaLetrasNIF
        char letraNIF = nif.charAt(8);
        int i = Integer.parseInt(numeroNIF) % 23;
        return letraNIF == secuenciaLetrasNIF.charAt(i);
    }

}
