import java.util.ArrayList;
import java.util.Scanner;

public class Maze{

    private int[][] maze;

    //=========================================================
    //  Following integers are used to help transform the maze
    //  0 - walls        1 - open         2 - Not a Solution
    //  3 - Attempted(Recursion method only)    4 - Solution
    //=========================================================
    private int wall = 0;
    private int open = 1;
    private int noSolution = 2;
    private int attempted = 3;
    private int path = 4;

    private Character player = new Character();                  //CHARACTER INSTANCE
    private boolean solved = false;                              //TESTS IF MAZE IS SOLVED
    private ArrayList<Integer> stack = new ArrayList<Integer>(); //USED FOR BACKTRACKING WHEN TRAPPED

    public Maze(int[][] pathArray){
        maze = pathArray;
    }

    public void run(){
        while (solved == false){
            System.out.println("Take a Step: T  |  Show Path: S  |  Quit: Q" + "\n" + player.getY() + " " +player.getX());
            displayMaze();
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            input.toLowerCase();
            if(input.equals("t")){
                System.out.println("Take a Step: T  |  Show Path: S  |  Quit: Q" + "\n");
                takeStep();
                displayMaze();
            } else if (input.equals("s")){
                System.out.println("Take a Step: T  |  Show Path: S  |  Quit: Q" + "\n");
                findExit(player.getY(),player.getX());
                displayMaze();
            }
            else if(input.equals("q")){
                System.exit(0);
            }
            else {
                System.out.println("Please choose a valid input");
            }
        }
        if(solved == true){
            System.out.println("\n EXIT FOUND!! :D");
            System.exit(0);
        }
    }

    public void displayMaze(){

        for(int row = 0; row<maze.length; row++){
            for(int column = 0; column<maze[row].length; column++){
                if(maze[row][column]== wall){
                    System.out.print("#");
                }
                else if(maze[row][column] == path){
                    if( player.checkLocation(row,column)) {
                        System.out.print(player.getPlayer());
                    }
                    else {
                        System.out.print("~");
                    }
                }
                else if(maze[row][column] == open) {
                    if( player.checkLocation(row,column)) {
                        System.out.print(player.getPlayer());
                    }
                    else {System.out.print(" ");}
                }
                else if(maze[row][column] == attempted || maze[row][column] == noSolution){
                    if( player.checkLocation(row,column)) {
                        System.out.print(player.getPlayer());
                    }
                    else {System.out.print(" ");}
                }
            }
            System.out.print("\n");
        }
    }

    /**=======================================================
     * Following code handles movement one step at a time
     =========================================================*/
    //========================================================
    //  takes one step each time when it is called and
    //  displays the maze again. Use the algorithm that checks
    //  right turn first. And keeps checking counter-clockwise
    //  until finds a path.
    //========================================================
    public void takeStep(){
        int row = player.getY();
        int column = player.getX();
        if(valid(row, column)){
            maze[row][column] = path;
            stack.add(row);
            stack.add(column);
            if (row == maze.length-1 || column == maze[0].length-1 || (row == 0 && column != 2) || column == 0){
                solved = true;}
            else{
                if (checkRight() == true){player.stepRight();}
                else if (checkFront() == true) {player.stepForward();}
                else if (checkLeft() == true) {player.stepLeft(); }
                else { deadEnd(); player.stepBack(stack.get(stack.size()-2), stack.get(stack.size()-1));}}}
        else{
            if (checkRight() == true){player.stepRight();}
            else if (checkFront() == true) {player.stepForward();}
            else if (checkLeft() == true) {player.stepLeft(); }
            else {deadEnd(); player.stepBack(stack.get(stack.size()-2), stack.get(stack.size()-1));}}
    }

    //==========================================================
    //  If character is at dead end, this method sets the
    //  location to noSolution and removes it from the stack
    //==========================================================
    public void deadEnd(){
        maze[stack.get(stack.size()-2)][stack.get(stack.size()-1)] = noSolution;
        stack.remove(stack.size() - 1); //REMOVES X
        stack.remove(stack.size() - 1); //REMOVES Y
    }

    //==========================================================
    //  Following methods check sides based off of characters
    //  orientation and return weather the side is open
    //  and not attempted.
    //==========================================================

