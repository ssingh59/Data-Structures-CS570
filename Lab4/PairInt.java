package Maze;

import Maze.PairInt;

public class PairInt {

    // private data field
    private int x;
    private int y;

    // constructor
    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;

    }

    // getter setter methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(PairInt p) {
        if ( p == null) {
            return false;	
        }
        if (p.getX() == x) {
        	if(p.getY() == y) {
        	return true;
        	}
        }
        return false;
    }

    @Override
    public String toString() {
    	String s1 = String.valueOf(x);
    	String s2 = String.valueOf(y);
        return "(" + s1 + ", " + s2 + ")";
    }

    //makes copy of pairint object
    public PairInt copy() {
    	PairInt pCopy = new PairInt(x,y);
		return pCopy;

    }
}