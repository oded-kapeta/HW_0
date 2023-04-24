import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Random;
public class HW_0_practice {
    public static Scanner scanner;
    public static void battleshipGame() {
        // TODO: Add your code here (and add more methods).
        String boardSize = new String();
        boardSize = getBoardSize(boardSize);
        int[] sizeOfBoard = new int[2];
        splitBoard(sizeOfBoard,boardSize);
        int row = getLength(sizeOfBoard[0]);
        int col = getLength(sizeOfBoard[1]);
        String battleships = new String();
        battleships  = getBattleships(battleships);
        String[] battleships_Array = battleships.split(" ");
        int[][] battleships_Length = new int[battleships_Array.length][2];
        splitBattleships(battleships_Length,battleships_Array);
        print_all_Ships(battleships_Length);
        //HHJJHJ

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
     * prints the amount and size of each ship
     * @param arr
     */
    public static void print_all_Ships(int arr[][]){
        for (int i = 0 ; i < arr.length;i++){
            System.out.println();
            for (int j = 0 ;j < arr[0].length;j++){
                System.out.print(arr[i][j] + " ");
            }
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
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        battleshipGame();

    }
}

