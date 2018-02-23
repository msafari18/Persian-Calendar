/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.UIManager;

/**
 *
 * @author USER!
 */
public class Calendar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, AWTException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        Frame m1 = new Frame();
        m1.setVisible(true);

        IconTray mo = new IconTray(m1);
        mo.displayTray();
    }

}
