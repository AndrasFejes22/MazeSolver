package mazesolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        Coordinates FirstCoordinates = new Coordinates(1, 1);
        Coordinates VcCoordinates2 = new Coordinates(12, 4);
        Coordinates VcCoordinates3 = new Coordinates(3, 5);

        MovingEntity movingEntity = new MovingEntity("@ ", FirstCoordinates, Direction.UP);


        //Room room = new Room(15, 15, movingEntity, 3, 3); //komment



        ////////////MAIN CONTROLL////////////
        //VacuumCleanerController.cleaning(room, movingEntity);//komment
        ///file-ból////
        int rows = 21; //it is better to scan how many rows and columns there are
        int columns = 21;
        String[][] myArray = new String[rows][columns];
        try {
            System.out.println("FIRST draw a room from a text: ");
            File myObj = new File("C:/Users/Andris/IdeaProjects/MazeSolver/src/mazes/maze2.txt");
            System.out.println("myObj.length(): " + myObj.length());
            Scanner myReader = new Scanner(myObj);


            while (myReader.hasNextLine()) {

                for (int i = 0; i < myArray.length; i++) {
                    String[] line = myReader.nextLine().trim().split(""); //itt volt jó

                    for (int j = 0; j < line.length; j++) {
                        myArray[i][j] = line[j];
                        System.out.print(myArray[i][j]);
                    }
                    System.out.println();
                }
            }
            myReader.close();
        } catch (NoSuchElementException | FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Room room = new Room(21, 21, myArray, movingEntity);

        Controller.cleaning(room, movingEntity);


    }

}

