<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
	<c:if test="${roleType == 'ROLE_ADMIN'}">
		<jsp:include page="../admins/navbar.jsp" />
	</c:if>
	<c:if test="${roleType == 'ROLE_PERSONAL'}">
		<jsp:include page="../personal/navbar.jsp" />
	</c:if>
	<c:if test="${roleType == 'ROLE_PATIENT'}">
		<jsp:include page="../patient/navbar.jsp" />
	</c:if>

	<br />
	<jsp:include page="../fragments/alert.jsp" />
	<br />
	<div class="container">
		<div class="card">
			<div class="card-block">

				<div class="row">
					<div class="col-12 col-lg-6">

						<h5>Información personal</h5>
						<hr />

						<div class="row">
							<div class="col text-md-right">
								<strong>Nombre: </strong>
							</div>
							<div class="col">${patient.given}${patient.family}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>${patient.ndi}: </strong>
							</div>
							<div class="col">${patient.ndivalue}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Género: </strong>
							</div>
							<div class="col">${patient.gender}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Estado civil: </strong>
							</div>
							<div class="col">${patient.maritalStatus}</div>
						</div>
					</div>
					<div class="col-12 col-lg-6">
						<h5>Datos de contacto</h5>
						<hr />

						<div class="row">
							<div class="col text-md-right">
								<strong>Teléfono móvil: </strong>
							</div>
							<div class="col">${patient.telmobile}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Teléfono de oficina: </strong>
							</div>
							<div class="col">${patient.telwork}</div>
						</div>

						<div class="row">
							<div class="col text-md-right">
								<strong>Teléfono de oficina: </strong>
							</div>
							<div class="col">${patient.telhome}</div>
						</div>

						<div class="row">
							<div class="col text-md-right">
								<strong>Correo: </strong>
							</div>
							<div class="col">${patient.email}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Oficina: </strong>
							</div>
							<div class="col">${patient.line}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Ciudad: </strong>
							</div>
							<div class="col">${patient.city}</div>
						</div>
					</div>
				</div>
				<hr />
				<div class="row">
					<div class="col-12 col-lg-6">

						<h5>Contactar a:</h5>
						<hr />

						<div class="row">
							<div class="col text-md-right">
								<strong>Nombre: </strong>
							</div>
							<div class="col">${patient.givenc}${patient.familyc}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Teléfono: </strong>
							</div>
							<div class="col">${patient.telc}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Parentesco: </strong>
							</div>
							<div class="col">${patient.relationship}</div>
						</div>
					</div>
					<div class="col-12 col-lg-6">
						<h5>Datos de salud:</h5>
						<hr />

						<div class="row">
							<div class="col text-md-right">
								<strong>Centro de salud: </strong>
							</div>
							<div class="col">${patient.telmobile}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Tipo de sangre: </strong>
							</div>
							<div class="col">${patient.blood}</div>
						</div>
					</div>
				</div>

				<hr />
				<c:if test="${roleType == 'ROLE_ADMIN'}">
    				<div class="row">
						<div class="col-12 col-md-4">
							<strong>Que deseas hacer ?</strong>
						</div>

						<spring:url value="/admins/${patient.ndivalue}/ActualizarPaciente"
							var="ActualizarPacienteUrl" />
						<div class="col-6 col-md-4">
							<a href="${ActualizarPacienteUrl}" class="btn btn-primary">Actualizar
								los datos</a>
						</div>

						<spring:url value="/admins/${patient.ndivalue}/EliminarPaciente"
							var="EliminarPacienteUrl" />
						<div class="col-6 col-md-4">
							<a data-toggle="modal" data-href="${EliminarPacienteUrl}"
								data-target="#myModal" href="#" class="btn btn-danger">Eliminar
								usuario</a>
						</div>
					</div>
					
					<!-- call Modal -->
					<c:set var="modal_title" value="Eliminar usuario" scope="request"/>
					<c:set var="modal_body" value="¿Estas seguro que deseas eliminar a <strong>${patient.given}</strong>,
										como paciente?. Toda su información como paciente será
										eliminada de la plataforma." scope="request"/>
					<c:set var="modal_btn_action" value="Eliminar usuario" scope="request"/>
					<jsp:include page="../fragments/modal.jsp" />					
				</c:if>
				<hr />
			</div>
		</div>
