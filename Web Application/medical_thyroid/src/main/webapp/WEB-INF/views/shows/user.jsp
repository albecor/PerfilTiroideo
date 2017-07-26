<%@page import="java.time.LocalDateTime"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page session="true"%>
<html>
<head>
<jsp:include page="../fragments/title.jsp" />
<jsp:include page="../fragments/css/bootstrap.jsp" />
<jsp:include page="../fragments/css/footer.jsp" />
<jsp:include page="../fragments/css/fontAwesome.jsp" />
<jsp:include page="../fragments/css/datatable.jsp" />
<style>
@media ( min-width : 768px) {
	dt {
		text-align: right;
	}
}
</style>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<div class="container">
		<br />
		<div class="card">
			<div class="card-block">
				<div class="row">
					<div class="col-12 col-lg-6 py-2">
						<h5>
							<spring:message code="form.personal-information" />
						</h5>
						<hr />
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.ndi" />: </strong>
							</div>
							<div class="col">${user.ndivalue}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="user.name" />: </strong>
							</div>
							<div class="col">${user.given}&nbsp;${user.family}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.email" />: </strong>
							</div>
							<div class="col">${user.email}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.age" />: </strong>
							</div>
							<div class="col">${age}
								<spring:message code="age.years" />
							</div>
						</div>
					</div>
					<div class="col-12 col-lg-6 py-2">
						<h5>
							<spring:message code="form.general-information" />
						</h5>
						<hr />
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.birthdate" />: </strong>
							</div>
							<div class="col">${user.birthDate}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.gender" />: </strong>
							</div>
							<div class="col">
								<spring:message code="gender.${user.gender}" />
							</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.maritalStatus" />: </strong>
							</div>
							<div class="col">
								<spring:message code="marital-status.${user.maritalStatus}" />
							</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.blood" />: </strong>
							</div>
							<div class="col">${user.bloodtype}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.managingOrganization" />: </strong>
							</div>
							<div class="col">${user.managingOrganization}</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12 col-lg-6 py-2">
						<h5>
							<spring:message code="form.contact-information" />
						</h5>
						<hr />
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.relationship" />: </strong>
							</div>
							<div class="col">
								<spring:message code="relationship.${user.relationship}" />
							</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="contact.name" />: </strong>
							</div>
							<div class="col">${user.givenc}&nbsp;${user.familyc}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.telc" />: </strong>
							</div>
							<div class="col">${user.telc}</div>
						</div>
					</div>
					<div class="col-12 col-lg-6 py-2">
						<h5>
							<spring:message code="form.location-data" />
						</h5>
						<hr />
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.telhome" />: </strong>
							</div>
							<div class="col">${user.telhome}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.telwork" />: </strong>
							</div>
							<div class="col">${user.telwork}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.telmobile" />: </strong>
							</div>
							<div class="col">${user.telmobile}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.line" />: </strong>
							</div>
							<div class="col">${user.line}</div>
						</div>
						<div class="row">
							<div class="col text-md-right">
								<strong><spring:message code="form.city" />: </strong>
							</div>
							<div class="col">${user.city}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<br />
			<div class="card">
				<div class="card-block">
					<div class="row">
						<div class="col-12 col-md-4 py-1 text-center">
							<strong><spring:message code="question-do" /></strong>
						</div>
						<spring:url value="/ad/update-user/${user.ndivalue}" var="updateUserUrl" />
						<div class="col-12 col-md-4 py-1">
							<a href="${updateUserUrl}" class="btn btn-primary btn-block"><i class="fa fa-edit"></i> <spring:message code="update-user" /></a>
						</div>
						<spring:url value="/ad/delete-user-${user.ndivalue}" var="deleteUserUrl" />
						<div class="col-12 col-md-4 py-1">
							<a data-toggle="modal" data-href="${deleteUserUrl}" data-target="#myModalDeleteUser" href="#" class="btn btn-danger btn-block"><i class="fa fa-times"></i> <spring:message code="delete-user" /></a>
						</div>
					</div>
				</div>
			</div>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_PERSONAL')">
			<br />
			<spring:url value="/pe/authorize-exam" var="userActionUrl" />
			<form:form method="post" modelAttribute="examForm" action="${userActionUrl}">
				<div class="card">
					<div class="card-header text-center">
						<spring:message code="authorize-exam" />
					</div>
					<div class="card-block">
						<form:hidden path="displayPerformer" value="${loggedinuser.given} ${loggedinuser.family}" />
						<form:hidden path="referencePerformer" value="${loggedinuser.ndivalue}" />
						<form:hidden path="displaySubject" value="${user.given} ${user.family}" />
						<form:hidden path="referenceSubject" value="${user.ndivalue}" />
						<div class="row">
							<div class="col-12 col-md-4 text-center py-1">
								<strong><spring:message code="question-exam" /></strong>
							</div>
							<div class="col-12 col-md-4 py-1">
								<div class="${status.error ? 'has-danger' : ''}">
									<select class="form-control" id="code" name="code" required>
										<option value=""><spring:message code="select" /></option>
										<c:forEach items="${listExam}" var="data">
											<option value="${data}">
												<spring:message code="${data}" /></option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-12 col-md-4 py-1">
								<button type="submit" class="btn btn-primary btn-block">
									<spring:message code="add" />
								</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_PERSONAL') or hasRole('ROLE_PATIENT')">
			<c:if test="${not empty exams}">
				<div class="card">
					<div class="card-header text-center">
						<spring:message code="exams" />
					</div>
					<div class="card-block">
						<table class="table table-sm table-hover table-bordered" id="datatable" data-order='[[0 , "desc" ]]' data-page-length='10'>
							<thead>
								<tr>
									<th>
										<div class="hidden-sm-down">
											<spring:message code="order" />
										</div>
										<div class="hidden-md-up">#</div>
									</th>
									<th><spring:message code="laboratory" /></th>
									<th class="hidden-xs-down"><spring:message code="date" /></th>
									<sec:authorize access="hasRole('ROLE_PERSONAL')">
										<th class="text-center">
											<div class="hidden-xs-down">
												<spring:message code="action" />
											</div>
											<div class="hidden-sm-up">
												<i class="fa fa-trash" aria-hidden="true"></i>
											</div>
										</th>
									</sec:authorize>
									<th>
										<div class="hidden-xs-down">
											<spring:message code="realized" />
										</div>
										<div class="hidden-sm-up">
											<i class="fa fa-low-vision" aria-hidden="true"></i>
										</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="exam" items="${exams}">
									<tr>
										<th scope="row">${exam.order}</th>
										<td>
											<div class="hidden-sm-down">
												<spring:message code="${exam.code}" />
											</div>
											<div class="hidden-md-up text-center">
												<a tabindex="0" class="btn btn-outline-primary" role="button" data-toggle="popover" data-trigger="focus" data-placement="top" data-content=" <spring:message code="${exam.code}" />"> <i class="fa fa-info-circle fa-fw" aria-hidden="true"> </i>
												</a>
											</div>
										</td>
										<c:set var="issued" value="${exam.issued}" />
										<% String dateIssued = LocalDateTime.parse((String)pageContext.getAttribute("issued")).toLocalDate().toString(); %>
										<c:choose>
											<c:when test="${exam.done == 0 }">
												<td class="hidden-xs-down">--</td>
											<sec:authorize access="hasRole('ROLE_PERSONAL')">	<td class="text-center">
														<spring:url value="/pe/delete-exam-${exam.order}/${user.ndivalue}" var="EliminarOrdenUrl" />
														<a data-toggle="modal" data-href="${EliminarOrdenUrl}" data-target="#myModal${exam.code}" href="#" class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></a>
													</td></sec:authorize>
												<td class="text-center"><sec:authorize access="hasRole('ROLE_PATIENT') OR hasRole('ROLE_PERSONAL')">
												No
											</sec:authorize></td>
											</c:when>
											<c:otherwise>
												<td class="hidden-xs-down">
													<div class="hidden-sm-down"><%= dateIssued %></div>
													<div class="hidden-md-up text-center">
														<a tabindex="0" class="btn btn-info" role="button" data-toggle="popover" data-trigger="focus" data-placement="top" data-content="<%= dateIssued %>"><i class="fa fa-info-circle fa-fw" aria-hidden="true"> </i> </a>
													</div>
												</td>
												<sec:authorize access="hasRole('ROLE_PERSONAL')">
													<td class="text-center">
														<button type="button" class="btn btn-danger" disabled>
															<i class="fa fa-trash" aria-hidden="true"></i>
														</button>
													</td>
												</sec:authorize>
												<sec:authorize access="hasRole('ROLE_PATIENT') or hasRole('ROLE_PERSONAL')">
													<td class="text-center"><spring:url value="/pp/exam-${exam.order}" var="ExamenUrl" /> <a href="${ExamenUrl}" class="btn btn-sm btn-primary"><spring:message code="see" /> </a></td>
												</sec:authorize>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:if>
		</sec:authorize>
	</div>
	<jsp:include page="../fragments/css/animate.jsp" />
	<!-- /container -->
	<!-- call Modal -->
	<jsp:include page="../fragments/footer.jsp" />
	<jsp:include page="../fragments/js/jquery.jsp" />
	<jsp:include page="../fragments/js/bootstrap.jsp" />
	<script>
