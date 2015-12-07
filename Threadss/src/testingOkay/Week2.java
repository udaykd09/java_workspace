package testingOkay;

public class Week2 {
public static void main(String[] args) {

	int[] A1 = {1,2,3};
	int[] A2 = new int[4];
	A2 = A1;
	A1[4] = 1;
	System.out.println(A2.length);
	//System.out.println(args[0] + args[1]);
//	float[] score= new float[4];
//	for(int i=0;i<=5;i++){
//		score[i]=Float.valueOf(args[i]).floatValue();
//		}
//	float avg = avgScore(score);
//	char grade = grade(avg);
//	System.out.println("avg" + avg);
//	System.out.println("grade" + grade);
//
}
//static float avgScore(float[] scores){
//	float sum = 0;
//	for(int i=1;i<=5;i++){
//	sum = sum + scores[i];
//	}
//	
//	return sum/5;
//}
//
//static char grade(float avgScore){
//	char grade;
//	if (avgScore>=90)
//		grade = 'A';
//	else grade = 'B';
//	return grade;
//}
//
////String Critique(char grade){
////	
////}
}