/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Monireh.S
 * a  frame to showing all the notes of the day
 */
public class ShowNote extends JFrame {

    table t = new table();
    int numberOfDay;
    JLabel[] notes = new JLabel[10];
    private Color color;
    private int font1 = 3;
    private int size1 = 18;

    public ShowNote() {
        super("یادداشت ها");
        makeNote1();
    }

    public ShowNote(table t) {
        super("یادداشت ها");
        this.t = t;
    }

    public ShowNote(int n) {
        super("یادداشت ها");
        makeNote1();
        numberOfDay = n - 1;

    }

    /**
     * add all notes of the day to this frame
     */
    public void makeNote1() {
        int numberOfDay1 = t.selectedDay;
        if (numberOfDay == 0) {
            numberOfDay = numberOfDay1 - 1;
        }
        if (numberOfDay == -1) {
            numberOfDay = t.whichDayOfYear(t.month + 1, t.a);
        }
        String notes1 = t.days.get(numberOfDay).note;
        String[] notes2 = notes1.split("-");
        for (int i = 0; i < 10; i++) {
            notes[i] = new JLabel("");
        }
        for (int i = 0; i < notes2.length; i++) {

            notes[i].setText(notes2[i]);

            notes[i].setComponentOrientation(RIGHT_TO_LEFT);
            notes[i].setBackground(new Color(154, 205, 50));
            notes[i].setOpaque(true);
        }
        for (int i = 0; i < 10; i++) {
            notes[i].setBackground(new Color(154, 205, 50));
            notes[i].setOpaque(true);
        }

    }

    /**
     * add notes
     * add button of size
     * add button of change the color
     * add button of font
     */
    public void showNote1() {
        JComboBox<Integer> size;
        Integer[] arr = {14, 16, 18, 20, 22, 24, 26, 28, 36};
        JRadioButton bold = new JRadioButton("درشت نمایی");
        JRadioButton italic = new JRadioButton("ایتالیک");
        JRadioButton plain = new JRadioButton("ساده");
        ImageIcon color1 = new ImageIcon("color.png");
        JButton changeColor = new JButton(color1);
        changeColor.setToolTipText("رنگ متنو نغییر بده!");
        JPanel showButton = new JPanel();
        JPanel showNotes = new JPanel();
        JPanel showSize = new JPanel();
        JPanel showAll = new JPanel();
        ////////////
        showNotes.setOpaque(true);
        showNotes.setBackground(new Color(154, 205, 50));
        for (int i = 0; i < 10; i++) {
            showNotes.add(notes[i]);
        }
        showNotes.setLayout(new GridLayout(10, 1));
        //////////////////////////

        ButtonGroup group = new ButtonGroup();
        group.add(bold);
        group.add(italic);
        group.add(plain);

        showButton.add(bold);
        showButton.add(italic);
        showButton.add(plain);
        showButton.setComponentOrientation(RIGHT_TO_LEFT);
        ////////////////////////
        size = new JComboBox<Integer>(arr);
        showSize.add(size);

        //////////////////////
        size.addItemListener(
                new ItemListener() {

                    @Override
                    public void itemStateChanged(ItemEvent event) {

                        if (event.getStateChange() == ItemEvent.SELECTED) {
                            size1 = (int) event.getItem();
                            change();
                        }
                    }
                }
        );

        /////////////////////////////////////
        changeColor.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        color = JColorChooser.showDialog(ShowNote.this, "choose a color", color);
                        if (color == null) {
                            color = Color.BLACK;
                        }
                        for (int i = 0; i < 10; i++) {
                            notes[i].setForeground(color);
                            notes[i].setOpaque(true);
                        }

                    }
                }
        );

        bold.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                font1 = 1;
                change();
            }
        });

        italic.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                font1 = 2;
                change();
            }
        });
        plain.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                font1 = 3;
                change();
            }
        });
        showButton.setLayout(new GridLayout(3, 1));
        showAll.add(showButton);
        showAll.add(changeColor);
        showAll.add(size);
        showAll.setLayout(new FlowLayout());

        add(showAll, BorderLayout.NORTH);
        add(showNotes, BorderLayout.CENTER);
        setSize(300, 400);
        setLocation(400, 150);
        setBackground(new Color(30, 144, 255));
        setVisible(true);
        setComponentOrientation(RIGHT_TO_LEFT);
        setResizable(false);
    }

    /**
     * change font or size of the text
     */
    public void change() {

        if (font1 == 1) {
            for (int i = 0; i < 10; i++) {
                notes[i].setFont(new Font("Afra", Font.BOLD, size1));
            }
        }
        if (font1 == 2) {
            for (int i = 0; i < 10; i++) {
                notes[i].setFont(new Font("Afra", Font.ITALIC, size1));
            }
        }
        if (font1 == 3) {
            for (int i = 0; i < 10; i++) {
                notes[i].setFont(new Font("Afra", Font.PLAIN, size1));
            }
        }

    }
}