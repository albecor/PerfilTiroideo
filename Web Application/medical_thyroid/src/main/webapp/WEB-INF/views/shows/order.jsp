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
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<div class="container">
		<br />
		<div class="card">
			<div class="card-header">
				<spring:message code="exam-information" />
			</div>
			<div class="card-block">
				<div class="row d-flex align-items-start flex-wrap">
					<div class="pl-3">
						<strong> <spring:message code="exam" />:
						</strong>
					</div>
					<div class="pl-3">
						(${exam.code}) <spring:message code="${exam.code}" /> <a href="${exam.systemCode}" target="_blank"  data-toggle="tooltip" data-placement="top" title="<spring:message code="exam-information" />" class="btn btn-info btn-circle "><i class="fa fa-info fa-fw"></i></a>
					</div>
				</div>
				<div class="row d-flex align-items-start flex-wrap">
					<div class="pl-3">
						<strong> <spring:message code="order" />:
						</strong>
					</div>
					<div class="pl-3">${exam.order}</div>
				</div>
				<div class="row d-flex align-items-start flex-wrap">
					<div class="pl-3">
						<strong> <spring:message code="patient" />:
						</strong>
					</div>
					<div class="pl-3">${exam.referenceSubject} - ${exam.displaySubject}</div>
				</div>
				<div class="row d-flex align-items-start flex-wrap">
					<div class="pl-3">
						<strong> <spring:message code="specialist" />:
						</strong>
					</div>
					<div class="pl-3">${exam.displayPerformer}</div>
				</div>
				<div class="row d-flex align-items-start flex-wrap">
					<div class="pl-3">
						<strong> <spring:message code="authorized" />:
						</strong>
					</div>
					<div class="pl-3">
						<c:if test="${not empty exam.issued}">
							<c:set var="issued" value="${exam.issued}" />
							<%
								String dateIssued = LocalDateTime.parse((String) pageContext.getAttribute("issued")).toLocalDate()
											.toString();
							%>
							<%=dateIssued%>
						</c:if>
					</div>
				</div>
			</div>
			
			<div class="card-block">
			<h5 class="pl-3"> <spring:message code="results" /></h5>
			<hr />
			</div>
			<c:if test="${not empty exam.value}">
			<div class="card-block">
				<div class="hidden-xs-down">
				<table class="table table-bordered table-sm">
					<thead>
						<tr>
							<th><spring:message code="unit" /></th>
							<th><spring:message code="value" /></th>
							<th><spring:message code="range" /></th>					
							<th><spring:message code="interpretation" /></th>							
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${exam.unit}</td>
							<td>${exam.value}</td>
							<td>${exam.low} - ${exam.high}</td>							
							<td><spring:message code="interpretation.${exam.interpretation}" /></td>						
						</tr>
					</tbody>
				</table>
				</div>
				<div class="hidden-sm-up">
				<div class="row d-flex align-items-start flex-wrap">
					<div class="pl-3">
						<strong><spring:message code="unit" />:</strong>
					</div>
					<div class="pl-3">${exam.unit}</div>
				</div>
				<div class="row d-flex align-items-start flex-wrap">
					<div class="pl-3">
						<strong><spring:message code="value" />:</strong>
					</div>
					<div class="pl-3">${exam.value}</div>
				</div>
				<div class="row d-flex align-items-start flex-wrap">
					<div class="pl-3">
						<strong><spring:message code="range" />:</strong>
					</div>
					<div class="pl-3">${exam.low} - ${exam.high}</div>
				</div>
				
				<div class="row d-flex align-items-start flex-wrap">
					<div class="pl-3">
						<strong><spring:message code="interpretation" />:</strong>
					</div>
					<div class="pl-3"><spring:message code="interpretation.${exam.interpretation}" /></div>
				</div>
				
				</div>
			</div>
			</c:if>
		
			<div class="card-block">
				<c:if test="${not empty exam.labComments}">
					<div class="row d-flex align-items-start flex-wrap">
						<div class="pl-3">
							<strong> <spring:message code="labComments" />:
							</strong>
						</div>
						<div class="pl-3">${exam.labComments}</div>
					</div>
				</c:if>
				<c:choose>
					<c:when test="${not empty exam.performerComments}">
						<div class="row d-flex align-items-start flex-wrap">
							<div class="pl-3">
								<strong> <spring:message code="performerComments" />:
								</strong>
							</div>
							<div class="pl-3">${exam.performerComments}</div>
						</div>
					</c:when>
					<c:otherwise>
						<sec:authorize access="hasRole('ROLE_PERSONAL')">
							<spring:url value="/pe/perform-exam" var="postActionUrl" />
							<form:form method="POST" modelAttribute="exam" class="form-horizontal" action="${postActionUrl}">
								<form:hidden path="order" />
								<spring:bind path="performerComments">
									<div class="form-group row ${status.error ? 'has-danger' : ''}">
										<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><strong><spring:message code="performerComments" />:</strong></label>
										<spring:message code="placeholder.performerComments" var="placeholderPerformerComments" />
										<div class="col-sm-7 col-md-8 col-lg-9">
											<form:textarea path="performerComments" rows="5" required="required" class="form-control form-control-feedback" placeholder="${placeholderPerformerComments}" autofocus="${status.error ? 'autofocus' : ''}" />
											<form:errors path="performerComments" class="form-control-feedback" />
										</div>
									</div>
								</spring:bind>
								<div class="row">
									<div class="col">
										<button type="submit" class="btn-lg btn-primary pull-right">
											<spring:message code="form.user.button.value.add" />
										</button>
									</div>
								</div>
							</form:form>
						</sec:authorize>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
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
			trigger : 'focus'
		})
		

  $('[data-toggle="tooltip"]').tooltip();

	</script>
	<c:if test="${not empty notifyMesage}">
		<jsp:include page="../fragments/js/notify.jsp">
			<jsp:param name="notifyMesage" value="${notifyMesage}" />
			<jsp:param name="notifyType" value="${notifyType}" />
		</jsp:include>
	</c:if>
</body>
</html>