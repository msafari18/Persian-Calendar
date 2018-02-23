/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Monireh.S
 */
public class MyTimeTask extends TimerTask {

    int a, b;
    String event;
    ArrayList<Ruydad> days = new ArrayList(366);

    /**
     *
     * @param a month of the event day
     * @param  date of the event month
     * @param event 
     */
    public MyTimeTask(int a, int b) {
        Ruydad r = new Ruydad();
        days = r.loadDays();
        this.a = a;
        this.b = b;
        this.event = updateRuydad(whichDayOfYear(b, a));
        days = r.loadDays();

    }

    /**
     *
     * @param day given integer of day of month
     * @param month given integer of month of year
     * @return the given day is which day of year
     */
    private int whichDayOfYear(int day, int month) {

        int result = 0;
        if (month <= 6) {
            result = ((month - 1) * 31 + day);
        } else {
            result = 186 + (month - 7) * 30 + day;
        }
        return result;
    }

    /**
     *
     * @param ruydad3 given day of year find the event of every day in days
     * ArrayList
     * @return returns the event for today
     */
    private String updateRuydad(int ruydad3) {

        Ruydad ruydad1 = new Ruydad();
        ruydad3--;
        ruydad1 = days.get(ruydad3);
        String monasebat;
        monasebat = ruydad1.ruydad;
        return monasebat;
    }

    @Override
    public void run() {

        JFrame frame = new JFrame("یادت نرههههه!!!");
        JPanel frame2 = new JPanel();
        JLabel event = new JLabel();
        event.setText(this.event);
        frame2.add(event);
        frame2.setBackground(new Color(138, 43, 226));
        frame.add(frame2);
        frame.setLocation(400, 400);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }

}
