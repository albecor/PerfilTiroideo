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
			<li class="nav-item active"><spring:url value="/admins"
					var="adminsUrl" /> <a class="nav-link" href="${adminsUrl}">Inicio
					<span class="sr-only">(current)</span>
			</a></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				role="button" aria-haspopup="true" aria-expanded="false">Personal</a>
				<div class="dropdown-menu">
					<spring:url value="/admins/InsertarPersonal"
						var="insertPersonalUrl" />
					<a class="dropdown-item" href="${insertPersonalUrl}"><span
						class="text-primary"><i class="fa fa-plus"></i> Agregar </span></a>
					<spring:url value="/admins/consulta/personal"
						var="updatePersonalUrl" />
					<a class="dropdown-item" href="${updatePersonalUrl}"><span
						class="text-primary"><i class="fa fa-pencil"></i> Editar</span></a>
					<div class="dropdown-divider"></div>
					<spring:url value="/admins/consulta/personal"
						var="deletePersonalUrl" />
					<a class="dropdown-item" href="${deletePersonalUrl}"><i
						class="fa fa-times"></i> Eliminar</a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				role="button" aria-haspopup="true" aria-expanded="false">Pacientes</a>
				<div class="dropdown-menu ">
					<spring:url value="/admins/InsertarPaciente" var="insertPatientUrl" />
					<a class="dropdown-item" href="${insertPatientUrl}"><span
						class="text-primary"><i class="fa fa-plus"></i> Agregar</span></a>
					<spring:url value="/admins/consulta/paciente"
						var="updatePatientUrl" />
					<a class="dropdown-item" href="${updatePatientUrl}"><span
						class="text-primary"><i class="fa fa-pencil"></i> Editar</span></a>
					<div class="dropdown-divider"></div>
					<spring:url value="/admins/consulta/paciente"
						var="deletePatientUrl" />
					<a class="dropdown-item" href="${deletePatientUrl}"><i
						class="fa fa-times"></i> Eliminar</a>
				</div></li>
				
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				role="button" aria-haspopup="true" aria-expanded="false">Laboratorios</a>
				<div class="dropdown-menu ">
					<spring:url value="/admins/InsertarLaboratorio" var="insertLabUrl" />
					<a class="dropdown-item" href="${insertLabUrl}"><span
						class="text-primary"><i class="fa fa-plus"></i> Agregar</span></a>
					<spring:url value="/admins/consulta/lab"
						var="updateLabUrl" />
					<a class="dropdown-item" href="${updateLabUrl}"><span
						class="text-primary"><i class="fa fa-pencil"></i> Editar</span></a>
					<div class="dropdown-divider"></div>
					<spring:url value="/admins/consulta/lab"
						var="deleteLabUrl" />
					<a class="dropdown-item" href="${deleteLabUrl}"><i
						class="fa fa-times"></i> Eliminar</a>
				</div></li>
		</ul>


		<ul class="navbar-nav">
			<li class="nav-item "><a class="nav-link" href="#">Administrador
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				role="button" aria-haspopup="true" aria-expanded="false"> <i
					class="fa fa-user-circle-o"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right">
					<spring:url value="/admins/ActualizarAdministrador" var="updateAdminUrl" />

					<a class="dropdown-item" href="${updateAdminUrl}"><span
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
