<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Student</title>
<link href="css/siteStyles.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	type="text/javascript"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="js/form-validation.js" type="text/javascript"></script>
</head>
<body>

	
	<c:if test="${messages.failure != null}">
		<span class="error">${messages.failure}</span>
	</c:if>
	
	<form action="StudentController" method="post" name="registration">
		<fieldset>
			 <div>
				<label for="studentId">Student ID</label> <input type="hidden"
					name="studentId" value="<c:out value="${student.studentId}" />"
					 />
			</div> 
			
			 
			<div>
				<label for="firstName">First Name</label> <input type="text"
					name="firstName" value="<c:out value="${student.firstName}" />"
					placeholder="First Name" id="firstName" /> <span class="error">${messages.errorUerName}</span>
			</div>
			<div>
				<label for="lastName">Last Name</label> <input type="text"
					name="lastName" value="<c:out value="${student.lastName}" />"
					placeholder="Last Name" />
			</div>
			<div>
				<label for="course">Course</label> <input type="text" name="course"
					value="<c:out value="${student.course}" />" placeholder="Course" />
			</div>
			<div>
				<label for="year">Year</label> <input type="text" name="year"
					value="<c:out value="${student.year}" />" placeholder="Year" />
			</div>
			<input type="date" name="bday">
			<div>
				<input type="submit" value="Submit" />
			</div>
		</fieldset>
	</form>
</body>

</html>




