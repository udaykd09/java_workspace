package filerdwr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class filerdwr {
public static void main(String[] args) {
	try{
	File file = new File(args[1]);
	BufferedReader r = new BufferedReader(new FileReader(file));
	BufferedWriter w = new BufferedWriter(new FileWriter(file));
	
	r.read();
	
	
	
	}
	catch (IOException io){
		
	};
}

void read(String file) {

}

void write(String file, byte b) {

}
}