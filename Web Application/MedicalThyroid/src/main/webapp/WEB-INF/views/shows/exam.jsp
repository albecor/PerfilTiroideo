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

	<c:if test="${roleType == 'ROLE_PATIENT'}">
		<jsp:include page="../patient/navbar.jsp" />
	</c:if>
	<c:if test="${roleType == 'ROLE_PERSONAL'}">
		<jsp:include page="../personal/navbar.jsp" />
	</c:if>

	<br />
	<jsp:include page="../fragments/alert.jsp" />
	<br />
	
	<div class="container">
		<div class="card">
			<div class="card-block">
				<div class="row">
					<div class="col text-center">
						<h5>Orden de laboratorio: </h5>
					</div>					
				</div>
				<div class="row">
					<div class="col-12 col-lg-6">

						<hr />

						<div class="row">
							<div class="col text-md-right">
								<strong>Orden de laboratorio: </strong>
							</div>
							<div class="col">${exam.order}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Estudio: </strong>
							</div>
							<div class="col">${exam.displayCode}</div>
						</div>
						<hr />
						<div class="row">
							<div class="col text-md-right">
								<strong>Entidad: </strong>
							</div>
							<div class="col">${exam.entity}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong>Fecha: </strong>
							</div>
							<div class="col">${exam.issued}</div>
						</div>
					</div>
					<div class="col-12 col-lg-6">
						
						<hr />

						<div class="row">
							<div class="col text-md-right">
								<strong>Médico: </strong>
							</div>
							<div class="col">${exam.displayPerformer}</div>
						</div>						
						<div class="row">
							<div class="col text-md-right">
								<strong>Tipo: </strong>
							</div>
							<div class="col">Especialista</div>
						</div>
						
						<hr />
						<div class="row">
							<div class="col text-md-right">
								<strong>Paciente: </strong>
							</div>
							<div class="col">${exam.displaySubject}</div>
						</div>

						<div class="row">
							<div class="col text-md-right">
								<strong>No. de documento: </strong>
							</div>
							<div class="col">${exam.referenceSubject}</div>
						</div>
					</div>
				</div>
				<hr />

					<c:if test="${not empty exam}">
						<table class="table table-bordered table-sm">
							<thead>
								<tr>
									<th>Variable</th>
									<th>Valor</th>
									<th>Val. Referencia</th>
									<th>Unidad</th>
									<th>Interpretación</th>
																		
								</tr>
							</thead>
							<tbody>						
									<tr>
										<th scope="row">${exam.displayCode}</th>
										<td>${exam.valueQuantity}</td>
										<td>${exam.low} - ${exam.high}</td>
										<td>${exam.unit}</td>
										<td>${exam.interpretation}</td>										
									</tr>
							
							</tbody>
						</table>
					</c:if>


				<hr />
				
				<div class="row">
			<div class="col">
				<strong>Comentarios: </strong> ${exam.comments}
			</div>							
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->
	
	<hr />
	
	


	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>