// Name:
// Date:

import java.util.*;
import java.io.*;

public class MazeMaster {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the maze's filename (no .txt): ");
		Maze m = new Maze(sc.next() + ".txt");
		// Maze m = new Maze(); //extension
		m.display();
		System.out.println("Options: ");
		System.out.println("1: Mark all dots.");
		System.out.println("2: Mark all dots and display the number of recursive calls.");
		System.out.println("3: Mark only the correct path.");
		System.out.println("4: Mark only the correct path. If no path exists, say so.");
		System.out.println(
				"5: Mark only the correct path and display the number of steps.\n\tIf no path exists, say so.");
		System.out.print("Please make a selection: ");
		m.solve(sc.nextInt());
		m.display(); // display solved maze
	}
}

class Maze {
	// constants
	private int rows;
	private int col;
	private final char WALL = 'W';
	private final char DOT = '.';
	private final char START = 'S';
	private final char EXIT = 'E';
	private final char TEMP = 'o';
	private final char PATH = '*';
	// instance fields
	private char[][] maze;
	private int startRow, startCol;

	// constructors

	/*
	 * EXTENSION This is a no-arg constructor that generates a random maze
	 */
	public Maze() {

	}

	/*
	 * Copy Constructor
	 */
	public Maze(char[][] m) {
		maze = m;
		for (int r = 0; r < maze.length; r++) {
			for (int c = 0; c < maze[0].length; c++) {
				if (maze[r][c] == START) // identify start location
				{
					startRow = r;
					startCol = c;
				}
			}
		}
	}

