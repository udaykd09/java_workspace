package color_detection;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GrayScale {

   BufferedImage  image;
   int width;
   int height;
   
   public GrayScale() {
   
      try {
         File input = new File("Green.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         
         for(int i=height/4; i<3*height/4; i++){
         
            for(int j=width/4; j<3*width/4; j++){
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,
               
               red+green+blue,red+green+blue);
               System.out.println("red :"+red+",  green :"+green+",  blue :"+blue+"/n");
               image.setRGB(j,i,newColor.getRGB());
            }
         }
         
         File ouptut = new File("grayscale.jpg");
         ImageIO.write(image, "jpg", ouptut);
         
      } catch (Exception e) {}
   }
   
   static public void main(String args[]) throws Exception 
   {
      GrayScale obj = new GrayScale();
   }
}