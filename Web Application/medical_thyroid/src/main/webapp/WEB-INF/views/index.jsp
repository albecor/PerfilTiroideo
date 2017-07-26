<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="fragments/title.jsp" />
<jsp:include page="fragments/css/bootstrap.jsp" />
<jsp:include page="fragments/css/footer.jsp" />
<jsp:include page="fragments/css/fontAwesome.jsp" />
<jsp:include page="fragments/css/animate.jsp" />
<style>
.card-icon {
	height: 5rem;
	width: 100%;
	background-color: #0C89F0;
}

.card-icon-event {
	height: 5rem;
	width: 100%;
	background-color: powderblue;
}

.card-width-event {
	width: 15rem;
}

.card-width {
	width: 20rem;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container-fluid">
		<div class="row justify-content-center ">
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="col col-auto py-2">
					<div class="card  h-100 card-width">
						<div class="card-icon d-flex justify-content-center">
							<i class="align-self-center fa fa-users fa-3x "></i>
						</div>
						<div class="card-block d-flex flex-column ">
							<h4 class="card-title">
								<spring:message code="users" />
							</h4>
							<div class="card-text">
								<spring:message code="users-message" />
								<hr />
								<a href="<spring:url value="/ad/new-user"/>" class="btn btn-primary btn-block"><i class="fa fa-plus fa-fw"></i> <spring:message code="navbar.user.new" /> <spring:message code="user" /></a>
								<hr />
								<spring:url value="/ad/consult-user" var="postActionUrl" />
								<form:form class="form-horizontal" method="post" modelAttribute="user" action="${postActionUrl}">
									<div class="form-group ${notExistUser ? 'has-danger' : ''}">
										<spring:message code="user-id" var="placeholderNdivalue" />
										<form:input path="ndivalue" type="number" class="form-control form-control-feedback" placeholder="${placeholderNdivalue}" required="required" />
										<c:if test="${notExistUser}">
											<spring:message code="form.user.error.ndivalue.not-exist" />
										</c:if>
									</div>
									<button type="submit" class="btn btn-primary btn-block">
										<spring:message code="navbar.user.update" /> / 
										<spring:message code="navbar.user.delete" /> 
										<spring:message code="user" />
									</button>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_PERSONAL')">
				<div class="col col-auto py-2">
					<div class="card  h-100 card-width">
						<div class="card-icon d-flex justify-content-center">
							<i class=" align-self-center fa fa-user-md fa-3x "></i>
						</div>
						<div class="card-block d-flex flex-column ">
							<h4 class="card-title">
								<spring:message code="consult-patient" />
							</h4>
							<div class="card-text">
								<spring:message code="consult-patient-message" />
								<hr />
								<spring:url value="/pe/consult-patient" var="postActionUrl" />
								<form:form class="form-horizontal" method="post" modelAttribute="user" action="${postActionUrl}">
									<div class="form-group ${notExistPatient ? 'has-danger' : ''}">
										<spring:message code="patient-id" var="placeholderNdivalue" />
										<form:input path="ndivalue" type="number" class="form-control form-control-feedback" placeholder="${placeholderNdivalue}" required="required" />
										<c:if test="${notExistPatient}">
											<spring:message code="error.notExistPatient" />
										</c:if>
									</div>
									<button type="submit" class="btn btn-primary btn-block">
										<spring:message code="consult" />
									</button>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_LAB')">
				<div class="col col-auto py-2">
					<div class="card  h-100 card-width">
						<div class="card-icon d-flex justify-content-center">
							<i class=" align-self-center fa fa-users fa-3x "></i>
						</div>
						<div class="card-block d-flex flex-column ">
							<h4 class="card-title">
								<spring:message code="perform-exam" />
							</h4>
							<div class="card-text">
								<spring:message code="labs-message" />
								<hr />
								<a href="<spring:url value="/la/exams"/>" class="btn btn-primary btn-block"><i class="fa fa-medkit fa-fw" aria-hidden="true"></i> <spring:message code="perform-exam" /></a>
								
							</div>
						</div>
						
					</div>
				</div>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_PATIENT')">
				<div class="col col-auto py-2">
					<div class="card  h-100 card-width">
						<div class="card-icon d-flex justify-content-center">
							<i class=" align-self-center fa fa-users fa-3x "></i>
						</div>
						<div class="card-block d-flex flex-column ">
							<h4 class="card-title">
								<spring:message code="consult-exam" />
							</h4>
							<div class="card-text">
								<spring:message code="consult-exam-message" />
								<hr />
								<a href="<spring:url value="/pa/exams"/>" class="btn btn-primary btn-block"><i class="fa fa-medkit fa-fw" aria-hidden="true"></i> <spring:message code="consult-exam" /></a>								
							</div>
						</div>
					</div>
				</div>
			</sec:authorize>
		</div>
		<sec:authorize access="hasRole('ROLE_PATIENT')">
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_LAB')">
			<div class="card">
				<c:if test="${not empty exams}">
					<table class="table table-bordered table-sm">
						<thead>
							<tr>
								<th>Orden</th>
								<th>Estudio</th>
								<th>Paciente</th>
								<th>Acción</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="exam" items="${exams}">
								<tr>
									<th scope="row">${exam.order}</th>
									<td>${exam.displayCode}</td>
									<td>${exam.displaySubject}</td>
									<td><spring:url value="/lab/${exam.order}/${exam.referenceSubject}/RealizarOrden" var="RealizarOrdenUrl" /> <a href="${RealizarOrdenUrl}" class="btn btn-sm btn-primary">Realizar</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_PERSONAL')">
		</sec:authorize>
	</div>
	<jsp:include page="fragments/footer.jsp" />
	<jsp:include page="fragments/js/jquery.jsp" />
	<jsp:include page="fragments/js/bootstrap.jsp" />
	<c:if test="${not empty notifyMesage}">
		<jsp:include page="fragments/js/notify.jsp">
			<jsp:param name="notifyMesage" value="${notifyMesage}" />
			<jsp:param name="notifyType" value="notifyType" />
		</jsp:include>
	</c:if>
	
</body>
</html>