	/*
	 * Use a try-catch block Use next(), not nextLine()
	 */
	public Maze(String filename) {
		Scanner f = null;
		try {
			f = new Scanner(new File(filename));
		} catch (IOException e) {
			System.out.println("Error with File");
			System.exit(0);
		}
		maze = new char[f.nextInt()][f.nextInt()];
		;
		for (int i = 0; i < maze.length; i++) {
			String line = f.next();
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j] = line.charAt(j);
				if(maze[i][j] == START) {
					startRow = i;
					startCol = j;
				}
			}
		}

	}

	public char[][] getMaze() {
		return maze;
	}

	public void display() {
		if (maze == null)
			return;
		for (int a = 0; a < maze.length; a++) {
			for (int b = 0; b < maze[0].length; b++) {
				System.out.print(maze[a][b]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public void solve(int n) {
		switch (n) {
		case 1: {
			markAll(startRow, startCol);
			break;
		}
		case 2: {
			int count = markAllAndCountRecursions(startRow, startCol);
			System.out.println("Number of recursions = " + count);
			break;
		}
		case 3: {
			markTheCorrectPath(startRow, startCol);
			break;
		}
		case 4: // use mazeNoPath.txt
		{
			if (!markTheCorrectPath(startRow, startCol))
				System.out.println("No path exists.");
			break;
		}
		case 5: {
			if (!markCorrectPathAndCountSteps(startRow, startCol, 0))
				System.out.println("No path exists.");
			break;
		}
		default:
			System.out.println("File not found");
		}
	}

	/*
	 * From handout, #1. Fill the maze, mark every step. This is a lot like
	 * AreaFill.
	 */
	public void markAll(int r, int c) {
		if (maze[r][c] != '.' && maze[r][c] != 'S') {
			return;
		} else {
			maze[r][c] = '*';
		}
		if (r == 0) {
			markAll(r + 1, c);
		} else if (r == maze.length - 1) {
			markAll(r - 1, c);
		} else {
			markAll(r + 1, c);
			markAll(r - 1, c);
		}
		if (c == 0) {
			markAll(r, c + 1);
		} else if (c == maze[0].length - 1) {
			markAll(r, c - 1);
		} else {
			markAll(r, c - 1);
			markAll(r, c + 1);
		}
	}

	/*
	 * From handout, #2. Fill the maze, mark and count every recursive call as you
	 * go. Like AreaFill's counting without a static variable.
	 */
	public int markAllAndCountRecursions(int r, int c) {
		int count = 0;
		if (maze[r][c] != '.' && maze[r][c] != 'S') {
			return 0;
		} else {
			maze[r][c] = '*';
			count++;
		}
		if (r == 0) {
			count = count + markAllAndCountRecursions(r + 1, c);
		} else if (r == maze.length - 1) {
			count = count + markAllAndCountRecursions(r - 1, c);
		} else {
			count = count + markAllAndCountRecursions(r + 1, c);
			count = count + markAllAndCountRecursions(r - 1, c);
		}
		if (c == 0) {
			count = count + markAllAndCountRecursions(r, c + 1);
		} else if (c == maze[0].length - 1) {
			count = count + markAllAndCountRecursions(r, c - 1);
		} else {
			count = count + markAllAndCountRecursions(r, c - 1);
			count = count + markAllAndCountRecursions(r, c + 1);
		}
		return count;
	}

	public boolean markTheCorrectPath(int r, int c) {
		if(r >= 0 && r < maze.length && c >= 0 && c < maze[0].length) {
			if(maze[r][c] == '.' || maze[r][c] == 'S') {
				maze[r][c] = 'o';
				if(markTheCorrectPath(r - 1, c) || markTheCorrectPath(r, c - 1) || markTheCorrectPath(r + 1, c) || markTheCorrectPath(r, c + 1)) {
					maze[r][c] = '*';
					return true;
				}
				else {
					maze[r][c] = '.';
					return false;
				}
			}
			else if(maze[r][c] == 'E') {
				return true;
			}
			
		}
		
		
		return false;
	}

	/*
	 * 4 Mark only the correct path. If no path exists, say so. Hint: the method
	 * above returns the boolean that you need.
	 */

	/*
	 * From handout, #5. Solve the maze, mark the path, count the steps. Mark only
	 * the correct path and display the number of steps. If no path exists, say so.
	 */
	public boolean markCorrectPathAndCountSteps(int r, int c, int count) {
		if(r >= 0 && r < maze.length && c >= 0 && c < maze[0].length) {
			if(maze[r][c] == '.' || maze[r][c] == 'S') {
				maze[r][c] = 'o';
				if(markCorrectPathAndCountSteps(r - 1, c, count++) || markCorrectPathAndCountSteps(r, c - 1, count++) || markCorrectPathAndCountSteps(r + 1, c,  count++) || markCorrectPathAndCountSteps(r, c + 1, count++)) {
					maze[r][c] = '*';
					return true;
				}
				else {
					maze[r][c] = '.';
				}
			}
			else if(maze[r][c] == 'E') {
				System.out.println(count - 1);
				return true;
			}
			
		}
		
		
		return false;
	}
}

/*****************************************
 * 
 * ----jGRASP exec: java MazeMaster_teacher Enter the maze's filename (no .txt):
 * maze1 WWWWWWWW W....W.W WW.WW..W W....W.W W.W.WW.E S.W.WW.W WW.....W WWWWWWWW
 * 
 * Options: 1: Mark all dots. 2: Mark all dots and display the number of
 * recursive calls. 3: Mark only the correct path. 4: Mark only the correct
 * path. If no path exists, say so. 5: Mark only the correct path and display
 * the number of steps. If no path exists, say so. Please make a selection: 1
 * WWWWWWWW W****W*W WW*WW**W W****W*W W*W*WW*E S*W*WW*W WW*****W WWWWWWWW
 * 
 * 
 * ----jGRASP: operation complete.
 * 
 * ----jGRASP exec: java MazeMaster_teacher Enter the maze's filename (no .txt):
 * maze1 WWWWWWWW W....W.W WW.WW..W W....W.W W.W.WW.E S.W.WW.W WW.....W WWWWWWWW
 * 
 * Options: 1: Mark all dots. 2: Mark all dots and display the number of
 * recursive calls. 3: Mark only the correct path. 4: Mark only the correct
 * path. If no path exists, say so. 5: Mark only the correct path and display
 * the number of steps. If no path exists, say so. Please make a selection: 2
 * Number of recursions = 105 WWWWWWWW W****W*W WW*WW**W W****W*W W*W*WW*E
 * S*W*WW*W WW*****W WWWWWWWW
 * 
 * 
 * ----jGRASP: operation complete.
 * 
 * ----jGRASP exec: java MazeMaster_teacher Enter the maze's filename (no .txt):
 * maze1 WWWWWWWW W....W.W WW.WW..W W....W.W W.W.WW.E S.W.WW.W WW.....W WWWWWWWW
 * 
 * Options: 1: Mark all dots. 2: Mark all dots and display the number of
 * recursive calls. 3: Mark only the correct path. 4: Mark only the correct
 * path. If no path exists, say so. 5: Mark only the correct path and display
 * the number of steps. If no path exists, say so. Please make a selection: 3
 * WWWWWWWW W....W.W WW.WW..W W***.W.W W*W*WW*E S*W*WW*W WW.****W WWWWWWWW
 * 
 * 
 * ----jGRASP: operation complete.
 * 
 * 
 * ----jGRASP exec: java MazeMaster_teacher Enter the maze's filename (no .txt):
 * mazeNoPath WWWWWWWW W....W.W WW.WW..E W..WW.WW W.W.W..W S.W.WW.W WWW....W
 * WWWWWWWW
 * 
 * Options: 1: Mark all dots. 2: Mark all dots and display the number of
 * recursive calls. 3: Mark only the correct path. 4: Mark only the correct
 * path. If no path exists, say so. 5: Mark only the correct path and display
 * the number of steps. If no path exists, say so. Please make a selection: 4 No
 * path exists. WWWWWWWW W....W.W WW.WW..E W..WW.WW W.W.W..W S.W.WW.W WWW....W
 * WWWWWWWW
 * 
 * 
 * ----jGRASP: operation complete.
 * 
 * ----jGRASP exec: java MazeMaster_teacher Enter the maze's filename (no .txt):
 * maze1 WWWWWWWW W....W.W WW.WW..W W....W.W W.W.WW.E S.W.WW.W WW.....W WWWWWWWW
 * 
 * Options: 1: Mark all dots. 2: Mark all dots and display the number of
 * recursive calls. 3: Mark only the correct path. 4: Mark only the correct
 * path. If no path exists, say so. 5: Mark only the correct path and display
 * the number of steps. If no path exists, say so. Please make a selection: 5
 * Number of steps = 14 WWWWWWWW W....W.W WW.WW..W W***.W.W W*W*WW*E S*W*WW*W
 * WW.****W WWWWWWWW
 * 
 * 
 * ----jGRASP: operation complete.
 ********************************************/