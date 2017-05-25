<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>

<head>

	<jsp:include page="../fragments/title.jsp" />
	<jsp:include page="../fragments/css/bootstrap.jsp" />	
	<jsp:include page="../fragments/css/footer.jsp" />
	<jsp:include page="../fragments/fontAwesome.jsp" />

</head>

<body>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<jsp:include page="../admins/navbar.jsp" />
	</sec:authorize>
	<br />
	<jsp:include page="../fragments/alert.jsp" />
	<br />
	<div class="container">	
		<div class="card">
			<div class="card-block">
			
				<div class="row">
					<div class="col-12 col-md-6">
						
						<h5>Información personal</h5>
						<hr />		
						
						<div class="row">
							<div class="col text-md-right"><strong>Nombre: </strong></div>
							<div class="col">${personal.given} ${personal.family}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>${personal.ndi}: </strong></div>
							<div class="col">${personal.ndivalue}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Género: </strong></div>
							<div class="col">${personal.gender}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Centro de salud: </strong></div>
							<div class="col">${personal.managingOrganization}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Profesión: </strong></div>
							<div class="col">${personal.role}</div>
						</div>
					
					</div>
					<div class="col-12 col-md-6">
						<h5>Datos de contacto</h5>
						<hr />		
						
						<div class="row">
							<div class="col text-md-right"><strong>Teléfono móvil: </strong></div>
							<div class="col">${personal.telmobile}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Teléfono de oficina: </strong></div>
							<div class="col">${personal.telwork}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Correo: </strong></div>
							<div class="col">${personal.email}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Oficina: </strong></div>
							<div class="col">${personal.line}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Ciudad: </strong></div>
							<div class="col">${personal.city}</div>
						</div>					
					</div>
				</div>
				
				<hr />
				<div class="row">
					<div class="col-12 col-md-4"><strong>Que deseas hacer ?</strong></div>
					
					<spring:url value="/admins/${personal.ndivalue}/ActualizarPersonal" var="ActualizarPersonalUrl" />	
					<div class="col-6 col-md-4">
						<a href="${ActualizarPersonalUrl}" class="btn btn-primary">Actualizar los datos</a>
					</div>
					
					<spring:url value="/admins/${personal.ndivalue}/EliminarPersonal" var="EliminarPersonalUrl" />	
					<div class="col-6 col-md-4">
						<a data-toggle="modal" data-href="${EliminarPersonalUrl}" data-target="#myModal"  href="#" class="btn btn-danger">Eliminar usuario</a>
					</div>
				</div>				
				<hr />
			</div>			
		</div>
	</div>	<!-- /container -->


<!-- call Modal -->
					<c:set var="modal_title" value="Eliminar usuario" scope="request"/>
					<c:set var="modal_body" value="¿Estas seguro que deseas eliminar a <strong>${personal.given}</strong>, como usuario del personal de la plataforma?.
        Toda su información como personal de la plataforma será eliminada." scope="request"/>
					<c:set var="modal_btn_action" value="Eliminar usuario" scope="request"/>
					<jsp:include page="../fragments/modal.jsp" />				



	<jsp:include page="../fragments/footer.jsp" />
	<jsp:include page="../fragments/js/modal.jsp" />
</body>
</html>