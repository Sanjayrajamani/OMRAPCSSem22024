import FileIO.PDFHelper;
import Filters.DisplayInfoFilter;
import core.DImage;
import processing.core.PImage;

import javax.swing.*;
import java.io.File;

public class OpticalMarkReaderMain {
    public static void main(String[] args) {
        String pathToPdf = fileChooser();
        System.out.println("Loading pdf at " + pathToPdf);

        /*
        Your code here to...
        (1).  Load the pdf
        (2).  Loop over its pages
        (3).  Create a DImage from each page and process its pixels
        (4).  Output 2 csv files
         */


        for (int i = 1; i < 7; i++) {
            System.out.println("Loading pdf....");
            PImage in = PDFHelper.getPageImage("assets/OfficialOMRSampleDoc.pdf",1);
            DImage img = new DImage(in);       // you can make a DImage from a PImage

           // System.out.println("Running filter on page 1....");
            DisplayInfoFilter filter = new DisplayInfoFilter();
            filter.processImage(img);
          //  ArrayList<String> pageAnswers = filter.getAnswer(img);
        }




    }

    private static String fileChooser() {
        String userDirLocation = System.getProperty("user.dir");
        File userDir = new File(userDirLocation);
        JFileChooser fc = new JFileChooser(userDir);
        int returnVal = fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        return file.getAbsolutePath();
    }
}
