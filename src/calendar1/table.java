/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.ULocale;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monireh.S! the table of every month
 */
public class table {

    private String columns[] = {"جمعه", "پنج", "چهار", "سه", "دو", "یک", "شنبه"};
    private Calendar prsCal;
    public int month;
    private JLabel[] labels;
    private JButton ri;
    private JButton le;
    private JLabel month1;
    private JLabel updateYear;
    private JPanel label1;
    private JPanel week1;
    private JPanel jpanel1;
    private int updateYear1;
    public int a;
    ArrayList<Ruydad> days;
    Ruydad ruydad1;
    JMenuItem copy, addHappening, addNote, editNote;

    /**
     *
     * @param r next month button
     * @param l last month button
     * @param copy copy button
     */
    public table(JButton r, JButton l, JMenuItem copy) {

        prsCal = GregorianCalendar.getInstance(new ULocale("ar_IR"));
        labels = new JLabel[42];
        ri = new JButton();
        le = new JButton();
        month1 = new JLabel("");
        updateYear = new JLabel("");
        label1 = new JPanel();
        week1 = new JPanel();
        jpanel1 = new JPanel();
        a = prsCal.get(Calendar.DAY_OF_MONTH);
        days = new ArrayList(366);
        ruydad1 = new Ruydad();
        addNote = new JMenuItem();
        addHappening = new JMenuItem();
        editNote = new JMenuItem();

        ri = r;
        le = l;
        this.copy = copy;
        days = ruydad1.loadDays();

    }

    /**
     * constructor for new table
     */
    public table() {

        prsCal = GregorianCalendar.getInstance(new ULocale("ar_IR"));
        labels = new JLabel[42];
        ri = new JButton();
        le = new JButton();
        month1 = new JLabel("");
        updateYear = new JLabel("");
        label1 = new JPanel();
        week1 = new JPanel();
        jpanel1 = new JPanel();
        a = prsCal.get(Calendar.DAY_OF_MONTH);
        days = new ArrayList(366);
        ruydad1 = new Ruydad();
        copy = new JMenuItem();
        addNote = new JMenuItem();
        addHappening = new JMenuItem();
        editNote = new JMenuItem();
        days = ruydad1.loadDays();
    }

    /**
     *
     */
    public void myTable() {
        month = (Calendar.MONTH);
        updateYear1 = prsCal.get(Calendar.YEAR);;
        this.makeTable();
        this.buttonActionListener();
        this.updateMonth();

    }

    /**
     * make table of days for every month
     */
    private void makeTable() {

        week1.setPreferredSize(new Dimension(360, 20));
        week1.setLayout(new GridLayout(1, 7));
        JLabel[] week = new JLabel[7];
        for (int i = 0; i <= 6; i++) {
            week[i] = new JLabel(columns[i], SwingConstants.CENTER);
            week1.add(week[i]);
            week[i].setFont(new Font("Afra", Font.PLAIN, 15));
            week1.setBackground(new Color(255, 250, 240));
        }

        Border border = LineBorder.createGrayLineBorder();
        label1.setLayout(new GridLayout(6, 7));
        label1.setComponentOrientation(RIGHT_TO_LEFT);
        label1.setPreferredSize(new Dimension(360, 200));
        daysEventHandler dayHandler = new daysEventHandler();
        for (int i = 0; i <= 41; i++) {
            labels[i] = new JLabel("", SwingConstants.CENTER);
            label1.add(labels[i]);
            labels[i].setComponentPopupMenu(addPopUpMenu());
            labels[i].setBorder(border);
            labels[i].addMouseListener(dayHandler);

        }
        for (int i = 6; i <= 35; i = i + 7) {
            labels[i].setForeground(Color.red);
        }
        changeColor(jpanel1);

    }

