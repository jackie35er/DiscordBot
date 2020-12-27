package commands.games.ticTacToe;

import interfaces.Copyable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Field implements Comparable<Field>, Copyable<Field> {
    private int x;
    private int y;

    /*
    saves current state of the board
    0 for no player
    1 for x
    -1 for y
     */
    private int state;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Field(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }
    public Field(int x,int y){
        this(x,y,0);
    }

    @Override
    public String toString() {
        return "Field{" + "x=" + x +
                ", y=" + y +
                ", state=" + state +
                '}';
    }

    @Override
    public int compareTo(@NotNull Field o) {
        if(this.x == o.getX()){
            return Integer.compare(this.y,o.getY());
        }
        else{
            return Integer.compare(this.x,o.getX());
        }
    }

    @Override
    public Field copy() {
        return new Field(this.x,this.y,this.state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return x == field.x && y == field.y && state == field.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, state);
    }
}
