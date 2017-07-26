<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../fragments/title.jsp" />
<jsp:include page="../fragments/css/bootstrap.jsp" />
<jsp:include page="../fragments/css/footer.jsp" />
<jsp:include page="../fragments/css/fontAwesome.jsp" />
<jsp:include page="../fragments/css/animate.jsp" />
<style>
.navbar-padding {
	padding-top: 65px;
}

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

.navbar-inverse .navbar-nav .nav-link {
	color: rgb(255, 255, 255);
}
</style>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<br />


	<div class="container">
		<div class="row justify-content-center ">

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="col py-2">
					<div class="card  h-100 ">

						<div class="card-block d-flex flex-column ">
							<h4 class="card-title text-center">
								<spring:message code="delete-user" />
							</h4>
							<div class="card-text">
								<div class="alert alert-info" role="alert">
									<strong><spring:message code="user-to-delete" /></strong>
									<hr />


									<div class="row">
										<div class="col">
											<strong><spring:message code="form.ndivalue" />: </strong>
										</div>
										<div class="col">${user.ndivalue}</div>
									</div>
									<div class="row">
										<div class="col">
											<strong><spring:message code="user.name" />: </strong>
										</div>
										<div class="col">${user.given} ${user.family}</div>
									</div>
									<div class="row">
										<div class="col">
											<strong><spring:message code="user-roles" />: </strong>
										</div>
										<div class="col">
											<c:forEach items="${user.roles}" var="item" varStatus="loop">
												<spring:message code="${fn:toLowerCase(item.type)}" />
												<c:if test="${!loop.last}">,</c:if>
											</c:forEach>

										</div>
									</div>
								</div>
								<hr />
								<div class="text-justify">
									<div class="alert alert-warning" role="alert">
										<spring:message code="confirm-data-message" />
									</div>
								</div>
								<hr />
								<spring:url value="/ad/delete-user-${user.ndivalue}"
									var="postActionUrl" />
								<form:form class="form-horizontal" method="post"
									modelAttribute="userPrincipal" action="${postActionUrl}">
									<spring:bind path="ndivalue">
										<div class="form-group ${status.error ? 'has-danger' : ''}">
											<spring:message code="placeholder.id"
												var="placeholderNdivalue" />

											<form:input path="ndivalue" type="number"
												class="form-control form-control-feedback"
												placeholder="${placeholderNdivalue}" required="required" />
											<form:errors path="ndivalue" class="form-control-feedback" />

										</div>
									</spring:bind>
									<spring:bind path="password">
										<div class="form-group ${status.error ? 'has-danger' : ''}">
											<spring:message code="enter-password"
												var="placeholderPassword" />
											<form:input path="password" type="password"
												class="form-control form-control-feedback"
												placeholder="${placeholderPassword}" required="required" />
											<form:errors path="password" class="form-control-feedback" />

										</div>
									</spring:bind>
									<div class="form-group ${status.error ? 'has-danger' : ''}">


										<spring:message code="placeholder.confirmPassword"
											var="placeholderConfirmPassword" />
										<input id="confirmPassword" type="password"
											class="form-control form-control-feedback"
											placeholder="${placeholderConfirmPassword}"
											required="required" />

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

		</div>


	</div>
	<jsp:include page="../fragments/footer.jsp" />
	<jsp:include page="../fragments/js/jquery.jsp" />
	<jsp:include page="../fragments/js/bootstrap.jsp" />
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