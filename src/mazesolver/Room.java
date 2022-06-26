package mazesolver;

import java.util.Random;

public class Room {
    private  int height;

    private  int width;

    private String[][] room;

    //private  Random RANDOM;

    private int numberOfHorizontalWalls;
    private int numberOfVerticalWalls;

    private MovingEntity movingEntity;

    static Random RANDOM = new Random();

    /**
     * @param height
     * @param width
     *
     */
    //for manually created rooms (not from text file)
    public Room(int height, int width, MovingEntity movingEntity, int numberOfHorizontalWalls, int numberOfVerticalWalls) {
        super();
        //this.RANDOM = random;
        this.height = height;
        this.width = width;
        this.movingEntity = movingEntity;
        this.room = new String[height][width];
        int lastRowIndex = height - 1;
        int lastColumnIndex = width - 1;

        int isPassableCounter = 0;
        do {
            initLevelWithSurroundingWalls(height, width, lastRowIndex, lastColumnIndex);
            addRandomWalls(numberOfHorizontalWalls, numberOfVerticalWalls);
            isPassableCounter++;
        }while(!isPassable());
        draw();
        isPassable(true);

        System.out.println("The No " + isPassableCounter + " board is passable");

    }

    public Room(int height, int width, String[][] arr, MovingEntity cleaner){
        this.height = height;
        this.width = width;
        //RANDOM = random;
        this.movingEntity = cleaner;
        this.room =  new String[height][width];

        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                room[row][column] = arr[row][column];

            }
        }
    }



    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void initLevelWithSurroundingWalls(int height, int width, int lastRowIndex, int lastColumnIndex) {
        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                if(row == 0 ||  row == lastRowIndex || column == 0 || column == lastColumnIndex) {//walls
                    room[row][column] = "X"; //walls
                }else{
                    room[row][column] = "."; //dirt
                }
            }
        }
    }


    public Room(int height, int width, String[][] arr, Random random, MovingEntity cleaner){
        this.height = height;
        this.width = width;
        RANDOM = random;
        this.movingEntity = cleaner;
        this.room =  new String[height][width];

        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                room[row][column] = arr[row][column];

            }
        }
    }

    public void addRandomWalls(int numberOfHorizontalWalls, int numberOfVerticalWalls) {
        //TODO fal ne kerüljön a játékosokra
        for(int i = 0; i < numberOfHorizontalWalls; i++) {
            addHorizontalWall();
        }
        for(int i = 0; i < numberOfVerticalWalls; i++) {
            addVerticalWall();
        }
        /*
        for(int i = 0; i < numberOfDiagonalWalls; i++) {
            addDiagonalWall();
        }
        */

    }

    public void draw(){
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {//room[i].length

                Coordinates coordinatesToDraw = new Coordinates(row, column);
                if (coordinatesToDraw.isSame(movingEntity.getCoordinates())) {
                    System.out.print(movingEntity.getMark());
                }else{
                    System.out.print(getCell(coordinatesToDraw));
                }
            }
            System.out.println();
        }
    }


    private void addHorizontalWall() {

        int wallWidth = RANDOM.nextInt(width-3);
        int wallRow = RANDOM.nextInt(height-2)+1;
        int wallColumn = RANDOM.nextInt(width-2-wallWidth);
        for(int i = 0; i < wallWidth; i++ ) {
            room[wallRow][wallColumn + i] = "X";
        }
    }

    private void addVerticalWall() {//diagonal wall

        int wallHeight = RANDOM.nextInt(height - 3) + 1;
        int wallRow = RANDOM.nextInt(height - 2 - wallHeight);
        int wallColumn = RANDOM.nextInt(width - 2) + 1;
        for (int i = 0; i < wallHeight; i++) {
            room[wallRow + i][wallColumn] = "X";
        }

    }

    public void draw2DArray(){

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                System.out.print(room[row][column] );
            }
            System.out.println();
        }

    }

    public boolean checkRoom(String toCheck){

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if(room[row][column].equals(toCheck)){
                    return true;
                }
            }
        }
        return false;
    }


    public String getCell(Coordinates coordinates) {

        return room[coordinates.getRow()][coordinates.getColumn()];
    }

    public boolean isMark(Coordinates coordinates, String mark) {//isDot
        return mark.equals(room[coordinates.getRow()][coordinates.getColumn()]);
    }

    public String setCleaned(Coordinates coordinates) {

        return room[coordinates.getRow()][coordinates.getColumn()] = " ";
    }

    public String whatIsAround(Coordinates coordinates) {

        return room[coordinates.getRow()][coordinates.getColumn()] = " ";
    }

    public String setCleaned2(Coordinates coordinates, String text) {

        return room[coordinates.getRow()][coordinates.getColumn()] = "\u001b[1;31m" + text + "\u001b[0m";//red
        //return room[coordinates.getRow()][coordinates.getColumn()] = text ;
    }



    public String setCleaned3(Coordinates coordinates, String text) {

        return room[coordinates.getRow()][coordinates.getColumn()] = "\u001b[1;32m" + text + "\u001b[0m";//green
    }

    //2D array lemásolása
    private String[][] copy(String[][] level){
        String[][] copy = new String[height][width];
        for (int row = 0; row < height; row++) {
            System.arraycopy(level[row], 0, copy[row], 0, width);
        }
        return copy;
    }



    private boolean spreadAsterisks(String[][] levelCopy) {
        boolean changed = false;

        for (int row = 0; row < height; row++) {

            for (int column = 0; column < width; column++) {

                // a körülötte lévő helyekre *-ot rak (amíg tud)
                if ("*".equals(levelCopy[row][column])) {// * van valahol
                    if (".".equals(levelCopy[row - 1][column])) {
                        levelCopy[row - 1][column] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row + 1][column])) {
                        levelCopy[row + 1][column] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row][column - 1])) {
                        levelCopy[row][column - 1] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row][column + 1])) {// levelCopy[1][4]:" "
                        levelCopy[row][column + 1] = "*";
                        changed = true;
                    }

                }
            }
        }
        return changed;
    }

    private boolean spreadAsterisksWithCheck(String[][] levelCopy) {
        boolean[][] mask = new boolean [height][width]; //alapértelmezetten csupa false-val van tele
        //végigmegyek a levelCopy-n, és ha találok valahol csillagot, ott a mask-ot true-ra állítom:
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if ("*".equals(levelCopy[row][column])) {
                    mask[row][column] = true;

                }
            }
        }
        boolean changed = false;
        for (int row = 0; row < height; row++) {

            for (int column = 0; column < width; column++) {

                // a körülötte lévő helyekre *-ot rak (amíg tud)
                if ("*".equals(levelCopy[row][column]) && mask[row][column]) {// * van valahol ÉS a mask az true
                    if (".".equals(levelCopy[row - 1][column]) || " ".equals(levelCopy[row - 1][column])) {//" "
                        levelCopy[row - 1][column] = "*";
                        changed = true;//ez így mindig csak 1-et lép
                    }
                    if (".".equals(levelCopy[row + 1][column]) || " ".equals(levelCopy[row + 1][column])) {
                        levelCopy[row + 1][column] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row][column - 1])|| " ".equals(levelCopy[row][column - 1])) {
                        levelCopy[row][column - 1] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row][column + 1])|| " ".equals(levelCopy[row][column + 1])) {// levelCopy[1][4]:" "
                        levelCopy[row][column + 1] = "*";
                        changed = true;
                    }

                }
            }
        }
        return changed;
    }

    public Direction getShortestPath(Direction defaultDirection, Coordinates from, Coordinates to) {
        // pálya lemásolása:
        String[][] levelCopy = copy(room);// 2D array lemásolása
        // első csillag lehelyezése a célpontra
        levelCopy[to.getRow()][to.getColumn()] = "*";
        while(spreadAsterisksWithCheck(levelCopy)) {
            if("*".equals(levelCopy[from.getRow() -1][from.getColumn()])) {//ha a visszafelé terjedő csillagok közül az első felülről
                //jelent meg akkor felfele megyünk*
                return Direction.UP;
            }
            if("*".equals(levelCopy[from.getRow() +1][from.getColumn()])) {//ha a visszafelé terjedő csillagok közül az első alulról
                //jelent meg akkor lefele megyünk*
                return Direction.DOWN;
            }
            if("*".equals(levelCopy[from.getRow()][from.getColumn() -1])) {//ha a visszafelé terjedő csillagok közül az első balról
                //jelent meg akkor balra megyünk*
                return Direction.LEFT;
            }
            if("*".equals(levelCopy[from.getRow()][from.getColumn()+1])) {//ha a visszafelé terjedő csillagok közül az első jobbra
                //jelent meg akkor jobbra megyünk*
                return Direction.RIGHT;
            }
        }
        return defaultDirection;

    }

    public boolean isPassable() {
        //return isPassable(false);
        return isPassable(true);

    }

    public boolean isPassable(boolean drawAsterisks) {
        // pálya lemásolása
        String[][] levelCopy = copy(room);// 2D array lemásolása
        // Az első . megkeresése és *-al történő helyettesítése
        outer: for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (levelCopy[row][column].equals(".")) {
                    levelCopy[row][column] = "*";

                    break outer;
                    // break;
                }
            }
        }

        // stars spreading
        // nem tud alul/felülindexelődni mert szélsőséges esetben 1,1-ről vagy
        // max-1,max-1 ről indul

        while(spreadAsterisks(levelCopy)) {
            if(drawAsterisks) {
                draw2DArray2(levelCopy);
            }
        }
        draw2DArray2(levelCopy);
        //pályamásolat vizsgálata: maradt-e pont valahol

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (levelCopy[row][column].equals(".")) {
                    return false;
                }
            }
        }

        //System.exit(0);
        //return false;
        return true;

    }

    public static void draw2DArray2(String[][] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

    }
}
