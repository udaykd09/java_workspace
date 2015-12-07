public class Strings {

	static boolean isunique(String s) {
		boolean[] c = new boolean[256];
		for (int i = 0; i < s.length(); i++) {
			int x = s.charAt(i);
			if (c[x])
				return false;
			c[x] = true;
		}
		return true;
	}

	static void my_reverse(String s) {
		String s1 = "";
		for (int j = s.length() - 1; j >= 0; j--) {
			s1 += s.charAt(j);
		}
		System.out.println(s1);
	}

	static void remove_duplicate(String word) {
		String result = new String("");

		for (int i = 0; i < word.length(); i++) {
			if (!result.contains("" + word.charAt(i))) {
				result += "" + word.charAt(i);
			}
		}
		System.out.println(result);
	}

	static boolean is_anagram(String word1, String word2) {
		System.out.println(sort(word1) + "  " + sort(word2));
		if (sort(word1).equals(sort(word2)))
			return true;
		return false;

	}

	static public String sort(String s) {
		char[] c = s.toCharArray();
		String s1 = "";
		for (int i = 0; i < s.length(); i++) {
			int x = i;
			for (int j = i + 1; j < s.length(); j++) {
				if (c[i] >= c[j]) {
					char temp = c[j];
					c[j] = c[i];
					c[i] = temp;
				}
			}
			s1 += c[i];
		}
		return s1;
	}

	void check(int num){
		int n = num;
		int rev = 0,dig;
		while(num!=0){
			dig = num%10;
			rev = rev*10 + dig;
			num = num/10;
		}
		if (rev == n){
			System.out.println("Palindrome");
		}
		else System.out.println("Not a palindrome");
	}
	
	public static void main(String[] args) {

		// String s = "auday";
		// String s1 = "uaday";
		// if (isunique(s))
		// System.out.println("Unique");
		// else
		// System.out.println("Not Unique");
		// my_reverse(s);
		// remove_duplicate(s);
		// boolean x = is_anagram(s, s1);
		// System.out.println(x);
//FIZZBIZZ
//		for (int i = 1; i <= 100; i++) {
//			if (i % 5 == 0 && i % 7 == 0) {
//				System.out.println(i + "Fizzbizz");
//			} else if (i % 5 == 0) {
//				System.out.println(i + "Fizz");
//			} else if (i % 7 == 0) {
//				System.out.println(i + "bizz");
//			}
//		}
		
		Strings sd = new Strings();
		int ss = 12231;
		sd.check(ss);
	}
}
