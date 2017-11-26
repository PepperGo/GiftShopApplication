<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="peppergo.GiftShop.Bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Success</title>
</head>
<body>

<% 
		String name=(String)session.getAttribute("userdata");
		out.print(name);
%>

<p>You are successfully register</p>
<p id="demo"></p>

<script>
var myObj = <%=name%>;
var myJSON = JSON.stringify(myObj);
document.getElementById("demo").innerHTML = myObj;

</script>
</body>
</html>