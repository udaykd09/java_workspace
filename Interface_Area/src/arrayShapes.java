
public class arrayShapes {

public static void main(String[] args) {
	Shape[] array = new Shape[3];
	array[0] = new circle(2);
	array[1] = new rectangle(2,3);
	array[2] = new circle(2.5);
	
	for(int i=0; i<array.length; ++i){
		System.out.println(i+"A="+array[i].area() + "P ="+array[i].perimeter());
	}
}
}
