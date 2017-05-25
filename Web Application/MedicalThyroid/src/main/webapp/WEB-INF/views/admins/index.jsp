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
<jsp:include page="../fragments/fontAwesome.jsp" />
</head>

<body>

	<jsp:include page="navbar.jsp" />
	<br />
	<jsp:include page="../fragments/alert.jsp" />
	<br />

	<div class="container">
	<br />
		<div class="jumbotron">
			<h1 class="display-4">Hola, ${admin.name}</h1>
			<p class="lead">Como administrador puedes gestionar a los
				usuarios y la información de la plataforma.</p>
			<hr class="my-4">
			<p>Si deseas ver como funciona la plataforma puedes descargar el
				manual.</p>
			<p class="lead">
				<a class="btn btn-primary btn-lg" href="https://drive.google.com/file/d/0By1VjmcVY702Y1NzY0Q5aF9DSkU/view?usp=sharing" role="button">Descargar</a>
				
			</p>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>