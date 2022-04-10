package ExtraFeature;

import java.util.*;
import java.io.*;

import static Main.TesterDonald.group11;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chart {

    public static int se = 0, ai = 0, csn = 0, mm = 0, is = 0, ds = 0;

    public static void ReadNumberOfGraduates() {
        System.out.println("Enter file name: ");
        String name = group11.next();

        String a = "Software Engineering";
        String b = "Artificial Intelligence";
        String c = "Computer System and Network";
        String d = "Multimedia";
        String f = "information system";
        String g = "Data Science";


        try {
            Scanner csv = new Scanner(new FileInputStream(name));

            int column = 0;
            int row = 0;

            while (csv.hasNextLine()) {
                String s1 = csv.nextLine();
                String[] s1_spilt = s1.split(",");
                column = s1_spilt.length;
                row++;
            }

            Scanner csv2 = new Scanner(new FileInputStream(name));
            String[][] file = new String[row][column];
            for (int i = 0; i < row; i++) {
                String s1 = csv2.nextLine();
                String[] s1_split = s1.split(",");
                for (int j = 0; j < column; j++) {
                    file[i][j] = s1_split[j];
                    if (file[i][j].equalsIgnoreCase(a)) {
                        se++;
                    } else if (file[i][j].equalsIgnoreCase(b)) {
                        ai++;
                    } else if (file[i][j].equalsIgnoreCase(c)) {
                        csn++;
                    } else if (file[i][j].equalsIgnoreCase(d)) {
                        mm++;
                    } else if (file[i][j].equalsIgnoreCase(f)) {
                        is++;
                    } else if (file[i][j].equalsIgnoreCase(g)){
                        ds++;
                    }
                }
            }
            csv.close();
            System.out.println("Total number graduates for software engineering: " + se);
            System.out.println("Total number graduates for artificial intelligence: " + ai);
            System.out.println("Total number graduates for computer and system network: " + csn);
            System.out.println("Total number graduates for multimedia: " + mm);
            System.out.println("Total number graduates for information system: " + is);
            System.out.println("Total number graduates for data science: " + ds);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
        }
    }

    public static void getBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(se, "", "Software engineering");
        dataset.setValue(ai, "", "Artificial intelligence");
        dataset.setValue(csn, "", "Computer and System network");
        dataset.setValue(mm, "", "Multimedia");
        dataset.setValue(is, "", "Information System");
        JFreeChart chart = ChartFactory.createBarChart("CourseName", "", "Number of graduates", dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.BLACK);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.GREEN);
        ChartFrame frame1 = new ChartFrame("Count", chart);
        frame1.setVisible(true);
        frame1.setSize(800, 800);
    }
}

