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
					<div class="col-12 col-lg-6">
						
						<h5>Información laboratorista</h5>
						<hr />		
						
						<div class="row">
							<div class="col text-md-right"><strong>Nombre: </strong></div>
							<div class="col">${lab.name}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>${lab.ndi}: </strong></div>
							<div class="col">${lab.ndivalue}</div>
						</div>
					</div>
					<div class="col-12 col-lg-6">
						<h5>Información del laboratorio</h5>
						<hr />		
						
						<div class="row">
							<div class="col text-md-right"><strong>Entidad: </strong></div>
							<div class="col">${lab.entitylab}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Teléfono: </strong></div>
							<div class="col">${lab.phone}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Correo: </strong></div>
							<div class="col">${lab.email}</div>
						</div>
						<div class="row">
							<div class="col text-md-right"><strong>Dirección: </strong></div>
							<div class="col">${lab.address}</div>
						</div>									
					</div>
				</div>
				<hr />								
	
				<hr />
				<div class="row">
					<div class="col-12 col-md-4"><strong>Que deseas hacer ?</strong></div>
					
					<spring:url value="/admins/${lab.ndivalue}/ActualizarLaboratorio" var="ActualizarLaboratorioUrl" />	
					<div class="col-6 col-md-4">
						<a href="${ActualizarLaboratorioUrl}" class="btn btn-primary">Actualizar los datos</a>
					</div>
					
					<spring:url value="/admins/${lab.ndivalue}/EliminarLaboratorio" var="EliminarLaboratorioUrl" />	
					<div class="col-6 col-md-4">
						<a data-toggle="modal" data-href="${EliminarLaboratorioUrl}" data-target="#myModal"  href="#" class="btn btn-danger">Eliminar usuario</a>
					</div>
				</div>				
				<hr />
			</div>			
		</div>
	</div>	<!-- /container -->


<!-- call Modal -->
					<c:set var="modal_title" value="Eliminar usuario" scope="request"/>
					<c:set var="modal_body" value="¿Estas seguro que deseas eliminar a <strong>${lab.name}</strong>, como usuario de laboratorio?.
        Toda su información como laboratorista será eliminada de la plataforma." scope="request"/>
					<c:set var="modal_btn_action" value="Eliminar usuario" scope="request"/>
					<jsp:include page="../fragments/modal.jsp" />				



	<jsp:include page="../fragments/footer.jsp" />
	<jsp:include page="../fragments/js/modal.jsp" />

</body>
</html>