<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
   <head>
      <title>Student Registration</title>
   </head>
   <body>
   <div align="center">
   <h1> Student Registration Form</h1>
   <c:url var="url" value="/register"/>
   	<form:form method="post" action="${url}">
	   	<table>
	   		<tr>
	   			<td>First Name :</td><td><input type ="text" name = "firstName" value=""/></td>
	   		</tr>
	   		<tr>
	   			<td>Last Name :</td><td><input type ="text" name = "lastName" value=""/></td>
	   		</tr>
	   		<tr>
	   			<td>Email :</td><td><input type ="text" name = "email" value=""/></td>
	   		</tr>
	   		<tr align="center">
	   			<td><button type="submit"> Register</button></td>
	   		</tr>
	   	</table>
	</form:form>
	<%-- <c:choose>
		<c:when test="${not empty msg}">
			<font color="green">
			Data has been saved Successfully!!!
		</font>
		</c:when>
		<c:otherwise>
			Failed! unable to save the data
		</c:otherwise>
	</c:choose> --%>
   	</div>
   </body>
</html>

<!-- <label>First Name :
			<input type ="text" name = "firstName" value=""/><br/>
		</label>
		<label>Last Name :
		<input type ="text" name = "lastName" value=""/><br/>
		</label>
		<label>Email :
		<input type ="text" name = "email" value=""/><br/>
		</label> -->

<%-- <div class="user-register__headline">
	<spring:theme code="register.new.customer" />
</div>
<p>
	<spring:theme code="register.description" />
</p>
 --%>
