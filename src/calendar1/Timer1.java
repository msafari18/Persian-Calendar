/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Monireh.S
 */
public class Timer1 {

    int month, day;
    String time;

    /**
     *
     * @param day date of the day that set reminder for it
     * @param month month of the day that set reminder for it
     * @param time time of the day that set reminder for it
     */
    public Timer1(int day, int month, String time) {
        this.time = time;
        this.month = month;
        this.day = day;
    }

    /**
     *
     * @return date & that you want to set reminder for
     * @throws ParseException
     */
    public Date setTime() throws ParseException {
        String[] time1 = time.split("/");
        Date date = null;
        if (time1.length == 3) {
            if (day >= 32 || month >= 13 || Integer.parseInt(time1[0]) >= 25 || Integer.parseInt(time1[1]) >= 60 || Integer.parseInt(time1[2]) >= 60) {
                return date;
            } else if (day <= 0 || month <= 0 || Integer.parseInt(time1[0]) <= 0 || Integer.parseInt(time1[1]) < 0 || Integer.parseInt(time1[2]) < 0) {
                return date;
            } else {
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dateFormatter.parse("2016-" + month + "-" + day + " " + time1[0] + ":" + time1[1] + ":" + time1[2]);
                return date;
            }
        }
        return date;
    }
}
