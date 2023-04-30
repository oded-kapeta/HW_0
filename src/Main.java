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
        // get the board size, split per X and save them as row,col:
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
        int amountOfBattleships = amountOfBattleships(battleships_2dArray);


        //creating userboard:
        int maxDigitNumber = getLength(sizeOfBoard[0] - 1);
        int row = sizeOfBoard[0] + 1;
        int col = sizeOfBoard[1] + maxDigitNumber;
        String [][] userboard = new String[row][col];
        createBoard(userboard, maxDigitNumber);
        System.out.print("your current game board:");
        print2dBoard(userboard,maxDigitNumber);


        //inserting user battleships:
        insertUserBattleships(userboard,battleships_2dArray,maxDigitNumber);


        //creating computer board:
        String [][] computerBoard = new String[row][col];
        createBoard(computerBoard,maxDigitNumber);


        //inserting computer battleships:
        insertComputerBattleships(computerBoard,battleships_2dArray,maxDigitNumber);


        //creating guessing boards:
        String [][] userGuessingBoard = new String[row][col];
        createBoard(userGuessingBoard,maxDigitNumber);
        String [][] computerGuessingBoard = new String[row][col];
        createBoard(computerGuessingBoard,maxDigitNumber);


        //user and computer attacking:
        gameAttack(userboard,computerBoard,userGuessingBoard,computerGuessingBoard,maxDigitNumber,amountOfBattleships,amountOfBattleships);
    }

    /**
     * this function get input from the user of the board
     * @param boardSize variable that get input from the user
     * @return  return to the variable board size the size of the board
     */
    public static String getBoardSize(String boardSize) {
        System.out.println("Enter the board size");
        boardSize = scanner.nextLine();
        return  boardSize;
    }

    /**
     * this function split the input of the board per X
     * @param arr the array that being created from the split method
     * @param boardSize the string that contain the board size
     */
    public static void splitBoard(int[]arr, String boardSize){
        String[] temp = boardSize.split("X");
        arr[0] = Integer.parseInt(temp[0]);
        arr[1] = Integer.parseInt(temp[1]);
    }

    /**
     * gets the sizes and amount of the ships
     * @param battleships strint that contain the amount and sizes of the ship.
     * @return
     */
    public static String getBattleships(String battleships) {
        System.out.println("Enter the battleships sizes");
        battleships = scanner.nextLine();
        return battleships;
    }

    /**
     * splits the sizes and amounts of the ships into an array
     * @param arr is 2D array that contain in every row a mini array in length 2 that inside hi, is amount and size of ship
     * @param arr2  the array that we get from use split ,method per " "
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
     * this function return the amount of digits in number
     * @param x is the number
     * @return the amount of digits in the number
     */
    public static int getLength(int x){
        if(x == 0)   return 1;
        int counter = 0;
        while(x != 0){
            x /= 10;
            counter++;
        }
        return counter;
    }

    /**
     * prints string array
     * @param arr is the array we want to print
     */
    public static void print2dBoard(String arr[][], int maxDigits){
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
        System.out.println();
    }

    /**
     * create the board:
     * first we fill the empty places in the board with " " so that the columns will be straight with the numbers
     * after that we fill te columns with numbers
     * then we fill the rows numbers every time the kast digit until we didnt have any digits to fill
     * then we check with 'if' statement to check if we still have any 'null' places in our column
     * and in the end we fill all the "real game board" with '- '
     * @param arr is the array that we want to fill inside him our board
     * @param maxDigit  the amount of digit we have in our last row number
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
                arr[k][i] = Character.toString('–');
            }
        }
    }

    /**
     * this function unsert battleships to the user board
     * @param board is thr user board
     * @param battleshipsArray is the sizes of te ships and the amount from every size
     * @param maxDigitNum the amount of digit we have in our last row number
     */

    public static void insertUserBattleships(String[][] board , int[][] battleshipsArray , int maxDigitNum){
        String inputLocation = new String();
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < battleshipsArray.length; i++){
            for (int j = 0; j < battleshipsArray[i][0]; j++){
                System.out.println("Enter location and orientation for battleship of size " + battleshipsArray[i][1] );
                inputLocation = scanner.nextLine();
                String [] splitedInput = inputLocation.split(", ");
                int pointY =Integer.parseInt(splitedInput[0]) + 1;
                int pointX =Integer.parseInt(splitedInput[1]) + maxDigitNum;
                int orientation = Integer.parseInt(splitedInput[2]);
                while (!legalToInsert(board,row,col,maxDigitNum,pointX,pointY,orientation,battleshipsArray[i][1])) {
                    if (!checkOrientation(orientation)) {
                        System.out.println("Illegal orientation, try again!");
                        inputLocation = scanner.nextLine();
                        String[] splitedInputOr = inputLocation.split(", ");
                        pointY = Integer.parseInt(splitedInputOr[0]) + 1;
                        pointX = Integer.parseInt(splitedInputOr[1]) + maxDigitNum;
                        orientation = Integer.parseInt(splitedInputOr[2]);
                        continue;
                    }
                    if (!checkPointTiles(pointX, pointY, row, col, maxDigitNum)) {
                        System.out.println("Illegal tile, try again!");
                        inputLocation = scanner.nextLine();
                        String[] splitedInputOr = inputLocation.split(", ");
                        pointY = Integer.parseInt(splitedInputOr[0]) + 1;
                        pointX = Integer.parseInt(splitedInputOr[1]) + maxDigitNum;
                        orientation = Integer.parseInt(splitedInputOr[2]);
                        continue;
                    }
                    if(!checkBattleshipBound(pointX,pointY,orientation,row,col,maxDigitNum,battleshipsArray[i][1])){
                        System.out.println("Battleship exceeds the boundaries of the board, try again!  ");
                        inputLocation = scanner.nextLine();
                        String [] splitedInputBound = inputLocation.split(", ");
                        pointY =Integer.parseInt(splitedInputBound[0]) + 1;
                        pointX =Integer.parseInt(splitedInputBound[1]) + maxDigitNum;
                        orientation = Integer.parseInt(splitedInputBound[2]);
                        continue;
                    }
                    if(!checkOverLap(board,pointX,pointY,orientation,battleshipsArray[i][1],row,col,maxDigitNum)){
                        System.out.println("Battleship overlaps another battleship, try again!");
                        inputLocation = scanner.nextLine();
                        String [] splitedInputOverlap = inputLocation.split(", ");
                        pointY =Integer.parseInt(splitedInputOverlap[0]) + 1;
                        pointX =Integer.parseInt(splitedInputOverlap[1]) + maxDigitNum;
                        orientation = Integer.parseInt(splitedInputOverlap[2]);
                        continue;
                    }
                    if (!checkAdjacent(board,row,col,maxDigitNum,pointX,pointY,orientation,battleshipsArray[i][1])){
                        System.out.println("Adjacent battleship detected, try again!");
                        inputLocation = scanner.nextLine();
                        String [] splitedInputOverlap = inputLocation.split(", ");
                        pointY =Integer.parseInt(splitedInputOverlap[0]) + 1;
                        pointX =Integer.parseInt(splitedInputOverlap[1]) + maxDigitNum;
                        orientation = Integer.parseInt(splitedInputOverlap[2]);
                        continue;
                    }
                }
                insert(board,pointX,pointY,battleshipsArray[i][1],orientation,"#");
                System.out.print("Your current game board:");
                print2dBoard(board,maxDigitNum);
            }
        }
    }

    /**
     * this function check if the orientation user input is 1 or 0
     * @param orientation is the orientation that we get from the user
     * @return true if orientation is valid and false either
     */
    public static boolean checkOrientation(int orientation){
        if(orientation == 1 || orientation == 0 ){
            return true;
        }else{
            return false;
        }
    }

    /**
     * this function check if the current x,y is in the board
     * @param pointX    the column
     * @param pointY    the row
     * @param row       amount of rows that we have in our 2d array
     * @param col       amount of columns that we have in our 2d array
     * @param maxDigitNum   the amount of digit we have in our last row number
     * @return  return true if the point id in the board an false either
     */
    public static boolean checkPointTiles(int pointX,int pointY, int row , int col, int maxDigitNum){
        if(pointY >= row  || pointY < 1  || pointX < maxDigitNum || pointX >= col){
            return false;
        }else{
            return true;
        }
    }

    /**
     * this function check if the size of the battleship dont exceed the bounds of the board
     * @param pointX    the column
     * @param pointY    the row
     * @param orientation   the orientation
     * @param row   amount of rows that we have in our 2d array
     * @param col      amount of columns that we have in our 2d array
     * @param maxDigitNum   the amount of digit we have in our last row number
     * @param sizeBattleship the size of the battleship
     * @return
     */
    public static boolean checkBattleshipBound(int pointX,int pointY,int orientation,int row,int col,int maxDigitNum,int sizeBattleship){
        if(orientation == 0){
            for (int i = 0; i < sizeBattleship;i++){
                if(checkPointTiles(pointX + i,pointY,row,col,maxDigitNum) == false){
                    return false;
                }
            }
        }
        if(orientation == 1){
            for (int i = 0; i < sizeBattleship ;i++){
                if(checkPointTiles(pointX,pointY + i,row,col,maxDigitNum) == false){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this function check if the current battleship not sitting on the same olace with another battleship
     * @param board the board we checking
     * @param pointX    the column
     * @param pointY    the row
     * @param orientation   the orientation
     * @param sizeBattleship    the size of the battleship
     * @return
     */
    public static boolean checkOverLap(String[][] board,int pointX,int pointY, int orientation, int sizeBattleship,int row,int col,int maxDigist){
        if(orientation == 0){
            for (int i = 0; i < sizeBattleship;i++){
                if(!checkPointTiles(pointX + i,pointY,row,col,maxDigist) || !board[pointY][pointX + i ].equals("–")){
                    return false;
                }
            }
        }
        if(orientation == 1){
            for (int i = 0; i < sizeBattleship ;i++){
                if(!checkPointTiles(pointX,pointY + i,row,col,maxDigist) || !board[pointY + i][pointX ].equals("–")){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this function checks if the current battleship adjacent to another one
     * @param board the board we checking.
     * @param row   amount of rows that we have in our 2d array
     * @param col   amount of columns that we have in our 2d array
     * @param maxDigit  the amount of digit we have in our last row number
     * @param pointX    the column
     * @param pointY    the row
     * @param orientation   the orientation
     * @param sizeOfBattleship  the size of the battleship
     * @return
     */
    public  static boolean checkAdjacent(String[][] board,int row,int col,int maxDigit,int pointX,int pointY,int orientation,int sizeOfBattleship){
        if (orientation == 0){
            for (int i = -1 ;i < 2; i+=2){
                if(checkPointTiles(pointX ,pointY + i, row, col, maxDigit)){
                    if (!checkOverLap(board,pointX,pointY + i,orientation,sizeOfBattleship,row,col,maxDigit))    return false;
                }
            }
            for (int i = -1; i <= sizeOfBattleship; i+=sizeOfBattleship+1){
                if(checkPointTiles(pointX+i,pointY,row,col,maxDigit)){
                    if(!board[pointY][pointX + i].equals("–"))    return false;
                    if (checkPointTiles(pointX + i,pointY-1,row,col,maxDigit)){
                        if(!board[pointY - 1][pointX + i].equals("–"))    return false;
                    }
                    if (checkPointTiles(pointX + i,pointY+1,row,col,maxDigit)){
                        if(!board[pointY + 1][pointX + i].equals("–"))    return false;
                    }
                }
            }
            return true;
        }else{
            for (int  i = -1 ; i < 2; i += 2){
                if (checkPointTiles(pointX + i, pointY, row, col, maxDigit)){
                    if(!checkOverLap(board,pointX + i,pointY,orientation,sizeOfBattleship,row,col,maxDigit)) return false;
                }
            }
            for (int i = -1; i <= sizeOfBattleship; i += sizeOfBattleship+1){
                if (checkPointTiles(pointX, pointY + i,row,col,maxDigit)){
                    if (!board[pointY + i][pointX].equals("–"))   return false;
                    if(checkPointTiles(pointX - 1, pointY + i,row,col,maxDigit)){
                        if (!board[pointY + i][pointX - 1].equals("–"))    return false;
                    }
                    if(checkPointTiles(pointX + 1, pointY + i,row,col,maxDigit)){
                        if (!board[pointY +i][pointX + 1].equals("–"))    return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * this function make the condition of inserting battleship be more simple and proffesional
     * this function checks if the current battleship adjacent to another one
     * @param board the board we checking.
     * @param row   amount of rows that we have in our 2d array
     * @param col   amount of columns that we have in our 2d array
     * @param maxDigit  the amount of digit we have in our last row number
     * @param pointX    the column
     * @param pointY    the row
     * @param orientation   the orientation
     * @param sizeOfBattleship  the size of the battleship
     * @return
     */
    public static boolean legalToInsert(String[][] board,int row,int col,int maxDigit,int pointX,int pointY,int orientation,int sizeOfBattleship){
        int counter = 0;
        if (checkOrientation(orientation)) counter++;
        if (checkPointTiles(pointX,pointY,row,col,maxDigit)) counter++;
        if (checkBattleshipBound(pointX,pointY,orientation,row,col,maxDigit,sizeOfBattleship))  counter++;
        if (checkOverLap(board,pointX,pointY,orientation,sizeOfBattleship,row,col,maxDigit)) counter++;
        if (checkAdjacent(board,row,col,maxDigit,pointX,pointY,orientation,sizeOfBattleship))   counter++;
        if (counter < 5)    return false;
        return true;
    }

    /**
     * this function insert some string to 2D array
     * @param board
     * @param pointX    the column
     * @param pointY    the row
     * @param size  the size of the battleship
     * @param orientation   the orientation
     * @param str   what we wnt to insert to the board
     */
    public static void insert(String[][] board , int pointX , int pointY , int size, int orientation, String str){
        if(orientation == 0){
            for (int i = 0; i < size; i++){
                board[pointY][pointX + i] = str;
            }
        }
        if(orientation == 1){
            for (int i = 0; i < size; i++){
                board[pointY + i][pointX] = str;
            }
        }
    }

    /**
     * this function insert the battleships to the computer game board
     * @param board the computer board
     * @param battleshipsArray  the array that contain the amount of each battleshipsize
     * @param maxDigitNum   the amount of digit we have in our last row number
     */
    public static void insertComputerBattleships(String[][] board , int[][] battleshipsArray , int maxDigitNum){
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < battleshipsArray.length; i++) {
            for (int j = 0; j < battleshipsArray[i][0]; j++) {
                int pointY = rnd.nextInt(row - 1) + 1;
                int pointX = rnd.nextInt(col - maxDigitNum) + maxDigitNum;
                int orientation = rnd.nextInt(2);
                while (!legalToInsert(board,row,col,maxDigitNum,pointX,pointY,orientation,battleshipsArray[i][1])){
                    pointY = rnd.nextInt(row - 1) + 1;
                    pointX = rnd.nextInt(col - maxDigitNum) + maxDigitNum;
                    orientation = rnd.nextInt(2);
                }
                insert(board,pointX,pointY,battleshipsArray[i][1],orientation,"#");
            }
        }
    }

    /**
     * this function return the amount of battleships we have
     * @param battleships2D the array that contain the amount of each battleshipsize
     * @return the a,ount of battleships we have in total
     */
    public static int amountOfBattleships(int [][] battleships2D){
        int counter = 0;
        for (int i = 0; i < battleships2D.length;i++){
            counter += battleships2D[i][0];
        }
        return counter;
    }

    /**
     * this function check if we drown a battleship
     * @param board
     * @param pointX    the column
     * @param pointY    the row
     * @param row   amount of rows that we have in our 2d array
     * @param col   amount of columns that we have in our 2d array
     * @param maxDigit  the amount of digit we have in our last row number
     * @return true if we drown a battleship
     */
    public static boolean checkBattleshipDrown(String[][] board, int pointX , int pointY,int row,int col, int maxDigit){
        int counter = 0 , i = 1;
        while (checkPointTiles(pointX + 1,pointY,row,col,maxDigit) && !board[pointY][pointX + 1].equals("–")){
            if (board[pointY][pointX + 1].equals("#"))  return false;
            pointX += 1;
        }
        while (checkPointTiles(pointX - 1,pointY,row,col,maxDigit) && !board[pointY][pointX - 1].equals("–")){
            if (board[pointY][pointX - 1].equals("#"))  return false;
            pointX -= 1;
        }
        while (checkPointTiles(pointX,pointY + 1,row,col,maxDigit) && !board[pointY + 1][pointX].equals("–")){
            if (board[pointY + 1][pointX].equals("#"))  return false;
            pointY += 1;
        }
        while (checkPointTiles(pointX,pointY - 1,row,col,maxDigit) && !board[pointY - 1][pointX].equals("–")){
            if (board[pointY - 1][pointX].equals("#"))  return false;
            pointY -= 1;
        }
        return true;

    }

    /**
     * this function do the user attacking and return true if we drowned the computer ship
     * @param computerBoard
     * @param userGuessingBoard
     * @param maxDigit
     * @param amountBattleships
     * @return true if we drown 1 battleship
     */
    public static boolean userAttack( String[][] computerBoard , String[][] userGuessingBoard, int maxDigit, int amountBattleships){
        String whereToAttack = new String();
        int row = computerBoard.length;
        int col = computerBoard[0].length;
        System.out.print("Your current guessing board:");
        print2dBoard(userGuessingBoard,maxDigit);
        System.out.println("Enter a tile to attack");
        whereToAttack = scanner.nextLine();
        String[] splitedInput = whereToAttack.split(", ");
        int pointY = Integer.parseInt(splitedInput[0]) + 1;
        int pointX = Integer.parseInt(splitedInput[1]) + maxDigit;
        while (!checkPointTiles(pointX,pointY,row,col,maxDigit) || !userGuessingBoard[pointY][pointX].equals("–")){
            if (!checkPointTiles(pointX,pointY,row,col,maxDigit)){
                System.out.println("Illegal tile, try again!");
                whereToAttack = scanner.nextLine();
                String[] splitedInputTile = whereToAttack.split(", ");
                pointY = Integer.parseInt(splitedInputTile[0]) + 1;
                pointX = Integer.parseInt(splitedInputTile[1]) + maxDigit;
                continue;
            }
            if (!userGuessingBoard[pointY][pointX].equals("–")){
                System.out.println("Tile already attacked,try again!");
                whereToAttack = scanner.nextLine();
                String[] splitedInputAlready = whereToAttack.split(", ");
                pointY = Integer.parseInt(splitedInputAlready[0]) + 1;
                pointX = Integer.parseInt(splitedInputAlready[1]) + maxDigit;
            }
        }
        if (computerBoard[pointY][pointX].equals("#")){
            System.out.println("That is a hit!");
            userGuessingBoard[pointY][pointX] = "V";
            computerBoard[pointY][pointX] = "X";
            if(checkBattleshipDrown(computerBoard,pointX,pointY,row,col,maxDigit)){
                amountBattleships -= 1;
                System.out.println("The computer's battleship has been drowned, " + amountBattleships + " more battleships to go!");
                return true;
            }else return false;
        }else {
            System.out.println("That is a miss!");
            userGuessingBoard[pointY][pointX] = "X";
            return false;
        }
    }

    /**
     * this function do the computer attacking and return true if the computer drowned our ship
     * @param userBoard
     * @param maxDigitNum
     * @param amountOfBattleship
     */
    public static boolean computerAttack(String[][] userBoard, String [][] computerGuessingBoard,int maxDigitNum, int amountOfBattleship){
        int row = userBoard.length;
        int col = userBoard[0].length;
        int pointY = rnd.nextInt(row - 1) + 1;
        int pointX = rnd.nextInt(col - maxDigitNum) + maxDigitNum;
        while (!checkPointTiles(pointX,pointY,row,col,maxDigitNum) || !computerGuessingBoard[pointY][pointX].equals("–")){
            pointY = rnd.nextInt(row - 1) + 1;
            pointX = rnd.nextInt(col - maxDigitNum) + maxDigitNum;
        }
        System.out.println("The computer attacked (" + (pointY-1) + ", " + (pointX-maxDigitNum) + ")");
        if(userBoard[pointY][pointX].equals("#")){
            System.out.println("That is a hit!");
            userBoard[pointY][pointX] = "X";
            computerGuessingBoard[pointY][pointX] = "V";
            if (checkBattleshipDrown(userBoard,pointX,pointY,row,col,maxDigitNum)){
                amountOfBattleship -= 1;
                System.out.println("Your battleship has been drowned, you have left " + amountOfBattleship + " more battleships!");
                System.out.print("Your current game board:");
                print2dBoard(userBoard,maxDigitNum);
                return true;
            }else{
                System.out.print("Your current game board:");
                print2dBoard(userBoard,maxDigitNum);
                return false;
            }
        }else{
            System.out.println("That is a miss!");
            computerGuessingBoard[pointY][pointX] = "X";
            System.out.print("Your current game board:");
            print2dBoard(userBoard,maxDigitNum);
            return false;
        }

    }

    /**
     * this functon check if the game is over and the board not has any battleship
     * @param board
     * @param maxDigit
     * @return
     */
    public static boolean checkGameOver(String[][] board, int maxDigit){
        for (int i = 1; i < board.length; i++){
            for (int j = maxDigit; j < board[0].length;j++){
                if (board[i][j].equals("#"))    return false;
            }
        }
        return true;
    }

    /**
     * this function do the game attacking , first to attack is the user and after him is the computer
     * @param userBoard
     * @param computerBoard
     * @param userGuessingBoard
     * @param maxDig
     * @param userBattleships
     * @param compuetrBattleships
     */
    public static void gameAttack(String[][] userBoard, String[][] computerBoard,String[][] userGuessingBoard,String[][] computerGuessingBoard,int maxDig,int userBattleships,int compuetrBattleships){
        while (true){
            if (userAttack(computerBoard,userGuessingBoard,maxDig,userBattleships)) userBattleships--;
            if(checkGameOver(computerBoard,maxDig)){
                System.out.println("You won the game!");
                break;
            }
            if (computerAttack(userBoard,computerGuessingBoard,maxDig,compuetrBattleships))   compuetrBattleships--;
            if (checkGameOver(userBoard,maxDig)){
                System.out.println("You lost ):");
                break;
            }
        }
    }





    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
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



