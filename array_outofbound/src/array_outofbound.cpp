#include <iostream>
#include <fstream>
#include <pthread.h>

#define NUM_THREADS 3
using namespace std;

void readpuzzle ( int grid[][9]);
bool isValid(int i, int j, int grid[][9]);
bool isValid(int grid[][9]);
void printGrid(int grid[] [9]);

int main()
{
    
    pthread_t threads[NUM_THREADS];
    
    int grid[9][9] =
    {{8,3,5,4,1,6,9,2,7},
        {2,9,6,8,5,7,4,3,1},
        {4,1,7,2,9,3,6,5,8},
        {5,6,9,1,3,4,7,8,2},
        {1,2,3,6,7,8,5,4,9},
        {7,4,8,5,2,9,1,6,3},
        {6,5,2,7,8,1,3,9,4},
        {9,8,1,3,4,5,2,7,6},
        {3,7,4,9,6,2,8,1,5}};
    
    bool rc;
    for(int i=0; i < NUM_THREADS; i++ )
    {
        cout << "main() : creating thread, " << i << endl;
        rc = pthread_create(&threads[i], NULL,
                            isValid(grid), (void *)i);
    }
    
    //int grid [9][9];
    
    
    
    //readpuzzle (grid);
    if (!rc)
    cout << "Invalid input" << endl;
    else if (rc)
    printGrid(grid);
    else
    cout << "No solution" << endl;
    
    pthread_exit(NULL);
    return EXIT_SUCCESS;
}

/* void readpuzzle (int grid[][9])
 {
 {
 // Create a Scanner
 cout << "Enter a Sudoku puzzle:" << endl;
 for (int i = 0; i < 9; i++)
	for (int j = 0; j < 9; j++)
 cin >> grid[i] [j];
 }
 }
 */

bool isValid(int i, int j, int grid[] [9])
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
bool isValid(int grid[][9]) {
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
void printGrid(int grid[] [9])
{
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        cout << grid[i] [j] << " ";
        cout << endl;
    }
}
