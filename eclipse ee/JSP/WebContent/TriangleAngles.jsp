<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%! String computeAngles(double x1, double y1, double x2, double y2, double x3, double y3) {
	double angleP1=computeAngle(x1, y1, x2,  y2, x3, y3);
	double angleP2=computeAngle(x2, y2, x3,  y3, x1, y1);
	double angleP3=computeAngle(x3, y3, x1,  y1, x2, y2);
	
	return String.format("%f %f %f", angleP1,angleP2,angleP3);
}

    double computeAngle(double x1, double y1, double x2, double y2, double x3, double y3) {
	double p2p1 = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)); 
	double p3p1 = Math.sqrt(Math.pow(x1-x3,2)+Math.pow(y1-y3,2)); 
	double p2p3 = Math.sqrt(Math.pow(x3-x2,2)+Math.pow(y3-y2,2)); 
	
	return Math.acos((p3p1*p3p1+p2p1*p2p1-p2p3*p2p3)/(2*p3p1*p2p1))* 180 / Math.PI;		
} %>
unghiuri:<br>
<%
	double x0=Double.parseDouble(request.getParameter("x0"));
	double y0=Double.parseDouble(request.getParameter("y0"));
	double x1=Double.parseDouble(request.getParameter("x1"));
	double y1=Double.parseDouble(request.getParameter("y1"));
	double x2=Double.parseDouble(request.getParameter("x2"));
	double y2=Double.parseDouble(request.getParameter("y2"));
	out.println(computeAngles(x0,y0,x1,y1,x2,y2));
%>
</body>
</html>