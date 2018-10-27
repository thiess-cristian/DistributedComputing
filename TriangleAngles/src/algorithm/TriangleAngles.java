package algorithm;

import java.awt.Point;
import java.lang.Math;

public class TriangleAngles {

	public TriangleAngles(Point p1,Point p2,Point p3) {
		m_p1=p1;
		m_p2=p2;
		m_p3=p3;
	}
	
	public String anglesToString() {
		double angleP1=computeAngle(m_p1,m_p2,m_p3);
		double angleP2=computeAngle(m_p2,m_p3,m_p1);
		double angleP3=computeAngle(m_p3,m_p1,m_p2);
		
		return String.format("%f %f %f", angleP1,angleP2,angleP3);
	}
	
	private double computeAngle(Point p1,Point p2,Point p3 ) {
		double p2p1 = Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2)); 
		double p3p1 = Math.sqrt(Math.pow(p1.x-p3.x,2)+Math.pow(p1.y-p3.y,2)); 
		double p2p3 = Math.sqrt(Math.pow(p3.x-p2.x,2)+Math.pow(p3.y-p2.y,2)); 
		
		return Math.acos((p3p1*p3p1+p2p1*p2p1-p2p3*p2p3)/(2*p3p1*p2p1))* 180 / Math.PI;		
	}
	
	private Point m_p1;
	private Point m_p2;
	private Point m_p3;
}
