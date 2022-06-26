package mazesolver;

public class Controller {
    static Coordinates c = new Coordinates();

    static int loopCounter = 0;
    static int mainLoopCounter = 0;

    static int height = 15;
    static int width = 15;
    private static Room room;
    private static MovingEntity vc;

    public static void moving(Room room, MovingEntity movingEntity) throws InterruptedException {


        while (!(movingEntity.getCoordinates().isSame(new Coordinates(19,19)))) {

            movingEntity.runMaze(room);
            Thread.sleep(30);
            room.draw();

        }


        goToTheStartPosition(room, movingEntity);

    }




    private static void goToTheStartPosition(Room room, MovingEntity movingEntity) throws InterruptedException {

        Coordinates c2 = new Coordinates();
        int ctr = 0;

        c2.setRow(1);
        c2.setColumn(1);


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
