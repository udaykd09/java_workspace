package search;

public class multiDArray {

	public static void main(String[] args){
		
		//int a1[],b1,c1[][];
		int[] Array2 = {1,2,3,4,5};
		//int[][] Array3 = {{1},{2},{1,2}};
		//multiDArray a = new multiDArray();
		BinarySearch a = new BinarySearch();
		int n=5;
		int[][] b=new int[n][];
		b = PascalTrian(n);
		for (int j = 0; j < b.length ; j++) {
			int[] arr = new int[b[j].length];
		for (int i = 0;  i < b[j].length; i++) {
			 arr[i] = b[j][i]; 
			
		}
			int Bingos = a.search(arr, 0, b.length, 1);
			if (Bingos != -1){
				System.out.println("Row"+ j + "Column" + Bingos);
			}
		}	
//		int Bingo = a.search(Array2, 1 , 2, 4);
//		System.out.println("Search findme = " + Bingo);
	}
	public static int[][] PascalTrian(int n){
		int i,j;
		int[][] Array = new int[n][];
		
		for(i=0;i<n;i++){
			Array[i] = new int[i+1];
			Array[i][0]=1;
			for(j=1;j<i;j++){
			Array[i][j]= Array[i-1][j-1] + Array[i-1][j];	
			}
			Array[i][i]=1;
			}
		for(i=0;i<n;i++){
			for(j=0;j<=i;j++){
		System.out.print(Array[i][j]);
			}
			System.out.println("");
		}
		return Array;
		}
		
		
		
	}
