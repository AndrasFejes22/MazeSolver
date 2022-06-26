package mazesolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class VacuumCleaner {
    private String mark;

    private Coordinates coordinates;

    private Direction direction;

    @Override
    public String toString() {
        return "VacuumCleaner{" +
                "mark='" + mark + '\'' +
                ", coordinates=" + coordinates +

                ", direction=" + direction +
                '}';
    }

    /**
     * @param mark
     * @param coordinates


     */
    public VacuumCleaner(String mark, Coordinates coordinates, Direction direction) {
        super();
        this.mark = mark;
        this.coordinates = coordinates;
        this.direction = direction;
        //this.room = room;
    }




    public String getMark() {
        return mark;
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setMark(String mark) {
        //ellenőrzés
        this.mark = mark;
    }


    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void moveToTheNextDirt(Room room) throws InterruptedException {
        setMark("M");
        //System.out.println("moving to the next dirt");//kiírja, akkor is, ha nincs dirt, mert ez egy mozgató method, és ez a cleanerGoHome() is!
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        switch (getDirection()) {
            case UP:
                if (room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")
                        || room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
                    newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                    room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");//csaka maze-hez!!!
                }
                break;
            case DOWN:
                if (room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")
                        ||room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," ")){
                    newCoordinates.setRow(getCoordinates().getRow() + 1); //set newCoordinates
                    room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");//csaka maze-hez!!!
                }
                break;
            case LEFT:
                if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                        || room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," ")){
                    newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                    room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");//csaka maze-hez!!!
                }
                break;
            case RIGHT:
                if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                        || room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," ")){
                    newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                    room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");//csaka maze-hez!!!
                }
                break;
        }
        setCoordinates(newCoordinates);
        Thread.sleep(30);


    }

    public void cleaningARoom(Room room) throws InterruptedException {
        setMark("@");
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()+1),".")){
            //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()+1)," ")){
            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
            setCoordinates(newCoordinates);//setCoordinates(newCoordinates);
            room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
        }else

        if (room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")){
            //System.out.println("RIGHT2");
            //||room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
            newCoordinates.setRow(getCoordinates().getRow() - 1);
            setCoordinates(newCoordinates);
            room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
        }else


        if (room.isMark(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn()),".")){
            //System.out.println("RIGHT3");
            //|| room.isMark(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn())," ")){
            newCoordinates.setRow(getCoordinates().getRow()+1);
            setCoordinates(newCoordinates);
            room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        }else


        if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1),".")){
            //System.out.println("RIGHT4");
            //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1)," ")){
            newCoordinates.setColumn(getCoordinates().getColumn()-1);
            setCoordinates(newCoordinates);
            room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        }else {
            //System.out.println("searchingNextDirt");
            //setDirection(direction.DOWN);
            searchingNextDirt(room);
        }
        //break;
        //default:
        //goToTheNextDirt(room);
        //}//switch
        //goToTheNextDirt(room);

    }

    public void runMaze(Room room) throws InterruptedException {
        System.out.println(getCoordinates().getRow());
        System.out.println(getCoordinates().getColumn());
        //boolean way = true;
        setMark("@");
        Coordinates endCoordinates = new Coordinates(14, 14);
        System.out.println("true or false? "+ !getCoordinates().isSame(endCoordinates));
        System.out.println("Mark? "+ getMark());
        //Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
        //while (!(getCoordinates().isSame(endCoordinates))) {
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
        if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()+1),".")){
            //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()+1)," ")){
            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
            setCoordinates(newCoordinates);//setCoordinates(newCoordinates);
            room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
        }else

        if (room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")){
            //System.out.println("RIGHT2");
            //||room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
            newCoordinates.setRow(getCoordinates().getRow() - 1);
            setCoordinates(newCoordinates);
            room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
        }else


        if (room.isMark(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn()),".")){
            //System.out.println("RIGHT3");
            //|| room.isMark(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn())," ")){
            newCoordinates.setRow(getCoordinates().getRow()+1);
            setCoordinates(newCoordinates);
            room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        }else


        if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1),".")){
            //System.out.println("RIGHT4");
            //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1)," ")){
            newCoordinates.setColumn(getCoordinates().getColumn()-1);
            setCoordinates(newCoordinates);
            room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        }else {
            if(room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1)," ") ) {
                newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                setCoordinates(newCoordinates);//setCoordinates(newCoordinates);

                room.setCleaned2(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()+1), "-");
            } else
            if(room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()+1)," ")) {
                newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                setCoordinates(newCoordinates);//setCoordinates(newCoordinates);

                room.setCleaned2(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1), "-");
            } else
            if(room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," ")) {
                newCoordinates.setRow(getCoordinates().getRow()+ 1);
                setCoordinates(newCoordinates);//setCoordinates(newCoordinates);

                room.setCleaned2(new Coordinates(getCoordinates().getRow()-1, getCoordinates().getColumn()), "-");
            } else
            if(room.isMark(new Coordinates(getCoordinates().getRow() -1 , getCoordinates().getColumn())," ")) {
                newCoordinates.setRow(getCoordinates().getRow() - 1);
                setCoordinates(newCoordinates);//setCoordinates(newCoordinates);

                room.setCleaned2(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn()), "-");
            }

        }
        //break;
        //default:
        //goToTheNextDirt(room);
        //}//switch
        //goToTheNextDirt(room);

        //}//while

    }

    public void searchingNextDirt(Room room) throws InterruptedException {
        //System.out.println("goToTheNextDirt");
        setMark("?");
        Coordinates c2 = new Coordinates();
        int ctr = 0;                                                                                               //X
        int C =getCoordinates().getColumn(); //nem jó, kijöhet hogy a (13;13) után kell keresgélni, error lesz .... @X
        int R =getCoordinates().getRow();                                                                     //XXXXXX itt a sarokban (13;13) van

        //System.out.println(getCoordinates().getColumn());
        //System.out.println(getCoordinates().getRow());

        outer : for (int k = 1; k < room.getHeight() ; k++) {//height - 1
            for (int m = 1; m < room.getWidth() ; m++) {//width - 1
                Coordinates newCoordinates = new Coordinates(k, m);
                //c2 = new Coordinates();

                if (room.isMark(newCoordinates, ".")) {

                    Thread.sleep(30);
                    //Coordinates c3 = getClosestDirt(room, newCoordinates);
                    c2.setRow(k);
                    c2.setColumn(m);

                    while (!getCoordinates().isSame(c2)) {

                        setDirection(room.getShortestPath(getDirection(), getCoordinates(), c2));
                        moveToTheNextDirt(room); //felokosított mozgás
                        //cleaningARoom1(room);
                        room.draw();//ha nem rajzolunk, olyan mintha ugrana, akár 10 lépést is!
                        System.out.println("-------" + ctr + "dirt-----");
                        break outer;//**odaért a koszhoz
                        //


                        //ctr++;
                        //ctr = 0;
                    }

                }//if


            } // for

        } // for
        cleaningARoom(room);//**takarít
    }

    public Coordinates getClosestDirt(Room room, Coordinates coordinates) throws InterruptedException {
        HashMap<Coordinates, Integer> sm = new HashMap<Coordinates, Integer>();
        int ctr = 0;
        Coordinates c2 = coordinates;
        while (!getCoordinates().isSame(c2)) {

            setDirection(room.getShortestPath(getDirection(), getCoordinates(), c2));
            moveToTheNextDirt(room);
            //room.draw();
            System.out.println("-------" + ctr + "c-----");
            sm.put(c2, ctr);
            //System.out.println("sm_CleanerGoHome_inWhile: "+sm);
            //Thread.sleep(100);
            ctr++;
            //ctr = 0;
        }

        List<Map.Entry<Coordinates, Integer>> singleList = sm.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        //comparingByValue(): termésszetes sorrend
        singleList.get(0);

        //.forEach(System.out::println);

        System.out.println("sm_ "+sm);

        Collection<Integer> values = sm.values();
        List<Integer> val = new ArrayList<Integer>(values);
        System.out.println("values: "+val);

        //Stream<Entry<Coordinates, Integer>> values2 = ((HashMap<Coordinates, Integer>) sm.keySet()).entrySet().stream().sorted(Map.Entry.comparingByValue());

        List<Coordinates> val2 = new ArrayList<Coordinates>();
        System.out.println("legkisebb key_row: "+singleList.get(0).getKey().getRow());
        System.out.println("legkisebb key_column: "+singleList.get(0).getKey().getColumn());
        System.out.println("legkisebb value: "+singleList.get(0).getValue());
        return coordinates;

    }

    public static Direction RandomDirection(){
        //Direction direction = Direction.RIGHT;
        Random r = new Random();
        int low = 1;
        int high = 5;
        int result = r.nextInt(high-low) + low;
        switch (result) {
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.LEFT;
            case 3:
                return Direction.UP;
            case 4:
                return Direction.DOWN;
        }
        return null;
    }
}
