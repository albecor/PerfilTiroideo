<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.navbar-inverse .navbar-nav .nav-link {
	color: rgb(255, 255, 255);
}
.color-button{
color: rgb(255, 255, 255)!important;}
</style>
<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
				
		</form>
<nav class="navbar navbar-toggleable navbar-inverse bg-primary">
	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand" href="#"><i class="fa fa-stethoscope" aria-hidden="true"></i> </a>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav ">
			<li class="nav-item active"><spring:url value="/" var="indexUrl" /> <a class="nav-link" href="${indexUrl}"> <spring:message code="navbar.index-url" /> <span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"> <c:if test="${pageContext.response.locale.language == 'es'}">
			         ES
			      </c:if> <c:if test="${pageContext.response.locale.language == 'en'}">
			         EN
			      </c:if> <i class="fa fa-language" aria-hidden="true"></i></a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<spring:url value="/?lang=es" />"> <img src="<c:url value="/resources/images/spanish-icon.png"/>" class="img-fluid" alt="Responsive image" width="30" height="30"> <spring:message code="spanish" /></a> <a class="dropdown-item" href="<spring:url value="/?lang=en" />"><img src="<c:url value="/resources/images/english-icon.png"/>" class="img-fluid" alt="Responsive image" width="30" height="30"> <spring:message code="english" /></a>
				</div></li>
			
		</ul>
		<ul class="navbar-nav">
			
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"> 
 <i class="fa fa-file-text " aria-hidden="true"></i>   </a>
				<div class="dropdown-menu">
					<a class="dropdown-item"  target="_blank" href="https://drive.google.com/file/d/0By1VjmcVY702Q0JvQmV4WGxEM28/view?usp=sharing"> <i class="fa fa-cloud-download fw" aria-hidden="true"></i> <spring:message code="user-manual" /> </a> 
				</div></li>
			
		</ul>
		<ul class="navbar-nav mr-auto">
		</ul>
		<sec:authorize access="hasRole('ROLE_TEMPORAL2')">
			<a onclick="document.getElementById('logoutForm').submit()" class="btn btn-primary color-button"> <spring:message code="navbar.logout" />			
			</a>
		</sec:authorize>
		<sec:authorize access="NOT hasRole('ROLE_TEMPORAL')">
			<ul class="navbar-nav">
				<li class="nav-item "></li>
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"> <i class="fa fa-user-circle-o"></i> <spring:message code="greeting-hello" /> ${loggedinuser.given}
				</a>
					<div class="dropdown-menu dropdown-menu-right">
						<spring:url value="/updateYourself" var="editLoggedInUserUrl" />
						<a class="dropdown-item" href="${editLoggedInUserUrl}"> <span class="text-primary"> <i class="fa fa-edit"></i> <spring:message code="navbar.loggedin-user.edit" />
						</span></a>
						<div class="dropdown-divider"></div>
							
			<a onclick="document.getElementById('logoutForm').submit()" class="dropdown-item"> <i class="fa fa-sign-out"></i> <spring:message code="navbar.logout" />
					
						</a>
					</div></li>
			</ul>
		</sec:authorize>

		
	</div>
</nav>
