package factory;

public class MonthFactory {

     public month createMonth(int i){

         switch (i){
             case 1:
                 return new month(1,"Gennaio",30);
             case 2:
                 return new month(2,"Febbraio",28);
             case 3:
                 return new month(3,"Marzo",31);
             case 4:
                 return new month(4,"Aprile",30);
             case 5:
                 return new month(5,"Maggio",31);
             case 6:
                 return new month(6,"Giugno",30);
             case 7:
                 return new month(7,"Luglio",31);
             case 8:
                 return new month(8,"Agosto",31);
             case 9:
                 return new month(9,"Settembre",30);
             case 10:
                 return new month(10,"Ottobre",31);
             case 11:
                 return new month(11,"Novembre",30);
             case 12:
                 return new month(12,"Dicembre",31);
             default:
                 return null;
         }
     }
}
