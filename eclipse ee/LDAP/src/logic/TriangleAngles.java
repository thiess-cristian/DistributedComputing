package logic;

public class TriangleAngles implements java.io.Serializable{
	public String computeAngles(double x1, double y1, double x2, double y2, double x3, double y3) {
		double angleP1=computeAngle(x1, y1, x2,  y2, x3, y3);
		double angleP2=computeAngle(x2, y2, x3,  y3, x1, y1);
		double angleP3=computeAngle(x3, y3, x1,  y1, x2, y2);
		
		return String.format("%f %f %f", angleP1,angleP2,angleP3);
	}
	
	private double computeAngle(double x1, double y1, double x2, double y2, double x3, double y3) {
		double p2p1 = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)); 
		double p3p1 = Math.sqrt(Math.pow(x1-x3,2)+Math.pow(y1-y3,2)); 
		double p2p3 = Math.sqrt(Math.pow(x3-x2,2)+Math.pow(y3-y2,2)); 
		
		return Math.acos((p3p1*p3p1+p2p1*p2p1-p2p3*p2p3)/(2*p3p1*p2p1))* 180 / Math.PI;		
	}
}
