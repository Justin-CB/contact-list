import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
public class ContactList {
    private Table<Contact> table1;
    private Table<Contact> table2;
    private Scanner sysin;
    private Scanner file1;
    private Scanner file2;

    public ContactList()
    {
        File file;
        sysin = new Scanner(System.in);
        System.out.print("Enter filename for contact list 1> ");
        file = new File(sysin.readLine());
        try {
            file1 = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.err.println("Error1: bad filename");
            System.exit(1);
        }
        System.out.print("Enter filename for contact list 2> ");
        file = new File(sysin.readLine());
        try {
            file2 = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.err.println("Error2: bad filename");
            System.exit(2);
        }
        table1 = new Table<Contact>;
        table2 = new Table<Contact>;
    }

    public void go()
    {
        readInput(file1, table1);
        readInput(file2, table2);
    }

    private void readInput(Scanner in, Table<Contact> table)
    {
        String line = in.readLine();
        switch (line[0]) {
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

    private void readPersonal(Scanner in, Table<Contact> table)
    {
        String line[];
        while (in.hasNextLine()) {
            line = readCSVLine(in);
            if (line.length != 9) {
                System.err.println("Error4: bad input file");
                System.exit(4);
            }
            table.insert(new PersonalContact(line[1],line[0],line[4],line[5],line[6],line[7],line[3],line[2],line[8]));
        }
    }
    private void readWork(Scanner in, Table<Contact> table)
    {
        String line[];
        while (in.hasNextLine()) {
            line = readCSVLine(in);
            if (line.length != 12) {
                System.err.println("Error5: bad input file");
                System.exit(5);
            }
            table.insert(new PersonalContact(line[1],line[0],line[2],line[5],line[6],line[7],line[8],line[4],line[3],line[9],line[10],line[11]));
        }
    }
    private String[] readCSVLine(Scanner in)
    {
        String line[] = in.readLine().spit(",");
        String res[];
        int count = 0;
        int idx = 0;
        for (i = 0; i < result.length; i ++) {
            line[i] = line[i].trim();
            if (!line[i].equals("")) {
                count ++;
            }
        }
        res = new String[count];
        for (i = 0; i < result.length; i ++) {
            if (!line[i].equals("")) {
                res[idx++] = line[i];
            }
        }
        return res;
    }
    public static void main(String args[]) 
    {
        new ContactList().go();
    }
}
