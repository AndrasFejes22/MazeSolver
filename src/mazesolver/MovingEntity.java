package mazesolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class MovingEntity {
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
    public MovingEntity(String mark, Coordinates coordinates, Direction direction) {
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

    public void moveToTheStart(Room room) throws InterruptedException {
        setMark("M");

        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        switch (getDirection()) {
            case UP:
                if (room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")
                        || room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
                    newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                    room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");
                }
                break;
            case DOWN:
                if (room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")
                        ||room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," ")){
                    newCoordinates.setRow(getCoordinates().getRow() + 1); //set newCoordinates
                    room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");
                }
                break;
            case LEFT:
                if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                        || room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," ")){
                    newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                    room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");
                }
                break;
            case RIGHT:
                if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                        || room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," ")){
                    newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                    room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");
                }
                break;
        }
        setCoordinates(newCoordinates);
        Thread.sleep(30);


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
