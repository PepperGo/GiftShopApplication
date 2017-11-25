<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="peppergo.GiftShop.Bean.RegistrationBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Success</title>
</head>
<body>
<% 
	if (session == null)
  	{
    	String address = "/registrationConfirm.jsp";
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    	dispatcher.forward(request,response);
  	}
	else
	{
		String name=(String)session.getAttribute("USER");
		RegistrationBean bean=(RegistrationBean)request.getAttribute("bean");
		if(name == null || bean == null || name.compareTo(bean.getName()) != 0)
		{
	    	String address = "/login-error.jsp";
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
	    	dispatcher.forward(request,response);
		}
		out.print("Welcome, "+bean.getName());
	}
%>
<p>You are successfully register</p>
</body>
</html>