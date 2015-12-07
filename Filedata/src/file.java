import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class file {
public static void main(String[] args) throws IOException {
	File abc = new File("bca.txt");
	PrintWriter p = new PrintWriter(abc);
	p.println("I am Uday");
	p.close();
	
	Scanner s = new Scanner(abc);
	String ss = s.nextLine();
	System.out.println(ss);
}
}
