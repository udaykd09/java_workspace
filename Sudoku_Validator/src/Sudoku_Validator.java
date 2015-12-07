import java.util.*;

public class Sudoku_Validator {

	boolean isValid(int i, int j, int grid[][])
	{
	  // Check whether grid[i][j] is valid at the i's row
	  for (int column = 0; column < 9; column++)
		if (column != j && grid[i] [column] == grid[i] [j])
		  return false;

	  // Check whether grid[i][j] is valid at the j's column
	  for (int row = 0; row < 9; row++)
		if (row != i && grid[row] [j] == grid[i] [j])
		  return false;

	  // Check whether grid[i][j] is valid in the 3 by 3 box
	  for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
		for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
		  if (row != i && col != j && grid[row] [col] == grid[i] [j])
			return false;

	  return true; // The current value at grid[i][j] is valid
	}

	/** Check whether the fixed cells are valid in the grid */
	boolean isValid(int grid[][]) {
	  // Check for duplicate numbers
	  for (int i = 0; i < 9; i++)
		for (int j = 0; j < 9; j++)
		  if (grid[i][j] != 0)
			if (!isValid(i, j, grid))
			  return false;

	  // Check whether numbers are in the range
	  for (int i = 0; i < 9; i++)
		for (int j = 0; j < 9; j++)
		  if ((grid[i][j] < 0) || (grid[i][j] > 9))
			return false;

	  return true; // The fixed cells are valid
	}
	/** Print the values in the grid */
	void printGrid(int grid[][])
	{
	  for (int i = 0; i < 9; i++)
	  {
		for (int j = 0; j < 9; j++);
//		  cout << grid[i] [j] << " ";
//		cout << endl;
	  }
	}

}
