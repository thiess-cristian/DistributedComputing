package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.ITriangleAngles;
import objects.TriangleAngles;

public class Server {

	public static void main(String[] args) {
	try {
			
			TriangleAngles obj=new TriangleAngles();
			ITriangleAngles stub=(ITriangleAngles) UnicastRemoteObject.exportObject(obj,2088);
			
			Registry reg=LocateRegistry.createRegistry(2088);
			
			reg.bind("Angles", stub);
			System.out.println("server on 2088");
			
		}catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}	

	}

}
