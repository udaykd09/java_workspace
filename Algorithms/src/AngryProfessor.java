import java.util.*;

public class AngryProfessor {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();

		for (int i = 0; i < T; i++) {
			int N = in.nextInt();
			int K = in.nextInt();
			int counter = 0;
			for (int j = 0; j < N; j++) {
				int temp = in.nextInt();
				if (temp <= 0) {
					counter++;
				}
			}
				if (counter >= K) {
					System.out.println("No");
				} else
					System.out.println("Yes");
		}

	}

}
