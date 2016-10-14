<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>PERSON DETAILS: </h1>
<c:forEach var="item" items="${personList}">
<h2>${item.email} ${item.firstName} ${item.lastName} ${item.phoneNumber}</h2>
</c:forEach>
<form method="post"action="HomeController">
	<h3>FIRST NAME</h3><br>
	<input type="text" name="fName"></input>
	
	<h3>LAST NAME</h3><br>
	<input type="text" name="lName"></input>
	
	<h3>EMAIL</h3><br>
	<input type="text" name="email"></input>

	<h3>PHONE NUMBER</h3><br>
	<input type="text" name="pNumber"></input>

<button type="submit">SUBMIT</button>
</form>
<br>
<br>
<br>
<form method="post"action="HomeController">
	<h3>FIRST NAME</h3><br>
	<input type="text" name="firstName"></input>
	
	<h3>LAST NAME</h3><br>
	<input type="text" name="lastName"></input>
	
	<input type="hidden"name="query" value="true"/>
	
	<button type="submit">SUBMIT</button>
	
</form>

</body>
</html>