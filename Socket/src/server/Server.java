package server;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import algorithm.TriangleAngles;

public class Server {
	
	private static String coords;

	public static void main(String[] srgs) {

		try (ServerSocket serverSocket = new ServerSocket(5000)) {

			Socket socket = serverSocket.accept();
			System.out.println("Client connected");
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				String echoString = input.readLine();

				if (echoString.equals("exit")) {
					break;
				}
				coords=echoString;
				

				if (!coords.equals("")) {
				 	String[] points=coords.split(" ");
		                
	                Point p1=new Point(Integer.parseInt(points[0]),Integer.parseInt(points[1]));
		            Point p2=new Point(Integer.parseInt(points[2]),Integer.parseInt(points[3]));
		            Point p3=new Point(Integer.parseInt(points[4]),Integer.parseInt(points[5]));
	                
	                TriangleAngles t=new TriangleAngles(p1,p2,p3);
					coords="";
					output.println("Echo from server: " + t.anglesToString());
				}else {
					output.println("Coords: x1,y1,x2,y2,x3,y3");
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