    /**
     * update table for this month
     */
    private void updateMonth() {

        prsCal.set(Calendar.DAY_OF_MONTH, 1);

        int year = prsCal.get(Calendar.YEAR);
        int startDay = prsCal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = prsCal.getActualMaximum(Calendar.DAY_OF_MONTH);

        int i = startDay;
        if (i == 7) {
            i = i - 7;
        }
        changeColor(jpanel1);
        for (int day = 1; day <= numberOfDays; day++) {
            String m = new String("");
            m = Integer.toString(day);
            labels[i].setText(m);
            i = i + 1;

        }
        int k = startDay;
        if (k == 7) {
            k = k - 7;
        }

        for (int j = 0; j <= 41; j++) {
            labels[j].setToolTipText(" ");
        }

        for (int day = 1; day <= numberOfDays; day++) {

            Ruydad ruydad1 = new Ruydad();
            int month1 = month + 1;
            int ruydad3 = whichDayOfYear(day, month1);
            ruydad3--;
            ruydad1 = days.get(ruydad3);
            if(!ruydad1.note.isEmpty())
                labels[k].setToolTipText(ruydad1.rooydad +"/"+ ruydad1.note);
            else
                labels[k].setToolTipText(ruydad1.rooydad);
            k++;

        }
        today();
        updateYear();

    }

    /**
     * find today cell of the table find note & event for today
     */
    private void today() {

        String today = Integer.toString(a);
        int thisMonth = Calendar.MONTH;
        for (int i = 0; i <= 41; i++) {
            String m = labels[i].getText();
            if (m.equals(today) && month == thisMonth) {
                labels[i].setOpaque(true);
                labels[i].setBackground(new Color(255, 250, 240));
                updateDate(a, month);
                updateDateArb(a, month);
                int month1 = month + 1;
                updateRuydad(whichDayOfYear(a, month1));

            }

        }
    }

    /**
     *
     * @param date the given JPanel to change the color change the color of the
     * calendar for every season
     */
    private void changeColor(JPanel date) {
        if (month >= 0 && month <= 2) {
            for (int i = 0; i <= 41; i++) {
                labels[i].setOpaque(true);
                labels[i].setBackground(new Color(255, 160, 122));
            }
            jpanel1.setBackground(new Color(220, 20, 60));
            date.setBackground(new Color(220, 20, 60));
        }
        if (month >= 3 && month <= 5) {
            for (int i = 0; i <= 41; i++) {
                labels[i].setOpaque(true);
                labels[i].setBackground(new Color(152, 251, 152));
            }
            jpanel1.setBackground(new Color(0, 100, 0));
            date.setBackground(new Color(0, 100, 0));
        }
        if (month >= 6 && month <= 8) {
            for (int i = 0; i <= 41; i++) {
                labels[i].setOpaque(true);
                labels[i].setBackground(new Color(244, 164, 96));
            }
            jpanel1.setBackground(new Color(255, 69, 0));
            date.setBackground(new Color(255, 69, 0));
        }
        if (month >= 9 && month <= 11) {
            for (int i = 0; i <= 41; i++) {
                labels[i].setOpaque(true);
                labels[i].setBackground(new Color(173, 216, 230));
            }
            jpanel1.setBackground(new Color(30, 144, 255));
            date.setBackground(new Color(30, 144, 255));
        }
    }

