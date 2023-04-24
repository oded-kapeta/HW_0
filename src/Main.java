import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static Random rnd;

    public static void battleshipGame() {
        // TODO: Add your code here (and add more methods).
        String boardSize = new String();
        boardSize = getBoardSize(boardSize);
        int[] sizeOfBoard = new int[2];
        splitBoard(sizeOfBoard,boardSize);
        String battleships = new String();
        battleships  = getBattleships(battleships);
        String[] battleships_Array = battleships.split(" ");
        int[][] battleships_Length = new int[battleships_Array.length][2];
        splitBattleship(battleships_Length,battleships_Array);
    }

    public static String getBoardSize(String boardSize) {
        System.out.println("Enter the board size");
        boardSize = scanner.nextLine();
        return  boardSize;
    }
    public static void splitBoard(int[]arr, String boardSize){
        String[] temp = boardSize.split("X");
        arr[0] = Integer.parseInt(temp[0]);
        arr[1] = Integer.parseInt(temp[1]);
    }

    public static String getBattleships(String battleships) {
        System.out.println("Enter the battleships size :");
        battleships = scanner.nextLine();
        return battleships;
    }
    public static void splitBattleship(int arr[][],String arr2[]){
        for (int i = 0 ; i < arr2.length; i++){
            String [] tempArray = arr2[i].split("X");
            int row = Integer.parseInt(tempArray[0]);
            int col = Integer.parseInt(tempArray[1]);
            arr[i][0] = row;
            arr[i][1] = col;
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



