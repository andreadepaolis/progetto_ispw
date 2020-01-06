package factory;

import java.util.Calendar;

public class month {

   private int day;
   private int index;
   private String name;


    public month(int index, String name,int day){

        this.day = day;
        this.index = index;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public int getIndex() {
        return index;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
