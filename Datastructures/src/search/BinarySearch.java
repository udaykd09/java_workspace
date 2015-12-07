package search;
public class BinarySearch {
public static final int FAILURE = -1;

public static int search(int[] i,int left, int right, int findme){
	if (left > right)
		return FAILURE;
	int mid = (left + right)/2;
	//System.out.println(mid);
	if (mid < i.length){
	if (findme == i[mid])
		return mid;
	else if (findme < i[mid])
		return search(i, left, mid-1, findme);
	else 
		return search(i, mid+1, right, findme);
	}
	else
		return FAILURE;
}
}
