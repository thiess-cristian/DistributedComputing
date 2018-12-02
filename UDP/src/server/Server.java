package server;

import java.awt.Point;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import algorithm.TriangleAngles;

public class Server {
	 public static void main(String[] args) {
	        try {
	            DatagramSocket socket = new DatagramSocket(5000);
	            
	            while(true) {
	                byte[] buffer = new byte[50];
	                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	                socket.receive(packet);
	                
	                String receivedData=new String(buffer, 0, packet.getLength());
	                System.out.println("Text received is: " + receivedData);
	 
	                String[] points=receivedData.split(" ");
	                
	                Point p1=new Point(Integer.parseInt(points[0]),Integer.parseInt(points[1]));
		            Point p2=new Point(Integer.parseInt(points[2]),Integer.parseInt(points[3]));
		            Point p3=new Point(Integer.parseInt(points[4]),Integer.parseInt(points[5]));
	                
	                TriangleAngles t=new TriangleAngles(p1,p2,p3);
	                
	                String returnString = "echo: " + t.anglesToString();
	                byte[] buffer2 = returnString.getBytes();
	                InetAddress address = packet.getAddress();
	                int port = packet.getPort();
	                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
	                socket.send(packet);
	            }
	        } catch(SocketException e) {
	            System.out.println("SocketException: " + e.getMessage());
	        } catch(IOException e) {
	            System.out.println("IOException: " + e.getMessage());
	        }
	    }
}
