<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="../fragments/title.jsp" />
	<jsp:include page="../fragments/css/bootstrap.jsp" />
	<jsp:include page="../fragments/css/footer.jsp" />
</head>

<br />
<div class="container">
	<div class="jumbotron">
		
		<h1 class="display-4">El administrador ha sido creado!</h1>
		
		<p class="lead">
			<strong>
				<c:if test="${not empty name}">${name}</c:if>
			</strong>, ahora puedes iniciar sesión con tu numero de documento y contraseña
		</p>
		
		<hr class="my-4">
		
		<p>Como administrador puedes controlar la adición de los usuario a la plataforma.</p>
		
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<p class="lead">	
			<form class="form-inline my-2 my-lg-0 " action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button class="btn btn-primary btn-lg" type="submit">Aceptar</button>
			</form>
		</p>		
	</div>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>