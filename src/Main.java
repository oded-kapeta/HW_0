package PACKAGE_NAME;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static Random rnd;

    public static void battleshipGame() {
        // TODO: Add your code here (and add more methods).
        // get the board size, split per x and save them as row,col:
        String boardSize = new String();
        boardSize = getBoardSize(boardSize);
        int[] sizeOfBoard = new int[2];
        splitBoard(sizeOfBoard,boardSize);



        //get the battleships size and create 2d array that save them:
        String battleships = new String();
        battleships  = getBattleships(battleships);
        String[] battleships_TempArray = battleships.split(" ");
        int[][] battleships_2dArray = new int[battleships_TempArray.length][2];
        splitBattleships(battleships_2dArray, battleships_TempArray);
        //print2dIntArray(battleships_2dArray);

        //creating board:
        int maxDigitNumber = getLength(sizeOfBoard[0] - 1);
        int row = sizeOfBoard[0] + 1;
        int col = sizeOfBoard[1] + maxDigitNumber;
        String [][] userboard = new String[row][col];
        createBoard(userboard, maxDigitNumber);
        System.out.println("Your current game board:");
        print2dStringArray(userboard,maxDigitNumber);

    }

    /**
     * this function get input from the user of the board
     * @param boardSize
     * @return
     */
    public static String getBoardSize(String boardSize) {
        System.out.println("Enter the board size");
        boardSize = scanner.nextLine();
        return  boardSize;
    }

    /**
     * this function split the input of the board per X
     * @param arr
     * @param boardSize
     */
    public static void splitBoard(int[]arr, String boardSize){
        String[] temp = boardSize.split("X");
        arr[0] = Integer.parseInt(temp[0]);
        arr[1] = Integer.parseInt(temp[1]);
    }

    /**
     * gets the sizes and amount of the ships
     * @param battleships
     * @return
     */
    public static String getBattleships(String battleships) {
        System.out.println("Enter the battleships size :");
        battleships = scanner.nextLine();
        return battleships;
    }

    /**
     * splits the sizes and amounts of the ships into an array
     * @param arr
     * @param arr2
     */
    public static void splitBattleships(int arr[][],String arr2[]){
        for (int i = 0 ; i < arr2.length; i++){
            String [] tempArray = arr2[i].split("X");
            int row = Integer.parseInt(tempArray[0]);
            int col = Integer.parseInt(tempArray[1]);
            arr[i][0] = row;
            arr[i][1] = col;
        }
    }

    /**
     * this function return the amount of digits in numbet
     * @param x
     * @return
     */
    public static int getLength(int x){
        if(x==0)    return 1;
        int counter = 0;
        while(x != 0){
            x /= 10;
            counter++;
        }
        return counter;
    }

    /**
     * prints int array
     * @param arr
     */
    public static void print2dIntArray(int arr[][]){
        for (int i = 0 ; i < arr.length;i++){
            System.out.println();
            for (int j = 0 ;j < arr[0].length;j++){
                System.out.print(arr[i][j] + " ");
            }
        }
        System.out.println();
    }

    /**
     * prints string array:
     * @param arr
     */
    public static void print2dStringArray(String arr[][],int maxDigits){
        for (int i = 0 ; i < arr.length;i++){
            System.out.println();
            for(int k = 0; k < maxDigits - 1 ; k++ ){
                System.out.print(arr[i][k]);
            }
            for (int j = maxDigits - 1 ;j < arr[0].length;j++){
                System.out.print(arr[i][j] + " ");
            }
        }
        System.out.println();
    }

    /**
     * create the board:
     * first we fill the empty places in the board with " " so that the columns will be straight with the numbers
     * after that we fill te columns with numbers
     * then we fill the rows numbers every time the kast digit until we didnt have any digits to fill
     * then we check with 'if' statement to check if we still have any 'null' places in our column
     * and in the end we fill all the "real game board" with '- '
     * @param arr
     * @param maxDigit
     */
    public static void createBoard(String[][] arr , int maxDigit){
        for (int i = 0; i < maxDigit; i++)     arr[0][i] = " ";
        for (int j = maxDigit; j < arr[0].length ; j ++)   arr[0][j] = Integer.toString(j - maxDigit);
        for(int k = 1; k < arr.length;k++) {    /** fill the rows */
            int temp_DigitsAmount = getLength(k-1);
            int z = maxDigit - 1;
            int tempNumber = k-1;
            while (temp_DigitsAmount != 0) {
                arr[k][z] = Integer.toString(tempNumber % 10);
                tempNumber = (tempNumber - (tempNumber % 10)) / 10;
                if (z > 0) z--;
                temp_DigitsAmount--;
            }
            if (getLength(k-1) != maxDigit) {
                for (int i = z; i >= 0; i--) {
                    arr[k][i] = " ";
                }
            }
        }
        for(int k = 1 ; k < arr.length; k++){   /** fill the "real" board with '-' */
            for (int i = maxDigit; i < arr[0].length; i++){
                arr[k][i] = Character.toString('-');
            }
        }
    }




    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
        battleshipGame();
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Total of " + numberOfGames + " games.");

        for (int i = 1; i <= numberOfGames; i++) {
            scanner.nextLine();
            int seed = scanner.nextInt();
            rnd = new Random(seed);
            scanner.nextLine();
            System.out.println("Game number " + i + " starts.");
            battleshipGame();
            System.out.println("Game number " + i + " is over.");
            System.out.println("------------------------------------------------------------");
        }
        System.out.println("All games are over.");
    }
}



