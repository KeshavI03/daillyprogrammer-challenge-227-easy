import java.util.Scanner;

public class Solver {
	public static void main(String[] args) {
		//Example Inputs
		
		//Spiral Solution
		
		System.out.println(solveSpiral(11,50));
		
		//Value Solution
		
		System.out.println(solvePosition(9,6,8));
	}
	
	public static String solveSpiral(int size, int pos) {
		
		//If the start of the spiral is the desired value, we can easily calculate it, given that it is always in the center the grid
		
		if(pos == 1)
			return "(" + (size+1)/2 + ", " + (size+1)/2 + ")";
		
		//Shell represents which layer of the spiral a value is on - 1 results in 0, 2-9 results in 1, 10-25 results in 2 etc.
		
		int shell = (((int) Math.sqrt(pos-1))+1)/2;
		
		//Distance results in the value minus the first value of its shell, essentially its numerical distance from the start of its shell
		
		int distance = pos- (int) Math.pow((shell*2)-1,2) -1;
		
		//Line represents a division of the outermost shell. The Line variable represents the relative location of the value (which side of the shell it is on)
		//Line 0 represents the column on the right of the shell (excluding bottom right most value)
		//Line 1 represents the row across top of shell (excluding top right most value)
		//Line 2 represents the column to the left of the shell (excluding the top left most value)
		//Line 3 represents the bottom row of the shell(excluding the bottom left most value)
		
		int line = distance/(Math.max(shell*2,1));
		
		//Setting x and y values
		//(Determined by Line value) If the value is on the top of bottom of the shell, set the Y value
		// If the value is on the left or right of the shell, then set the X value
		//NOTE: the other value is defaulted to 0 and isn't changed
		
		int x = (-shell) * ((line-1)%2);
		
		int y = shell * ((line-2)%2);
		
		//changeFromStart represents the position of the value on the line. Essentially, its x/y change from the beginning of the line
		
		int changeFromStart = (distance % Math.max(shell*2,1)) * (((2 * line)-3)/Math.abs((2 * line)-3));
		
		//Here, we include the shell size to calculate the distance from the center of the spiral, rather from the beginning of the line
		//NOTE: this value represents x-distance when line is 0 or 2 (vertical), and y-distance when line is 1 or 3 (horizontal)
		
		changeFromStart+= (shell-1) * -(line-1) + ((line%2)*(shell-1));
		
		//Here we set the opposite x or y position that was defaulted to 0 based of the changeFromStart variable, and now we have the x and y position (kind of)
		
		if(line%2 == 0) y=changeFromStart;
		else x=changeFromStart;
		
		//NOTE: all the previous calculations have assumed the origin of the spiral to be located at 0,0. We can easily take into accound the grid size value by
		//Shifting the x and y values to the center of the grid by calculating the coordinate point values for the center of the grid (center of 7x7 grid is at (4,4))
		
		x+= (size+1)/2;
		y+= (size+1)/2;
		
		//Here we return a string representation of the calculated position
		
		return "(" + x + ", " + y + ")";
	}
	
	public static int solvePosition(int size, int x, int y) {
		int centerX = (size+1)/2;
		int centerY = centerX;
		int numShift = 1;
		int val = 1;
		
		//Here we simply go around the spiral using rules until we come upon a position matching x and y and display its value
		//RULES FOR MOVEMENT:
		// 1 Right
		// 1 Up
		// 2 Left
		// 2 Down
		// 3 Right
		// 3 Up
		// This system can be made using a counter that increments after moving right and up, and increment again after moving left and down
		
		//if the center is already the point we are looking for, return 1, the center value of the spiral
		if(centerX == x && centerY ==y) return val;
			
		//run until function is automatically broken by finding the correct value
		while(true) {
			
			//numShift starts of at 1 given by our rules
			//we increment our position until it reaches the right amount, numShift
			//for every shift in our position, we find its value by incrementing our val variable
			//we check if the position is the one we are looking for, and if it is we return the val variable which is the value at that position of the spiral
			
			
			for(int i = 0; i < numShift; i++) {
				//Moving right
				centerX++;
				val++;
				if(centerX == x && centerY ==y) return val;
			}	
			for(int j = 0; j < numShift; j++) {
				//Moving up
				centerY--;
				val++;
				if(centerX == x && centerY == y) return val;
			}
			//Increasing amount moved to get around spiral
			numShift++;
			for(int i = 0; i < numShift; i++) {
				//Moving left
				centerX--;
				val++;
				if(centerX == x && centerY ==y) return val;
			}
			for(int j = 0; j < numShift; j++) {
				//Moving down
				centerY++;
				val++;
				if(centerX == x && centerY == y) return val;
			}
			//Increasing amount moved to get around spiral
			numShift++;
		}
		
	}
}
