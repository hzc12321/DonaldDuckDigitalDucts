package Manipulation;

import static Main.TesterDonald.group11;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class RemoveDuplicatedRowInData extends ConnectorPart2 {
    public static String filepath4;


    public static void ReadFile4() {
        System.out.println();
        System.out.println("Enter file name that you want to use: ");
        filepath4 = group11.next();
        System.out.println();
    }

    public static void DeleteDuplicatedRow() {

        ReadFile4();
        try {

            Scanner group11 = new Scanner(System.in);
            Scanner csv = new Scanner(new FileInputStream(filepath4));
            int numberOfRow = 0;
            int numberOfColumn = 0;

            while (csv.hasNextLine()) { //detect number of row and column
                String s1 = csv.nextLine();
                String[] s1_spilt = s1.trim().split(",");
                numberOfColumn = s1_spilt.length;
                numberOfRow++;
            }

            Scanner csv2 = new Scanner(new FileInputStream(filepath4));
            String[][] data = new String[numberOfRow][numberOfColumn];
            for (int i = 0; i < numberOfRow; i++) { //copy data into an array
                String s1 = csv2.nextLine();
                String[] s1_split = s1.split(",");
                System.arraycopy(s1_split, 0, data[i], 0, numberOfColumn);
            }

            for (int i = 0; i < numberOfRow; i++) { //display out all the data in array
                for (int j = 0; j < numberOfColumn; j++) {
                    System.out.printf("%-30s", data[i][j]);
                }
                System.out.println();
            }

            String duplicatedName = "";
            int[] detector = new int[numberOfRow]; //detect duplication at specific index
            for (int i = 0; i < numberOfRow - 1; i++) {
                for (int j = i + 1; j < numberOfRow; j++) {
                    if (data[i][0].equalsIgnoreCase(data[j][0])) {
                        detector[i] = i;
                        detector[j] = j;
                        duplicatedName = data[i][0];
                    }
                }
            }

            int[] detector2 = new int[numberOfRow]; //change the duplication index starting from index 0
            int d = 0;
            for (int i = 0; i < numberOfRow; i++) {
                if (detector[i] != 0) {
                    detector2[d] = detector[i];
                    d++;
                }
            }

            if (d > 1) { //if duplication occurs

                System.out.println();
                for (int p = 0; p < d; p++) { //display lines with name duplication
                    System.out.print("Line " + detector2[p] + ", ");
                }
                System.out.print("have the same name (" + duplicatedName + ")!");
                System.out.println(); //user makes decision
                System.out.println("Choose line to be kept (e.g. 1) or input -1 to remove all lines: ");
                int choice = group11.nextInt();
                System.out.println();

                FileOutputStream fos = new FileOutputStream(filepath4);
                PrintWriter pw = new PrintWriter(fos);
                int m = 0;
                for (int q = 0; q < numberOfRow; q++) { //correction of data depends on user's decision
                    if (choice != -1) {
                        if (q == detector2[m]) {
                            m++;
                            if (q != choice) {
                                continue;
                            }
                        }
                    }
                    else {
                        if (q == detector2[m]) {
                            m++;
                            continue;
                        }
                    }
                    for (int r = 0; r < numberOfColumn; r++) {
                        System.out.printf("%-30s", data[q][r]);
                        if (r == numberOfColumn - 1)
                            pw.print(data[q][r] + "\n");
                        else
                            pw.print(data[q][r] + ",");
                    }
                    System.out.println();
                }
                pw.close(); //copy data after correction to a new file path

            }

            else //if duplication does not occur
                System.out.println("\nThere is no duplication of name!");

        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
        }


    }
}