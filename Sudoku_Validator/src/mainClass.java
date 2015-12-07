
public class mainClass{

	static Sudoku_Validator s = new Sudoku_Validator();
	
	static int grid[][] = 
		 {{8,3,5,4,1,6,9,2,7},
		  {2,9,6,8,5,7,4,3,1},
		  {4,1,7,2,9,3,6,5,8},
		  {5,6,9,1,3,4,7,8,2},
		  {1,2,3,6,7,8,5,4,9},
		  {7,4,8,5,2,9,1,6,3},
		  {6,5,2,7,8,1,3,9,4},
		  {9,8,1,3,4,5,2,7,6},
		  {3,7,4,9,6,2,8,1,5}};
	
	static int grid2[][] = 
		 {{8,2,5,4,1,6,9,2,7},
		  {2,9,6,8,5,7,4,3,1},
		  {4,1,7,2,9,3,6,5,8},
		  {5,6,9,1,3,4,7,8,2},
		  {1,2,3,6,7,8,5,4,9},
		  {7,4,8,5,2,9,1,6,3},
		  {6,5,2,7,8,1,3,9,4},
		  {9,8,1,3,4,5,2,7,6},
		  {3,7,4,9,6,2,8,1,5}};
	
	
	public static void main(String[] args) {
	
	Thread a = new Thread(new Runnable() {
		public void run() {
			if(s.isValid(grid)) System.out.println("1st grid Valid");
			else System.out.println("1st grid Invalid");
		}
	});

	Thread b = new Thread(new Runnable() {
		public void run() {
			if(s.isValid(grid2)) System.out.println("2nd grid Valid");
			else System.out.println("2nd grid Invalid");
		}
	});
	a.start();
	b.start();
}
}
