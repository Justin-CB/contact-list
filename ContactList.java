/**
 * @author Justin Bester and Kevin McCall
 * @version 1.0
 * Class containing the main logic of the ContactList program
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.invoke.StringConcatFactory;
import java.util.InputMismatchException;

public class ContactList {
    /** first table */
    private Table<Contact> table1;
    /** second table */
    private Table<Contact> table2;
    /** Scanner for keyboard input */
    private Scanner sysin;
    /** Scanner for first file */
    private Scanner file1;
    /** Scanner for second file */
    private Scanner file2;

    /**
     * Constructor for ContactList
     */
    public ContactList() {
        File file;
        sysin = new Scanner(System.in);
        System.out.print("Enter filename for contact list 1> ");
        file = new File(sysin.nextLine());
        try {
            file1 = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error1: bad filename");
            System.exit(1);
        }
        System.out.print("Enter filename for contact list 2> ");
        file = new File(sysin.nextLine());
        try {
            file2 = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error2: bad filename");
            System.exit(2);
        }
        table1 = new Table<Contact>("Contact List 1");
        table2 = new Table<Contact>("Contact List 2");
    }

    /**
     * Contains the main logic for the program
     */
    public void go() {
        int choice;
        readInput(file1, table1);
        readInput(file2, table2);
        System.out.println("Welcome to database display");
        while (true) {
            choice = menu();
            switch (choice) {
                case 0:
                    System.out.println("Goodbye");
                    System.exit(0);
                case 1:
                    handleIntersect();
                    break;
                case 2:
                    handleDifference();
                    break;
                case 3:
                    handleUnion();
                    break;
                case 4:
                    handleSelect();
                    break;
                case 5:
                    handleRemove();
                    break;
                case 6:
                    handlePrintBothTables();
                    break;
                default:
                    System.err.println("Invalid choice");
            }
        }
    }

    /**
     * Holds the logic for doing the intersect operator from user input
     */
    private void handleIntersect() {
        int group;
        String attr;
        String val;
        group = getGroup("Enter Group");
        if (group != 1 && group != 2) {
            System.err.println("Invalid group");
            return;
        }
        System.out.print("Enter attribute >");
        attr = sysin.nextLine();
        System.out.print("Enter value >");
        val = sysin.nextLine();
        try {
            if (group == 1) {
                System.out.println(table1.intersect(attr, val, table2));
            } else { /* group == 2 */
                System.out.println(table2.intersect(attr, val, table1));
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }
    }

    /**
     * Holds the logic for doing the difference operator from user input
     */
    private void handleDifference() {
        int group;
        group = getGroup("Enter Contact List");
        if (group == 1) {
            System.out.println(table1.difference(table2));
        } else if (group == 2) {
            System.out.println(table2.difference(table1));
        } else {
            System.err.println("Invalid List Number");
        }
    }

    /**
     * Holds the logic for doing the union operator from user input
     */
    private void handleUnion() {
        int group;
        group = getGroup("Enter Contact List");
        if (group == 1) {
            System.out.println(table1.union(table2));
        } else if (group == 2) {
            System.out.println(table2.union(table1));
        } else {
            System.err.println("Invalid List Number");
        }
    }

    /**
     * Holds the logic for doing the select operator from user input
     */
    private void handleSelect() {
        int group;
        String attr;
        String val;
        group = getGroup("Enter table (1/2)");
        if (group != 1 && group != 2) {
            System.err.println("Invalid group");
            return;
        }
        System.out.print("Enter attribute >");
        attr = sysin.nextLine();
        System.out.print("Enter value >");
        val = sysin.nextLine();
        try {
            if (group == 1) {
                System.out.println(table1.select(attr, val));
            } else { /* group == 2 */
                System.out.println(table2.select(attr, val));
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }
    }

    /**
     * Holds the logic for doing the remove operator from user input
     */
    private void handleRemove() {
        String attr;
        String val;
        System.out.print("Enter attribute >");
        attr = sysin.nextLine();
        System.out.print("Enter value >");
        val = sysin.nextLine();
        try {
            table1.remove(attr, val);
            table2.remove(attr, val);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }
    }

    /**
     * prints both tables
     */
    private void handlePrintBothTables() {
        System.out.print(table1);
        System.out.println();
        System.out.print(table2);
    }

    /**
     * helper method to read in an int for choice menus
     * @return the choice the user entered
     */
    private int readIntChoice() {
        /* Is invalid for choice menus */
        int choice = -1;
        try {
            choice = Integer.parseInt(sysin.nextLine());
        } catch (NumberFormatException e) {
            /* Already set to -1 */
        }
        return choice;
    }

    /**
     * Get the table number (without checking if it's correct) from the user
     * based on a prompt
     * @param prompt The prompt to give the user for the table number
     * @return The group the user entred
     */
    private int getGroup(String prompt) {
        System.out.printf("%s >", prompt);
        return readIntChoice();
    }

    /**
     * prints out the main menu and returns the choice the user chuses
     * @return the number the user enters
     */
    private int menu() {
        System.out.println();
        System.out.println("Please make choice:");
        System.out.println("\t0) Quit");
        System.out.println("\t1) Intersect");
        System.out.println("\t2) Difference");
        System.out.println("\t3) Union");
        System.out.println("\t4) Select");
        System.out.println("\t5) Remove");
        System.out.println("\t6) Print both tables");
        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
        return readIntChoice();
    }

    /**
     * Helper method to determine if a file is a personal or work contact
     * and call the correct method to read the rest of the file into the table
     * @param in the Scanner for the input file
     * @param table the table to read into
     */
    private void readInput(Scanner in, Table<Contact> table) {
        String line = in.nextLine();
        switch (line.charAt(0)) {
            case 'P':
                readPersonal(in, table);
                break;
            case 'W':
                readWork(in, table);
                break;
            default:
                System.err.println("Error3: bad input file");
                System.exit(3);
        }
    }

    /**
     * Helper method to read personal contacts into a table
     * @param in the Scanner for the input file
     * @param table the table to read into
     */
    private void readPersonal(Scanner in, Table<Contact> table) {
        String line[];
        while (in.hasNextLine()) {
            line = readCSVLine(in);
            if (line.length != 9) {
                System.err.println("Error4: bad input file");
                System.exit(4);
            }
            table.insert(new PersonalContact(line[1], line[0], line[4], line[5], line[6], line[7], line[3], line[2],
                    line[8]));
        }
    }

    /**
     * Helper method to read work contacts into a table
     * @param in the Scanner for the input file
     * @param table the table to read into
     */
    private void readWork(Scanner in, Table<Contact> table) {
        String line[];
        while (in.hasNextLine()) {
            line = readCSVLine(in);
            if (line.length != 12) {
                System.err.println("Error5: bad input file");
                System.exit(5);
            }
            table.insert(new WorkContact(line[1], line[0], line[2], line[5], line[6], line[7], line[8], line[4],
                    line[3], line[9], line[10], line[11]));
        }
    }

    /**
     * Helper method to read a line of CSV from an input file
     * @param in The Scanner to read from
     * @return A string array of the values split by the comma separation
     */
    private String[] readCSVLine(Scanner in) {
        String line[] = in.nextLine().split(",");
        String res[];
        int count = 0;
        int idx = 0;
        for (int i = 0; i < line.length; i++) {
            line[i] = line[i].trim();
            if (!line[i].equals("")) {
                count++;
            }
        }
        res = new String[count];
        for (int i = 0; i < line.length; i++) {
            if (!line[i].equals("")) {
                res[idx++] = line[i];
            }
        }
        return res;
    }

    /**
     * The main method
     * @param args the command-line arguments
     */
    public static void main(String args[]) {
        new ContactList().go();
    }
}
