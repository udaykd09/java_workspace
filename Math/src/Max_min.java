public class Max_min {
	public static void main(String[] args) {

		int a[] = { 0, -1, -1, -1, -1 };
		int n = 4, v0, v1, t0, i;
		v1 = v0 = a[0];
		while (n > 0) {
			for (i = 0; i <= n; i++) {
				System.out.println(a[i]);
				t0 = a[i];
				if (t0 < v0)
					v0 = t0;
				else if (t0 > v1)
					v1 = t0;
			}
			break;
		}
		System.out.println("max" + v1 + "min" + v0);
	}
}
