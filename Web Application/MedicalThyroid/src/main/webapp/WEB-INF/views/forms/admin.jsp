<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">

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

<sec:authorize access="hasRole('ROLE_TEMPORAL')">
	<jsp:include page="../init/navbar.jsp" />
</sec:authorize>

	<br />
	<div class="container">
		<div class="card">
		
			<h6 class="card-header">				
						<spring:url value="/${folder}/${action}Administrador" var="userActionUrl" />
						${action} administrador
			</h6>
			
			<div class="card-block">				
				<form:form class="form-horizontal" method="post"
					modelAttribute="adminForm" action="${userActionUrl}">

					<spring:bind path="ndi">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Tipo de
								documento</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:select path="ndi" class="form-control form-control-feedback" required="required">
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
									id="ndivalue" placeholder="Número de Cédula" required="required"/>
								<form:errors path="ndivalue" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>

					<form:hidden path="id" />

					<spring:bind path="name">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Nombre</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="name" type="text" class="form-control form-control-feedback"
									id="name" placeholder="Nombre" required="required"/>
								<form:errors path="name" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>

					<spring:bind path="phone">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Telefono</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="phone" type="number" class="form-control form-control-feedback"
									id="phone" placeholder="Telefono" required="required"/>
								<form:errors path="phone" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>

					<spring:bind path="email">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Correo</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:input path="email" class="form-control form-control-feedback" id="email"
									placeholder="Correo" required="required"/>
								<form:errors path="email" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>

					<spring:bind path="address">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Dirección</label>
							<div class="col-sm-7 col-md-8 col-lg-10">
								<form:textarea path="address" rows="5" class="form-control form-control-feedback"
									id="address" placeholder="Dirección" required="required"/>
								<form:errors path="address" class="form-control-feedback" />
							</div>
						</div>
					</spring:bind>

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
	<spring:url value="/resources/js/validatePassword.js" var="validatePasswordJs" />
	<script src="${validatePasswordJs}"></script>

</body>
</html>