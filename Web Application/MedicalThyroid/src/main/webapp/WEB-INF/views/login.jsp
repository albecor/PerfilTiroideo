<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>

<head>

<jsp:include page="fragments/title.jsp" />
<jsp:include page="fragments/css/bootstrap.jsp" />
<jsp:include page="fragments/css/signin.jsp" />
<jsp:include page="fragments/css/footer.jsp" />
<jsp:include page="fragments/fontAwesome.jsp" />

</head>


<body>
	<div class="container">
		<br />
		<div class="display-4 text-center">Perfil Tiroideo</div>
		<br />
			<div class="row justify-content-center">
				<div class="col col-auto">
					<i class="fa fa-stethoscope fa-5x " aria-hidden="true"></i>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-10 col-auto">
					<hr class="my-4">
					<p class="lead text-justify"><strong>Plataforma para el manejo de información
						médica, de los pacientes y el personal. A través del sistema
						podrás consultar, registrar o autorizar laboratorios médicos de
						acuerdo a tu perfil como usuario.</strong></p>
					<div class="row">
						<div class="col-6 ">
							<img src="<c:url value="/resources/images/LUNIVERSITY.png"/>"
								class="img-fluid mx-auto d-block" alt="Responsive image">
						</div>
						<div class="col-6">
							<img src="<c:url value="/resources/images/LGTST2.png"/>"
								class="img-fluid mx-auto d-block" alt="Responsive image">
						</div>
					</div>
				</div>
			</div>		
		<br />
		<div class="row justify-content-center">
			<div class="col col-auto">
				<p>
					<a class="btn btn-primary" data-toggle="collapse" href="#seccion"
						data-target="#collapseExample" aria-expanded="false"
						aria-controls="collapseExample"> Iniciar sesión </a>
				</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="collapse ${not empty error || not empty msg ? 'show' : ''  }" id="collapseExample">

				<div class="row">
					<form class="form-signin ${not empty error ? 'has-danger' : ''}"
						name='loginForm'
						action="<c:url value='/j_spring_security_check' />" method='POST'>

						<h2 class="form-signin-heading">Iniciar sesión</h2>

						<label for="inputUsername" class="sr-only">Usuario:</label> <input
							type="text" id="inputUsername" name='username'
							class="form-control" placeholder="Escribe tu usuario" required
							autofocus> <label for="inputPassword" class="sr-only">Contraseña</label>
						<input type="password" id="inputPassword" name='password'
							class="form-control" placeholder="Escribe tu contraseña" required>

						<button class="btn btn-lg btn-primary btn-block" name="submit"
							type="submit" value="submit">Iniciar sesión</button>
					<spring:url value="/RecoverPassword" var="RecoverPasswordUrl" />
					<a href="${RecoverPasswordUrl}"><span
						class="text-primary">recuperar contraseña</span></a>
						<c:if test="${not empty error}">
							<div class="form-control-feedback text-center">${error}</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="form-control-feedback text-center">${msg}</div>
						</c:if>

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
			</div>
		</div>
		<br />
	</div>
	<!-- /container -->

<div class="row">
		<div class="col text-center">
				<a href="https://drive.google.com/file/d/0By1VjmcVY702Y1NzY0Q5aF9DSkU/view?usp=sharing" ><i class="fa fa-cloud-download" aria-hidden="true"></i> Manual de usuario</a>

		</div>
		
		</div>

	<jsp:include page="fragments/footer.jsp" />
	<c:if test="${not empty error || not empty msg}">
	
	<script>		
		window.scrollTo(0,document.body.scrollHeight);
	</script>
	
	</c:if>
	
	<script>
		$(".collapse").on("shown.bs.collapse", function() {
			$("html, body").animate({
				scrollTop : $(document).height()
			}, 1000);
			document.getElementById("inputUsername").focus();
		});

		window.scrollTo(0,document.body.scrollHeight);
	</script>
</body>
</html>