    public boolean checkRight(){
        switch (player.getOrientation()){
            case 1:
                if (maze[player.getY()][player.getX()+1] == open){ return true; }
                break;
            case 2:
                if (maze[player.getY()+1][player.getX()] == open){ return true; }
                break;
            case 3:
                if (maze[player.getY()][player.getX()-1] == open){ return true; }
                break;
            case 4:
                if (maze[player.getY()-1][player.getX()] == open){ return true; }
                break;
        }
        return false;
    }

    public boolean checkFront(){
        switch (player.getOrientation()){
            case 1:
                if(maze[player.getY()-1][player.getX()] == open){return true;}
                break;
            case 2:
                if(maze[player.getY()][player.getX()+1] == open){return true;}
                break;
            case 3:
                if(maze[player.getY()+1][player.getX()] == open){return true;}
                break;
            case 4:
                if(maze[player.getY()][player.getX()-1] == open){return true;}
                break;
        }
        return false;
    }

    public boolean checkLeft(){
        switch (player.getOrientation()){
            case 1:
                if(maze[player.getY()][player.getX()-1] == open){return true;}
                break;
            case 2:
                if(maze[player.getY()-1][player.getX()] == open){return true;}
                break;
            case 3:
                if(maze[player.getY()][player.getX()+1] == open){return true;}
                break;
            case 4:
                if(maze[player.getY()+1][player.getX()] == open){return true;}
                break;
        }
        return false;
    }


    /**=======================================================
     * Following code solves the entire maze before display
     =========================================================*/
    //===========================================================
    //  Following methods recursively move through the maze.
    //  Replaces Open maze characters with 'attempted'
    //  characters then once a solution is found marks path
    //===========================================================

    public boolean findExit(int row, int column) {
        if (valid (row, column)) {
            maze[row][column] = attempted;  //MARKS PATH
            player.setLocation(row,column);
            if (row == maze.length-1 || column == maze[0].length-1 || (row == 0 && column != 2) || column == 0)
                solved = true;  //MAZE IS SOLVED
            else {
                switch (player.getOrientation()){
                    case 1: //NORTH
                        solved = findExit(row, column +1); player.setOrientation(2);
                        if(!solved) {solved = findExit(row - 1, column);player.setOrientation(1);}
                        if(!solved) {solved = findExit(row, column-1);player.setOrientation(4);}
                        if(!solved) {solved = findExit(row + 1, column);}
                        break;
                    case 2: //EAST
                        solved = findExit(row+1, column); player.setOrientation(3);
                        if(!solved) {solved = findExit(row, column+1);player.setOrientation(2);}
                        if(!solved) {solved = findExit(row-1, column); player.setOrientation(1);}
                        if(!solved) {solved = findExit(row , column-1);}
                        break;
                    case 3: //SOUTH
                        solved = findExit(row, column-1); player.setOrientation(4);
                        if(!solved) {solved = findExit(row+1, column);player.setOrientation(3);}
                        if(!solved) {solved = findExit(row, column+1);player.setOrientation(2);}
                        if(!solved) {solved = findExit(row-1 , column);}
                        break;
                    case 4: //WEST
                        solved = findExit(row-1, column); player.setOrientation(1);
                        if(!solved) {solved = findExit(row, column-1);player.setOrientation(4);}
                        if(!solved) {solved = findExit(row+1, column); player.setOrientation(3);}
                        if(!solved) {solved = findExit(row , column+1);}
                        break;
                }
            }
            if (solved)  // MARKS THE FINAL PATH DURING RECURSION
                maze[row][column] = path;
        }
        return solved;
    }
    //=====================================================
    //  Validates if the Characters location is inside the
    //  bounds of the Maze and if it is open
    //=====================================================
    private boolean valid (int row, int column) {
        boolean result = false;
        if (row >= 0 && row < maze.length &&
                column >= 0 && column < maze[0].length)
            if (maze[row][column] == open)
                result = true;
        return result;

    }

    public int getWall(){
        return wall;
    }

    public int getOpen(){
        return open;
    }

    public int getNoSolution(){
        return noSolution;
    }

    public int getAttempted(){
        return attempted;
    }

    public int getPath(){
        return path;
    }

    public boolean getSolved(){
        return solved;
    }

    public int getPlayerOrientation(){
        return player.getOrientation();
    }

    public int getPlayerX(){
        return player.getX();
    }
    public int getPlayerY(){
        return player.getY();
    }
}