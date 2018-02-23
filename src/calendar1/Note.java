/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.ULocale;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.ibm.icu.util.Calendar;
/**
 *
 * @author Monireh.S
 */
public class Note {

    int numberOfDay = 0;
    table t ;

    /**
     *
     */
    public Note() {

    }

    /**
     *
     * @param t table of month
     */
    public Note(table t) {
        this.t = t;
    }
    
    /**
     *
     * @param n  show today
     */
    public Note(int n) {
        numberOfDay = n - 1;
    }
    
    /**
     *
     * @param note given note or event that should write in file
     * @param ch show this is a new note or new event
     */
    public void writeInFile(String note,char ch) {
        
        Calendar prsCal = GregorianCalendar.getInstance(new ULocale("ar_IR"));
        int day = prsCal.get(Calendar.DAY_OF_MONTH);
        int month = prsCal.get(Calendar.MONTH);
        int numberOfDay1 = t.selectedDay;
        if(numberOfDay == 0){
         numberOfDay = numberOfDay1 - 1;
        }
        
        if (numberOfDay == -1) {
            numberOfDay = (whichDayOfYear(day,month + 1));
            numberOfDay--;
        }
        
        File log = new File("the-file-name.txt");
       
        try {
            String newLine = System.getProperty("line.separator");
            FileWriter fileWriter = new FileWriter(log, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if(numberOfDay != -1){
                if(ch == 'n'){
                    bufferedWriter.write(numberOfDay + "/" + note + newLine);
                    bufferedWriter.close();
                }else if (ch == 'r'){
                    bufferedWriter.write(numberOfDay + "/%/" + note + newLine);
                    bufferedWriter.close();
                }
            }else {
               
            }
        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
        }
    }
    
    /**
     *
     * @param day given integer of day of month
     * @param month given integer of month of year
     * @return the given day is which day of year
     */
    public int whichDayOfYear(int day, int month) {

        int result = 0;
        if (month <= 6) {
            result = ((month - 1) * 31 + day);
        } else {
            result = 186 + (month - 7) * 30 + day;
        }
        return result;
    }
}
