<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="obj" class="logic.TriangleAngles" scope="application"/>
<jsp:setProperty name="obj" property="*"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Unghiurile triunghiului:
<%=obj.getAngles()%>
</body>
</html>