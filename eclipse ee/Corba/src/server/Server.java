package server;

import java.util.Properties;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Policy;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NameComponent;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import trinagleAngles.TriangleAngles;
import trinagleAngles.TriangleAnglesHelper;

import org.omg.PortableServer.LifespanPolicyValue;

public class Server {

	 public static void main(String args[]) {
		    try{
		      // Crease si initializare ORB
		      ORB orb = ORB.init(args,null);

		      // Obtinerea unei referinte POA si activarea gestionarului POAManager
		      POA rootPOA=POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		      rootPOA.the_POAManager().activate();

		      // Crearea unui servant
		      TriangleAnglesImpl cmmdcImpl = new TriangleAnglesImpl(orb);

		      // Obtinerea unei referinte pentru servant
		      org.omg.CORBA.Object ref=rootPOA.servant_to_reference(cmmdcImpl);
		      TriangleAngles href = TriangleAnglesHelper.narrow(ref);
		    
		      // Obtinerea serviciului NameService
		      org.omg.CORBA.Object objRef =
		          orb.resolve_initial_references("NameService");
		      NamingContextExt ncRef=NamingContextExtHelper.narrow(objRef);

		      // Legarea servantului in NameService
		      String name = "TriangleAnglesService";
		      NameComponent path[] = ncRef.to_name(name);
		      ncRef.rebind(path,href);

		      System.out.println("CmmdcServer ready and waiting ...");

		      // Gata pentru satisfacerea clientilor
		      orb.run();
		    } 
		    catch (Exception e) {
		      System.err.println("ERROR: " + e.getMessage());
		    }
		    System.out.println("TriangleAnglesService server Exiting ...");
		  }
}
