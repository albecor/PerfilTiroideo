<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="fragments/title.jsp" />
	<jsp:include page="fragments/css/bootstrap.jsp" />
	<jsp:include page="fragments/css/footer.jsp" />
	<jsp:include page="fragments/fontAwesome.jsp" />
</head>

<br />
<div class="container">
	<div class="jumbotron">
		
		<h1 class="display-4 text-center">Selecciona el perfil</h1>
		
		<p class="lead text-center">
		Puedes acceder como:
		</p>
		
		<hr class="my-4">

		<div class="row">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div class="col text-center">
				<spring:url value="/admins" var="adminsUrl" />	
				<a class="btn btn-primary btn-lg" href="${adminsUrl}"><i class="fa fa-user"></i> Administrador</a>
				<hr />
			</div>
</sec:authorize>

			
			
					<sec:authorize access="hasRole('ROLE_PATIENT')">
		<div class="col text-center">
				<spring:url value="/patient" var="patientUrl" />	
				<a class="btn btn-primary btn-lg" href="${patientUrl}"><i class="fa fa-wheelchair"></i> Paciente</a>
				<hr />
			</div>
</sec:authorize>

		
			
					<sec:authorize access="hasRole('ROLE_PERSONAL')">
		<div class="col text-center">
				<spring:url value="/personal" var="personalUrl" />	
				<a class="btn btn-primary btn-lg" href="${personalUrl}"><i class="fa fa-user-md"></i> Personal médico</a>
				<hr />
			</div>	
</sec:authorize>

		
			
					<sec:authorize access="hasRole('ROLE_LAB')">
	<div class="col text-center">
				<spring:url value="/lab" var="labUrl" />	
				<a class="btn btn-primary btn-lg" href="${labUrl}"><i class="fa fa-medkit"></i> Laboratorista</a>
				<hr />
			</div>	
</sec:authorize>

					
		</div>
	</div>
</div>

<jsp:include page="fragments/footer.jsp" />

</body>
</html>