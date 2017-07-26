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
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<div class="container-fluid">
		<br />
		<div class="row">
			<div class="col">
				<c:choose>
					<c:when test="${not empty exams}">
						<div class="card">
							<div class="card-header text-center">
								<spring:message code="exams" />
							</div>
							<div class="card-block">
								<table class="table table-sm table-hover table-bordered" id="datatable" data-order='[[0 , "asc" ]]' data-page-length='10'>
									<thead>
										<tr>
											<th>
												<div class="hidden-sm-down">
													<spring:message code="order" />
												</div>
												<div class="hidden-md-up">#</div>
											</th>
											<th><spring:message code="laboratory" /></th>
											
											<th class="text-center">
												<div class="hidden-xs-down">
													<spring:message code="patient" />
												</div>
												<div class="hidden-sm-up">
													<i class="fa fa-user" aria-hidden="true"></i>
												</div>
											</th>
											<th class="text-center">
												<div class="hidden-xs-down">
													<spring:message code="action" />
												</div>
												<div class="hidden-sm-up">
													<i class="fa fa-eye" aria-hidden="true"></i>
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
												<td>
													<div class="hidden-sm-down">
														${exam.referenceSubject}
													</div>
													<div class="hidden-md-up text-center">
														<a tabindex="0" class="btn btn-outline-primary" role="button" data-toggle="popover" data-trigger="focus" data-placement="top" data-content="${exam.referenceSubject}"> <i class="fa fa-id-card-o fa-fw" aria-hidden="true"> </i>
														</a>
													</div>
												</td>												
												<td class="text-center"><spring:url value="/la/perform-exam-${exam.order}" var="performExamUrl" /> <a href="${performExamUrl}" class="btn btn-primary"><spring:message code="see" /></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-warning" role="alert">
							<spring:message code="no-exams" />
						</div>
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
	</script>
	<jsp:include page="../fragments/js/datatable.jsp" />
	<c:if test="${not empty notifyMesage}">
		<jsp:include page="../fragments/js/notify.jsp">
			<jsp:param name="notifyMesage" value="${notifyMesage}" />
			<jsp:param name="notifyType" value="${notifyType}" />
		</jsp:include>
	</c:if>
</body>
</html>