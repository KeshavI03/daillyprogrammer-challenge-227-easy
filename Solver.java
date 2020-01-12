import java.util.Scanner;

public class Solver {
	public static void main(String[] args) {
		
		System.out.println(solveSpiral(11,50));
		
		System.out.println(solvePosition(9,6,8));
	}
	
	public static String solveSpiral(int size, int pos) {
		
		if(pos == 1)
			return "(" + (size+1)/2 + ", " + (size+1)/2 + ")";
		
		int shell = (((int) Math.sqrt(pos-1))+1)/2;
		
		int distance = pos- (int) Math.pow((shell*2)-1,2) -1;
		
		int line = distance/(Math.max(shell*2,1));
		
		int x = (-shell) * ((line-1)%2);
		
		int y = shell * ((line-2)%2);
		
		int changeFromStart = (distance % Math.max(shell*2,1)) * (((2 * line)-3)/Math.abs((2 * line)-3));
		
		changeFromStart+= (shell-1) * -(line-1) + ((line%2)*(shell-1));
		
		if(line%2 == 0) y=changeFromStart * ((line+1)%2);
		else x=changeFromStart * (line%2);
		
		x+= (size+1)/2;
		y+= (size+1)/2;
		
		return "(" + x + ", " + y + ")";
	}
	
	public static int solvePosition(int size, int x, int y) {
		int centerX = (size+1)/2;
		int centerY = centerX;
		int numShift = 1;
		int val = 1;
		
		if(centerX == x && centerY ==y) return val;
			
		while(true) {
			for(int i = 0; i < numShift; i++) {
				centerX++;
				val++;
				if(centerX == x && centerY ==y) return val;
			}	
			for(int j = 0; j < numShift; j++) {
				centerY--;
				val++;
				if(centerX == x && centerY == y) return val;
			}
			numShift++;
			for(int i = 0; i < numShift; i++) {
				centerX--;
				val++;
				if(centerX == x && centerY ==y) return val;
			}
			for(int j = 0; j < numShift; j++) {
				centerY++;
				val++;
				if(centerX == x && centerY == y) return val;
			}
			numShift++;
		}
		
	}
}