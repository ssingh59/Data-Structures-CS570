package Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) { // Finds maze path
    	if(x < 0 || y < 0){//checks values for x and y
    		return false;
    	}
    	//if x& y is greater than allowed coordinates
    	else if(x >= maze.getNCols() || y >= maze.getNRows()) {
    		return false;
    	}
    	//If path is not red ,return false
    	else if(!maze.getColor(x, y).equals(NON_BACKGROUND)) 
    	{
    		return false;
    	}
    	//If path reaches end return true
    	if(x == maze.getNCols()-1 && y == maze.getNRows()-1) 
        {
    		//color the last block as path color green
        	maze.recolor(x, y, PATH);
        	return true;
        }
    	else
    	{
    		//if coodinate is correct with background color red, recolor path to green
    		maze.recolor(x, y, PATH);
    		if(findMazePath(x-1,y) || findMazePath(x+1,y) || findMazePath(x,y-1) || findMazePath(x,y+1))
    		{
    			return true;
    		}
    		else
    		{
    			maze.recolor(x, y, TEMPORARY);
    			return false;
    		}
    	}
    }

    
    
    //helper method
    //Push the visit cell in the stack
    //Push the contents of stack in the list and pop the cell if path is found
    @SuppressWarnings("unchecked")
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) 
    {
    	if(x < 0 || y < 0){//checks values for x and y
    		return;
    	}
    	//if x& y is greater than allowed coordinates
    	else if(x >= maze.getNCols() || y >= maze.getNRows()) {
    		return;
    	}
    	//If path is not red ,return false
    	else if(!maze.getColor(x, y).equals(NON_BACKGROUND)) 
    	{
    		return;
    	}
    	//push new pair of coordinate onto stack
    	else if(y == maze.getNRows()-1 && x == maze.getNCols()-1)
    	{
    		PairInt pNew = new PairInt(x,y);
    		trace.push(pNew);
    		ArrayList<PairInt> temp  = new ArrayList<PairInt>();
    		temp.addAll(trace);
    		result.add(temp);
    		trace.pop(); 
    		maze.recolor(x, y, NON_BACKGROUND);
            return;
    	}
    	else
    	{
    		PairInt pNew = new PairInt(x,y);
			trace.push(pNew);
			//color the path before recursion
    		maze.recolor(x, y, PATH);
    		//try to find paths from neighbor cells, 
    		//if cell is in the path it is added in stack and cycle goes on until end coordinate reached
    		this.findMazePathStackBased(x, y+1, result, (Stack<PairInt>)trace.clone());
    		this.findMazePathStackBased(x, y-1, result, (Stack<PairInt>)trace.clone());
    		this.findMazePathStackBased(x+1, y, result, (Stack<PairInt>)trace.clone());
    		this.findMazePathStackBased(x-1,y, result, (Stack<PairInt>)trace.clone());
    		//after a try of all paths
    		//re-color non paths to non-background
    		maze.recolor(x, y, NON_BACKGROUND);
    		trace.pop();
    		return;
    	}
    }
    
    //returns arraylist of all possible paths with pair of coordinates
    public ArrayList < ArrayList < PairInt >> findAllMazePaths ( int x, int y) {
    	
   	 ArrayList <ArrayList< PairInt >> result = new ArrayList < >();
   	 Stack < PairInt > trace = new Stack < >();
   	 findMazePathStackBased (0 ,0 , result , trace );
   	 //if their are no path it return empty array
   	 if(result.size() == 0) {
		 ArrayList<PairInt> temp = new ArrayList<PairInt>();
		 result.add(temp);
	 }
	 return result;
   }
    
    public ArrayList<PairInt> findMazePathMin(int x, int y) //Finds the index of the shortest path and prints the shortest path
    {
    	ArrayList<ArrayList<PairInt>> answer = findAllMazePaths(x,y);
    	ArrayList<PairInt> emptyList = new ArrayList<PairInt>();
    	//if no path can be found return null
    	int i=0;
    	int minListLength=0;
    	ArrayList<PairInt> minList = new ArrayList<PairInt>();
    		//suppose min length list
	    	minList = answer.get(0);
	    	minListLength = minList.size();
	    //check for each araylst size of arraylist
	    	for(i=1; i<answer.size(); i++){
	    		if(minListLength >= answer.get(i).size()){
	    			minList = answer.get(i);
	    			minListLength = minList.size();
	    		}
	    	}
	    	return minList;
    }
    
    
    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
