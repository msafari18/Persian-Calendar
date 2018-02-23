/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.ULocale;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import com.ibm.icu.util.Calendar;

/**
 *
 * @author Monireh.S to edit notes & event
 */
public class Edit {

    table t;
    ArrayList<Ruydad> days = new ArrayList(366);
    int m;

    /**
     * get main table
     */
    public Edit() {
        t = new table();
    }

    /**
     *
     * @param t get table
     */
    public Edit(table t) {
        this.t = t;
    }

    /**
     *
     * @param n number of day
     */
    public Edit(int n) {
        m = n - 1;
    }

    /**
     *
     * @param edit image of the icon
     * @param ch shows note or event save new note or event in array list
     */
    public void edit(ImageIcon edit, final char ch) {
        Calendar prsCal = GregorianCalendar.getInstance(new ULocale("ar_IR"));
        days = t.days;
        int day = prsCal.get(com.ibm.icu.util.Calendar.DAY_OF_MONTH);
        int month = prsCal.get(com.ibm.icu.util.Calendar.MONTH);
        int numberOfDay1 = t.selectedDay;
        if (m == 0) {
            m = numberOfDay1 - 1;
        }
        if (m == -1) {
            m = (whichDayOfYear(day, month + 1)) - 1;
        }
        

        final JFrame frame = new JFrame("ویرایش کن");
        final JTextArea note = new JTextArea(5, 20);
        frame.add(note);
        JButton editButton = new JButton(edit);

        editButton.setToolTipText("برای ذخیره سازی کلیک کن");
        if (ch == 'n') {
            note.setText(days.get(m).note);
        } else if (ch == 'r') {
            note.setText(days.get(m).ruydad);
        }
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ch == 'n') {
                    days.get(m).note = note.getText();
                    writeInFile();
                } else if (ch == 'r') {
                    days.get(m).ruydad = note.getText();
                    writeInFile();
                }
                JOptionPane.showMessageDialog(frame, "حله :)");

            }
        });
        note.setEditable(true);
        frame.add(editButton);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());

    }

    /**
     * write new note or event in file
     */
    public void writeInFile() {

        File log = new File("the-file-name.txt");

        try {
            String newLine = System.getProperty("line.separator");
            FileWriter fileWriter = new FileWriter(log, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i <= 365; i++) {
                if (!days.get(i).note.isEmpty()) {
                    bufferedWriter.write(i + "/" + days.get(i).note + newLine);
                }
                if (!days.get(i).ruydad.isEmpty()) {
                    bufferedWriter.write(i + "/%/" + days.get(i).ruydad + newLine);
                }

            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
        }
    }

    /**
     *
     * @param day date of wanted day
     * @param month month of wanted day
     * @return
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
