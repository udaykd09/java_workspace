import java.util.Scanner;

public class theBeast {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();

		for (int i = 0; i < T; i++) {
			long N = in.nextLong();
			int x=0;
			if(N%3==0){
				for(int j=0;j<N;j++){
				System.out.print("5");
				}
				System.out.println();
			}
			else if(N%5==0){
				for(int j=0;j<N;j++){
					System.out.print("3");
				}
				System.out.println();
			}
			else {
				long Z = N;
				do{
					if(Z<5){
						break;
					}
					Z = Z - 3;
					System.out.println(Z);
				}while(Z%5!=0);
				if(Z%5==0){
					for(int k=0;k<N-Z;k++){
						System.out.print("5");
					}
					for(int l=0;l<Z;l++){
						System.out.print("3");
					}
					System.out.println();
				}
				else{
					System.out.println("-1");
				}

			}
		}
	}
}
