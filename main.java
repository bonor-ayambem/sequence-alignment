import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String sequence1 = "";
        String sequence2 = "";

        System.out.println("Enter File Name: ");
        String filename = sc.nextLine();
        File inputFile = new File(filename);

        try{
            Scanner reader = new Scanner(inputFile);
            reader.nextLine();
            sequence1 = reader.nextLine().trim();
            reader.nextLine();
            sequence2 = reader.nextLine().trim();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        System.out.println("Input sequences are as follows: \n" + sequence1 + "\n" + sequence2);

        
    }
}
