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
	<jsp:include page="fragments/fontAwesome.jsp" />

</head>

<body>


<c:if test="${roleType == 'ROLE_ADMIN'}">
    <jsp:include page="admins/navbar.jsp" />
	<spring:url value="/admins/consulta/${action}" var="postActionUrl" />
</c:if>
<c:if test="${roleType == 'ROLE_PERSONAL'}">
   <jsp:include page="personal/navbar.jsp" />
	<spring:url value="/personal/consulta/${action}" var="postActionUrl" />
</c:if>


	<br />
	<jsp:include page="fragments/alert.jsp" />
	<br />
	
	<br />	
	<div class="container">	
		<div class="row justify-content-md-center">
		  <div class="col-12 col-md-auto">
		    <div class="card">
		      <div class="card-block">
		        <h3 class="card-title">${msgTitle}</h3>
		        <p class="card-text">${msgContent}</p>
						<form:form class="form-horizontal" method="post"
							modelAttribute="userform" action="${postActionUrl}">
							<spring:bind path="username">
								<div class="form-group row ${status.error ? 'has-danger' : ''}">
									<label class="col-sm-5 col-md-4 col-lg-2 col-form-label">Número</label>
									<div class="col-sm-7 col-md-8 col-lg-10">
										<form:input path="username" type="text"
											class="form-control form-control-feedback" id="username"
											placeholder="Numero de documento" required="required" />
										<form:errors path="username" class="form-control-feedback" />
									</div>
								</div>
							</spring:bind>
							<button type="submit" class="btn btn-primary ">Consultar</button>
						</form:form>
					</div>
		    </div>
		  </div>
  		</div>
	</div>	<!-- /container -->

	<jsp:include page="fragments/footer.jsp" />
</body>
</html>