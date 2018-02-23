    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;
//import calendar1.table.arrowHandler;

import java.awt.BorderLayout;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import javax.swing.JFrame;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.Calendar;

/**
 *
 * @author Monireh.S main frame
 */
public class Frame extends JFrame {

    public Frame() {

        Calendar prsCal = GregorianCalendar.getInstance(new ULocale("ar_IR"));
        setSize(460, 590);
        setLocation(230, 120);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setComponentOrientation(RIGHT_TO_LEFT);
        setTitle("تقویم من");
        MenuBar myMenu = new MenuBar();
        ToolBar toolBar = new ToolBar();
        setJMenuBar(myMenu.creatMenu());
        table t = new table(toolBar.rightArrow, toolBar.leftArrow, myMenu.copyDate);
        int day = prsCal.get(com.ibm.icu.util.Calendar.DAY_OF_MONTH);
        int month = prsCal.get(com.ibm.icu.util.Calendar.MONTH);
        Note n = new Note(t);
        Edit e = new Edit(t);
        ShowNote sh = new ShowNote(t);
        add(toolBar.toolBar(), BorderLayout.NORTH);
        add(t.add(), BorderLayout.CENTER);

    }

}
