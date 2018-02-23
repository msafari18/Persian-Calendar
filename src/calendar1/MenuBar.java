/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import static calendar1.table.selectedDay;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.ULocale;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 *
 * @author Monireh.S make a menu for main frame
 */
public class MenuBar {

    JMenuItem addHappening;
    JMenuItem addNote;
    JMenuItem editNote;
    JMenuItem copyDate;

    /**
     *
     */
    public MenuBar() {

        addHappening = new JMenuItem("رویداد :)");
        addNote = new JMenuItem("یادداشت :)");
        editNote = new JMenuItem("ویرایش یادداشت ها یا رویداد");
        copyDate = new JMenuItem("کپی کردن تاریخ روز");

    }

    /**
     *
     * @return it returns a JMenuBar with 2 JMenu
     */
    public JMenuBar creatMenu() {

        Ruydad r = new Ruydad();
        JMenuBar myMenu = new JMenuBar();
        myMenu.setBackground(new Color(255, 250, 240));
        myMenu.setComponentOrientation(RIGHT_TO_LEFT);
        JMenu insert = new JMenu("درج");
        JMenu edit = new JMenu("ویرایش");
        edit.setMnemonic('V');
        insert.setMnemonic('D');
        edit.setDisplayedMnemonicIndex(0);
        insert.setDisplayedMnemonicIndex(0);
        myMenu.add(edit);
        myMenu.add(insert);
        insert.setComponentOrientation(RIGHT_TO_LEFT);
        edit.setComponentOrientation(RIGHT_TO_LEFT);
        insert.setFont(new Font("Afra", Font.PLAIN, 23));
        edit.setFont(new Font("Afra", Font.PLAIN, 23));
        insert.add(addHappening);
        insert.add(addNote);
        edit.add(editNote);
        edit.add(copyDate);
        addHappening.setHorizontalAlignment(SwingConstants.RIGHT);
        addNote.setHorizontalAlignment(SwingConstants.RIGHT);
        editNote.setHorizontalAlignment(SwingConstants.RIGHT);
        copyDate.setHorizontalAlignment(SwingConstants.RIGHT);

        addHappening.setFont(new Font("Afra", Font.PLAIN, 17));
        addNote.setFont(new Font("Afra", Font.PLAIN, 17));
        editNote.setFont(new Font("Afra", Font.PLAIN, 17));
        copyDate.setFont(new Font("Afra", Font.PLAIN, 17));

        //////////////////  LISTENER
        menuItemHandler mIHandler = new menuItemHandler();
        addHappening.addActionListener(mIHandler);
        addNote.addActionListener(mIHandler);
        copyDate.addActionListener(mIHandler);
        editNote.addActionListener(mIHandler);

        //////////////////
        accelerator();
        return myMenu;
    }

    /**
     * set an accelerator for menu item
     */
    private void accelerator() {
        editNote.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.ALT_MASK));
        copyDate.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.ALT_MASK));
        addNote.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.ALT_MASK));
        addHappening.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_H, ActionEvent.ALT_MASK));
    }

    /**
     * handles events of menu item
     */
    private class menuItemHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == addHappening) {
                String title = "رویداداضافه کن";

                char r = 'r';
                showNewFrameForAddEvent(title, r);

            } else if (e.getSource() == addNote) {
                String title = "یادداشت اضافه کن";
                ImageIcon happenIconL = new ImageIcon("note1.gif");
                char n = 'n';
                showNewFrame(title, happenIconL, n);
            } else if (e.getSource() == editNote) {
                String title = "ویرایش کن";
                ImageIcon happenIcon = new ImageIcon("note1.gif");
                ImageIcon happenIcon1 = new ImageIcon("edit.gif");
                ImageIcon happenIcon2 = new ImageIcon("baloon.gif");
                showEditFrame(title, happenIcon, happenIcon1, happenIcon2);
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

        final com.ibm.icu.util.Calendar miladiDate = GregorianCalendar.getInstance();
        final com.ibm.icu.util.Calendar prsDate = GregorianCalendar.getInstance(new ULocale("ar_IR"));

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
                    int miladiDay = miladiDate.get(com.ibm.icu.util.Calendar.DATE);
                    int miladiMonth = miladiDate.get(com.ibm.icu.util.Calendar.MONTH);
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

}
