
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import static calendar1.table.selectedDay;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.ULocale;
import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import com.ibm.icu.util.Calendar;
import java.awt.BorderLayout;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import java.awt.GridLayout;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Monireh.S makes a system tray icon
 */
public class IconTray {

    String monasebat;
    ArrayList<Ruydad> days = new ArrayList(366);
    Ruydad ruydad1 = new Ruydad();
    JFrame frame;

    /**
     *
     * @param frame main frame
     * @throws IOException
     */
    public IconTray(JFrame frame) throws IOException {
        this.frame = frame;
        days = ruydad1.loadDays();
    }

    Calendar prsCal = GregorianCalendar.getInstance(new ULocale("ar_IR"));

    /**
     *
     * @throws IOException
     * @throws AWTException displays a tray icon that change with date
     */
    public void displayTray() throws IOException, AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        ImageIcon icon = null;
        int day = prsCal.get(Calendar.DAY_OF_MONTH);
        int month = prsCal.get(Calendar.MONTH);
        int month1 = month + 1;

        if (prsCal.get(Calendar.DAY_OF_MONTH) == 1) {
            icon = new ImageIcon("1.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 2) {
            icon = new ImageIcon("2.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 3) {
            icon = new ImageIcon("3.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 4) {
            icon = new ImageIcon("4.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 5) {
            icon = new ImageIcon("5.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 6) {
            icon = new ImageIcon("6.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 7) {
            icon = new ImageIcon("7.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 8) {
            icon = new ImageIcon("8.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 9) {
            icon = new ImageIcon("9.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 10) {
            icon = new ImageIcon("10.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 11) {
            icon = new ImageIcon("11.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 12) {
            icon = new ImageIcon("12.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 13) {
            icon = new ImageIcon("13.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 14) {
            icon = new ImageIcon("14.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 15) {
            icon = new ImageIcon("15.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 16) {
            icon = new ImageIcon("16.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 17) {
            icon = new ImageIcon("17.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 18) {
            icon = new ImageIcon("18.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 19) {
            icon = new ImageIcon("19.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 20) {
            icon = new ImageIcon("20.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 21) {
            icon = new ImageIcon("21.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 22) {
            icon = new ImageIcon("22.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 23) {
            icon = new ImageIcon("23.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 24) {
            icon = new ImageIcon("24.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 25) {
            icon = new ImageIcon("25.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 26) {
            icon = new ImageIcon("26.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 27) {
            icon = new ImageIcon("27.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 28) {
            icon = new ImageIcon("28.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 29) {
            icon = new ImageIcon("29.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 30) {
            icon = new ImageIcon("30.png");
        } else if (prsCal.get(Calendar.DAY_OF_MONTH) == 31) {
            icon = new ImageIcon("31.png");
        }

        int whichDay = whichDayOfYear(day, month1);
        monasebat = updateRuydad(whichDay);
        Image image = icon.getImage();

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        TrayIconListener trayListener = new TrayIconListener();
        trayIcon.addMouseListener(trayListener);
        trayIcon.setPopupMenu(addPopUpMenu());
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(monasebat);
        tray.add(trayIcon);
        trayIcon.displayMessage("PCalendar Started", "Click Here To See", TrayIcon.MessageType.INFO);
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
     * @param ruydad3 given day of year 
     * find the event of every day in days ArrayList
     * @return returns the event for today
     */
    private String updateRuydad(int ruydad3) {

        Ruydad ruydad1 = new Ruydad();
        ruydad3--;
        ruydad1 = days.get(ruydad3);
        String monasebat;
        monasebat = ruydad1.rooydad;
        return monasebat;
    }

    MenuItem addHappening;
    MenuItem addNote;
    MenuItem editNote;
    MenuItem copyDate;
    MenuItem copyHappening;

    /**
     *
     * @return make a JPopupMenu for right clicked on every day
     */
    private PopupMenu addPopUpMenu() {
        PopupMenu labelPopupMenu = new PopupMenu();
        Menu insert = new Menu("درج");
        Menu edit = new Menu("ویرایش");
        MenuItem close = new MenuItem("بستن برنامه");
        MenuItem open = new MenuItem("باز کردن پنجره ی اصلی");

        addHappening = new MenuItem("رویداد");
        addNote = new MenuItem("یادداشت");
        editNote = new MenuItem("ویرایش یادداشت ها یا رویداد");
        copyDate = new MenuItem("کپی کردن تاریخ روز");
        copyHappening = new MenuItem("کپی کردن رویداد روز");

        insert.add(addHappening);
        insert.add(addNote);
        edit.add(editNote);
        edit.add(copyDate);
        edit.add(copyHappening);


        labelPopupMenu.add(insert);
        labelPopupMenu.add(edit);
        labelPopupMenu.add(open);
        labelPopupMenu.add(close);
        insert.setFont(new Font("Afra", Font.PLAIN, 15));
        edit.setFont(new Font("Afra", Font.PLAIN, 15));
        addHappening.setFont(new Font("Afra", Font.PLAIN, 15));
        addNote.setFont(new Font("Afra", Font.PLAIN, 15));
        editNote.setFont(new Font("Afra", Font.PLAIN, 15));
        copyDate.setFont(new Font("Afra", Font.PLAIN, 15));
        copyHappening.setFont(new Font("Afra", Font.PLAIN, 15));

        ActionListener actionListener = new PopupActionListener();
        close.addActionListener(actionListener);
        open.addActionListener(actionListener);
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
                ImageIcon happenIconL = new ImageIcon("baloon.gif");
                char r = 'r';
                showNewFrameForAddEvent(title,r);

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
                int day = prsCal.get(Calendar.DAY_OF_MONTH);
                int month = prsCal.get(Calendar.MONTH) + 1;
                String myString = (day + "/" + month + "/1395");
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            } else if ("کپی کردن رویداد روز".equals(e.getActionCommand())) {
                String myString = (monasebat);
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }else if ("بستن برنامه".equals(e.getActionCommand())) {
                System.exit(0);
            }else if ("باز کردن پنجره ی اصلی".equals(e.getActionCommand())) {
                frame.setVisible(true);
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
        final int day = prsCal.get(Calendar.DAY_OF_MONTH);
        final int month = prsCal.get(Calendar.MONTH);
        final int month1 = month + 1;
        JButton eventButton = new JButton(happenIcon2);
        eventButton.setToolTipText("ویرایش رویداد ها");
        JButton noteButton = new JButton(happenIcon);
        noteButton.setToolTipText("ویرایش یادداشت ها");
        noteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Edit e = new Edit(whichDayOfYear(day,month1));
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
        final int day = prsCal.get(Calendar.DAY_OF_MONTH);
        final int month = prsCal.get(Calendar.MONTH);
        final int month1 = month + 1;

        addNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showNewFrameForAddNote(title, m, ch);

            }
        });

        showNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ShowNote shNote = new ShowNote(whichDayOfYear(day, month1));
                shNote.showNote1();
            }
        });

        addNote.setToolTipText("یادداشت اضافه کن");
        frame.add(addNote);
        showNote.setToolTipText("یادداشتاتو ببین!");
        frame.add(showNote);
        frame.setSize(250, 150);
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
        final int day = prsCal.get(Calendar.DAY_OF_MONTH);
        final int month = prsCal.get(Calendar.MONTH);
        final int month1 = month + 1;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String newNote = note.getText();
                Note n = new Note(whichDayOfYear(day, month1));
                n.writeInFile(newNote, ch);
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
        final int day = prsCal.get(Calendar.DAY_OF_MONTH);
        final int month = prsCal.get(Calendar.MONTH);
        final int month3 = month + 1;
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
                Note n = new Note(whichDayOfYear(day, month3));
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

    private class TrayIconListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() >= 2) {
                frame.setVisible(true);
            }
        }

    }
}
