<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="peppergo.GiftShop.Bean.User"%>
<%@page import="com.google.gson.JsonObject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Information</title>

<style>
#editPart{
    display: none;
}

#notshow{
	display: none;
}
</style>


</head>
<body>
<p> Your Information </p>

<% 
        int userId = (Integer) session.getAttribute("userId");
		JsonObject jsonObj = (JsonObject) session.getAttribute("userdata");
		String userName = jsonObj.get("userName").getAsString();
        String email = jsonObj.get("email").getAsString();
		String phone = jsonObj.get("phoneNumber").getAsString();
		//out.print(email);
%>
<div id="displayPart">
<table>
  <tr>
    <th>Type</th>
    <th>Value</th>
  </tr>
  <tr>
    <td>Username</td>
    <td class="value"><%=userName %></td>
  </tr>
  <tr>
    <td>Email Address</td>
    <td class="value"><%=email%></td>
  </tr>
  <tr>
    <td>Phone Number</td>
    <td class="value"><%=phone %></td>
  </tr>
</table>

<button onclick="startToEdit()">Edit</button>
</div>


 <div id="editPart" >
	<form id="editForm" action="UserInformationServlet" method="post">
	    <div>userId:<input type="text" name="userId" value=<%=userId%>>
	    userName:<input type="text" name="userId" value=<%=userName%>>
	    </div>
		UserName:<%=userName %> <br>
		Email Address:<input type="text" name="password" value=<%=email%>><br>
		Phone Number:<input type="text" name="phone" value=<%=phone%>><br>
		<input type="submit" value="submit">
	</form>
	<button onclick="cancelEdit()">Cancel</button>
</div>	

<script>
function startToEdit() {
	var x = document.getElementById("displayPart")
    var y = document.getElementById("editPart");
	x.style.display = "none";
	y.style.display = "block";
}

function cancelEdit(){
	var x = document.getElementById("displayPart")
    var y = document.getElementById("editPart");
	x.style.display = "block";
	y.style.display = "none";
}

</script>

</body>
</html>