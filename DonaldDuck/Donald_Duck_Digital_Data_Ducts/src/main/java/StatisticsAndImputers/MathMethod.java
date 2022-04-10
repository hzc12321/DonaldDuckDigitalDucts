package StatisticsAndImputers;

import java.io.*;
import java.util.Scanner;

public class MathMethod extends ConnectorPart3 {
    public static String filepath2;
    public static Scanner group11 = new Scanner(System.in);

    private static void ReadCsv2() {
        System.out.println("Enter file name that you want to use: ");
        filepath2 = group11.next();
        System.out.println();
    }

    public static void ComputeMath() {

        ReadCsv2();
        String[][] outputCSV = new String[9][5];
        try {
            File a = new File(filepath2);
            Scanner sc1 = new Scanner(a);
            int numberOfRow = 0;
            int numberOfColumn = 0;
            while (sc1.hasNextLine()) { //detect number of row and column
                String s1 = sc1.nextLine();
                String[] s1_spilt = s1.split(",");
                numberOfColumn = s1_spilt.length;
                numberOfRow++;
            }

            Scanner sc2 = new Scanner(a);
            String[][] data = new String[numberOfRow][numberOfColumn];
            for (int i = 0; i < numberOfRow; i++) { //copy data into an array
                String s1 = sc2.nextLine();
                String[] s1_split = s1.split(",");
                System.arraycopy(s1_split, 0, data[i], 0, numberOfColumn);
            }

            for (int i = 0; i < numberOfRow; i++) { //display out all the data in array
                for (int j = 0; j < numberOfColumn; j++) {
                    System.out.printf("%-30s", data[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            int p = 0;
            for (int j = 2; j < 5; j++) { //for Current CGPA, Expected Graduation Salary and Actual Graduation Salary

                double min = Double.MAX_VALUE;
                double max = 0;
                double total = 0;
                double total2 = 0;
                for (int i = 1; i < numberOfRow; i++) { //min and max
                    double actual = Double.parseDouble(data[i][j]);
                    total += actual;
                    total2 += (Math.pow(actual, 2));
                    if (actual > max) { //determine max
                        max = actual;
                    } else if (actual < min) { //determine min
                        min = actual;
                    }
                }
                double[] newArray = new double[numberOfRow - 1]; //new array for sorting
                for (int i = 1; i < numberOfRow; i++) {
                    newArray[i - 1] = Double.parseDouble(data[i][j]);
                }
                for (int pass = 1; pass < newArray.length; pass++) { //data sorting
                    for (int i = 0; i < newArray.length - 1; i++) {
                        if (newArray[i] > newArray[i + 1]) {
                            double hold = newArray[i];
                            newArray[i] = newArray[i + 1];
                            newArray[i + 1] = hold;
                        }
                    }
                }
                double median;
                if ((newArray.length) % 2 != 0) { //determine median
                    median = newArray[((newArray.length + 1) / 2) - 1];
                } else {
                    median = (newArray[newArray.length / 2] + newArray[(newArray.length / 2) - 1]) / 2;
                }
                StringBuilder mode = new StringBuilder(); //only mode is string because it is possible that there is no mode / 2 or more mode
                double num = 0;
                int maxCount = 0;
                int count = 0;
                for (double v : newArray) { //determine mode(duplication)--(highest frequency) using data being sorted
                    if (v == num) {
                        count++;
                    }
                    else {
                        num = v;
                        count = 1;
                    }
                    if (count == maxCount) {
                        if (count > 1) {
                            mode.append("; ").append(num);
                        }
                        else {
                            mode = new StringBuilder("No mode");
                        }
                    }
                    if (count > maxCount) {
                        if (count > 1) {
                            mode = new StringBuilder(num + "");
                        }
                        maxCount = count;
                    }
                }
                double mean = total / (numberOfRow - 1);
                double range = max - min;
                double variance = (total2 - (Math.pow(total, 2) / (numberOfRow - 1))) / (numberOfRow - 2);
                double standardDeviation = Math.sqrt(variance);

                if (j == 2) {
                    outputCSV[0][0] = "<Current CGPA>";
                    System.out.println(outputCSV[0][0]);
                }
                if (j == 3) {
                    outputCSV[0][1] = "<Expected Graduation Salary>";
                    System.out.println(outputCSV[0][1]);
                }
                if (j == 4) {
                    outputCSV[0][2] = "<Actual Graduation Salary>";
                    System.out.println(outputCSV[0][2]);
                }

                outputCSV[1][p] = "Min: " + min;
                outputCSV[2][p] = "Max: " + max;
                if ("No mode".equals(mode.toString()))
                    maxCount = 0;
                outputCSV[3][p] = "Mode: " + mode + " (" + maxCount + ")";
                outputCSV[4][p] = "Median: " + median;
                outputCSV[5][p] = "Mean: " + mean;
                outputCSV[6][p] = "Range: " + range;
                outputCSV[7][p] = "Variance: " + variance;
                outputCSV[8][p] = "Standard Deviation: " + standardDeviation;

                System.out.println(outputCSV[1][p]);
                System.out.println(outputCSV[2][p]);
                System.out.println(outputCSV[3][p]);
                System.out.println(outputCSV[4][p]);
                System.out.println(outputCSV[5][p]);
                System.out.println(outputCSV[6][p]);
                System.out.println(outputCSV[7][p]);
                System.out.println(outputCSV[8][p]);
                System.out.println();

                p++;

            }

            int q = 3;
            for (int j = 0; j < 2; j++) { //for MODE of Name and Department

                String[] newArray2 = new String[numberOfRow - 1]; //new array for sorting
                for (int i = 1; i < numberOfRow; i++) {
                    newArray2[i - 1] = data[i][j];
                }
                for (int pass = 1; pass < newArray2.length; pass++) { //data sorting
                    for (int i = 0; i < newArray2.length - 1; i++) {
                        if (newArray2[i].compareToIgnoreCase(newArray2[i + 1]) > 0) {
                            String hold2 = newArray2[i];
                            newArray2[i] = newArray2[i + 1];
                            newArray2[i + 1] = hold2;
                        }
                    }
                }
                StringBuilder mode = new StringBuilder(); //mode is string because it is possible that there is no mode / 2 or more mode
                String num = "";
                int maxCount = 0;
                int count = 0;
                for (String s : newArray2) { //determine mode(duplication)--(highest frequency) using data being sorted
                    if (s.equals(num)) {
                        count++;
                    }
                    else {
                        num = s;
                        count = 1;
                    }
                    if (count == maxCount) {
                        if (count > 1) {
                            mode.append("; ").append(num);
                        }
                        else {
                            mode = new StringBuilder("No mode");
                        }
                    }
                    if (count > maxCount) {
                        if (count > 1) {
                            mode = new StringBuilder(num + "");
                        }
                        maxCount = count;
                    }
                }
                if (j == 0) {
                    outputCSV[0][3] = "<Name>";
                    System.out.println(outputCSV[0][3]);
                }
                if (j == 1) {
                    outputCSV[0][4] = "<Department>";
                    System.out.println(outputCSV[0][4]);
                }

                if ("No mode".equals(mode.toString()))
                    maxCount = 0;
                outputCSV[1][q] = "Mode: " + mode + " (" + maxCount + ")";

                System.out.println(outputCSV[1][q]);
                System.out.println();

                q++;

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
        }

        try {
            PrintWriter output = new PrintWriter(new FileOutputStream("statistics.csv"));
            for (int i = 0; i < outputCSV.length; i++) {
                for (int j = 0; j < outputCSV[i].length; j++) {
                    if (j == 3 && i > 1)
                        output.print("");
                    else if (j == 4 && i > 1)
                        output.println("");
                    else {
                        if (j == outputCSV[i].length - 1)
                            output.println(outputCSV[i][j]);
                        else
                            output.print(outputCSV[i][j] + ",");
                    }
                }
            }
            output.close();
        }
        catch (IOException e) {
            System.out.println("Problem with file output");
        }

    }
}