$('[data-toggle="popover"]').popover();   

$('.popover-dismiss').popover({
	  trigger: 'focus'
	})

</script>
	<jsp:include page="../fragments/js/datatable.jsp" />
	<%-- Modal for delete user button --%>
	<spring:message code="delete-user" var="titleModal" />
	<spring:message code="modal-body-delete-user" var="bodyModal" arguments="${user.given}" />
	<jsp:include page="../fragments/modal.jsp">
		<jsp:param name="idModal" value="myModalDeleteUser" />
		<jsp:param name="bodyModal" value="${bodyModal}" />
		<jsp:param name="titleModal" value="${titleModal}" />
	</jsp:include>
	<%-- Modals for delete exams button --%>
	<spring:message code="delete-order" var="titleModal" />
	<c:forEach var="exam" items="${exams}">
		<spring:message code="modal-body-delete-exam" var="bodyModal" arguments="${exam.order}" />
		<c:if test="${exam.done == 0 }">
			<jsp:include page="../fragments/modal.jsp">
				<jsp:param name="idModal" value="myModal${exam.code}" />
				<jsp:param name="bodyModal" value="${bodyModal}" />
				<jsp:param name="titleModal" value="${titleModal}" />
			</jsp:include>
		</c:if>
	</c:forEach>
	<c:if test="${not empty notifyMesage}">
		<jsp:include page="../fragments/js/notify.jsp">
			<jsp:param name="notifyMesage" value="${notifyMesage}" />
			<jsp:param name="notifyType" value="${notifyType}" />
		</jsp:include>
	</c:if>
</body>
</html>