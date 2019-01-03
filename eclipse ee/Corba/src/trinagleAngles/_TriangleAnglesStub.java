package trinagleAngles;

/**
* trinagleAngles/_TriangleAnglesStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from angles.idl
* Thursday, January 3, 2019 10:05:40 AM EET
*/

public class _TriangleAnglesStub extends org.omg.CORBA.portable.ObjectImpl implements trinagleAngles.TriangleAngles
{

  public String computeAngles (double x1, double y1, double x2, double y2, double x3, double y3)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("computeAngles", true);
                $out.write_double (x1);
                $out.write_double (y1);
                $out.write_double (x2);
                $out.write_double (y2);
                $out.write_double (x3);
                $out.write_double (y3);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return computeAngles (x1, y1, x2, y2, x3, y3        );
            } finally {
                _releaseReply ($in);
            }
  } // computeAngles

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:trinagleAngles/TriangleAngles:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _TriangleAnglesStub
