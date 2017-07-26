<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>

<head>

	<jsp:include page="fragments/title.jsp" />
	<jsp:include page="fragments/css/bootstrap.jsp" />	
	<jsp:include page="fragments/css/footer.jsp" />
	<jsp:include page="fragments/css/fontAwesome.jsp" />

</head>

<body>
	<jsp:include page="navbar.jsp" />

	<br />	
	<div class="container">	
		<div class="row justify-content-md-center">
		  <div class="col-12 col-lg-auto">
		    <div class="card">
		      <div class="card-block">
		        <h3 class="card-title"><spring:message code="consult.consult-user" /></h3>
		        <p class="card-text"><spring:message code="consult.consult-message" /></p>
		        
		        <spring:url value="/consult-user" var="postActionUrl" />
				<form:form class="form-horizontal" method="post"
					modelAttribute="user" action="${postActionUrl}">
					
					<div class="form-group row ${error ? 'has-danger' : ''}">
						<label class="col-sm-5 col-md-4 col-lg-3 col-form-label"><spring:message code="form.user.label.ndivalue" /></label>
						<div class="col-sm-7 col-md-8 col-lg-9">						
							<spring:message code="form.user.placeholder.ndivalue" var="placeholderNdivalue" />
							<form:input path="ndivalue" type="number"
								class="form-control form-control-feedback" placeholder="${placeholderNdivalue}"
								required="required" />
								<c:if test="${error}">
								<spring:message code="form.user.error.ndivalue.not-exist" />
								</c:if>
							
						</div>
					</div>
					
					
					<button type="submit" class="btn btn-primary "><spring:message code="consult" /></button>
				</form:form>
			</div>
		    </div>
		  </div>
  		</div>
	</div>	<!-- /container -->

	<jsp:include page="fragments/footer.jsp" />
	<jsp:include page="fragments/js/jquery.jsp" />
	<jsp:include page="fragments/js/bootstrap.jsp" />
</body>
</html>