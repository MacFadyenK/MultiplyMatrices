import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class App {
    //reads matrix from file
    //parameter fileName: name of file location
    //returns 2D matrix array
    public static int[][] readMatrix(String fileName) throws IOException{
        FileReader fread = new FileReader(fileName);
        BufferedReader r = new BufferedReader(fread);
        int rows = 0;
        int cols = 0;
        String line;

        //gets the number of rows and columns of the matrix
        while((line = r.readLine()) != null){
            rows++;
            cols = line.split(" ").length;
        }
        r.close();

        fread = new FileReader(fileName);
        r = new BufferedReader(fread);
        int [][] mat = new int[rows][cols];

        //changes text file format into a 2D array
        for(int i = 0; i<rows; i++){
            line = r.readLine();
            String[] elem = line.split(" ");
            for(int j = 0; j<cols; j++){
                mat[i][j] = Integer.parseInt(elem[j]);
            }
        }
        r.close();
        return mat; 
    }

    //creates a square matrix of random numbers of specified dimensions
    //parameter rows: the number of rows and subsequent columns in the matrix
    //returns square matrix array with random values between 0 and 49
    public static int[][] randSquareMatrix(int rows){
        Random rand = new Random();
        int[][] mat = new int[rows][rows];

        //creates the matrix
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<rows; j++){
                mat[i][j] = rand.nextInt(50);
            }
        }
        return mat;
    }

    //writes a matrix to a text file
    //parameter matrix: the matrix to be written into the file
    //parameter fileName: the path to the file to be written to
    public static void writeMatrix(int[][] matrix, String fileName) throws IOException{
        FileWriter fwrite = new FileWriter(fileName);
        BufferedWriter w = new BufferedWriter(fwrite);

        //the writing of the file
        for(int i=0; i<matrix.length; i++){
            for(int j = 0; j<matrix[0].length; j++){
                w.write(matrix[i][j] + " ");
            }
            if(i != matrix.length-1){
                w.newLine();
            }
        }
        w.close();
    }

    //multiplies two matrices together
    //parameters mat1 and mat2: matrices to be multiplied together in that order
    //returns the multiplied matrix
    public static int[][] multiplyMatrix(int[][] mat1, int[][] mat2){
        int[][] multMat = new int[mat1.length][mat2[0].length];

        //multiplication process
        for(int i = 0; i<mat1.length; i++){
            for(int j = 0; j<mat2[i].length; j++){
                for(int k = 0; k<mat2.length; k++)
                    multMat[i][j] += mat1[i][k]*mat2[k][j];
            }
        }
        return multMat;
    }

    //checks if a number is an integer
    //parameter s: the string in question of being an integer
    //returns true if it is an integer and false if it is not
    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static void main(String[] args) throws Exception {
        int[][] mat1;
        int[][] mat2;
        int[][] mat3;
        
        //looks for two file names and reads then multiplies them
        try{
            mat1 = readMatrix(args[0]);
            System.out.println("matrix1 read");

            mat2 = readMatrix(args[1]);
            System.out.println("matrix2 read");

            mat3 = multiplyMatrix(mat1, mat2);
            System.out.println("matrices multiplied");

            writeMatrix(mat3, "matrix3.txt");
            System.out.println("Result of multiplication of givin files in matrix3.txt");

        }catch (Exception e) {
            //the case where an integer is input
            //makes and writes two square matrices of the size of the integer argument
            //multiples the matrices and writes the result
            if(isInteger(args[0])){
                int rows = Integer.parseInt(args[0]);

                mat1 = randSquareMatrix(rows);
                mat2 = randSquareMatrix(rows);

                writeMatrix(mat1, "matrix1.txt");
                System.out.println("random square matrix in matrix1.txt");

                writeMatrix(mat2, "matrix2.txt");
                System.out.println("random square matrix in matrix2.txt");

                mat3 = multiplyMatrix(mat1, mat2);
                System.out.println("matrices multiplied");

                writeMatrix(mat3, "matrix3.txt");
                System.out.println("Result of multiplication in matrix3.txt");
            }
            //when there are not two file names or an integer input
            else{
                System.out.println("Uh Oh! Something went wrong :/");
                System.out.println("Please make sure all arguments are valid inputs.");
            }
        }
    }
}