    /**
     * action listener for next month & last month button
     */
    private void buttonActionListener() {
        le.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (int i = 0; i <= 41; i++) {
                    labels[i].setText("");
                    labels[i].setOpaque(false);
                }
                prsCal.add(Calendar.MONTH, -1);
                month--;
                if (month < 0) {
                    month = 11;
                    changeColor(jpanel1);
                    updateYear1--;
                    updateYear();
                    monthLable();
                    updateMonth();
                } else {

                    monthLable();
                    updateMonth();

                }
            }
        });

        ri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (int i = 0; i <= 41; i++) {
                    labels[i].setText("");
                    labels[i].setOpaque(false);
                }

                prsCal.add(Calendar.MONTH, +1);
                month++;
                if (month >= 12) {
                    month = 0;
                    updateYear1++;
                    updateYear();
                    changeColor(jpanel1);
                    monthLable();
                    updateMonth();
                } else {

                    monthLable();
                    updateMonth();

                }
            }
        });

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String myString;
                if (selectedDay2 != 0) {
                    myString = ("روز" + (selectedDay2) + "ام  " + (month1.getText()));
                } else {
                    myString = ("روز" + a + "ام  " + (month1.getText()));
                }
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);

            }
        });
    }

    /////////////
    static int selectedDay;
    int selectedDay2;

    /**
     * event handler for every day change the color of selected day show event
     * of day
     */
    private class daysEventHandler extends MouseAdapter {

        int dayYear;

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel clicked = new JLabel("");
            clicked = (JLabel) e.getSource();
            if (clicked.getText().isEmpty()) {

            } else {
                changeColor(jpanel1);
                clicked.setOpaque(true);
                clicked.setBackground(new Color(255, 250, 240));
                updateDate(Integer.parseInt(clicked.getText()), month);
                updateDateArb(Integer.parseInt(clicked.getText()), month);
                int month1 = month + 1;
                dayYear = whichDayOfYear(Integer.parseInt(clicked.getText()), month1);
                selectedDay = dayYear;
                selectedDay2 = Integer.parseInt(clicked.getText());
                updateRuydad(dayYear);

            }
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
    /////////////////////////////

    String ruydad;
    JLabel monasebat1 = new JLabel("");
    JLabel monasebat2 = new JLabel("");
    JLabel note = new JLabel("");
    JLabel rooydad = new JLabel("");

    /**
     *
     * @param ruydad3 given day of year find the event of every day in days
     * ArrayList
     */
    private void updateRuydad(int ruydad3) {
        days = ruydad1.loadDays();
        monasebat2.setText(" ");
        note.setText(" ");
        rooydad.setText(" ");
        ruydad3--;
        Ruydad ruydad1 = new Ruydad();
        ruydad1 = days.get(ruydad3);
        String monasebat, note, rooydad;
        monasebat = ruydad1.rooydad;
        note = ruydad1.note;
        rooydad = ruydad1.ruydad;

        String[] buffer = monasebat.split("-");
        if (buffer.length == 1) {
            monasebat1.setText(ruydad1.rooydad);
        } else {
            monasebat1.setText(buffer[0]);
            monasebat2.setText(buffer[1]);
        }
        if (!note.equals("")) {
            this.note.setText(note);
        }
        if (!rooydad.equals("")) {
            this.rooydad.setText(rooydad);
        }
        this.rooydad.setFont(new Font("Afra", Font.ITALIC, 15));
        this.rooydad.setBackground(new Color(255, 250, 240));
        this.note.setFont(new Font("Afra", Font.PLAIN, 15));
        this.note.setBackground(new Color(255, 250, 240));
        monasebat2.setFont(new Font("Afra", Font.PLAIN, 15));
        monasebat2.setBackground(new Color(255, 250, 240));
        monasebat1.setFont(new Font("Afra", Font.PLAIN, 15));
        monasebat1.setBackground(new Color(255, 250, 240));
        monasebat1.setComponentOrientation(RIGHT_TO_LEFT);
        monasebat2.setComponentOrientation(RIGHT_TO_LEFT);
        this.note.setComponentOrientation(RIGHT_TO_LEFT);
        this.rooydad.setComponentOrientation(RIGHT_TO_LEFT);
    }

    /**
     *
     * @return it return the panel with suitable event to add to the main frame
     */
    private JPanel addRuydad() {
        JPanel all = new JPanel();
        JPanel monasebat = new JPanel();
        JPanel note1 = new JPanel();
        note1.add(note);
        note1.add(rooydad);
        monasebat.add(monasebat1);
        monasebat.add(monasebat2);
        monasebat.setLayout(new GridLayout(2, 1));
        note1.setLayout(new GridLayout(2, 1));
        note1.setComponentOrientation(RIGHT_TO_LEFT);
        all.setComponentOrientation(RIGHT_TO_LEFT);
        monasebat.setComponentOrientation(RIGHT_TO_LEFT);
        all.add(note1);
        all.add(monasebat);
        all.setLayout(new GridLayout(2, 1));
        all.setBackground(new Color(255, 250, 240));
        note1.setBackground(new Color(255, 250, 240));
        monasebat.setBackground(new Color(255, 250, 240));
        all.setPreferredSize(new Dimension(360, 70));
        return all;

    }

    /**
     *
     * @return make a JPopupMenu for right clicked on every day
     */
    private JPopupMenu addPopUpMenu() {
        JPopupMenu labelPopupMenu = new JPopupMenu();

        JMenu insert = new JMenu("درج");
        JMenu edit = new JMenu("ویرایش");
        addHappening = new JMenuItem("رویداد");
        addNote = new JMenuItem("یادداشت");
        editNote = new JMenuItem("ویرایش یادداشت ها یا رویداد");
        JMenuItem copyDate = new JMenuItem("کپی کردن تاریخ روز");
        JMenuItem copyHappening = new JMenuItem("کپی کردن رویداد روز");

        insert.add(addHappening);
        insert.add(addNote);
        edit.add(editNote);
        edit.add(copyDate);
        edit.add(copyHappening);
        edit.setHorizontalAlignment(SwingConstants.RIGHT);
        insert.setHorizontalAlignment(SwingConstants.RIGHT);
        addNote.setHorizontalAlignment(SwingConstants.RIGHT);
        addHappening.setHorizontalAlignment(SwingConstants.RIGHT);
        copyDate.setHorizontalAlignment(SwingConstants.RIGHT);
        copyHappening.setHorizontalAlignment(SwingConstants.RIGHT);
        editNote.setHorizontalAlignment(SwingConstants.RIGHT);
        labelPopupMenu.setComponentOrientation(RIGHT_TO_LEFT);

        labelPopupMenu.add(insert);
        labelPopupMenu.add(edit);

        insert.setFont(new Font("Afra", Font.PLAIN, 15));
        edit.setFont(new Font("Afra", Font.PLAIN, 15));
        addHappening.setFont(new Font("Afra", Font.PLAIN, 15));
        addNote.setFont(new Font("Afra", Font.PLAIN, 15));
        editNote.setFont(new Font("Afra", Font.PLAIN, 15));
        copyDate.setFont(new Font("Afra", Font.PLAIN, 15));
        copyHappening.setFont(new Font("Afra", Font.PLAIN, 15));

        ActionListener actionListener = new PopupActionListener();
        editNote.addActionListener(actionListener);
        addHappening.addActionListener(actionListener);
        addNote.addActionListener(actionListener);
        copyDate.addActionListener(actionListener);
        copyHappening.addActionListener(actionListener);
        return labelPopupMenu;
    }

    private class PopupActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ("رویداد".equals(e.getActionCommand())) {
                String title = "رویداداضافه کن";
                char r = 'r';
                showNewFrameForAddEvent(title, r);
            } else if ("یادداشت".equals(e.getActionCommand())) {
                String title = "یادداشت اضافه کن";
                ImageIcon happenIconL = new ImageIcon("note1.gif");
                char n = 'n';
                showNewFrame(title, happenIconL, n);
            } else if ("ویرایش یادداشت ها یا رویداد".equals(e.getActionCommand())) {
                String title = "ویرایش کن";
                ImageIcon happenIcon = new ImageIcon("note1.gif");
                ImageIcon happenIcon1 = new ImageIcon("edit.gif");
                ImageIcon happenIcon2 = new ImageIcon("baloon.gif");
                showEditFrame(title, happenIcon, happenIcon1, happenIcon2);
            } else if ("کپی کردن تاریخ روز".equals(e.getActionCommand())) {
                String myString = ("روز" + selectedDay2 + "ام  " + (month1.getText()));
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            } else if ("کپی کردن رویداد روز".equals(e.getActionCommand())) {
                String myString = (days.get(selectedDay - 1).rooydad);
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        }

    }

    /**
     *
     * @param title the title of edit frame
     * @param happenIcon the icon image of the button
     * @param happenIcon1 the icon image of the button
     * @param happenIcon2 the icon image of the button show a new frame for edit
     */
    private void showEditFrame(String title, ImageIcon happenIcon, final ImageIcon happenIcon1, ImageIcon happenIcon2) {
        final JFrame frame = new JFrame(title);

        JButton eventButton = new JButton(happenIcon2);
        eventButton.setToolTipText("ویرایش رویداد ها");
        JButton noteButton = new JButton(happenIcon);
        noteButton.setToolTipText("ویرایش یادداشت ها");
        noteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Edit e = new Edit();
                e.edit(happenIcon1, 'n');
            }
        });
        eventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Edit e = new Edit();
                e.edit(happenIcon1, 'r');
            }
        });

        frame.add(eventButton);
        frame.add(noteButton);
        frame.setLayout(new FlowLayout());
        frame.setSize(250, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     *
     * @param title the title of frame
     * @param m the icon image for button
     * @param ch show it is new event or note show a new frame for new note
     */
    private void showNewFrame(final String title, final ImageIcon m, final char ch) {
        final JFrame frame = new JFrame(title);
        frame.setLayout(new FlowLayout());
        ImageIcon showNote1 = new ImageIcon("showNote.gif");
        JButton addNote = new JButton(m);
        JButton showNote = new JButton(showNote1);

        addNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showNewFrameForAddNote(title, m, ch);

            }
        });

        showNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ShowNote shNote = new ShowNote();
                shNote.showNote1();
            }
        });

        addNote.setToolTipText("یادداشت اضافه کن");
        frame.add(addNote);
        showNote.setToolTipText("یادداشتاتو ببین!");
        frame.add(showNote);
        frame.setSize(250, 150);
        frame.setBackground(new Color(240, 128, 128));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

    }

    /**
     *
     * @param title the title of frame
     * @param m the icon image for button
     * @param ch show it is new note show a new frame to add note
     */
    private void showNewFrameForAddNote(String title, ImageIcon m, final char ch) {
        final JFrame frame = new JFrame(title);
        frame.setLayout(new FlowLayout());
        final JTextArea note = new JTextArea(5, 20);
        note.setLineWrap(true);
        JButton button = new JButton(m);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String newNote = note.getText();
                Note n = new Note();
                n.writeInFile(newNote, ch);
                int s = selectedDay - 1;
                JOptionPane.showMessageDialog(frame, "ذخیره شد :)");

            }
        });
        button.setToolTipText("برای ذخیره کردن کلیک کن");
        frame.add(button);
        frame.add(note);
        note.setComponentOrientation(RIGHT_TO_LEFT);
        frame.setSize(250, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    /////////////////////////////
    String[] month5;

    /**
     *
     * @param title the title of frame
     * @param ch show it is new note show a new frame to add note add event &
     * set reminder
     */
    private void showNewFrameForAddEvent(String title, final char ch) {
        JPanel month2 = new JPanel();
        JPanel time2 = new JPanel();
        JPanel all = new JPanel();

        ImageIcon happenIconL = new ImageIcon("baloon.gif");
        final JFrame frame = new JFrame(title);
        frame.setLayout(new FlowLayout());
        final JTextArea note = new JTextArea(5, 20);
        final JTextField monthDate = new JTextField();
        final JTextField time = new JTextField();
        JLabel time1 = new JLabel("ثانیه/دقیقه/ساعت");
        JLabel month1 = new JLabel("روز/ماه/سال");
        time1.setComponentOrientation(RIGHT_TO_LEFT);
        month1.setComponentOrientation(RIGHT_TO_LEFT);
        month2.add(month1);
        month2.add(monthDate);
        time2.add(time1);
        time2.add(time);
        month2.setLayout(new GridLayout(2, 1));
        time2.setLayout(new GridLayout(2, 1));
        all.add(month2);
        all.add(time2);
        all.setLayout(new GridLayout(2, 1));
        note.setLineWrap(true);
        JButton button = new JButton(happenIconL);

        final Calendar miladiDate = GregorianCalendar.getInstance();
        final Calendar prsDate = GregorianCalendar.getInstance(new ULocale("ar_IR"));

        button.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent ae) {
                String newNote = note.getText();
                Note n = new Note();
                int s = selectedDay - 1;
                month5 = monthDate.getText().split("/");
                if (month5.length == 3) {
                    prsDate.set(1395, Integer.parseInt(month5[1]), Integer.parseInt(month5[2]));
                    miladiDate.setTime(prsDate.getTime());
                    int miladiDay = miladiDate.get(Calendar.DATE);
                    int miladiMonth = miladiDate.get(Calendar.MONTH);
                    Timer1 timer = new Timer1(miladiDay, miladiMonth, time.getText());
                    Timer timer2 = new Timer();
                    Date date2 = null;
                    try {
                        date2 = timer.setTime();
                    } catch (ParseException ex) {
                        Logger.getLogger(MenuBar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (date2 != null) {
                        n.writeInFile(newNote, ch);
                        timer2.schedule(new MyTimeTask(Integer.parseInt(month5[1]), Integer.parseInt(month5[2])), date2);

                        JOptionPane.showMessageDialog(frame, "ذخیره شد :)");
                    } else {
                        JOptionPane.showMessageDialog(frame, "خطا");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "خطا");
                }
            }
        });
        button.setToolTipText("برای ذخیره کردن کلیک کن");
        frame.add(button, BorderLayout.NORTH);
        frame.add(note, BorderLayout.CENTER);
        frame.add(all, BorderLayout.SOUTH);

        note.setComponentOrientation(RIGHT_TO_LEFT);
        frame.setSize(250, 330);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    ////////////////
    /**
     * make a JLabel with correct name of the month
     */
    private void monthLable() {

        if (month == 0) {
            month1.setText("فروردین");
        } else if (month == 1) {
            month1.setText("اردیبهشت");
        } else if (month == 2) {
            month1.setText("خرداد");
        } else if (month == 3) {
            month1.setText("تیر");
        } else if (month == 4) {
            month1.setText("مرداد");
        } else if (month == 5) {
            month1.setText("شهریور");
        } else if (month == 6) {
            month1.setText("مهر");
        } else if (month == 7) {
            month1.setText("ابان");
        } else if (month == 8) {
            month1.setText("آذر");
        } else if (month == 9) {
            month1.setText("دی");
        } else if (month == 10) {
            month1.setText("بهمن");
        } else if (month == 11) {
            month1.setText("اسفند");
        }

        month1.setComponentOrientation(RIGHT_TO_LEFT);
        month1.setFont(new Font("Afra", Font.PLAIN, 30));

    }
    //////////////////////////////////////
    JPanel monthYear = new JPanel();

    /**
     * make a JLabel with correct year
     */
    private void updateYear() {

        updateYear.setText(Integer.toString(updateYear1));
        updateYear.setFont(new Font("Afra", Font.PLAIN, 25));
        monthYearLabel();
    }

    /**
     * set the label of month & year and change the color
     */
    private void monthYearLabel() {

        monthYear.add(updateYear);
        monthYear.add(month1);

        if (month >= 0 && month <= 2) {
            monthYear.setBackground(new Color(220, 20, 60));
        }
        if (month >= 3 && month <= 5) {
            monthYear.setBackground(new Color(0, 100, 0));
        }
        if (month >= 6 && month <= 8) {
            monthYear.setBackground(new Color(255, 69, 0));
        }
        if (month >= 9 && month <= 11) {
            monthYear.setBackground(new Color(30, 144, 255));
        }
    }

    JLabel dayLabel = new JLabel("");
    JLabel monthLabel = new JLabel("");
    JLabel dayLabel1 = new JLabel("");
    JLabel monthLabel1 = new JLabel("");
    JLabel yearLabel = new JLabel("");
    JLabel yearLabel1 = new JLabel("");
    int year = 0, year1 = 0;

    /**
     *
     * @param miladiDay the Persian date of wanted day
     * @param miladiMonth the Persian month of wanted day find the Gregorian
     * date of wanted day
     */
    private void updateDate(int miladiDay, int miladiMonth) {
        int miladiMonth1;
        int miladiDay1;
        Calendar miladiDate = GregorianCalendar.getInstance();
        Calendar prsDate = GregorianCalendar.getInstance(new ULocale("ar_IR"));
        prsDate.set(1395, miladiMonth, miladiDay);
        miladiDate.setTime(prsDate.getTime());
        miladiDay1 = miladiDate.get(Calendar.DATE);
        miladiMonth1 = miladiDate.get(Calendar.MONTH);
        year = miladiDate.get(Calendar.YEAR);
        dayLabel.setText(Integer.toString(miladiDay1));
        miladiMonthLabel(miladiMonth1);
        yearLabel.setText(" سال" + year);

    }

    /**
     *
     * @return it returns a panel with correct Gregorian date
     */
    private JPanel miladiDate() {
        JPanel miladiPanel = new JPanel();
        JLabel whichLabel = new JLabel();
        String arbDate3[] = new String[3];
        dayLabel.setFont(new Font("Afra", Font.PLAIN, 15));
        monthLabel.setFont(new Font("Afra", Font.PLAIN, 15));
        yearLabel.setFont(new Font("Afra", Font.PLAIN, 15));
        whichLabel.setFont(new Font("Afra", Font.PLAIN, 15));
        miladiPanel.add(whichLabel);
        miladiPanel.add(dayLabel);
        miladiPanel.add(monthLabel);
        miladiPanel.add(yearLabel);
        miladiPanel.setComponentOrientation(RIGHT_TO_LEFT);
        miladiPanel.setBackground(new Color(255, 250, 240));
        return miladiPanel;

    }

    /**
     *
     * @param arbDay the Persian date of wanted day
     * @param arbMonth the Persian month of wanted day find the Arabic date of
     * wanted day
     */
    private void updateDateArb(int arbDay, int arbMonth) {
        int arbMonth1;
        int arbDay1;
        Calendar arbDate = GregorianCalendar.getInstance(new ULocale("ar_SA"));
        Calendar prsDate = GregorianCalendar.getInstance(new ULocale("ar_IR"));
        prsDate.set(1395, arbMonth, arbDay);
        arbDate.setTime(prsDate.getTime());
        arbDay1 = arbDate.get(Calendar.DATE);
        arbMonth1 = arbDate.get(Calendar.MONTH);
        year1 = arbDate.get(Calendar.YEAR);
        arbMonthLabel(arbMonth1);
        dayLabel1.setText(Integer.toString(arbDay1));
        yearLabel1.setText(" سال" + year1);

    }

    /**
     *
     * @return it returns a panel with correct Arabic date
     */
    private JPanel arbDate() {

        JPanel arbPanel = new JPanel();
        String arbDate3[] = new String[3];
        dayLabel1.setFont(new Font("Afra", Font.PLAIN, 15));
        monthLabel1.setFont(new Font("Afra", Font.PLAIN, 15));
        yearLabel1.setFont(new Font("Afra", Font.PLAIN, 15));
        arbPanel.add(dayLabel1);
        arbPanel.add(monthLabel1);
        arbPanel.add(yearLabel1);
        arbPanel.setComponentOrientation(RIGHT_TO_LEFT);
        arbPanel.setBackground(new Color(255, 250, 240));
        return arbPanel;

    }

    /**
     *
     * @param month the number of month make a label of Arabic month name
     */
    private void arbMonthLabel(int month) {

        if (month == 0) {
            monthLabel1.setText("محرم");
        } else if (month == 1) {
            monthLabel1.setText("صفر");
        } else if (month == 2) {
            monthLabel1.setText("ربیع الاول");
        } else if (month == 3) {
            monthLabel1.setText("ربیع الثانی");
        } else if (month == 4) {
            monthLabel1.setText("جمادی الاول ");
        } else if (month == 5) {
            monthLabel1.setText("جمادی الثانی");
        } else if (month == 6) {
            monthLabel1.setText("رجب");
        } else if (month == 7) {
            monthLabel1.setText("شعبان");
        } else if (month == 8) {
            monthLabel1.setText("رمضان");
        } else if (month == 9) {
            monthLabel1.setText("شوال");
        } else if (month == 10) {
            monthLabel1.setText("ذی القعده");
        } else if (month == 11) {
            monthLabel1.setText("ذی الحجه");
        }

    }

    /**
     *
     * @param month the number of month make a label of Gregorian month name
     */
    private void miladiMonthLabel(int month) {

        if (month == 0) {
            monthLabel.setText("ژانویه");
        } else if (month == 1) {
            monthLabel.setText("فوریه");
        } else if (month == 2) {
            monthLabel.setText("مارس");
        } else if (month == 3) {
            monthLabel.setText("اوریل");
        } else if (month == 4) {
            monthLabel.setText("مه");
        } else if (month == 5) {
            monthLabel.setText("ژوئن");
        } else if (month == 6) {
            monthLabel.setText("ژوئیه");
        } else if (month == 7) {
            monthLabel.setText("اوت");
        } else if (month == 8) {
            monthLabel.setText("سپتامبر");
        } else if (month == 9) {
            monthLabel.setText("اکتبر");
        } else if (month == 10) {
            monthLabel.setText("نوامبر");
        } else if (month == 11) {
            monthLabel.setText("دسامبر");
        }

    }

    /**
     *
     * @return it returns the main JPanel that add to the main frame
     */
    public JPanel add() {
        myTable();
        monthLable();
        updateYear();
        jpanel1.add(monthYear, BorderLayout.NORTH);
        jpanel1.add(week1, BorderLayout.CENTER);
        jpanel1.add(label1, BorderLayout.SOUTH);
        JPanel datePanel = new JPanel();
        JPanel datePanel1 = new JPanel();
        JPanel datePanel2 = new JPanel();
        datePanel.setLayout(new GridLayout(1, 2));
        datePanel.add(miladiDate());
        datePanel.add(arbDate());
        datePanel1.add(addRuydad());
        datePanel2.add(datePanel);
        datePanel2.add(datePanel1);
        datePanel2.setPreferredSize(new Dimension(360, 120));
        datePanel2.setLayout(new FlowLayout());
        datePanel1.setBackground(new Color(255, 250, 240));
        datePanel2.setBackground(new Color(255, 250, 240));
        datePanel.setBackground(new Color(255, 250, 240));
        jpanel1.add(datePanel2);
        return jpanel1;
    }

}
