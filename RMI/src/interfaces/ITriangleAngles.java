package interfaces;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITriangleAngles extends Remote{

	public double computeAngle(Point p1,Point p2,Point p3 )throws RemoteException;
	public void printAngles(Point p1,Point p2,Point p3)throws RemoteException;
}
