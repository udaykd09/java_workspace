package color_detection;
	import java.awt.*;
	import java.awt.image.BufferedImage;

	import java.io.*;

	import javax.imageio.ImageIO;
	import javax.swing.JFrame;



public class rgb_average {

	   BufferedImage  image;
	   int width;
	   int height;
	   
	   public rgb_average() {
	   
	      try {
	         File input = new File("Green.jpg");
	         image = ImageIO.read(input);
	         width = image.getWidth();
	         height = image.getHeight();
	         int area = width*height; 
	         int sumR=0;
	         int sumG=0;
	         int sumB=0;
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               Color c = new Color(image.getRGB(j, i));
	               
	               int red = (int)(c.getRed() * 0.299);
	               int green = (int)(c.getGreen() * 0.587);
	               int blue = (int)(c.getBlue() *0.114);
	               sumR+=red;
	               sumG+=green;
	               sumB+=blue;
	            }
	         }
	         System.out.println("sumR="+(sumR/area)+", sumG="+(sumG/area)+", sumB="+(sumB/area));
	         
	      } catch (Exception e) {}
	   }
	   
	   static public void main(String args[]) throws Exception 
	   {
	      rgb_average obj = new rgb_average();
	   }
	}
