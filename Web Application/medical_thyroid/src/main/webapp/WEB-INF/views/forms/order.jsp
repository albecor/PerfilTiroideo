<%@page import="java.time.LocalDateTime"%>
<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../fragments/title.jsp" />
<jsp:include page="../fragments/css/bootstrap.jsp" />
<jsp:include page="../fragments/css/footer.jsp" />
<jsp:include page="../fragments/css/fontAwesome.jsp" />
<jsp:include page="../fragments/css/animate.jsp" />
</head>
<style>
.btn-circle {
	width: 30px;
	height: 30px;
	text-align: center;
	padding: 6px 0;
	font-size: 12px;
	line-height: 1.428571429;
	border-radius: 15px;
}
</style>
<body>
	<jsp:include page="../navbar.jsp" />
	<br />
	<div class="container">
		<div class="card">
			<h5 class="card-header text-center">
				<spring:message code="realize-exam" />
			</h5>
			<div class="card-block">
				<div class="alert alert-warning" role="alert">
					<spring:message code="realize-exam-message" />
				</div>
				<spring:url value="/la/perform-exam" var="postActionUrl" />
				<form:form method="POST" modelAttribute="examForm" class="form-horizontal" action="${postActionUrl}">
					<form:hidden path="order" />
					<form:hidden path="code" />
					<form:hidden path="systemCode" />
					<form:hidden path="displayCode" />
					<form:hidden path="displaySubject" />
					<form:hidden path="referenceSubject" />
					<form:hidden path="referencePerformer" />
					<form:hidden path="displayPerformer" />
					<hr />
					<div class="row d-flex align-items-start flex-wrap">
						<div></div>
						<div></div>
					</div>
					<div class="row d-flex align-items-start flex-wrap">
						<div class="pl-3">
							<strong> <spring:message code="exam" />:
							</strong>
						</div>
						<div class="pl-3">
							(${examForm.code}) <spring:message code="${examForm.code}" /> <a href="${examForm.systemCode}" target="_blank" data-toggle="tooltip" data-placement="top" title="<spring:message code="exam-information" />"  class="btn btn-info btn-circle "><i class="fa fa-info fa-fw"></i></a>
						</div>
					</div>
					<div class="row d-flex align-items-start flex-wrap">
						<div class="pl-3">
							<strong> <spring:message code="order" />:
							</strong>
						</div>
						<div class="pl-3">${examForm.order}</div>
					</div>
					<div class="row d-flex align-items-start flex-wrap">
						<div class="pl-3">
							<strong> <spring:message code="patient" />:
							</strong>
						</div>
						<div class="pl-3">${examForm.referenceSubject} - ${examForm.displaySubject}</div>
					</div>
					<div class="row d-flex align-items-start flex-wrap">
						<div class="pl-3">
							<strong> <spring:message code="specialist" />:
							</strong>
						</div>
						<div class="pl-3">${examForm.displayPerformer}</div>
					</div>
					<div class="row d-flex align-items-start flex-wrap">
						<div class="pl-3">
							<strong> <spring:message code="authorized" />:
							</strong>
						</div>
						<div class="pl-3">
							<c:if test="${not empty examForm.issued}">
								<c:set var="issued" value="${examForm.issued}" />
								<%
									String dateIssued = LocalDateTime.parse((String) pageContext.getAttribute("issued")).toLocalDate()
													.toString();
								%>
								<%=dateIssued%>
							</c:if>
						</div>
					</div>
					<hr />
					<c:choose>
						<c:when test="${examForm.unit != null }">
							<form:hidden path="unit" />
							<div class="row py-2">
								<div class="col-sm-5 col-md-4 col-lg-3">
									<strong> <spring:message code="unit" />:</strong>
								</div>
								<div class="col-sm-7 col-md-8 col-lg-9">
									<strong>${examForm.unit}</strong>
								</div>
							</div>
							<spring:bind path="value">
								<div class="form-group row ${status.error ? 'has-danger' : ''}">
									<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><strong><spring:message code="value" /></strong>:</label>
									<spring:message code="placeholder.value" var="placeholderValue" />
									<div class="col-sm-7 col-md-8 col-lg-9">
										<form:input path="value" type="number" step="any" class="form-control form-control-feedback" placeholder="${placeholderValue}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
										<form:errors path="value" class="form-control-feedback" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="low">
								<div class="form-group row ${status.error ? 'has-danger' : ''}">
									<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><strong><spring:message code="min-value" /></strong>:</label>
									<spring:message code="placeholder.min-value" var="placeholderLow" />
									<div class="col-sm-7 col-md-8 col-lg-9">
										<form:input path="low" type="number" step="any" class="form-control form-control-feedback" placeholder="${placeholderLow}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
										<form:errors path="low" class="form-control-feedback" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="high">
								<div class="form-group row ${status.error ? 'has-danger' : ''}">
									<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><strong><spring:message code="max-value" /></strong>:</label>
									<spring:message code="placeholder.max-value" var="placeholderHigh" />
									<div class="col-sm-7 col-md-8 col-lg-9">
										<form:input path="high" type="number" step="any" class="form-control form-control-feedback" placeholder="${placeholderHigh}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
										<form:errors path="high" class="form-control-feedback" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="labComments">
								<div class="form-group row ${status.error ? 'has-danger' : ''}">
									<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><strong><spring:message code="labComments" /></strong>:</label>
									<spring:message code="placeholder.labComments" var="placeholderlabComments" />
									<spring:message code="title.birthdate" var="titleBirthdate" />
									<div class="col-sm-7 col-md-8 col-lg-9">
										<form:textarea path="labComments" rows="5" class="form-control form-control-feedback" placeholder="${placeholderlabComments}" autofocus="${status.error ? 'autofocus' : ''}" />
										<form:errors path="labComments" class="form-control-feedback" />
									</div>
								</div>
							</spring:bind>
						</c:when>
						<c:otherwise>
							<spring:bind path="labComments">
								<div class="form-group row ${status.error ? 'has-danger' : ''}">
									<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><strong><spring:message code="labComments.presence" />:</strong></label>
									<spring:message code="placeholder.labComments.presence" var="placeholderlabComments" />
									<div class="col-sm-7 col-md-8 col-lg-9">
										<form:textarea path="labComments" rows="5" required="required" class="form-control form-control-feedback" placeholder="${placeholderlabComments}" autofocus="${status.error ? 'autofocus' : ''}" />
										<form:errors path="labComments" class="form-control-feedback" />
									</div>
								</div>
							</spring:bind>
						</c:otherwise>
					</c:choose>
					<div class="form-group row">
						<div class="col">
							<button type="submit" class="btn-lg btn-primary pull-right">
								
										<spring:message code="form.user.button.value.add" />
								
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
	<jsp:include page="../fragments/js/jquery.jsp" />
	<jsp:include page="../fragments/js/bootstrap.jsp" />
	<script>
		$('[data-toggle="popover"]').popover();

		$('.popover-dismiss').popover({
			trigger : 'focus'
		})
		

  $('[data-toggle="tooltip"]').tooltip();

	</script>
	<script>
		var password = document.getElementById("password"), confirm_password = document
				.getElementById("confirmPassword");

		function validatePassword() {
			if (password.value != confirm_password.value) {
				confirm_password
						.setCustomValidity('<spring:message code="form.password-match" />');
			} else {
				confirm_password.setCustomValidity('');
			}
		}
		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	</script>
	<c:if test="${not empty notifyMesage}">
		<jsp:include page="../fragments/js/notify.jsp">
			<jsp:param name="notifyMesage" value="${notifyMesage}" />
			<jsp:param name="notifyType" value="notifyType" />
		</jsp:include>
	</c:if>
</body>
</html>