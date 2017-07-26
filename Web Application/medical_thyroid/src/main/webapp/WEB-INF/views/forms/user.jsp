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
<body>
	<sec:authorize access="hasRole('ROLE_TEMPORAL')">
		<spring:url value="/te/new-admin" var="userActionUrl" />
	</sec:authorize>
	<sec:authorize access="NOT hasRole('ROLE_TEMPORAL')">
		<c:choose>
			<c:when test="${empty user.id}">
				<spring:url value="/ad/new-user" var="userActionUrl" />
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${loggedinuser.ndivalue != user.ndivalue}">
						<spring:url value="/ad/update-user" var="userActionUrl" />
					</c:when>
					<c:otherwise>
						<spring:url value="/updateYourself" var="userActionUrl" />
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</sec:authorize>
	<jsp:include page="../navbar.jsp" />
	<br />
	<div class="container">
		<div class="card">
			<h5 class="card-header text-center">
				<sec:authorize access="hasRole('ROLE_TEMPORAL')">
					<spring:message code="form.user.card-header.temporal" />
				</sec:authorize>
				<sec:authorize access="NOT hasRole('ROLE_TEMPORAL')">
					<c:choose>
						<c:when test="${empty user.id}">
							<spring:message code="form.user.card-header.add-user" />
						</c:when>
						<c:otherwise>
							<spring:message code="form.user.card-header.edit-user" />
						</c:otherwise>
					</c:choose>
				</sec:authorize>
			</h5>
			<div class="card-block">
				<div class="alert alert-warning" role="alert">
					<sec:authorize access="hasRole('ROLE_TEMPORAL')">
						<spring:message code="form.user.alert-warning.temporal" />
					</sec:authorize>
					<sec:authorize access="NOT hasRole('ROLE_TEMPORAL')">
						<spring:message code="form.user.alert-warning.admin" />
					</sec:authorize>
				</div>
				<hr />
				<form:form method="POST" modelAttribute="user" class="form-horizontal" action="${userActionUrl}">
					<form:hidden path="id" />
					<h5>
						<spring:message code="form.personal-information" />
					</h5>
					<hr />
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.ndi" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.lastName" var="placeholderLastName" />
							<select id="ndi" name="ndi" class="form-control">
								<option value="C.C"><spring:message code="ndi.CC" /></option>
								<option value="T.I"><spring:message code="ndi.TI" /></option>
								<option value="T.E"><spring:message code="ndi.TE" /></option>
							</select>
							<form:errors path="ndi" class="form-control-feedback" />
						</div>
					</div>
					<sec:authorize access="NOT hasRole('ROLE_TEMPORAL') AND NOT hasRole('ROLE_ADMIN') ">
						<c:if test="${not empty user.id}">
							<c:set var="disabledNdivalue" value="true" />
							<form:hidden path="ndivalue" />
						</c:if>
					</sec:authorize>
					<spring:bind path="ndivalue">
						<div class="form-group row ${status.error || disabledNdivalue ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.ndivalue" /></label>
							<div class="col-sm-7 col-md-8 col-lg-9">
								<spring:message code="placeholder.ndivalue" var="placeholderNdivalue" />
								<form:input path="ndivalue" value="${user.ndivalue}" type="number" class="form-control form-control-feedback" placeholder="${placeholderNdivalue}" required="required" autofocus="${status.error ? 'autofocus' : ''}" disabled="${disabledNdivalue ? 'true' : ''}" />
								<form:errors path="ndivalue" class="form-control-feedback" />
								<div class="form-control-feedback" ${disabledNdivalue ? '' : 'style="display: none;"'}><spring:message code="notify.id.not-change" /></div>
							</div>
						</div>
					</spring:bind>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.firstName" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.firstName" var="placeholderfirstName" />
							<form:input path="given" type="text" class="form-control form-control-feedback" placeholder="${placeholderfirstName}" required="required" />
							<form:errors path="given" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.lastName" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.lastName" var="placeholderLastName" />
							<form:input path="family" type="text" class="form-control form-control-feedback" placeholder="${placeholderLastName}" required="required" />
							<form:errors path="family" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.email" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.email" var="placeholderEmail" />
							<form:input path="email" type="email" class="form-control form-control-feedback" placeholder="${placeholderEmail}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="email" class="form-control-feedback" />
						</div>
					</div>
					<h5>
						<spring:message code="form.general-information" />
					</h5>
					<hr />
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.birthdate" /></label>
						<spring:message code="placeholder.birthdate" var="placeholderBirthdate" />
						<spring:message code="title.birthdate" var="titleBirthdate" />
						<div class="col-sm-7 col-md-8 col-lg-9">
							<form:input path="birthDate" type="date" class="form-control form-control-feedback" placeholder="${placeholderBirthdate}" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])-(0[1-9]|1[012])-[0-9]{4}" title="${titleBirthdate}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="birthDate" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.gender" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<select id="gender" name="gender" class="form-control">
								<option value="male"><spring:message code="gender.male" /></option>
								<option value="female"><spring:message code="gender.female" /></option>
								<option value="other"><spring:message code="gender.other" /></option>
								<option value="unknown"><spring:message code="gender.unknown" /></option>
							</select>
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.blood" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<select id="bloodtype" name="bloodtype" class="form-control">
								<option value="A+">A+</option>
								<option value="A-">A-</option>
								<option value="B+">B+</option>
								<option value="B-">B-</option>
								<option value="AB+">AB+</option>
								<option value="AB-">AB-</option>
								<option value="O+">O+</option>
								<option value="O-">O-</option>
							</select>
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.maritalStatus" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<select id="maritalStatus" name="maritalStatus" class="form-control">
								<option value="U"><spring:message code="marital-status.U" /></option>
								<option value="A"><spring:message code="marital-status.A" /></option>
								<option value="D"><spring:message code="marital-status.D" /></option>
								<option value="I"><spring:message code="marital-status.I" /></option>
								<option value="L"><spring:message code="marital-status.L" /></option>
								<option value="M"><spring:message code="marital-status.M" /></option>
								<option value="P"><spring:message code="marital-status.P" /></option>
								<option value="S"><spring:message code="marital-status.S" /></option>
								<option value="T"><spring:message code="marital-status.T" /></option>
								<option value="W"><spring:message code="marital-status.W" /></option>
								<option value="UNK"><spring:message code="marital-status.UNK" /></option>
							</select>
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.managingOrganization" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.managingOrganization" var="placeholderManagingOrganization" />
							<form:input path="managingOrganization" type="text" class="form-control form-control-feedback" id="telc" placeholder="${placeholderManagingOrganization}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="managingOrganization" class="form-control-feedback" />
						</div>
					</div>
					<h5>
						<spring:message code="form.contact-information" />
					</h5>
					<hr />
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.relationship" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<select id="relationship" name="relationship" class="form-control">
								<option value="N"><spring:message code="relationship.N" /></option>
								<option value="BP"><spring:message code="relationship.BP" /></option>
								<option value="C"><spring:message code="relationship.C" /></option>
								<option value="CP"><spring:message code="relationship.CP" /></option>
								<option value="E"><spring:message code="relationship.E" /></option>
								<option value="EP"><spring:message code="relationship.EP" /></option>
								<option value="F"><spring:message code="relationship.F" /></option>
								<option value="I"><spring:message code="relationship.I" /></option>
								<option value="O"><spring:message code="relationship.O" /></option>
								<option value="PR"><spring:message code="relationship.PR" /></option>
								<option value="S"><spring:message code="relationship.S" /></option>
								<option value="U"><spring:message code="relationship.U" /></option>
							</select>
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.firstName" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.firstName" var="placeholderGivenc" />
							<form:input path="givenc" type="text" class="form-control form-control-feedback" placeholder="${placeholderGivenc}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="givenc" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.lastName" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.lastName" var="placeholderFamilyc" />
							<form:input path="familyc" type="text" class="form-control form-control-feedback" placeholder="${placeholderFamilyc}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="familyc" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.telc" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.telc" var="placeholderTelc" />
							<form:input path="telc" type="number" class="form-control form-control-feedback" placeholder="${placeholderTelc}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="telc" class="form-control-feedback" />
						</div>
					</div>
					<h5>
						<spring:message code="form.location-data" />
					</h5>
					<hr />
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.telhome" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.telhome" var="placeholderTelhome" />
							<form:input path="telhome" type="number" class="form-control form-control-feedback" placeholder="${placeholderTelhome}"  autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="telhome" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.telmobile" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.telmobile" var="placeholderTelmobile" />
							<form:input path="telmobile" type="number" class="form-control form-control-feedback" placeholder="${placeholderTelmobile}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="telmobile" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.telwork" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.telwork" var="placeholderTelwork" />
							<form:input path="telwork" type="number" class="form-control form-control-feedback" placeholder="${placeholderTelwork}"  autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="telwork" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.line" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.line" var="placeholderLine" />
							<form:input path="line" type="text" class="form-control form-control-feedback" placeholder="${placeholderLine}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="line" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.city" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.city" var="placeholderCity" />
							<form:input path="city" type="text" class="form-control form-control-feedback" placeholder="${placeholderCity}" required="required" autofocus="${status.error ? 'autofocus' : ''}" />
							<form:errors path="city" class="form-control-feedback" />
						</div>
					</div>
					<h5>
						<spring:message code="form.session-information" />
					</h5>
					<hr />
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.password" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.password" var="placeholderPassword" />
							<form:input path="password" type="password" class="form-control form-control-feedback" placeholder="${placeholderPassword}" required="required" />
							<form:errors path="password" class="form-control-feedback" />
						</div>
					</div>
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.confirmPassword" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<spring:message code="placeholder.confirmPassword" var="placeholderConfirmPassword" />
							<input id="confirmPassword" type="password" class="form-control form-control-feedback" placeholder="${placeholderConfirmPassword}" required="required" />
						</div>
					</div>
					<sec:authorize access="NOT hasRole('ROLE_ADMIN') AND NOT hasRole('ROLE_TEMPORAL')">
						<c:forEach items="${user.roles}" var="current" varStatus="status">
							<c:set var="rolesId" value="${status.first ? '' : rolesId}${current.id}${status.last ? '' : ','}" />
						</c:forEach>
						<form:hidden path="roles" value="${rolesId}" />
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_TEMPORAL')">
						<form:hidden path="roles" value="1" />
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<spring:bind path="roles">
						<div class="form-group row ${status.error ? 'has-danger' : ''}">
							<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="role_user" /></label>
							<div class="col-sm-7 col-md-8 col-lg-9">
								<select multiple class="form-control" id="roles" name="roles" onchange="warningaa(this);" required size="4" autofocus="${status.error ? 'autofocus' : ''}">
									<c:forEach items="${roleList}" var="role">
										<c:forEach items="${user.roles}" var="roleSelected">
											<c:if test="${role.id eq roleSelected.id}">
												<c:set var="roleSelect" value="true" />
											</c:if>
										</c:forEach>
										<option value="${role.id}" ${roleSelect eq true ? 'selected' : ''}>
											<spring:message code="${fn:toLowerCase(role.type)}" /></option>
										<c:set var="roleSelect" value="false" />
									</c:forEach>
								</select>
								<form:errors path="roles" class="form-control-feedback" />
							</div>
							
						</div>
						</spring:bind>
					</sec:authorize>
					<h5>
						<spring:message code="form.profession" />
					</h5>
					<hr />
					<div class="form-group row ${status.error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.practitionerRole" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">
							<select id="practitionerRole" name="practitionerRole" class="form-control">
								<option value="other"><spring:message code="practitionerRole.other" /></option>
								<option value="doctor"><spring:message code="practitionerRole.doctor" /></option>
								<option value="nurse"><spring:message code="practitionerRole.nurse" /></option>
								<option value="pharmacist"><spring:message code="practitionerRole.pharmacist" /></option>
								<option value="researcher"><spring:message code="practitionerRole.researcher" /></option>
								<option value="teacher"><spring:message code="practitionerRole.teacher" /></option>
								<option value="ict"><spring:message code="practitionerRole.ict" /></option>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<div class="col">
							<button type="submit" class="btn-lg btn-primary pull-right">
								<c:choose>
									<c:when test="${empty user.id}">
										<spring:message code="form.user.button.value.add" />
									</c:when>
									<c:otherwise>
										<spring:message code="form.user.button.value.edit" />
									</c:otherwise>
								</c:choose>
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