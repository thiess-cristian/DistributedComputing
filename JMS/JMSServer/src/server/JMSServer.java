package server;

import java.awt.Point;

import algorithm.TriangleAngles;

import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.JMSContext;
import javax.jms.JMSConsumer;
import javax.jms.JMSProducer;



public class JMSServer {

	public static void main(String args[]) {
		JMSServer server=new JMSServer();
	    server.service();
	}

	private void service() {
		try{
		      com.sun.messaging.TopicConnectionFactory cf=new com.sun.messaging.TopicConnectionFactory();
		      //cf.setProperty("imqBrokerHostName","host");
		      //cf.setProperty("imqBrokerHostPort","7676");
		      Topic t=new com.sun.messaging.Topic("Triangle"); 
		      JMSContext ctx=cf.createContext();
		      JMSConsumer consumer = ctx.createSharedConsumer(t,"Triangle");
		      JMSProducer producer = ctx.createProducer();
		      while(true){ 
		        Message msg=null;
		        while((msg=consumer.receive())!=null){
		          if(msg instanceof TextMessage){
		            TextMessage tm=(TextMessage)msg;
		            String s=tm.getText();
		            String[] ss=s.split(" ");
		        		  
		            Point p1=new Point(Integer.parseInt(ss[0]),Integer.parseInt(ss[1]));
		            Point p2=new Point(Integer.parseInt(ss[2]),Integer.parseInt(ss[3]));
		            Point p3=new Point(Integer.parseInt(ss[4]),Integer.parseInt(ss[5]));
		            
		            String angles= new TriangleAngles(p1,p2,p3).anglesToString();
		            
		            String topic=ss[6];
		            
		            Topic t1=new com.sun.messaging.Topic(topic); 
		            producer.send(t1,angles);
		            System.out.println("Server sent "+angles+" to "+topic);
		          }  
		        }  
		      }        	      
		    }
		    catch(Exception e){
		      System.out.println("JMSException : "+e.getMessage());
		    }		
	}
}
