import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TimeConversion {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String s1 = s.substring(8,10);
        if(s1.equals("PM")){
        	if(s.substring(0,2).equals("12")){
        		System.out.println(s.substring(0,8));
        	}
        	else{
            int x = Integer.parseInt(s.substring(0,2)) + 12;
            String s2 = Integer.toString(x);
            s2 = s2 + s.substring(2,8);
            System.out.print(s2);
        	}
        }
        else{
        	if(s.substring(0,2).equals("12")){
        		System.out.println("00"+s.substring(2,8));
        	}
        	else{
            System.out.print(s.substring(0,8));
        	}
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}