package client;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import trinagleAngles.*;
import java.util.Scanner;

public class Client {
	
	static TriangleAngles triangleAnglesImpl;
	
	public static void main(String args[]){
	    try{
	        // create and initialize the ORB
		    ORB orb = ORB.init(args, null);

	        // get the root naming context
	        org.omg.CORBA.Object objRef = 
		    orb.resolve_initial_references("NameService");
	        // Use NamingContextExt instead of NamingContext. This is 
	        // part of the Interoperable naming Service.  
	        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	 
	        // resolve the Object Reference in Naming
	        String name = "TriangleAnglesService";
	        triangleAnglesImpl = TriangleAnglesHelper.narrow(ncRef.resolve_str(name));

	        System.out.println("Obtained a handle on server object: " + triangleAnglesImpl);
	        Scanner scanner=new Scanner(System.in);
	       
		      
		      double[] a=new double[6];
		      
		      for(int i=0;i<6;i+=2) {
		    	  System.out.println("x"+i+"=");
		    	  a[i]=scanner.nextDouble();
		    	  System.out.println("y"+i+"=");
		    	  a[i+1]=scanner.nextDouble();
		      }
		      
		      System.out.println(triangleAnglesImpl.computeAngles(a[0], a[1], a[2], a[3], a[4], a[5]));


		} catch (Exception e) {
	          System.out.println("ERROR : " + e) ;
		  e.printStackTrace(System.out);
		  }
	    }
}
