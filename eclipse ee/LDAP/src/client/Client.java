package client;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import logic.TriangleAngles;

import java.util.Scanner;

public class Client {
	public static void main(String[] args){
	    Scanner scanner=new Scanner(System.in);
	    System.out.println("Alegeti furnizorul LDAP: ");
	    System.out.println("1: OpenDS");
	    System.out.println("2: Apache Directory Service");
	    int provider=scanner.nextInt();
	    Hashtable env = new Hashtable();
	    env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
	    if(provider==1){
	      env.put(Context.PROVIDER_URL,"ldap://localhost:389/dc=example,dc=com");
	      env.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager"); 
	      env.put(Context.SECURITY_CREDENTIALS, "1q2w3e");
	    }
	    else{ 
	      env.put(Context.PROVIDER_URL,"ldap://localhost:10389/uid=admin,ou=system"); 
	      env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system"); 
	      env.put(Context.SECURITY_CREDENTIALS, "secret"); 
	    }
	    DirContext ctx = null;
	    try {
	      ctx=new InitialDirContext(env);
	      if (ctx != null) {
	        System.out.println("Introduceti valoarea atributului \"cn\" a obiectului triangleAngles");
	        System.out.println("cn=");
	        String cmmdcObj=scanner.next().trim();
  			Object object = ctx.lookup("cn="+cmmdcObj);  			
  			double[] a=new double[6];
			      
	        for(int i=0;i<6;i+=2) {
	        	System.out.println("x"+i+"=");
	    	    a[i]=scanner.nextDouble();
	    	    System.out.println("y"+i+"=");
	    	    a[i+1]=scanner.nextDouble();
	        }
	        TriangleAngles obj=(TriangleAngles) object;
            System.out.println("Rezultatul este: "+obj.computeAngles(a[0], a[1], a[2], a[3], a[4], a[5]));
	      }
	    } 
	    catch (NamingException e) {
	      System.out.println("LDAPClient :  "+e.getMessage());
	    }
	  }
}
