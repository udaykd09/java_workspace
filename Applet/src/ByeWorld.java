import java.applet.Applet;
import java.awt.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;



public class ByeWorld extends Applet{

public void paint(Graphics g){
	Font f = new Font("System" , Font.BOLD, 18);
	g.setFont(f);
	String in = getParameter("hi");
	g.drawString("This" + in , 25, 50);
}
	
}

