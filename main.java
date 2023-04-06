import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String name1 = "", sequence1 = "";
        String name2 = "", sequence2 = "";

        System.out.println("Enter File Name: ");
        String filename = sc.nextLine();
        File inputFile = new File(filename);

        try{
            Scanner reader = new Scanner(inputFile);
            name1 = reader.nextLine().trim();
            sequence1 = reader.nextLine().trim();
            name2 = reader.nextLine().trim();
            sequence2 = reader.nextLine().trim();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        System.out.println("Input sequences are as follows: \n" + sequence1 + "\n" + sequence2);

        int m = sequence2.length();
        int n = sequence1.length();

        double[][] mGrid = new double[m+1][n+1];
        double[][] xGrid = new double[m+1][n+1];
        double[][] yGrid = new double[m+1][n+1];

        // fill grids with initial border values
        initializeGrids(mGrid, xGrid, yGrid);

        // fill in the rest of the grids
        for(int i = 1; i < m+1; i++){
            for(int j = 1; j < n+1; j++){
                double x1 = -10 - 0.5 + mGrid[i][j-1];
                double x2 = -0.5 + xGrid[i][j-1];
                double x3 = -10 - 0.5 + yGrid[i][j-1];

                xGrid[i][j] = Math.max(x1, x2);
                xGrid[i][j] = Math.max(xGrid[i][j], x3);


                double y1 = -10 + -0.5 + mGrid[i-1][j];
                double y2 = -0.5 + yGrid[i-1][j];
                double y3 = -10 + -0.5 + xGrid[i-1][j];

                yGrid[i][j] = Math.max(y1, y2);
                yGrid[i][j] = Math.max(yGrid[i][j], y3);


                int match = sequence2.charAt(i-1) == sequence1.charAt(j-1) ? 1 : -4;

                double m1 = match + mGrid[i-1][j-1];
                double m2 = match + xGrid[i][j];
                double m3 = match + yGrid[i][j];

                mGrid[i][j] = Math.max(m1, m2);
                mGrid[i][j] = Math.max(mGrid[i][j], m3);
            }
        }

        System.out.println("The Grids have been scored as follows: ");
        System.out.println("M GRID");
        printGrid(mGrid);
        System.out.println("X GRID");
        printGrid(xGrid);
        System.out.println("Y GRID");
        printGrid(yGrid);

        // backtrack to find algnment
        System.out.println("\nBacktracking Graphs to Find Alignment...");
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        int i = m, j = n;
        while(i >= 0 || j >= 0){
            // System.out.println("here");
            int match = i > 0 && j > 0 && sequence1.charAt(j-1) == sequence2.charAt(i-1) ? 1 : -4;

            double mTrans = i > 0 && j > 0 ? mGrid[i-1][j-1] + match : Double.MAX_VALUE;
            double xTrans = xGrid[i][j];
            double yTrans = yGrid[i][j];

            while((i > 0 || j > 0) && mTrans < xTrans || mTrans < yTrans){ // while we are not in the M grid / open a gap
            // System.out.println(i + " " + j + " " + mTrans + " " + xTrans + " " + yTrans);
                while(j > 0 && xTrans > mTrans && xTrans > yTrans){
                    // System.out.println("in x graph");
                    sb1.insert(0, sequence1.charAt(j-1));
                    sb2.insert(0, '-');

                    mTrans = mGrid[i][j-1] - 10.5 - 0.5;
                    xTrans = xGrid[i][j-1] - 0.5;
                    yTrans = yGrid[i][j-1] - 10.5 - 0.5;

                    j--;
                }
                while(i > 0 && yTrans > mTrans && yTrans > xTrans){
                    // System.out.println("in Y graph");
                    sb1.insert(0, '-');
                    sb2.insert(0, sequence2.charAt(i-1));

                    mTrans = mGrid[i-1][j] - 10.5 - 0.5;
                    yTrans = yGrid[i-1][j] - 0.5;
                    xTrans = xGrid[i-1][j] - 10.5 - 0.5;

                    i--;
                }
            }

            
            if(j > 0) sb1.insert(0, sequence1.charAt(j-1));
            // else sb1.insert(0, sequence1.charAt(j-1));
            if(i > 0)sb2.insert(0, sequence2.charAt(i-1));

            i--; j--;        
        }

        String ansSeq1 = sb1.toString();
        String ansSeq2 = sb2.toString();

        System.out.println("The final alignments are as follows: ");
        System.out.println(name1 + "\n" + ansSeq1);
        System.out.println(name2 + "\n" + ansSeq2);
    }

    public static void initializeGrids(double[][] m, double[][] x, double[][] y){
        m[0][0] = 0;
        x[0][0] = 0;
        y[0][0] = 0;

        // first rows
        for(int i = 1; i < m[0].length; i++){
            m[0][i] = -Double.MAX_VALUE;
            y[0][i] = -Double.MAX_VALUE;
            x[0][i] = -10.5 - (0.5 * (i-1));
        }

        // first columns
        for(int i = 1; i < m.length; i++){
            m[i][0] = -Double.MAX_VALUE;
            x[i][0] = -Double.MAX_VALUE;
            y[i][0] = -10.5 - (0.5 * (i-1));
        }
    }

    public static void printGrid(double[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == -Double.MAX_VALUE){
                    System.out.print("-INF" + "\t");
                }
                else System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
