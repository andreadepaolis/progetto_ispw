package utils;

public abstract class registerUtils {

        protected int monthToInt(String s){

            switch (s){
                case "Gennaio":
                    return 1;
                case "Febbraio":
                    return 2;
                case "Marzo":
                    return 3;
                case "Aprile":
                    return 4;
                case "Maggio":
                    return 5;
                case "Giugno":
                    return 6;
                case "Luglio":
                     return 7;
                case "Agosto":
                    return 8;
                case "Settembre":
                    return 9;
                case "Ottobre":
                    return 10;
                case "Novembre":
                    return 11;
                case "Dicembre":
                    return 12;

            }
            return 0;
        }



}
