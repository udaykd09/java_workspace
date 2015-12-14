import java.util.Arrays;
import java.util.Comparator;

public class Zombit {

	public static void main(String[] args) {
		
		int[][] a = {{0,1},{1,2},{4,7}};
		check(a);
	}
	
	static int check(int[][] a){
		int x=0;
		int[] a1 = new int[a.length];
		int[] a2 = new int[a.length];
		for(int i = 0; i < a.length; i ++){
			a1[i] = a[i][0];
			a2[i] = a[i][1];
		}
		
		return x;
	}
}
