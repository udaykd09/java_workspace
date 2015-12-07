import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MatchString {

	public static void main(String args[]) throws FileNotFoundException {
		
		// Create Stacks (TreeSet, ArrayList)
		Stack<String> MyStack = new Stack<String>();
		Stack<String> MyStack_2 = new Stack<String>();

		// Add each word to Stack from file with 1000 words
		Scanner in = new Scanner(new File("words.txt"));
		String File_String = in.nextLine();
		String[] words_Array = File_String.split(" ");
		for (int i = 0; i < words_Array.length; i++){
			MyStack.push(words_Array[i]);
			MyStack_2.push(words_Array[i]);
		}
		
		MyStack.push("is");
		MyStack_2.push("is");
		
		
		// Record time
		final long startTime = System.nanoTime();

		// Call function using Tree passing = "is"
		match_String_Tree(MyStack, "is");

		// Record secondtime and duration for tree
		final long secondTime = System.nanoTime();
		final long duration = System.nanoTime() - startTime;
		System.out.println("Duration using Tree in Nanosecs : "+duration);
		
		// Call function using ArrayList passing = "is"
		match_String_List(MyStack_2, "is");
		
		// Record duration for ArrayList
		final long Final_duration = System.nanoTime() - secondTime;
		System.out.println("Duration using Tree in Nanosecs : "+ Final_duration);
	}

// Method to match stack words with given string and print them using Tree
	public static void match_String_Tree(Stack<String> stack, String input) {

		TreeSet<word_Object> check_Tree = new TreeSet<word_Object>(new word_Object("", 0));

		while (!stack.isEmpty()) {
			String check_String = stack.pop();
			if (check_String.contains(input)) {
				if (check_String.equals(input))
					check_Tree.add(new word_Object(check_String, Integer.MAX_VALUE));
				else
					check_Tree.add(new word_Object(check_String, check_String.length()));
			}
		}
		System.out.println("TreeSet output :");
		System.out.println(check_Tree.toString());

	}

// Method to match stack words with given string and print them using Tree
	public static void match_String_List(Stack<String> stack, String input) {

		ArrayList<word_Object> check_List = new ArrayList<word_Object>();

		while (!stack.isEmpty()) {
			String check_String = stack.pop();
			if (check_String.contains(input)) {
				if (check_String.equals(input))
					check_List.add(new word_Object(check_String, Integer.MAX_VALUE));
				else
					check_List.add(new word_Object(check_String, check_String.length()));
			}
		}

		Collections.sort(check_List,new word_Object("", 0));
		
		System.out.println();
		System.out.println("ArrayList output :");
		System.out.println(check_List.toString());

	}
}

// Object having the word(string) and weight for data structure
class word_Object implements Comparator<word_Object> {
	@Override
	public String toString() {
		return "[" + stack_string + ", " + Weight + "]";
	}

	String stack_string;
	int Weight;

	word_Object(String s, int w) {
		this.stack_string = s;
		this.Weight = w;
	}

	@Override
	public int compare(word_Object o1, word_Object o2) {
		return o1.Weight - o2.Weight;
	}
}
