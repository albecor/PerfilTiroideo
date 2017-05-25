<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

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
		<div class="card">
		
			<h6 class="card-header">
						Información de Laboratorio
			</h6>
			
			<div class="card-block">

			<spring:url value="/lab/${order}/${ndivalue}/RealizarOrden" var="postActionUrl" />

				<form:form class="form-horizontal" method="post"
					modelAttribute="examForm" action="${postActionUrl}">				
    
    					<div class="row">
							<div class="col col-lg-2">Orden: </div>
							<div class="col">${examForm.order}</div>
						</div>
						<div class="row">
							<div class="col col-lg-2">Laboratorio: </div>
							<div class="col">${examForm.displayCode}</div>
						</div>
						<div class="row">
							<div class="col col-lg-2">Paciente: </div>
							<div class="col">${examForm.displaySubject}</div>
						</div>
						<div class="row">
							<div class="col col-lg-2">Especialista: </div>
							<div class="col">${examForm.displayPerformer}</div>
						</div>
						<div class="row">
							<div class="col col-lg-2">Entidad:</div>
							<div class="col">${examForm.entity}</div>
						</div>						
					
					<form:hidden path="referenceSubject" value="${examForm.referenceSubject}"/>
					<form:hidden path="displaySubject" value="${examForm.displaySubject}"/>
					<form:hidden path="displayPerformer" value="${examForm.displayPerformer}"/>
					<form:hidden path="referencePerfomer" value="${examForm.referencePerfomer}"/>
					<form:hidden path="code" value="${examForm.code}"/>
					<form:hidden path="entity" value="${examForm.entity}"/>
					
					<spring:bind path="valueQuantity">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Valor</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="valueQuantity" type="number" class="form-control form-control-feedback"
									id="valueQuantity" placeholder="Valor" step="any" required="required"  autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="valueQuantity" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="low">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Rango bajo</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="low" type="number" class="form-control form-control-feedback"
									id="low" placeholder="Rango bajo" step="any" required="required"  autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="low" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					 
					<spring:bind path="high">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Rango alto</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="high" type="number" class="form-control form-control-feedback"
									id="high" placeholder="Rango alto" step="any" required="required"  autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="high" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>

					<spring:bind path="comments">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Comentarios</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:textarea path="comments" rows="5" class="form-control form-control-feedback"
									id="comments" placeholder="Agrega tu comentario" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="comments" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
  					<div class="form-group row">
						<div class="col">						
							<button type="submit" class="btn-lg btn-primary pull-right">Agregar</button>						
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>