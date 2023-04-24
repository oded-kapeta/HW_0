package PACKAGE_NAME;

import java.util.Scanner;
import java.util.Random;
public class HW_0_practice{
    public static Scanner scanner;
    public static Random rnd;
    public static void battleshipGame() {
        // TODO: Add your code here (and add more methods).
        // get the board size, split per x and save them as row,col:
        String boardSize = new String();
        boardSize = getBoardSize(boardSize);
        int[] sizeOfBoard = new int[2];
        splitBoard(sizeOfBoard,boardSize);
        int row = sizeOfBoard[0] + 1;
        int col = sizeOfBoard[1] + 1;



        //get the battleships size and create 2d array that save them:
        String battleships = new String();
        battleships  = getBattleships(battleships);
        String[] battleships_TempArray = battleships.split(" ");
        int[][] battleships_2dArray = new int[battleships_TempArray.length][2];
        splitBattleships(battleships_2dArray, battleships_TempArray);
        print2dIntArray(battleships_2dArray);

        //creating board:
        String [][] board = new String[row][col];
        createBoard(board);
        print2dStringArray(board);

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
    public static void print2dStringArray(String arr[][]){
        for (int i = 0 ; i < arr.length;i++){
            System.out.println();
            for (int j = 0 ;j < arr[0].length;j++){
                System.out.print(arr[i][j] + " ");
            }
        }
        System.out.println();
    }

    /**
     * create the board:
     * @param arr
     */
    public static void createBoard(String[][] arr){
        arr[0][0] = " ";
        for(int  i = 1 ; i < arr[0].length ; i ++){
            arr[0][i]  = Integer.toString(i-1);
        }
        for (int j = 1 ; j < arr.length; j++){
            arr[j][0] = Integer.toString(j-1);
        }
        for (int k = 1; k < arr.length; k++){
            for (int z =1 ; z < arr[0].length;z++){
                arr[k][z] = Character.toString('-');
            }
        }

    }
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        battleshipGame();

    }
}

