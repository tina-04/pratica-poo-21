package upm.etsisi.Utility;

import upm.etsisi.View.ViewUtility;

public class Utility {
    public static boolean leerNum(int num){
        boolean exist = false;
        if (num > 0 && num<200){
            exist = true;
        }

        return exist;
    }
    public  static int id=0 ;
    public static int generateId() {
        id++;
        return id;
    }


}