<br />
<c:if test="${roleType == 'ROLE_PERSONAL'}">
		<div class="card">
			<div class="card-block">			
					<spring:url value="/personal/autorizarExamen" var="postActionUrl" />
					<form:form class="form-horizontal" method="post"
						modelAttribute="examForm" action="${postActionUrl}">
						<div class="row">

							<div class="col-12 col-md-10">
								<spring:bind path="code">
									<div class="form-group row ${status.error ? 'has-danger' : ''}">
										<label class="col-4  col-form-label"><strong>Autorizar
												examen </strong></label>
										<div class="col-8 ">
											<form:select path="code"
												class="form-control form-control-feedback"
												required="required"
												autofocus="${status.error ? 'autofocus' : ''}">
												<form:option value="" label="--- Seleccionar ---" />
												<form:options items="${thyroidProfileList}" />
											</form:select>
											<div class="form-control-feedback">
												<form:errors path="code" />
											</div>
										</div>
									</div>
								</spring:bind>
								<form:hidden path="displaySubject" value="${patient.given} ${patient.family}" />							
								<form:hidden path="referenceSubject" value="${patient.ndivalue}" />							
								<form:hidden path="entity" value="${patient.managingOrganization}" />
							</div>
							<div class="col-md-2">
								<button type="submit" class="btn btn-primary">Agregar</button>
							</div>
						</div>
					</form:form>				
			</div>
		</div>
</c:if>
<br />
<c:if test="${roleType == 'ROLE_PERSONAL' || roleType == 'ROLE_PATIENT'}">
<c:if test="${not empty exams}">
	<div class="card">
			<div class="card-block">
					
						<table class="table table-bordered table-sm">
							<thead>
								<tr>
									<th>Orden</th>
									<th>Estudio</th>
									<th>Fecha</th>
									<th>Acción</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="exam" items="${exams}">
									<tr>
										<th scope="row">${exam.order}</th>
										<td>${exam.displayCode}</td>
										
											<c:if test="${roleType == 'ROLE_PERSONAL'}">
											<c:choose>
											<c:when test="${exam.valueQuantity == null }">
												<td>En espera</td>
												<td><spring:url
														value="/personal/${exam.order}/${patient.ndivalue}/EliminarOrden"
														var="EliminarOrdenUrl" /> <a data-toggle="modal"
													data-href="${EliminarOrdenUrl}" data-target="#myModal"
													href="#" class="btn btn-sm btn-danger">Eliminar</a></td>
											</c:when>
											<c:otherwise>
												<td>${exam.issued}</td>
												<td><spring:url
														value="/personal/${exam.order}/examen"
														var="ExamenUrl" /> <a 
													href="${ExamenUrl}" class="btn btn-sm btn-primary">ver</a></td>
											</c:otherwise>
										</c:choose>
										</c:if>
										<c:if test="${roleType == 'ROLE_PATIENT'}">
										<c:choose>
											<c:when test="${exam.valueQuantity == null }">
												<td>En espera</td>
												<td>En espera</td>
											</c:when>
											<c:otherwise>
												<td>${exam.issued}</td>
												<td><spring:url
														value="/patient/${exam.order}/examen"
														var="ExamenUrl" /> <a 
													href="${ExamenUrl}" class="btn btn-sm btn-primary">ver</a></td>
											</c:otherwise>
										</c:choose>
									</c:if>										
									</tr>
								</c:forEach>
							</tbody>
						</table>
					
					<c:set var="modal_title" value="Eliminar Orden" scope="request"/>
					<c:set var="modal_body" value="La orden será eliminada. ¿Desea continuar?" scope="request"/>
					<c:set var="modal_btn_action" value="Eliminar Orden" scope="request"/>
					<jsp:include page="../fragments/modal.jsp" />
						
			</div>
		</div>
		</c:if>
</c:if>			
				
	</div>
	<!-- /container -->
	
	<hr />
	
	


	<jsp:include page="../fragments/footer.jsp" />
	<jsp:include page="../fragments/js/modal.jsp" />
</body>
</html>