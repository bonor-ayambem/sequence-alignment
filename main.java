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

        int m = sequence1.length();
        int n = sequence2.length();

        double[][] mGrid = new double[m+1][n+1];
        double[][] xGrid = new double[m+1][n+1];
        double[][] yGrid = new double[m+1][n+1];

        // fill grids with initial border values
        initializeGrids(mGrid, xGrid, yGrid);

        // fill in the rest of the grids
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                double x1 = -10 + -0.5 + mGrid[i][j-1];
                double x2 = -0.5 + xGrid[i][j-1];
                double x3 = -10 + -0.5 + yGrid[i][j-1];

                x[i][j] = Math.max(x1, x2);
                x[i][j] = Math.max(x[i][j], x3);


                double y1 = -10 + -0.5 + mGrid[i-1][j];
                double y2 = -0.5 + yGrid[i-1][j];
                double y3 = -10 + -0.5 + xGrid[i-1][j];

                y[i][j] = Math.max(y1, y2);
                y[i][j] = Math.max(y[i][j], y3);


                int match = sequence1.charAt(i-1) == sequence2.charAt(i-1) ? 1 : -4;

                double m1 = match + mGrid[i-1][j-1];
                double m2 = match + xGrid[i][j];
                double m3 = match + yGrid[i][j];

                x[i][j] = Math.max(x1, x2);
                x[i][j] = Math.max(x[i][j], x3);
            }
        }


        System.out.println("The Grids have been scored as follows: ");
        System.out.println("M GRID");
        printGrid(mGrid);
        System.out.println("X GRID");
        printGrid(xGrid);
        System.out.println("Y GRID");
        printGrid(yGrid);
    }

    public static void initializeGrids(double[][] m, double[][] x, double[][] y){
        m[0][0] = 0;
        x[0][0] = 0;
        y[0][0] = 0;

        // first rows
        for(int i = 1; i < m[0].length; i++){
            m[0][i] = Double.MIN_VALUE;
            y[0][i] = Double.MIN_VALUE;
            x[0][i] = -10.5 - (0.5 * i);
        }

        // first columns
        for(int i = 1; i < m.length; i++){
            m[i][0] = Double.MIN_VALUE;
            x[i][0] = Double.MIN_VALUE;
            y[i][0] = -10.5 - (0.5 * i);
        }
    }

    public static void printGrid(double[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                System.out.print(grid[i][j] + "   \t");
            }
            System.out.println();
        }
    }
}
