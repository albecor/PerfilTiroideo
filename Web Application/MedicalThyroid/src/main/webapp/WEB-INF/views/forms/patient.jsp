<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="es">

<head>

<jsp:include page="../fragments/title.jsp" />
<jsp:include page="../fragments/css/bootstrap.jsp" />
<jsp:include page="../fragments/css/footer.jsp" />
<jsp:include page="../fragments/fontAwesome.jsp" />

</head>

<body>


<c:if test="${roleType == 'ROLE_ADMIN'}">
   <jsp:include page="../admins/navbar.jsp" />
	<spring:url value="/admins/${action}Paciente" var="postActionUrl" />
</c:if>
<c:if test="${roleType == 'ROLE_PATIENT'}">
   <jsp:include page="../patient/navbar.jsp" />
	<spring:url value="/patient/${action}Paciente" var="postActionUrl" />
</c:if>

	<br />
	<div class="container">
		<div class="card">
		
			<h6 class="card-header">		
						${action} información de paciente
			</h6>
			
			<div class="card-block">
			
					
									
				<form:form class="form-horizontal" method="post"
					modelAttribute="patientForm" action="${postActionUrl}">			
    
					<form:hidden path="id" />
					
					<h5>Datos personales:</h5>					
					<hr>

					
					<spring:bind path="given">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Nombre</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="given" type="text" class="form-control form-control-feedback"
									id="given" placeholder="Nombre" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="given" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="family">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Apellidos</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="family" type="text" class="form-control form-control-feedback"
									id="family" placeholder="Apellidos" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="family" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					
					
					
					<c:choose>
		<c:when test="${edit == 'patient'}">
			<form:hidden path="ndi" />
			<form:hidden path="ndivalue" />
			
			<div class="row">
			<br />
			<div class="col-sm-5 col-md-4 col-lg-2"> <strong>No. de documento</strong></div>
			<div class="col-sm-7 col-md-8 col-lg-10">${patientForm.ndivalue}</div>
			<br /><br />
			</div>
			
		</c:when>
		<c:otherwise>
				<spring:bind path="ndi">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Tipo de
								documento</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:select path="ndi" class="form-control form-control-feedback" required="required" autofocus="${status.error ? 'autofocus' : ''}">
									<form:option value="" label="--- Seleccionar ---" />
									<form:options items="${ndiList}" />
								</form:select>
								<div class="form-control-feedback">
									<form:errors path="ndi"/>
								</div>								
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="ndivalue">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Numero de
								cédula</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="ndivalue" type="number" class="form-control form-control-feedback"
									id="ndivalue" placeholder="Número de Cédula" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="ndivalue" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>		
		</c:otherwise>
	</c:choose>
					
						
				
					<spring:bind path="birthDate">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Fecha de nacimiento</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="birthDate" type="text" class="form-control form-control-feedback"
									id="birthDate" placeholder="dd-mm-aaaa" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])-(0[1-9]|1[012])-[0-9]{4}" title="El formato debe ser dd-mm-aaa" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="birthDate" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="gender">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Género</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:select path="gender" class="form-control form-control-feedback" required="required" autofocus="${status.error ? 'autofocus' : ''}">
									<form:option value="" label="--- Seleccionar ---" />
									<form:options items="${genderList}" />
								</form:select>
								<div class="form-control-feedback">
									<form:errors path="gender"/>
								</div>								
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="maritalStatus">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Estado civil</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:select path="maritalStatus" class="form-control form-control-feedback" required="required" autofocus="${status.error ? 'autofocus' : ''}">
									<form:option value="" label="--- Seleccionar ---" />
									<form:options items="${maritalStatusList}" />
								</form:select>
								<div class="form-control-feedback">
									<form:errors path="maritalStatus"/>
								</div>								
							</div>
						</div>
					</spring:bind>
					
					<br />
					<h5>Datos de contacto:</h5>					
					<hr>					
					
					<spring:bind path="telmobile">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Teléfono móvil</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="telmobile" type="number" class="form-control form-control-feedback"
									id="telmobile" placeholder="Teléfono móvil" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="telmobile" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="telwork">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Teléfono de oficina</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="telwork" type="number" class="form-control form-control-feedback"
									id="telwork" placeholder="Teléfono de oficina" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="telwork" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="telhome">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Teléfono de casa</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="telhome" type="number" class="form-control form-control-feedback"
									id="telhome" placeholder="Teléfono de casa" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="telhome" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>

					<spring:bind path="email">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Correo</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input type="email" path="email" class="form-control form-control-feedback" id="email"
									placeholder="tucorreo@example.com" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="email" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>					
					

					<spring:bind path="line">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Dirección de residencia</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:textarea type="text" path="line" rows="5" class="form-control form-control-feedback"
									id="line" placeholder="Dirección de residencia" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="line" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="city">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Ciudad</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input type="tex" path="city" class="form-control form-control-feedback" id="city"
									placeholder="Ciudad" required="required" autofocus="${status.error ? 'autofocus' : ''}"/>
								<form:errors path="city" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>	
					
					
					<br />
					<h5>Contactar a:</h5>					
					<hr>	
					
					<spring:bind path="givenc">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Nombre</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="givenc" type="text" class="form-control form-control-feedback"
									id="givenc" placeholder="Nombre" required="required"/>
								<form:errors path="givenc" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="familyc">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Apellidos</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="familyc" type="text" class="form-control form-control-feedback"
									id="familyc" placeholder="Apellidos" required="required"/>
								<form:errors path="familyc" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="telc">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Teléfono</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="telc" type="number" class="form-control form-control-feedback"
									id="telc" placeholder="Teléfono de contacto" required="required"/>
								<form:errors path="telc" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="relationship">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Parentesco</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:select path="relationship" class="form-control form-control-feedback" required="required">
									<form:option value="" label="--- Seleccionar ---" />
									<form:options items="${relationshipList}" />
								</form:select>
								<div class="form-control-feedback">
									<form:errors path="relationship"/>
								</div>								
							</div>
						</div>
					</spring:bind>
					
					
					<br />
					<h5>Datos de salud:</h5>					
					<hr>
					
					<spring:bind path="managingOrganization">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">EPS/IPS/Centro de salud</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input type="text" path="managingOrganization" class="form-control form-control-feedback" id="managingOrganization"
									placeholder="EPS/IPS/Centro de salud" required="required"/>
								<form:errors path="managingOrganization" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
					
					<spring:bind path="blood">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Tipo de sangre</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:select path="blood" class="form-control form-control-feedback" required="required">
									<form:option value="" label="--- Seleccionar ---" />
									<form:options items="${bloodList}" />
								</form:select>
								<div class="form-control-feedback">
									<form:errors path="blood"/>
								</div>								
							</div>
						</div>
					</spring:bind>
					
					<br />
					<h5>Datos de usuario:</h5>					
					<hr>
					
					<spring:bind path="password">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Contraseña</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:password path="password" class="form-control form-control-feedback"
									id="password" placeholder="Contraseña" required="required"/>
								<form:errors path="password" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>

					<spring:bind path="confirmPassword">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Confirmar
								contraseña</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:password path="confirmPassword" class="form-control form-control-feedback"
									id="confirmPassword" placeholder="Confirmar contraseña" required="required"/>
								<form:errors path="confirmPassword"
									class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>
  
  					<div class="form-group row">
						<div class="col">						
							<button type="submit" class="btn-lg btn-primary pull-right">${action}</button>						
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
	<jsp:include page="../fragments/validatePasswordJs.jsp" />

</body>
</html>