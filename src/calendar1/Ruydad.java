/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monireh.S 
 * read events from file
 */
public class Ruydad {

    String rooydad;
    String ruydad = "";
    String note = "";
    String monasebat2;

    public Ruydad() {

    }

    public Ruydad(String o) {
        rooydad = o;

    }

    /**
     *
     * @return it returns an ArrayList that contains event of every days
     */
    public ArrayList<Ruydad> loadDays() {
        ArrayList<Ruydad> days = new ArrayList(366);
        try {

            String fileName = "monasebat-95.txt";
            int numberOfLines = findNumberOfLines(fileName);

            String part2 = "";
            File file = new File(fileName);
            try (Scanner sc = new Scanner(file)) {
                String line = "";
                for (int i = 0; i < numberOfLines; i++) {

                    line = sc.nextLine();
                    String[] event = line.split("/");
                    if (event.length == 1) {
                        part2 = "رویدادی یافت نشد";
                    } else {

                        if ("0".equals(event[1])) {
                            part2 = "رویدادی یافت نشد";
                        } else {
                            part2 = event[1];
                        }
                        days.add(new Ruydad(part2));
                    }
                }

                String fileName2 = "the-file-name.txt";
                int numberOfLines2 = findNumberOfLines(fileName2);
                File file2 = new File(fileName2);
                Scanner sc2 = new Scanner(file2);
                String line2;
                for (int i = 0; i < numberOfLines2; i++) {
                    line = sc2.nextLine();
                    if (i == 0) {
                        String[] buffer = line.split("/");
                        if (buffer[0].charAt(0) == '1' || buffer[0].charAt(0) == '2' || buffer[0].charAt(0) == '3' || buffer[0].charAt(0) == '4' 
                                || buffer[0].charAt(0) == '5' || buffer[0].charAt(0) == '6' || buffer[0].charAt(0) == '7' 
                                || buffer[0].charAt(0) == '8' || buffer[0].charAt(0) == '9' || buffer[0].charAt(0) == '0') {
                            line2 = line;
                        } else {
                            line2 = line.substring(1);
                        }
                    } else {
                        line2 = line;
                    }
                    String[] buffer = line2.split("/");
                    if (buffer.length == 2) {
                        if (!days.get(Integer.parseInt(buffer[0])).note.isEmpty()) {
                            days.get(Integer.parseInt(buffer[0])).note = buffer[1] + " - " + days.get(Integer.parseInt(buffer[0])).note;
                        } else {
                            days.get(Integer.parseInt(buffer[0])).note = buffer[1];
                        }
                    } else if (buffer.length == 3) {
                        if (!days.get(Integer.parseInt(buffer[0])).ruydad.isEmpty()) {
                            days.get(Integer.parseInt(buffer[0])).ruydad = buffer[2] + " - " + days.get(Integer.parseInt(buffer[0])).ruydad;
                        } else {
                            days.get(Integer.parseInt(buffer[0])).ruydad = buffer[2];
                        }
                    }

                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ruydad.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(Ruydad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return days;

    }

    /**
     *
     * @param filename the name of the file
     * @return number of line
     * @throws IOException
     */
    public static int findNumberOfLines(String filename) throws IOException {
        int nol;
        try (LineNumberReader reader = new LineNumberReader(new FileReader(filename))) {
            nol = 0;
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {
            }
            nol = reader.getLineNumber();
        }
        return nol;
    }

}
