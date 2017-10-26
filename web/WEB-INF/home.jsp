<%@page import="model.Student"%>
<%
Student student = (Student) request.getAttribute("student");
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>
    <%@ include file="../styleInclude.html" %>
    

</head>

<body>
	
	<div>
		<h2>BMI Calculation</h2>
		<form action="BMI" method="POST">
			<p>Name: <input type="text" name="name" required/>
	    		<p>Height in cm: <input type="number" name="height" step="any" minimum="10" required/>
	    		<p>Weight in kg: <input type="number" name="weight" step="any" minimum="10" required/>
	    		<p><input type="submit" name="calculate" value="Calculate"/>
	    		
	    		<% if(student != null){ %>
	    		<p><%=student.getName() %>'s BMI is <%=student.getBMI() %></p>
	    		<% } %>
	    		
	    </form>
    </div>
</body>

</html>
