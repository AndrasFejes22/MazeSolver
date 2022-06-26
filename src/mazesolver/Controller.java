package mazesolver;

public class Controller {
    static Coordinates c = new Coordinates();

    static int loopCounter = 0;
    static int mainLoopCounter = 0;

    static int height = 15;
    static int width = 15;
    private static Room room;
    private static MovingEntity vc;

    public static void cleaning(Room room, MovingEntity movingEntity) throws InterruptedException {


        while (!(movingEntity.getCoordinates().isSame(new Coordinates(19,19)))) { //MAZE_Runner //ellenőrzés a koordináta nem e "X"

            movingEntity.runMaze(room);
            Thread.sleep(30);
            room.draw();

        }


        GoToTheStartPosition(room, movingEntity);

    }




    private static void GoToTheStartPosition(Room room, MovingEntity movingEntity) throws InterruptedException {

        Coordinates c2 = new Coordinates();
        int ctr = 0;

        c2.setRow(1);
        c2.setColumn(1);

        for (int i = 2; i < width-1; i++) {
            if (room.isMark(c2, "X")) {
                c2.setColumn(i);
                //break;
            }
        }




        while (!movingEntity.getCoordinates().isSame(c2)) {

            movingEntity.setDirection(room.getShortestPath(movingEntity.getDirection(), movingEntity.getCoordinates(), c2));
            movingEntity.moveToTheStart(room);
            room.draw();
            System.out.println("-------" + ctr + "c-----");

            Thread.sleep(60);
            ctr++;

        }

    }
}
