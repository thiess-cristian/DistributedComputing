package server;

import java.util.Hashtable;
import java.util.Scanner;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import logic.TriangleAngles;

public class Server {
	  public static void main(String[] args) {
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
		    //env.put(Context.SECURITY_AUTHENTICATION, "simple");
		    DirContext ctx = null;
		    System.out.println("Alegeti operatia : 1- bind; 2- unbind");
		    int oper=scanner.nextInt();
		    System.out.println("Introduceti valoarea atributului \"cn\" a obiectului ");
		    System.out.println("cn=");
		    String cmmdcObj=scanner.next().trim();
				
		    try {
		      ctx = new InitialDirContext(env);
		      if(oper==1){
		    	  TriangleAngles obj=new TriangleAngles();
		    	  String str="cn="+cmmdcObj;
		    	  ctx.bind(str,obj);
		      }
		      else{
		        ctx.unbind("cn="+cmmdcObj);
		      }
		      ctx.close();
		    }
		    catch (NamingException e) {
		      System.out.println("LDAPserverCmmdc :  "+e.getMessage());
		    }
		  }
}
