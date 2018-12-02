package objects;

import java.awt.Point;
import java.rmi.RemoteException;

import interfaces.ITriangleAngles;

public class TriangleAngles implements ITriangleAngles{

	@Override
	public double computeAngle(Point p1, Point p2, Point p3) throws RemoteException {
		double p2p1 = Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2)); 
		double p3p1 = Math.sqrt(Math.pow(p1.x-p3.x,2)+Math.pow(p1.y-p3.y,2)); 
		double p2p3 = Math.sqrt(Math.pow(p3.x-p2.x,2)+Math.pow(p3.y-p2.y,2)); 
		
		return Math.acos((p3p1*p3p1+p2p1*p2p1-p2p3*p2p3)/(2*p3p1*p2p1))* 180 / Math.PI;	
	}

	@Override
	public void printAngles(Point p1, Point p2, Point p3) throws RemoteException {
		
		double angleP1=computeAngle(p1,p2,p3);
		double angleP2=computeAngle(p2,p3,p1);
		double angleP3=computeAngle(p3,p1,p2);
		
		String angles= String.format("%f %f %f", angleP1,angleP2,angleP3);
		
		System.out.println(angles);
	}

}
