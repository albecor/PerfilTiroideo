<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<spring:url value="Medical" var="title" />

<nav class="navbar navbar-toggleable-md navbar-inverse bg-primary">

	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<a class="navbar-brand" href="#">${title}</a>	

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto"></ul>		
		
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form class="form-inline my-2 my-lg-0 " action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button class="btn btn-primary my-2 my-sm-0 mr-auto" type="submit">Cerrar sesión</button>
		</form>
	</div>
</nav>
