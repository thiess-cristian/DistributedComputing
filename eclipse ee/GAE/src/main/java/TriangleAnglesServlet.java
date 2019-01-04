import java.awt.Point;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "TriangleAngles",
    urlPatterns = {"/servlet"}
)
public class TriangleAnglesServlet extends HttpServlet {

	private static final long serialVersionUID =102831973239L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TriangleAnglesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletOutputStream out=res.getOutputStream();
		
		int x0=Integer.parseInt(req.getParameter("x0"));
		int y0=Integer.parseInt(req.getParameter("y0"));
		int x1=Integer.parseInt(req.getParameter("x1"));
		int y1=Integer.parseInt(req.getParameter("y1"));
		int x2=Integer.parseInt(req.getParameter("x2"));
		int y2=Integer.parseInt(req.getParameter("y2"));
		
		String angles=anglesToString(new Point(x0,y0),new Point(x1,y1),new Point(x2,y2));
		
		String tip=req.getParameter("tip");
		
		if(tip.equals("text/html")) {
			res.setContentType("text/html");
			out.println("<html><head><title>angles</head></title>");
			out.println("<body>");
			out.println("<h1>angles</h1>");
			out.println(angles);
			out.println("</body>");
			out.println("</html>");
		}else {
			res.setContentType("text/plain");
			out.print("agnles:"+angles);
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private double computeAngle(Point p1,Point p2,Point p3 ) {
		double p2p1 = Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2)); 
		double p3p1 = Math.sqrt(Math.pow(p1.x-p3.x,2)+Math.pow(p1.y-p3.y,2)); 
		double p2p3 = Math.sqrt(Math.pow(p3.x-p2.x,2)+Math.pow(p3.y-p2.y,2)); 
		
		return Math.acos((p3p1*p3p1+p2p1*p2p1-p2p3*p2p3)/(2*p3p1*p2p1))* 180 / Math.PI;		
	}
	
	public String anglesToString(Point p1,Point p2,Point p3) {
		double angleP1=computeAngle(p1,p2,p3);
		double angleP2=computeAngle(p2,p3,p1);
		double angleP3=computeAngle(p3,p1,p2);
		
		return String.format("%f %f %f", angleP1,angleP2,angleP3);
	}
}