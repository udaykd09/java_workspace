package org.uday;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class Application {
	public static void main(String[] args){
		//Traingle t = new Traingle();
		//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Triangle t = (Triangle)context.getBean("triangle");
		t.draw();
		
	}
}
