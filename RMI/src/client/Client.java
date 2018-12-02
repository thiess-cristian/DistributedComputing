package client;

import java.awt.Point;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.ITriangleAngles;

public class Client {

	public static void main(String[] args) {
		try {	
			Registry reg=LocateRegistry.getRegistry(2088);
			ITriangleAngles stub=(ITriangleAngles)reg.lookup("Angles");
			
			Point p1 = new Point(1, 1);
			Point p2 = new Point(4, 2);
			Point p3 = new Point(2, 7);
			
			stub.printAngles(p1, p2, p3);
		}catch(Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

}
