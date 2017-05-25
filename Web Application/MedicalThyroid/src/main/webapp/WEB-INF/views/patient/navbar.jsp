<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<nav class="navbar navbar-toggleable-md navbar-inverse bg-primary">

	<button class="navbar-toggler navbar-toggler-right" type="button"
		data-toggle="collapse" data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<a class="navbar-brand" href="#"><i class="fa fa-stethoscope"></i></a>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">

		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><spring:url value="/patient"
					var="adminsUrl" /> <a class="nav-link" href="${adminsUrl}">Inicio
					<span class="sr-only">(current)</span>
			</a></li>			
		</ul>

		<ul class="navbar-nav">
			<li class="nav-item "><a class="nav-link" href="#">Paciente
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				role="button" aria-haspopup="true" aria-expanded="false"> <i
					class="fa fa-user-circle-o"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right">
					<spring:url value="/patient/ActualizarPaciente" var="updatePatientUrl" />

					<a class="dropdown-item" href="${updatePatientUrl}"><span
						class="text-primary"><i class="fa fa-edit"></i> Editar
							perfil</span></a>

					<div class="dropdown-divider"></div>

					<c:url value="/j_spring_security_logout" var="logoutUrl" />
					<form class="form-inline my-2 my-lg-0 " action="${logoutUrl}"
						method="post" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<button class="dropdown-item" type="submit">
							<i class="fa fa-sign-out"></i> Salir
						</button>
					</form>
				</div></li>

		</ul>
	</div>
</nav>
