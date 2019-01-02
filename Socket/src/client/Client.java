package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] arg) {

		try (Socket socket = new Socket("localhost", 5000)) {
			BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter stringEchoes = new PrintWriter(socket.getOutputStream(), true);

			Scanner scanner = new Scanner(System.in);
			String echoString="";
			String response="";

			do {

				System.out.println("Enter:");
				echoString = scanner.nextLine();
				stringEchoes.println(echoString);

				if (!echoString.equals("exit")) {
					response = echoes.readLine();
					System.out.println(response);
				}
			} while (!echoString.equals("exit"));

			scanner.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
