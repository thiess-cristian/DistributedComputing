package sender;

import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSConsumer;

import java.awt.Point;
import java.util.Scanner;

public class JMSSender {
	private String msg, clientID, clientName, topicResult;

	JMSSender(String clientID, String clientName) {
		this.clientID = clientID;
		this.clientName = clientName;
		/*
		 * Scanner scanner = new Scanner(System.in);
		 * System.out.println("Introduceti m :"); long m = scanner.nextLong(); String
		 * sm= new Long(m).toString(); System.out.println("Introduceti n :"); long n
		 * =scanner.nextLong(); String sn = new Long(n).toString();
		 * System.out.println("Introduceti 'Topic'-ul raspunsului"); topicResult
		 * =scanner.next(); msg = sm + " " + sn + " " + topicResult;
		 */
		Point p1 = new Point(1, 1);
		Point p2 = new Point(4, 2);
		Point p3 = new Point(2, 7);

		topicResult = "Triangle";

		msg = String.format("%d %d %d %d %d %d %s", p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, topicResult);
	}

	private void service() {
		try {
			// Variant Oracle-Sun Message Topic
			com.sun.messaging.TopicConnectionFactory cf = new com.sun.messaging.TopicConnectionFactory();
			// cf.setProperty("imqBrokerHostName","host");
			// cf.setProperty("imqBrokerHostPort","7676");
			Topic t = new com.sun.messaging.Topic("Triangle");
			JMSContext ctx = cf.createContext();

			Topic t1 = new com.sun.messaging.Topic(topicResult);
			ctx.setClientID(clientID);
			JMSConsumer consumer = ctx.createDurableConsumer(t1, clientName);
			// JMSConsumer consumer = ctx.createSharedDurableConsumer(t,clientName);

			JMSProducer producer = ctx.createProducer();
			producer.send(t, msg);

			ctx.close();
		} catch (Exception e) {
			System.out.println("JMSException : " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage:");
			System.out.println("java MsgClientSender clientID clientName");
			System.exit(0);
		}
		JMSSender client = new JMSSender(args[0], args[1]);
		client.service();
	}
}
